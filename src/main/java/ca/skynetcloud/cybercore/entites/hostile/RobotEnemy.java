package ca.skynetcloud.cybercore.entites.hostile;

import ca.skynetcloud.cybercore.init.SoundInit;
import net.minecraft.block.BlockState;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.MobEntity;
import net.minecraft.entity.ai.attributes.AttributeModifierMap;
import net.minecraft.entity.ai.attributes.Attributes;
import net.minecraft.entity.ai.goal.HurtByTargetGoal;
import net.minecraft.entity.ai.goal.LookAtGoal;
import net.minecraft.entity.ai.goal.MeleeAttackGoal;
import net.minecraft.entity.ai.goal.NearestAttackableTargetGoal;
import net.minecraft.entity.ai.goal.RandomWalkingGoal;
import net.minecraft.entity.ai.goal.WaterAvoidingRandomWalkingGoal;
import net.minecraft.entity.merchant.villager.VillagerEntity;
import net.minecraft.entity.monster.AbstractIllagerEntity;
import net.minecraft.entity.monster.CreeperEntity;
import net.minecraft.entity.monster.IMob;
import net.minecraft.entity.monster.MonsterEntity;
import net.minecraft.entity.passive.IronGolemEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.particles.BlockParticleData;
import net.minecraft.particles.ParticleTypes;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.DamageSource;
import net.minecraft.util.Hand;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.SoundEvents;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.vector.Vector3d;
import net.minecraft.world.World;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

public class RobotEnemy extends MonsterEntity {

	private int attackAnimationTick;

	private static final DataParameter<Integer> ATTACK_WARMUP_TICK = EntityDataManager.defineId(RobotEnemy.class,
			DataSerializers.INT);
	private static final DataParameter<Integer> ATTACK_TYPE = EntityDataManager.defineId(RobotEnemy.class,
			DataSerializers.INT);
	private static final DataParameter<Integer> ATTACK_TICK = EntityDataManager.defineId(RobotEnemy.class,
			DataSerializers.INT);

	public RobotEnemy(EntityType<? extends MonsterEntity> type, World world) {
		super(type, world);
		this.xpReward = 20;
		this.maxUpStep = 1.0F;

	}

	@Override
	protected void registerGoals() {
		super.registerGoals();

		this.goalSelector.addGoal(1, new MeleeAttackGoal(this, 1D, true));
		this.goalSelector.addGoal(2, new WaterAvoidingRandomWalkingGoal(this, 1D));
		this.goalSelector.addGoal(8, new RandomWalkingGoal(this, 0.4D));
		this.goalSelector.addGoal(9, new LookAtGoal(this, PlayerEntity.class, 3.0F, 1.0F));
		this.goalSelector.addGoal(10, new LookAtGoal(this, LivingEntity.class, 8.0F));
		this.targetSelector.addGoal(3, new HurtByTargetGoal(this, new Class[] { AbstractIllagerEntity.class }));
		this.targetSelector.addGoal(5, (new NearestAttackableTargetGoal<PlayerEntity>(this, PlayerEntity.class, true))
				.setUnseenMemoryTicks(300));
		this.targetSelector.addGoal(4,
				(new NearestAttackableTargetGoal<VillagerEntity>(this, VillagerEntity.class, false))
						.setUnseenMemoryTicks(300));
		this.targetSelector.addGoal(6,
				(new NearestAttackableTargetGoal<IronGolemEntity>(this, IronGolemEntity.class, false))
						.setUnseenMemoryTicks(300));
	}

	public static AttributeModifierMap attributes() {

		return MonsterEntity.createMonsterAttributes().add(Attributes.MOVEMENT_SPEED, 0.2499999940395355D)
				.add(Attributes.FOLLOW_RANGE, 30.0D).add(Attributes.MAX_HEALTH, 200.0D)
				.add(Attributes.ATTACK_DAMAGE, 6.0D).add(Attributes.KNOCKBACK_RESISTANCE, 20.0D).build();
	}

	protected void defineSynchedData() {
		super.defineSynchedData();
		this.entityData.define(ATTACK_TICK, 100);
		this.entityData.define(ATTACK_WARMUP_TICK, 20);
		this.entityData.define(ATTACK_TYPE, 0);
	}

	public int getAttackTick() {
		return this.entityData.get(ATTACK_TICK);
	}

	public void setAttackTick(int tick) {
		this.entityData.set(ATTACK_TICK, tick);
	}

	public int getWarmupTick() {
		return this.entityData.get(ATTACK_WARMUP_TICK);
	}

	public void setWarmupTick(int tick) {
		this.entityData.set(ATTACK_WARMUP_TICK, tick);
	}

	public int getAttackType() {
		return this.entityData.get(ATTACK_TYPE);
	}

	public void setAttackType(int tick) {
		this.entityData.set(ATTACK_TYPE, tick);
	}

