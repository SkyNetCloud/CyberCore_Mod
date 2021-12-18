package ca.skynetcloud.cybercore.common.blocks.techentity;

import ca.skynetcloud.cybercore.client.init.BlockEntityInit;
import ca.skynetcloud.cybercore.client.init.BlockInit;
import ca.skynetcloud.cybercore.client.utilities.blocks.itemcables.handler.ItemCableInvtoryHandler;
import ca.skynetcloud.cybercore.client.utilities.blocks.itemcables.routing.Route;
import ca.skynetcloud.cybercore.client.utilities.blocks.itemcables.routing.RoutingNetwork;
import ca.skynetcloud.cybercore.client.utilities.blocks.itemcables.wrapper.ItemInSystemWrapper;
import ca.skynetcloud.cybercore.client.utilities.blocks.itemcables.wrapper.WorldHelperWrapper;
import ca.skynetcloud.cybercore.common.blocks.tech.cable.ItemCable;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.ListTag;
import net.minecraft.network.Connection;
import net.minecraft.network.protocol.game.ClientboundBlockEntityDataPacket;
import net.minecraft.world.Containers;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.items.CapabilityItemHandler;
import net.minecraftforge.items.IItemHandler;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.*;

public class ItemCableBlockEntity extends BlockEntity {


    // public static final String DIST_NBT_KEY = "distance";
    public static final String INV_NBT_KEY_ADD = "inventory_new_items";
    public static final String INV_NBT_KEY_RESET = "inventory_reset";

    @Nonnull
    public Queue<ItemInSystemWrapper> inventory = new LinkedList<ItemInSystemWrapper>();

    protected final ItemCableInvtoryHandler[] inventoryHandlers = Arrays.stream(Direction.values())
            .map(dir -> new ItemCableInvtoryHandler(this, dir)).toArray(ItemCableInvtoryHandler[]::new); // one
    // handler
    // for
    // each direction

    @SuppressWarnings("unchecked")
    protected final LazyOptional<IItemHandler>[] handlerOptionals = Arrays.stream(this.inventoryHandlers)
            .map(handler -> LazyOptional.of(() -> handler))
            .toArray(size -> (LazyOptional<IItemHandler>[]) new LazyOptional[size]);

    private Queue<ItemInSystemWrapper> wrappers_to_send_to_client = new LinkedList<ItemInSystemWrapper>();
    public Queue<ItemInSystemWrapper> incoming_wrapper_buffer = new LinkedList<ItemInSystemWrapper>();

    @Nonnull // use getNetwork()
    private RoutingNetwork network = RoutingNetwork.INVALID_NETWORK;

    public ItemCableBlockEntity(){
        this(BlockPos.ZERO, BlockInit.ITEM_CABLE.get().defaultBlockState());
    }

    public ItemCableBlockEntity(BlockPos worldPosition, BlockState blockState) {
        super(BlockEntityInit.ITEM_CABLE_BE.get(), worldPosition, blockState);
    }


    /**** Getters and Setters ****/
    public RoutingNetwork getNetwork() {
        if (this.network.invalid) {
            this.network = RoutingNetwork.buildNetworkFrom(this.worldPosition, this.level);
        }
        return this.network;
    }

    public void setNetwork(RoutingNetwork network) {
        this.network = network;
    }

    public boolean isItemPipeCompatible(ItemCableBlockEntity tube) {
        Block thisBlock = this.getBlockState().getBlock();
        Block otherBlock = tube.getBlockState().getBlock();
        if (thisBlock instanceof ItemCable && otherBlock instanceof ItemCable) {
            return true;
        }
        return false;
    }

    public List<Direction> getConnectedDirections() {
        BlockState state = this.getBlockState();
        return ItemCable.getConnectedDirections(state);
    }

    public static Optional<ItemCableBlockEntity> getTubeTEAt(Level world, BlockPos pos) {
        BlockEntity te = world.getBlockEntity(pos);
        return Optional.ofNullable(te instanceof ItemCableBlockEntity ? (ItemCableBlockEntity) te : null);
    }

    // insertionSide is the side of this block the item was inserted from
    public Route getBestRoute(Direction insertionSide, ItemStack stack) {
        return this.getNetwork().getBestRoute(this.level, this.worldPosition, insertionSide, stack);
    }

    /**** Event Handling ****/

    @Override
    public void setRemoved() {
        this.dropItems();
        this.network.invalid = true;
        Arrays.stream(this.handlerOptionals).forEach(optional -> optional.invalidate());
        super.setRemoved();
    }

    public void onPossibleNetworkUpdateRequired() {
        if (!this.network.invalid && this.didNetworkChange()) {
            this.network.invalid = true;
        }
    }

    private boolean didNetworkChange() {
        for (Direction face : Direction.values()) {
            BlockPos checkPos = this.worldPosition.relative(face);
            // if the adjacent block is a tube or endpoint but isn't in the network
            // OR if the adjacent block is in the network but isn't a tube or endpoint
            // then the network changed
            if (this.getNetwork().contains(this.worldPosition, face.getOpposite()) != this.getNetwork()
                    .isValidToBeInNetwork(checkPos, this.level, face.getOpposite()))
                return true;
        }
        return false;
    }

