package ca.skynetcloud.cybercore.client.energy.baseclasses;

import ca.skynetcloud.cybercore.client.energy.PyroEnergyStorage;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.world.MenuProvider;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.ContainerData;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.energy.CapabilityEnergy;
import net.minecraftforge.energy.IEnergyStorage;

import java.util.Random;

abstract public class PyroEnergyBlockEntity extends BlockEntity implements MenuProvider {


        protected PyroEnergyStorage energystorage;
        private final LazyOptional<IEnergyStorage> energyCap;
        public String customname;
        protected final Random rand = new Random();

	public PyroEnergyBlockEntity(BlockEntityType<?> type, BlockPos pos, BlockState state, int energyStorage)
        {
            super(type, pos, state);
            energystorage = new PyroEnergyStorage(energyStorage);
            energyCap = LazyOptional.of(() -> energystorage);
        }

        public static void tick(Level level, BlockPos pos, BlockState state, BlockEntity be)
        {
            if (level != null && !level.isClientSide){
                if(be instanceof PyroEnergyBlockEntity ebe){
                    ebe.doUpdate();
                }
            }
        }

        @Override
        public void handleUpdateTag(CompoundTag tag)
        {
            saveAdditional(super.getUpdateTag());
        }

        public void doUpdate()
        {
        }

        @Override
        public <T> LazyOptional<T> getCapability(Capability<T> capability, Direction facing)
        {
            return capability == CapabilityEnergy.ENERGY ? energyCap.cast() : super.getCapability(capability, facing);
        }

        @Override
        public void saveAdditional(CompoundTag compound)
        {
            compound.put("energy", this.energystorage.serializeNBT());
            super.saveAdditional(compound);
        }

        @Override
        public void load(CompoundTag compound)
        {
            super.load(compound);
            this.energystorage.deserializeNBT(compound.getCompound("energy"));
        }

        public String getNameString()
        {
            return "default";
        }

        public int getEnergyStored()
        {
            return this.energystorage.getEnergyStored();
        }

        public int getMaxEnergyStored()
        {
            return this.energystorage.getMaxEnergyStored();
        }

        public boolean isUsableByPlayer(Player player)
        {
            if (level == null || this.level.getBlockEntity(worldPosition) != this)
                return false;
            return player.distanceToSqr((double) this.worldPosition.getX() + 0.5D, (double) this.worldPosition.getY() + 0.5D, (double) this.worldPosition.getZ() + 0.5D) <= 64.0D;
        }

        public void onSlotContentChanged()
        {

        }

        public int getUpgradeSlot()
        {
            return -1;
        }

        public abstract ContainerData getContainerData();

        @Override
        public Component getDisplayName()
        {
            return Component.translatable("container." + getNameString());
        }

        public void notifyClient()
        {
            if (level != null && !level.isClientSide())
            {
                BlockState state = level.getBlockState(getBlockPos());
                level.sendBlockUpdated(getBlockPos(), state, state, 3);
            }
        }

        /**
         * Whether this block entity should be marked as changed (sync to client)
         * on initial interaction of a player
         * @return if sync is needed
         */
        public boolean requireSyncUponOpen()
        {
            return false;
        }
    }
