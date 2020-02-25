package xyz.skynetcloud.cybercore.entities.passive;

import java.util.Random;

import javax.annotation.Nullable;

import net.minecraft.entity.EntityType;
import net.minecraft.entity.ILivingEntityData;
import net.minecraft.entity.SpawnReason;
import net.minecraft.entity.ai.goal.NearestAttackableTargetGoal;
import net.minecraft.entity.monster.DrownedEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.pathfinding.SwimmerPathNavigator;
import net.minecraft.tags.FluidTags;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.Difficulty;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.IWorld;
import net.minecraft.world.World;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.Biomes;
import xyz.skynetcloud.cybercore.api.items.ItemInit;

public class WildSharkEntity extends DrownedEntity {

	protected final SwimmerPathNavigator waterNavigator;

	public WildSharkEntity(EntityType<? extends WildSharkEntity> type, World worldIn) {
		super(type, worldIn);

		this.waterNavigator = new SwimmerPathNavigator(this, worldIn);

	}

	@Override
	protected void applyEntityAI() {
		this.targetSelector.addGoal(5,
				new NearestAttackableTargetGoal<>(this, PlayerEntity.class, 10, true, false, this::shouldAttack));
	}

	@Override
	protected boolean canBreakDoors() {
		return true;
	}

	@Override
	public ILivingEntityData onInitialSpawn(IWorld worldIn, DifficultyInstance difficultyIn, SpawnReason reason,
			@Nullable ILivingEntityData spawnDataIn, @Nullable CompoundNBT dataTag) {
		spawnDataIn = super.onInitialSpawn(worldIn, difficultyIn, reason, spawnDataIn, dataTag);
		if (this.getItemStackFromSlot(EquipmentSlotType.OFFHAND).isEmpty() && this.rand.nextFloat() < 0.03F) {
			this.setItemStackToSlot(EquipmentSlotType.OFFHAND, new ItemStack(ItemInit.card));
			this.inventoryHandsDropChances[EquipmentSlotType.OFFHAND.getIndex()] = 2.0F;
		}

		return spawnDataIn;
	}

	public static boolean func_223332_b_(EntityType<WildSharkEntity> p_223332_0_, IWorld p_223332_1_,
			SpawnReason p_223332_2_, BlockPos p_223332_3_, Random p_223332_4_) {
		Biome biome = p_223332_1_.func_226691_t_(p_223332_3_);
		boolean flag = p_223332_1_.getDifficulty() != Difficulty.PEACEFUL
				&& func_223323_a(p_223332_1_, p_223332_3_, p_223332_4_) && (p_223332_2_ == SpawnReason.SPAWNER
						|| p_223332_1_.getFluidState(p_223332_3_).isTagged(FluidTags.WATER));
		if (biome != Biomes.RIVER && biome != Biomes.FROZEN_RIVER) {
			return p_223332_4_.nextInt(40) == 0 && func_223333_a(p_223332_1_, p_223332_3_) && flag;
		} else {
			return p_223332_4_.nextInt(15) == 0 && flag;
		}
	}

	private static boolean func_223333_a(IWorld p_223333_0_, BlockPos p_223333_1_) {
		return p_223333_1_.getY() < p_223333_0_.getSeaLevel() - 5;
	}

}