    public void tick() {
        this.merge_buffer();
        if (!this.inventory.isEmpty()) // if inventory is empty, skip the tick
        {
            if (!this.level.isClientSide) // block has changes that need to be saved (serverside)
            {
                this.setChanged();
            }
            Queue<ItemInSystemWrapper> remainingWrappers = new LinkedList<ItemInSystemWrapper>();
            for (ItemInSystemWrapper wrapper : this.inventory) {
                wrapper.ticksElapsed++;
                if (wrapper.ticksElapsed >= wrapper.maximumDurationInTube) {
                    if (wrapper.freshlyInserted) {
                        wrapper.freshlyInserted = false;
                        wrapper.remainingMoves.removeFirst();
                        wrapper.ticksElapsed = 0;
                        remainingWrappers.add(wrapper);
                    } else {
                        this.sendWrapperOnward(wrapper);
                    }
                } else {
                    remainingWrappers.add(wrapper);
                }
            }
            this.inventory = remainingWrappers;
        }
        if (!this.level.isClientSide && this.inventory.size() > 64) {
            this.level.removeBlock(this.worldPosition, false);
        }
    }

    public void sendWrapperOnward(ItemInSystemWrapper wrapper) {
        if (!wrapper.remainingMoves.isEmpty()) // wrapper has remaining moves
        {
            Direction dir = wrapper.remainingMoves.poll();
            BlockEntity te = this.level.getBlockEntity(this.worldPosition.relative(dir));
            if (te instanceof ItemCableBlockEntity && this.isItemPipeCompatible((ItemCableBlockEntity) te)) {
                ((ItemCableBlockEntity) te).enqueueItemStack(wrapper.stack, wrapper.remainingMoves,
                        wrapper.maximumDurationInTube);
            } else if (!this.level.isClientSide) {
                if (te != null) // te exists but is not a tube
                {
                    ItemStack remaining = te
                            .getCapability(CapabilityItemHandler.ITEM_HANDLER_CAPABILITY, dir.getOpposite())
                            .map(handler -> WorldHelperWrapper.disperseItemToHandler(wrapper.stack, handler))
                            .orElse(wrapper.stack.copy());

                    if (!remaining.isEmpty()) // target inventory filled up unexpectedly
                    {
                        this.enqueueItemStack(remaining, dir, false); // re-enqueue the item on that side
                    }
                } else // no TE -- eject stack
                {
                    WorldHelperWrapper.ejectItemstack(this.level, this.worldPosition, dir, wrapper.stack);
                }
            }
        } else if (!this.level.isClientSide) // wrapper has no remaining moves -- this isn't expected, eject the item
        {
            WorldHelperWrapper.ejectItemstack(this.level, this.worldPosition, null, wrapper.stack);
        }
    }

    /**** Inventory handling ****/

    @Override
    @Nonnull
    public <T> LazyOptional<T> getCapability(@Nonnull Capability<T> cap, @Nullable Direction side) {
        if (cap == CapabilityItemHandler.ITEM_HANDLER_CAPABILITY && side != null) {
            return this.handlerOptionals[side.get3DDataValue()].cast(); // T is <IItemHandler> here, which our handler
            // implements
        }
        return super.getCapability(cap, side);
    }

    // insert a new itemstack into the tube network from a direction
    // and determine a route for it
    public ItemStack enqueueItemStack(ItemStack stack, Direction face, boolean simulate) {
        Route route = this.getNetwork().getBestRoute(this.level, this.worldPosition, face, stack);
        if (route == null || route.sequenceOfMoves.isEmpty())
            return stack.copy();

        if (simulate)
            return ItemStack.EMPTY;

        int ticks_per_tube = this.getNetwork().getTicksPerTube();
        this.wrappers_to_send_to_client
                .add(new ItemInSystemWrapper(stack, route.sequenceOfMoves, ticks_per_tube, face.getOpposite()));

        this.level.sendBlockUpdated(this.worldPosition, this.getBlockState(), this.getBlockState(), 2);

        return this.enqueueItemStack(new ItemInSystemWrapper(stack, route.sequenceOfMoves, 10, face.getOpposite()));
    }

    public ItemStack enqueueItemStack(ItemInSystemWrapper wrapper) {
        this.incoming_wrapper_buffer.add(wrapper);
        return ItemStack.EMPTY;
    }

    public ItemStack enqueueItemStack(ItemStack stack, Queue<Direction> remainingMoves, int ticksPerTube) {
        return this.enqueueItemStack(new ItemInSystemWrapper(stack, remainingMoves, ticksPerTube));
    }

    public void merge_buffer() {
        if (!this.incoming_wrapper_buffer.isEmpty()) {
            for (ItemInSystemWrapper wrapper : this.incoming_wrapper_buffer) {
                this.inventory.add(wrapper);
            }
            this.incoming_wrapper_buffer = new LinkedList<ItemInSystemWrapper>();
        }
    }

