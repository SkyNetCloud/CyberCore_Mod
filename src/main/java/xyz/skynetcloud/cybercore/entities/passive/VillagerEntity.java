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
import xyz.skynetcloud.cybercore.entities.tradesandjobs.VillagerContainerProvider;
import xyz.skynetcloud.cybercore.entities.tradesandjobs.VillagerTrade;
import xyz.skynetcloud.cybercore.entities.tradesandjobs.VillagerTradePool;
import xyz.skynetcloud.cybercore.entities.tradesandjobs.VillagerTradePools;

public class VillagerEntity extends AgeableEntity {
	public static final int SCIENTIST = 0;

	private static final DataParameter<Integer> PROFESSION = EntityDataManager.createKey(VillagerEntity.class,
			DataSerializers.VARINT);

	private List<VillagerTrade> offers;

	public VillagerEntity(EntityType<VillagerEntity> type, World worldIn) {
		super(type, worldIn);
	}

	@Override
	protected void registerData() {
		super.registerData();
		this.dataManager.register(PROFESSION, 0);
	}

	public int getProfession() {
		return dataManager.get(PROFESSION);
	}

	public static String getProfessionString(int profession) {
		switch (profession) {
		case 0:
			return "scientist";
		default:
			return "scientist";
		}
	}

	private List<VillagerTrade> getOffers() {
		if (offers == null) {
			offers = new ArrayList<VillagerTrade>();
			populateTrades();
		} else if (offers.size() == 0) {
			populateTrades();
		}
		return offers;
	}

	private void populateTrades() {
		List<VillagerTradePool> pool = VillagerTradePools.getSCIENTISTS();
		List<Integer> integers = new ArrayList<Integer>();
		for (int i = 0; i < pool.size(); i++) {
			integers.add(i);
		}

		Random rand = new Random();
		for (int i = 0; i < Math.min(8, pool.size()); i++) {
			int randomint = rand.nextInt(integers.size());
			offers.add(pool.get(integers.get(randomint)).generateTechVillagerTrade());
			integers.remove(randomint);
		}

	}

	@Override
	public void readAdditional(CompoundNBT compound) {
		super.readAdditional(compound);
		offers = new ArrayList<VillagerTrade>();
		for (int i = 0; i < compound.getInt("length_trades"); i++) {
			offers.add(VillagerTrade.fromNBT(compound.getCompound("offer_" + i)));
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
				NetworkHooks.openGui((ServerPlayerEntity) player,
						new VillagerContainerProvider(getOffers(), getProfession()), buffer -> {
							buffer.writeInt(getProfession());
							buffer.writeInt(offers.size());
							for (VillagerTrade trade : offers) {
								trade.toBuffer(buffer);
							}
						});
			}
		}
		return true;
	}

	@Override
	public boolean canDespawn(double distanceToClosestPlayer) {
		return true;
	}

	public EntityType<?> getEntityType() {
		return ForgeRegistries.ENTITIES.getValue(new ResourceLocation(Names.CYBERVILLAGER));
	}

	@SuppressWarnings("unchecked")
	@Override
	public AgeableEntity createChild(AgeableEntity ageable) {
		return new VillagerEntity((EntityType<VillagerEntity>) getEntityType(), this.world);
	}

}
