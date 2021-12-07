package ca.skynetcloud.cybercore.client.entites.hostile;

import java.util.Random;

import ca.skynetcloud.cybercore.client.init.CoreInit;
import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.BlockParticleOption;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.util.Mth;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.MobSpawnType;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.goal.LookAtPlayerGoal;
import net.minecraft.world.entity.ai.goal.MeleeAttackGoal;
import net.minecraft.world.entity.ai.goal.WaterAvoidingRandomStrollGoal;
import net.minecraft.world.entity.ai.goal.target.HurtByTargetGoal;
import net.minecraft.world.entity.ai.goal.target.NearestAttackableTargetGoal;
import net.minecraft.world.entity.animal.IronGolem;
import net.minecraft.world.entity.monster.AbstractIllager;
import net.minecraft.world.entity.monster.Creeper;
import net.minecraft.world.entity.monster.Enemy;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.entity.npc.Villager;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

public class RobotEnemy extends Monster {

	private int attackAnimationTick;

	private static final EntityDataAccessor<Integer> ATTACK_WARMUP_TICK = SynchedEntityData.defineId(RobotEnemy.class,
			EntityDataSerializers.INT);
	private static final EntityDataAccessor<Integer> ATTACK_TYPE = SynchedEntityData.defineId(RobotEnemy.class,
			EntityDataSerializers.INT);
	private static final EntityDataAccessor<Integer> ATTACK_TICK = SynchedEntityData.defineId(RobotEnemy.class,
			EntityDataSerializers.INT);

	public RobotEnemy(EntityType<? extends Monster> type, Level world) {
		super(type, world);
		this.xpReward = 20;
		this.maxUpStep = 1.0F;

	}

	@Override
	protected void registerGoals() {
		super.registerGoals();

		this.goalSelector.addGoal(1, new MeleeAttackGoal(this, 1D, true));
		this.goalSelector.addGoal(2, new WaterAvoidingRandomStrollGoal(this, 1D));
		// this.goalSelector.addGoal(8, new RandomWalkingGoal(this, 0.4D));
		this.goalSelector.addGoal(9, new LookAtPlayerGoal(this, Player.class, 3.0F, 1.0F));
		this.goalSelector.addGoal(10, new LookAtPlayerGoal(this, LivingEntity.class, 8.0F));
		this.targetSelector.addGoal(3, new HurtByTargetGoal(this, new Class[] { AbstractIllager.class }));
		this.targetSelector.addGoal(5,
				(new NearestAttackableTargetGoal<Player>(this, Player.class, true)).setUnseenMemoryTicks(300));
		this.targetSelector.addGoal(4,
				(new NearestAttackableTargetGoal<Villager>(this, Villager.class, false)).setUnseenMemoryTicks(300));
		this.targetSelector.addGoal(6,
				(new NearestAttackableTargetGoal<IronGolem>(this, IronGolem.class, false)).setUnseenMemoryTicks(300));
	}

	public static AttributeSupplier.Builder createAttributes() {

		return Mob.createMobAttributes().add(Attributes.MOVEMENT_SPEED, 0.2499999940395355D)
				.add(Attributes.FOLLOW_RANGE, 30.0D).add(Attributes.MAX_HEALTH, 200.0D)
				.add(Attributes.ATTACK_DAMAGE, 6.0D).add(Attributes.KNOCKBACK_RESISTANCE, 20.0D);
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
	
    public static boolean canRobotSpawn(EntityType<? extends RobotEnemy> animal, LevelAccessor levelAccessor, MobSpawnType spawnType, BlockPos pos, Random random) {
        return levelAccessor.getBlockState(pos.below()).is(Blocks.GRASS_BLOCK) && levelAccessor.getRawBrightness(pos, 0) > 8 && levelAccessor.canSeeSky(pos);
    }

	public void setAttackType(int tick) {
		this.entityData.set(ATTACK_TYPE, tick);
	}

	protected int decreaseAirSupply(int p_70682_1_) {
		return p_70682_1_;
	}

	protected void doPush(Entity p_82167_1_) {
		if (p_82167_1_ instanceof Enemy && !(p_82167_1_ instanceof Creeper) && this.getRandom().nextInt(20) == 0) {
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

		if (this.getDeltaMovement().horizontalDistanceSqr() > (double) 2.5000003E-7F && this.random.nextInt(5) == 0) {
			int i = Mth.floor(this.getX());
			int j = Mth.floor(this.getY() - (double) 0.2F);
			int k = Mth.floor(this.getZ());
			BlockPos pos = new BlockPos(i, j, k);
			BlockState blockstate = this.level.getBlockState(pos);
			if (!blockstate.isAir()) {
				this.level.addParticle(new BlockParticleOption(ParticleTypes.BLOCK, blockstate).setPos(pos),
						this.getX() + ((double) this.random.nextFloat() - 0.5D) * (double) this.getBbWidth(),
						this.getY() + 0.1D,
						this.getZ() + ((double) this.random.nextFloat() - 0.5D) * (double) this.getBbWidth(),
						4.0D * ((double) this.random.nextFloat() - 0.5D), 0.5D,
						((double) this.random.nextFloat() - 0.5D) * 4.0D);
			}
		}

	}

	public void addAdditionalSaveData(CompoundTag compound) {
		super.addAdditionalSaveData(compound);
		compound.putInt("AttackTick", this.getAttackTick());
		compound.putInt("AttackWarmupTick", this.getWarmupTick());
		compound.putInt("AttackType", this.getAttackType());
	}

	/**
	 * (abstract) Protected helper method to read subclass entity data from NBT.
	 */
	public void readAdditionalSaveData(CompoundTag compound) {
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
		return CoreInit.SoundInit.Robot_Hurt_Noise;
	}

	protected SoundEvent getDeathSound() {
		return CoreInit.SoundInit.Robot_Death_Noise;
	}

	protected void playStepSound(BlockPos p_180429_1_, BlockState p_180429_2_) {
		this.playSound(CoreInit.SoundInit.Robot_Walk_Noise, 1.0F, 1.0F);
	}

	public void die(DamageSource p_70645_1_) {
		super.die(p_70645_1_);
	}

	@OnlyIn(Dist.CLIENT)
	public Vec3 getLeashOffset() {
		return new Vec3(0.0D, (double) (0.875F * this.getEyeHeight()), (double) (this.getBbWidth() * 0.4F));
	}

}
