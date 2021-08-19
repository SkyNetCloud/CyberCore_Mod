package ca.skynetcloud.cybercore.util.TE.techblock;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.Queue;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import ca.skynetcloud.cybercore.block.blocks.cables.ItemCable;
import ca.skynetcloud.cybercore.init.CoreInit;
import ca.skynetcloud.cybercore.util.networking.config.CyberConfig.Config;
import ca.skynetcloud.cybercore.util.networking.handler.ItemPipeInventoryHandler;
import ca.skynetcloud.cybercore.util.networking.helper.WorldHelper;
import ca.skynetcloud.cybercore.util.networking.routing.Route;
import ca.skynetcloud.cybercore.util.networking.routing.RoutingNetwork;
import ca.skynetcloud.cybercore.util.networking.wrapper.ItemInTubeWrapper;
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
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.entity.TickingBlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.items.CapabilityItemHandler;
import net.minecraftforge.items.IItemHandler;

public class ItemPipeTileEntity extends BlockEntity {

	// public static final String DIST_NBT_KEY = "distance";
	public static final String INV_NBT_KEY_ADD = "inventory_new_items";
	public static final String INV_NBT_KEY_RESET = "inventory_reset";

	@Nonnull
	public Queue<ItemInTubeWrapper> inventory = new LinkedList<ItemInTubeWrapper>();

	protected final ItemPipeInventoryHandler[] inventoryHandlers = Arrays.stream(Direction.values())
			.map(dir -> new ItemPipeInventoryHandler(this, dir)).toArray(ItemPipeInventoryHandler[]::new); // one
																											// handler
																											// for
	// each direction

	@SuppressWarnings("unchecked")
	protected final LazyOptional<IItemHandler>[] handlerOptionals = Arrays.stream(this.inventoryHandlers)
			.map(handler -> LazyOptional.of(() -> handler))
			.toArray(size -> (LazyOptional<IItemHandler>[]) new LazyOptional[size]);

	private Queue<ItemInTubeWrapper> wrappers_to_send_to_client = new LinkedList<ItemInTubeWrapper>();
	public Queue<ItemInTubeWrapper> incoming_wrapper_buffer = new LinkedList<ItemInTubeWrapper>();

	@Nonnull // use getNetwork()
	private RoutingNetwork network = RoutingNetwork.INVALID_NETWORK;

	public ItemPipeTileEntity(BlockEntityType<?> tileEntityTypeIn, BlockPos worldPosition, BlockState blockState) {
		super(tileEntityTypeIn, worldPosition, blockState);
	}

