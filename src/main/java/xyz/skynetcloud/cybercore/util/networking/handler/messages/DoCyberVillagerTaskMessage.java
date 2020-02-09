package xyz.skynetcloud.cybercore.util.networking.handler.messages;

import java.util.function.Supplier;

import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.network.PacketBuffer;
import net.minecraftforge.fml.network.NetworkEvent;
import xyz.skynetcloud.cybercore.entities.capabilities.villagertrust.IVillagerTrust;
import xyz.skynetcloud.cybercore.entities.capabilities.villagertrust.VillagerTrust;
import xyz.skynetcloud.cybercore.entities.passive.VillagerEntity;
import xyz.skynetcloud.cybercore.entities.tradesandjobs.VillagerTask;
import xyz.skynetcloud.cybercore.entities.tradesandjobs.VillagerTaskList;
import xyz.skynetcloud.cybercore.util.networking.handler.CyberCorePacketHandler;
import xyz.skynetcloud.cybercore.util.networking.handler.PlayerInventoryUtils;

public class DoCyberVillagerTaskMessage {

	;
	private int task;

	public DoCyberVillagerTaskMessage(int task) {
		this.task = task;
	}

	public static void encode(DoCyberVillagerTaskMessage pkt, PacketBuffer buf) {
		buf.writeInt(pkt.task);
	}

	public static DoCyberVillagerTaskMessage decode(PacketBuffer buf) {
		return new DoCyberVillagerTaskMessage(buf.readInt());
	}

	public static class DoTechVillagerTaskHandler {
		public static void handle(final DoCyberVillagerTaskMessage pkt, Supplier<NetworkEvent.Context> ctx) {
			ctx.get().enqueueWork(() -> {
				ServerPlayerEntity player = ctx.get().getSender();
				PlayerInventory inv = player.inventory;
				VillagerTask task = VillagerTaskList.getByID(pkt.task);
				if (PlayerInventoryUtils.hasList(inv, task.getInputs())) {
					IVillagerTrust trust = player.getCapability(VillagerTrust.INSTANCE).orElse(null);
					if (trust != null) {
						int level = trust.getLevel(VillagerEntity.getProfessionString(task.getProfession()));
						if (level >= task.getMinTrustLevel() && level <= task.getMaxTrustLevel()) {
							if (PlayerInventoryUtils.removeList(inv, task.getInputs())) {
								String profession = VillagerEntity.getProfessionString(task.getProfession());
								trust.increaseTrust(profession, task.getTrust(), task.getMaxTrustLevel() + 1);
								CyberCorePacketHandler.sendTo(
										new CyberSyncTrustMessage(profession, trust.getTrust(profession)), player);
							}
						}
					}
				}
			});
			ctx.get().setPacketHandled(true);
		}
	}

}
