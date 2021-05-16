package ca.skynetcloud.cybercore.entites.hostile;

import net.minecraft.block.BlockState;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
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
import net.minecraft.entity.monster.MonsterEntity;
import net.minecraft.entity.passive.IronGolemEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.particles.ParticleTypes;
import net.minecraft.util.DamageSource;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.SoundEvents;
import net.minecraft.util.math.BlockPos;
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

	public float isAttacking(float f) {
		return this.getWarmupTick() == 0 ? 0.0F : (f) / (float) this.getWarmupTick();
	}

	@Override
	public void aiStep() {
		super.aiStep();

		if (this.attackAnimationTick > 0) {
			--this.attackAnimationTick;
		}

		// Choose your attack!
		if (this.getAttackTick() == 100) {
			this.setAttackType(random.nextInt(4));
		}

		// Let's not break this boi, okay? He has feelings :(
		if (this.getAttackType() > 3) {
			this.setAttackType(3);
		}
		if (this.getAttackType() < 0) {
			this.setAttackType(0);
		}

		// Decrement attack tick
		if (this.getAttackTick() > 0) {
			if (this.getTarget() != null) {
				this.setAttackTick(this.getAttackTick() - 1);
			}
		}

		// Decrement warmup tick, stop golem
		if (this.getAttackTick() == 0) {
			if (this.getWarmupTick() > 0) {
				this.setWarmupTick(this.getWarmupTick() - 1);
				this.getAttribute(Attributes.MOVEMENT_SPEED).setBaseValue(0);
			}

			// Roar only when necessary
			if (this.getWarmupTick() == 19) {
				if (this.getAttackType() == 0) {
					this.level.playSound(null, this.getBlockPosBelowThatAffectsMyMovement(), SoundEvents.RAVAGER_ROAR,
							SoundCategory.HOSTILE, 1, 1);
				}
			}

			// Attaaaaaack!
			if (this.getWarmupTick() == 0) {
				if (getAttackType() == 2) {
					this.createParticles();
				}
				attack();
				this.getAttribute(Attributes.MOVEMENT_SPEED).setBaseValue(0.2499999940395355D);
				this.attackAnimationTick = 15;
				this.setWarmupTick(20);
				this.setAttackTick(this.random.nextInt(100) + 100);
			}
		}
	}

	private void attack() {
		this.attackAnimationTick = 10;
		if (!this.level.isClientSide) {

			if (this.getAttackType() == 1) {
				this.attackAnimationTick = 10;
				Entity target = this.getTarget();
				if (target != null) {
					this.level.playSound(null, this.getBlockPosBelowThatAffectsMyMovement(),
							SoundEvents.PLAYER_ATTACK_NODAMAGE, SoundCategory.HOSTILE, 2, 0);
					if (target.distanceTo(this) < 4D) {
						target.hurt(DamageSource.mobAttack(this), 8.0F);
						this.level.playSound(null, this.getBlockPosBelowThatAffectsMyMovement(),
								SoundEvents.STONE_BREAK, SoundCategory.HOSTILE, 2, 0);
						this.launch(target, 8.0D);
					}
				}
			}
			if (this.getAttackType() == 2) {
				this.attackAnimationTick = 10;
				for (Entity entity : this.level.getEntitiesOfClass(LivingEntity.class,
						this.getBoundingBox().inflate(4.0D))) {
					if (!(entity instanceof AbstractIllagerEntity)) {
						if (!(entity instanceof RobotEnemy)) {
							entity.hurt(DamageSource.mobAttack(this), 6.0F);
						}
					}
					this.launch(entity, 4.0D);
					this.level.playSound(null, this.getBlockPosBelowThatAffectsMyMovement(),
							SoundEvents.GENERIC_EXPLODE, SoundCategory.HOSTILE, 1f,
							(float) (random.nextInt(3) / 10) + 0.2F);
				}
			}

		}
	}

	@OnlyIn(Dist.CLIENT)
	public int getAttackAnimationTick() {
		return this.attackAnimationTick;
	}

	private void launch(Entity entity, double amplifier) {
		double d0 = entity.getX() - this.getX();
		double d1 = entity.getZ() - this.getZ();
		double d2 = Math.max(d0 * d0 + d1 * d1, 0.001D);
		entity.push(d0 / d2 * amplifier, 0.2D, d1 / d2 * amplifier);
	}

	private void createParticles() {
		Vector3d vec3d = this.getBoundingBox().getCenter();

		for (int i = 0; i < 40; ++i) {
			double d0 = this.level.random.nextGaussian() * 0.4D;
			double d1 = this.level.random.nextGaussian() * 0.4D;
			double d2 = this.level.random.nextGaussian() * 0.4D;
			this.level.addParticle(ParticleTypes.CLOUD, vec3d.x, vec3d.y, vec3d.z, d0, d1, d2);

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

	@Override
	public boolean canBreatheUnderwater() {
		return true;
	}

	@Override
	protected SoundEvent getAmbientSound() {
		return null;
	}

	@Override
	protected SoundEvent getDeathSound() {
		return SoundEvents.IRON_GOLEM_DEATH;
	}

	@Override
	protected SoundEvent getHurtSound(DamageSource damageSourceIn) {
		return SoundEvents.IRON_GOLEM_HURT;
	}

	@Override
	protected void playStepSound(BlockPos pos, BlockState blockIn) {
		this.playSound(SoundEvents.IRON_GOLEM_STEP, 1.0F, 1.0F);
	}

}