	public ItemPipeTileEntity(BlockPos worldPosition, BlockState blockState) {
		this(CoreInit.BlockEntityInit.BLOCK_PIPE, worldPosition, blockState);
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

	public boolean isItemPipeCompatible(ItemPipeTileEntity tube) {
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

	public static Optional<ItemPipeTileEntity> getTubeTEAt(Level world, BlockPos pos) {
		BlockEntity te = world.getBlockEntity(pos);
		return Optional.ofNullable(te instanceof ItemPipeTileEntity ? (ItemPipeTileEntity) te : null);
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
			Queue<ItemInTubeWrapper> remainingWrappers = new LinkedList<ItemInTubeWrapper>();
			for (ItemInTubeWrapper wrapper : this.inventory) {
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
		if (!this.level.isClientSide && this.inventory.size() > Config.MAX_ITEMS_IN_PIPE.get()) {
			this.level.removeBlock(this.worldPosition, false);
		}
	}

	public void sendWrapperOnward(ItemInTubeWrapper wrapper) {
		if (!wrapper.remainingMoves.isEmpty()) // wrapper has remaining moves
		{
			Direction dir = wrapper.remainingMoves.poll();
			BlockEntity te = this.level.getBlockEntity(this.worldPosition.relative(dir));
			if (te instanceof ItemPipeTileEntity && this.isItemPipeCompatible((ItemPipeTileEntity) te)) {
				((ItemPipeTileEntity) te).enqueueItemStack(wrapper.stack, wrapper.remainingMoves,
						wrapper.maximumDurationInTube);
			} else if (!this.level.isClientSide) {
				if (te != null) // te exists but is not a tube
				{
					ItemStack remaining = te
							.getCapability(CapabilityItemHandler.ITEM_HANDLER_CAPABILITY, dir.getOpposite())
							.map(handler -> WorldHelper.disperseItemToHandler(wrapper.stack, handler))
							.orElse(wrapper.stack.copy());

					if (!remaining.isEmpty()) // target inventory filled up unexpectedly
					{
						this.enqueueItemStack(remaining, dir, false); // re-enqueue the item on that side
					}
				} else // no TE -- eject stack
				{
					WorldHelper.ejectItemstack(this.level, this.worldPosition, dir, wrapper.stack);
				}
			}
		} else if (!this.level.isClientSide) // wrapper has no remaining moves -- this isn't expected, eject the item
		{
			WorldHelper.ejectItemstack(this.level, this.worldPosition, null, wrapper.stack);
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
				.add(new ItemInTubeWrapper(stack, route.sequenceOfMoves, ticks_per_tube, face.getOpposite()));

		this.level.sendBlockUpdated(this.worldPosition, this.getBlockState(), this.getBlockState(), 2);

		return this.enqueueItemStack(new ItemInTubeWrapper(stack, route.sequenceOfMoves, 10, face.getOpposite()));
	}

	public ItemStack enqueueItemStack(ItemInTubeWrapper wrapper) {
		this.incoming_wrapper_buffer.add(wrapper);
		return ItemStack.EMPTY;
	}

	public ItemStack enqueueItemStack(ItemStack stack, Queue<Direction> remainingMoves, int ticksPerTube) {
		return this.enqueueItemStack(new ItemInTubeWrapper(stack, remainingMoves, ticksPerTube));
	}

	public void merge_buffer() {
		if (!this.incoming_wrapper_buffer.isEmpty()) {
			for (ItemInTubeWrapper wrapper : this.incoming_wrapper_buffer) {
				this.inventory.add(wrapper);
			}
			this.incoming_wrapper_buffer = new LinkedList<ItemInTubeWrapper>();
		}
	}

	public void dropItems() {
		this.merge_buffer();
		for (ItemInTubeWrapper wrapper : this.inventory) {
			Containers.dropItemStack(this.level, this.worldPosition.getX(), this.worldPosition.getY(),
					this.worldPosition.getZ(), wrapper.stack);
		}

		this.inventory = new LinkedList<ItemInTubeWrapper>(); // clear it in case this is being called without
																// destroying the TE
	}

	public static boolean isSpaceForAnythingInItemHandler(IItemHandler handler) {
		return true;
	}

	public boolean isAnyInventoryAdjacent() {
		for (Direction face : Direction.values()) {
			BlockEntity te = this.level.getBlockEntity(this.worldPosition.relative(face));
			if (te != null && !(te instanceof ItemPipeTileEntity)) {
				// if a nearby inventory that is not a tube exists
				LazyOptional<IItemHandler> cap = te.getCapability(CapabilityItemHandler.ITEM_HANDLER_CAPABILITY,
						face.getOpposite());
				IItemHandler handler = cap.orElse(null);
				if (handler != null) {
					if (ItemPipeTileEntity.isSpaceForAnythingInItemHandler(handler)) {
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
			Queue<ItemInTubeWrapper> inventory = new LinkedList<ItemInTubeWrapper>();
			for (int i = 0; i < invList.size(); i++) {
				CompoundTag itemTag = invList.getCompound(i);
				inventory.add(ItemInTubeWrapper.readFromNBT(itemTag));
			}
			this.inventory = inventory;
		} else if (compound.contains(INV_NBT_KEY_ADD)) // add newly inserted items to this tube
		{
			ListTag invList = compound.getList(INV_NBT_KEY_ADD, 10);
			for (int i = 0; i < invList.size(); i++) {
				CompoundTag itemTag = invList.getCompound(i);
				this.inventory.add(ItemInTubeWrapper.readFromNBT(itemTag));
			}
		}
	}

	@Override // write entire inventory by default (for server -> hard disk purposes this is
				// what is called)
	public CompoundTag save(CompoundTag compound) {

		ListTag invList = new ListTag();
		this.merge_buffer();

		for (ItemInTubeWrapper wrapper : this.inventory) {
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
	@Override
	public ClientboundBlockEntityDataPacket getUpdatePacket() {
		CompoundTag nbt = new CompoundTag();
		super.save(nbt); // write the basic TE stuff

		ListTag invList = new ListTag();

		while (!this.wrappers_to_send_to_client.isEmpty()) {
			// empty itemstacks are not added to the tube
			ItemInTubeWrapper wrapper = this.wrappers_to_send_to_client.poll();
			CompoundTag invTag = new CompoundTag();
			wrapper.writeToNBT(invTag);
			invList.add(invTag);
		}
		if (!invList.isEmpty()) {
			nbt.put(INV_NBT_KEY_ADD, invList);
		}

		return new ClientboundBlockEntityDataPacket(this.getBlockPos(), 1, nbt);
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
			Queue<ItemInTubeWrapper> inventory = new LinkedList<ItemInTubeWrapper>();
			for (int i = 0; i < invList.size(); i++) {
				CompoundTag itemTag = invList.getCompound(i);
				inventory.add(ItemInTubeWrapper.readFromNBT(itemTag));
			}
			this.inventory = inventory;
		} else if (compound.contains(INV_NBT_KEY_ADD)) // add newly inserted items to this tube
		{
			ListTag invList = compound.getList(INV_NBT_KEY_ADD, 10);
			for (int i = 0; i < invList.size(); i++) {
				CompoundTag itemTag = invList.getCompound(i);
				this.inventory.add(ItemInTubeWrapper.readFromNBT(itemTag));
			}
		}
	}

}