	protected int decreaseAirSupply(int p_70682_1_) {
		return p_70682_1_;
	}

	protected void doPush(Entity p_82167_1_) {
		if (p_82167_1_ instanceof IMob && !(p_82167_1_ instanceof CreeperEntity) && this.getRandom().nextInt(20) == 0) {
			this.setTarget((LivingEntity) p_82167_1_);
		}

		super.doPush(p_82167_1_);
	}

	@OnlyIn(Dist.CLIENT)
	public void handleEntityEvent(byte p_70103_1_) {
		if (p_70103_1_ == 4) {
			this.attackAnimationTick = 10;
			this.playSound(SoundEvents.IRON_GOLEM_ATTACK, 1.0F, 1.0F);
		}
		super.handleEntityEvent(p_70103_1_);
	}

	@OnlyIn(Dist.CLIENT)
	public int getAttackAnimationTick() {
		return this.attackAnimationTick;
	}

	@SuppressWarnings("deprecation")
	public void aiStep() {
		super.aiStep();
		if (this.attackAnimationTick > 0) {
			--this.attackAnimationTick;
		}

		if (getHorizontalDistanceSqr(this.getDeltaMovement()) > (double) 2.5000003E-7F && this.random.nextInt(5) == 0) {
			int i = MathHelper.floor(this.getX());
			int j = MathHelper.floor(this.getY() - (double) 0.2F);
			int k = MathHelper.floor(this.getZ());
			BlockPos pos = new BlockPos(i, j, k);
			BlockState blockstate = this.level.getBlockState(pos);
			if (!blockstate.isAir(this.level, pos)) {
				this.level.addParticle(new BlockParticleData(ParticleTypes.BLOCK, blockstate).setPos(pos),
						this.getX() + ((double) this.random.nextFloat() - 0.5D) * (double) this.getBbWidth(),
						this.getY() + 0.1D,
						this.getZ() + ((double) this.random.nextFloat() - 0.5D) * (double) this.getBbWidth(),
						4.0D * ((double) this.random.nextFloat() - 0.5D), 0.5D,
						((double) this.random.nextFloat() - 0.5D) * 4.0D);
			}
		}

	}

	public void addAdditionalSaveData(CompoundNBT compound) {
		super.addAdditionalSaveData(compound);
		compound.putInt("AttackTick", this.getAttackTick());
		compound.putInt("AttackWarmupTick", this.getWarmupTick());
		compound.putInt("AttackType", this.getAttackType());
	}

	/**
	 * (abstract) Protected helper method to read subclass entity data from NBT.
	 */
	public void readAdditionalSaveData(CompoundNBT compound) {
		super.readAdditionalSaveData(compound);
		this.setAttackTick(compound.getInt("AttackTick"));
		this.setWarmupTick(compound.getInt("AttackWarmupTick"));
		if (compound.getInt("AttackType") > 3) {
			this.setAttackType(3);
		} else if (compound.getInt("AttackType") < 0) {
			this.setAttackType(0);
		} else {
			this.setAttackType(compound.getInt("AttackType"));
		}
	}

	private float getAttackDamage() {
		return (float) this.getAttributeValue(Attributes.ATTACK_DAMAGE);
	}

	public boolean doHurtTarget(Entity p_70652_1_) {
		this.attackAnimationTick = 10;
		this.level.broadcastEntityEvent(this, (byte) 4);
		float f = this.getAttackDamage();
		float f1 = (int) f > 0 ? f / 2.0F + (float) this.random.nextInt((int) f) : f;
		boolean flag = p_70652_1_.hurt(DamageSource.mobAttack(this), f1);
		if (flag) {
			p_70652_1_.setDeltaMovement(p_70652_1_.getDeltaMovement().add(0.0D, (double) 0.4F, 0.0D));
			this.doEnchantDamageEffects(this, p_70652_1_);
		}

		this.playSound(SoundEvents.IRON_GOLEM_ATTACK, 1.0F, 1.0F);
		return flag;
	}

	protected SoundEvent getHurtSound(DamageSource p_184601_1_) {
		return SoundInit.Robot_Hurt_Noise;
	}

	protected SoundEvent getDeathSound() {
		return SoundInit.Robot_Death_Noise;
	}

	protected void playStepSound(BlockPos p_180429_1_, BlockState p_180429_2_) {
		this.playSound(SoundInit.Robot_Walk_Noise, 1.0F, 1.0F);
	}

	public void die(DamageSource p_70645_1_) {
		super.die(p_70645_1_);
	}

	@OnlyIn(Dist.CLIENT)
	public Vector3d getLeashOffset() {
		return new Vector3d(0.0D, (double) (0.875F * this.getEyeHeight()), (double) (this.getBbWidth() * 0.4F));
	}

}