    public void dropItems() {
        this.merge_buffer();
        for (ItemInSystemWrapper wrapper : this.inventory) {
            Containers.dropItemStack(this.level, this.worldPosition.getX(), this.worldPosition.getY(),
                    this.worldPosition.getZ(), wrapper.stack);
        }

        this.inventory = new LinkedList<ItemInSystemWrapper>(); // clear it in case this is being called without
        // destroying the TE
    }

    public static boolean isSpaceForAnythingInItemHandler(IItemHandler handler) {
        return true;
    }

    public boolean isAnyInventoryAdjacent() {
        for (Direction face : Direction.values()) {
            BlockEntity te = this.level.getBlockEntity(this.worldPosition.relative(face));
            if (te != null && !(te instanceof ItemCableBlockEntity)) {
                // if a nearby inventory that is not a tube exists
                LazyOptional<IItemHandler> cap = te.getCapability(CapabilityItemHandler.ITEM_HANDLER_CAPABILITY,
                        face.getOpposite());
                IItemHandler handler = cap.orElse(null);
                if (handler != null) {
                    if (ItemCableBlockEntity.isSpaceForAnythingInItemHandler(handler)) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    @Override
    public void load(CompoundTag compound) {
        super.load(compound);

        if (compound.contains(INV_NBT_KEY_RESET)) // only update inventory if the compound has an inv. key
        { // this lets the client receive packets without the inventory being cleared
            ListTag invList = compound.getList(INV_NBT_KEY_RESET, 10);
            Queue<ItemInSystemWrapper> inventory = new LinkedList<ItemInSystemWrapper>();
            for (int i = 0; i < invList.size(); i++) {
                CompoundTag itemTag = invList.getCompound(i);
                inventory.add(ItemInSystemWrapper.readFromNBT(itemTag));
            }
            this.inventory = inventory;
        } else if (compound.contains(INV_NBT_KEY_ADD)) // add newly inserted items to this tube
        {
            ListTag invList = compound.getList(INV_NBT_KEY_ADD, 10);
            for (int i = 0; i < invList.size(); i++) {
                CompoundTag itemTag = invList.getCompound(i);
                this.inventory.add(ItemInSystemWrapper.readFromNBT(itemTag));
            }
        }
    }

    @Override // write entire inventory by default (for server -> hard disk purposes this is
    // what is called)
    public CompoundTag save(CompoundTag compound) {

        ListTag invList = new ListTag();
        this.merge_buffer();

        for (ItemInSystemWrapper wrapper : this.inventory) {
            CompoundTag invTag = new CompoundTag();
            wrapper.writeToNBT(invTag);
            invList.add(invTag);
        }
        if (!invList.isEmpty()) {
            compound.put(INV_NBT_KEY_RESET, invList);
        }

        return super.save(compound);
    }

    /**
     * Get an NBT compound to sync to the client with SPacketChunkData, used for
     * initial loading of the chunk or when many blocks change at once. This
     * compound comes back to you clientside in {@link //handleUpdateTag}
     * //handleUpdateTag just calls read by default
     */
    @Override
    public CompoundTag getUpdateTag() {
        return this.save(new CompoundTag()); // okay to send entire inventory on chunk load
    }

    /**
     * Prepare a packet to sync TE to client We don't need to send the inventory in
     * every packet but we should notify the client of new items entering the
     * network
     */



    public ClientboundBlockEntityDataPacket getUpdatePacket() {
        CompoundTag nbt = new CompoundTag();
        super.save(nbt); // write the basic TE stuff

        ListTag invList = new ListTag();

        while (!this.wrappers_to_send_to_client.isEmpty()) {
            // empty itemstacks are not added to the tube
            ItemInSystemWrapper wrapper = this.wrappers_to_send_to_client.poll();
            CompoundTag invTag = new CompoundTag();
            wrapper.writeToNBT(invTag);
            invList.add(invTag);
        }
        if (!invList.isEmpty()) {
            nbt.put(INV_NBT_KEY_ADD, invList);
        }

        return null;
    }

    /**
     * Receive packet on client and get data out of it
     */
    @Override
    public void onDataPacket(Connection net, ClientboundBlockEntityDataPacket packet) {
        this.readNBT(packet.getTag());
    }

    public void readNBT(CompoundTag compound) {

        if (compound.contains(INV_NBT_KEY_RESET)) // only update inventory if the compound has an inv. key
        { // this lets the client receive packets without the inventory being cleared
            ListTag invList = compound.getList(INV_NBT_KEY_RESET, 10);
            Queue<ItemInSystemWrapper> inventory = new LinkedList<ItemInSystemWrapper>();
            for (int i = 0; i < invList.size(); i++) {
                CompoundTag itemTag = invList.getCompound(i);
                inventory.add(ItemInSystemWrapper.readFromNBT(itemTag));
            }
            this.inventory = inventory;
        } else if (compound.contains(INV_NBT_KEY_ADD)) // add newly inserted items to this tube
        {
            ListTag invList = compound.getList(INV_NBT_KEY_ADD, 10);
            for (int i = 0; i < invList.size(); i++) {
                CompoundTag itemTag = invList.getCompound(i);
                this.inventory.add(ItemInSystemWrapper.readFromNBT(itemTag));
            }
        }
    }

}
