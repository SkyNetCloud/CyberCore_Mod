package xyz.skynetcloud.cybercore.entities.passive;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import net.minecraft.entity.AgeableEntity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.util.Hand;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;
import net.minecraftforge.fml.network.NetworkHooks;
import net.minecraftforge.registries.ForgeRegistries;
import xyz.skynetcloud.cybercore.api.Names;
import xyz.skynetcloud.cybercore.entities.tradesandjobs.BaseVillagerContainerProvider;
import xyz.skynetcloud.cybercore.entities.tradesandjobs.BaseVillagerTrade;
import xyz.skynetcloud.cybercore.entities.tradesandjobs.BaseVillagerTradePool;
import xyz.skynetcloud.cybercore.entities.tradesandjobs.BaseVillagerTradePools;

public class ShadowVillagerEntity extends AgeableEntity {

	private List<BaseVillagerTrade> offers;

	public ShadowVillagerEntity(EntityType<ShadowVillagerEntity> type, World worldIn) {
		super(type, worldIn);
	}

	private List<BaseVillagerTrade> getOffers() {
		if (offers == null) {
			offers = new ArrayList<BaseVillagerTrade>();
			populateTrades();
		} else if (offers.size() == 0) {
			populateTrades();
		}
		return offers;
	}

	private void populateTrades() {
		List<BaseVillagerTradePool> pool = BaseVillagerTradePools.getTrades();
		List<Integer> integers = new ArrayList<Integer>();
		for (int i = 0; i < pool.size(); i++) {
			integers.add(i);
		}

		
		for (int i = 0; i < Math.min(8, pool.size()); i++) {

			offers.add(pool.get(integers.get(i)).generateVillagerTrade());
			integers.remove(i);
		}

	}

	@Override
	public void readAdditional(CompoundNBT compound) {
		super.readAdditional(compound);
		offers = new ArrayList<BaseVillagerTrade>();
		for (int i = 0; i < compound.getInt("length_trades"); i++) {
			offers.add(BaseVillagerTrade.fromNBT(compound.getCompound("offer_" + i)));
		}
	}

	@Override
	public void writeAdditional(CompoundNBT compound) {
		super.writeAdditional(compound);
		if (offers != null) {
			compound.putInt("length_trades", offers.size());
			for (int i = 0; i < offers.size(); i++) {
				compound.put("offer_" + i, offers.get(i).toNBT());
			}
		} else {
			compound.putInt("length_trades", 0);
		}
	}

	@Override
	public boolean processInteract(PlayerEntity player, Hand hand) {
		if (hand == Hand.MAIN_HAND && !world.isRemote) {
			if (player instanceof ServerPlayerEntity) {
				NetworkHooks.openGui((ServerPlayerEntity) player, new BaseVillagerContainerProvider(getOffers()),
						buffer -> {

							buffer.writeInt(offers.size());
							for (BaseVillagerTrade trade : offers) {
								trade.toBuffer(buffer);
							}
						});
			}
		}
		return true;
	}

	@Override
	public boolean canDespawn(double distanceToClosestPlayer) {
		return false;
	}

	public EntityType<?> getEntityType() {
		return ForgeRegistries.ENTITIES.getValue(new ResourceLocation(Names.CYBERVILLAGER));
	}

	@SuppressWarnings("unchecked")
	@Override
	public AgeableEntity createChild(AgeableEntity ageable) {
		return new ShadowVillagerEntity((EntityType<ShadowVillagerEntity>) getEntityType(), this.world);
	}

}
