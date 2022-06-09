package ca.skynetcloud.cybercore.client.world.level.block;


import ca.skynetcloud.cybercore.client.init.DamageInit;
import ca.skynetcloud.cybercore.client.init.MainInit;
import ca.skynetcloud.cybercore.client.init.SoundInit;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.ItemBlockRenderTypes;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.core.particles.SimpleParticleType;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundSource;
import net.minecraft.util.RandomSource;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.animal.IronGolem;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.GameType;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.FlowerBlock;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.material.Material;
import net.minecraft.world.level.storage.loot.LootContext;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import org.jetbrains.annotations.Nullable;

import java.util.Collections;
import java.util.List;
import java.util.Random;

public class ShockflowerBlock extends FlowerBlock {

    public ShockflowerBlock() {
        super(MobEffects.MOVEMENT_SPEED, 100, BlockBehaviour.Properties.of(Material.PLANT).noCollission().sound(SoundType.GRASS).instabreak());
    }

    @Override
    public int getEffectDuration() {
        return 100;
    }

    @Override
    public int getFlammability(BlockState state, BlockGetter world, BlockPos pos, Direction face) {
        return 100;
    }

    @Override
    public int getFireSpreadSpeed(BlockState state, BlockGetter world, BlockPos pos, Direction face) {
        return 60;
    }

    public boolean checkPlayerGamemode(Entity _ent) {
        if (_ent instanceof ServerPlayer _serverPlayer) {
            return _serverPlayer.gameMode.getGameModeForPlayer() == GameType.CREATIVE;
        } else if (_ent.level.isClientSide() && _ent instanceof Player _player) {
            return Minecraft.getInstance().getConnection().getPlayerInfo(_player.getGameProfile().getId()) != null && Minecraft.getInstance()
                    .getConnection().getPlayerInfo(_player.getGameProfile().getId()).getGameMode() == GameType.CREATIVE;
        }
        return true;
    }


    @Override
    public void entityInside(@Nullable BlockState state, Level worldIn, BlockPos pos, Entity entity) {

            if (entity instanceof LivingEntity _entGetArmor) {


                if(entity instanceof IronGolem){
                    entity.hurt(DamageInit.ELECTRIC_FENCE, 50.0F);
                    if (worldIn.isClientSide)
                        doCollideAnimation(pos, worldIn, 1, ParticleTypes.ELECTRIC_SPARK, SoundInit.ELECTRIC_FENCE_SPARK.get(), 0.75F, 50F);
                }


                if(_entGetArmor.getItemBySlot(EquipmentSlot.HEAD).getItem() == Items.IRON_HELMET && _entGetArmor.getItemBySlot(EquipmentSlot.CHEST).getItem() == Items.IRON_CHESTPLATE && _entGetArmor.getItemBySlot(EquipmentSlot.LEGS).getItem() == Items.IRON_LEGGINGS && _entGetArmor.getItemBySlot(EquipmentSlot.FEET).getItem() == Items.IRON_BOOTS){
                    entity.hurt(DamageInit.ELECTRIC_FENCE, 25.0F);
                    if (worldIn.isClientSide)
                        doCollideAnimation(pos, worldIn, 1, ParticleTypes.ELECTRIC_SPARK, SoundInit.ELECTRIC_FENCE_SPARK.get(),
                                0.75F, 50F);
                }



                if (_entGetArmor.getItemBySlot(EquipmentSlot.CHEST).getItem() == Items.LEATHER_CHESTPLATE) {
                    entity.isInvulnerableTo(DamageInit.ELECTRIC_FENCE);
                } else {
                    if (worldIn.isRaining() && worldIn.canSeeSky(pos))  {

                        entity.hurt(DamageInit.ELECTRIC_FENCE, 5.0F);
                        if (worldIn.isClientSide)
                            doCollideAnimation(pos, worldIn, 1, ParticleTypes.ELECTRIC_SPARK, SoundInit.ELECTRIC_FENCE_SPARK.get(),
                                    0.75F, 50F);
                    } else {
                        entity.hurt(DamageInit.ELECTRIC_FENCE, 2.5F);
                        if (worldIn.isClientSide)
                            doCollideAnimation(pos, worldIn, 1, ParticleTypes.ELECTRIC_SPARK, SoundInit.ELECTRIC_FENCE_SPARK.get(), 0.55F,
                                    20F);
                    }
                }

            } else {
                entity.remove(Entity.RemovalReason.KILLED);
                if (worldIn.isClientSide)
                    doCollideAnimation(pos, worldIn, 7, ParticleTypes.ELECTRIC_SPARK, SoundInit.ELECTRIC_FENCE_SPARK.get(), 0.8F, 20F);
            }

    }



    @OnlyIn(Dist.CLIENT)
    @Override
    public void animateTick(BlockState blockstate, Level world, BlockPos pos, RandomSource random) {
        super.animateTick(blockstate, world, pos, random);
        Player entity = Minecraft.getInstance().player;
        int x = pos.getX();
        int y = pos.getY();
        int z = pos.getZ();
        for (int l = 0; l < 1; ++l) {
            double x0 = x + 0.5 + (random.nextFloat() - 0.5) * 1.9000000000000001D;
            double y0 = y + 1.2 + (random.nextFloat() - 0.5) * 1.9000000000000001D;
            double z0 = z + 0.5 + (random.nextFloat() - 0.5) * 1.9000000000000001D;
            world.addParticle(ParticleTypes.ELECTRIC_SPARK, x0, y0, z0, 0, 0, 0);
        }
    }


    private void doCollideAnimation(BlockPos pos, Level worldIn, int amount, SimpleParticleType particle,
                                    SoundEvent sound, float volume, float pitch) {
        double x = pos.getX();
        double y = pos.getY();
        double z = pos.getZ();
        Random random = new Random();
        // worldIn.playSound(x + 0.5, y + 0.5, z + 0.5,
        // SoundEvents.ENTITY_GENERIC_EXTINGUISH_FIRE, SoundCategory.BLOCKS, volume,
        // pitch, false);
        worldIn.playLocalSound(x + 0.5, y + 0.5, z + 0.5, sound, SoundSource.BLOCKS, volume, pitch, false);
        for (int i = 0; i < amount; i++)
            worldIn.addParticle(particle, x + random.nextFloat(), y + random.nextFloat(), z + random.nextFloat(), 0.0D,
                    0.0D, 0.0D);
    }


    @Override
    public List<ItemStack> getDrops(BlockState state, LootContext.Builder builder) {
        List<ItemStack> dropsOriginal = super.getDrops(state, builder);
        if (!dropsOriginal.isEmpty())
            return dropsOriginal;
        return Collections.singletonList(new ItemStack(this, 1));
    }


    @OnlyIn(Dist.CLIENT)
    public static void registerRenderLayer() {
        ItemBlockRenderTypes.setRenderLayer(MainInit.SHOCKFLOWER.get(), renderType -> renderType == RenderType.cutout());
    }






}
