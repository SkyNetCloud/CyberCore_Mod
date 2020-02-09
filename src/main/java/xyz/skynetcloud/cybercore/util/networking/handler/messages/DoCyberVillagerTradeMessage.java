package xyz.skynetcloud.cybercore.util.networking.handler.messages;

import java.util.function.Supplier;

import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.network.PacketBuffer;
import net.minecraftforge.fml.network.NetworkEvent;
import xyz.skynetcloud.cybercore.api.items.ItemInit;
import xyz.skynetcloud.cybercore.entities.capabilities.villagertrust.IVillagerTrust;
import xyz.skynetcloud.cybercore.entities.capabilities.villagertrust.VillagerTrust;
import xyz.skynetcloud.cybercore.entities.passive.VillagerEntity;
import xyz.skynetcloud.cybercore.entities.tradesandjobs.VillagerTrade;
import xyz.skynetcloud.cybercore.util.networking.handler.PlayerInventoryUtils;

public class DoCyberVillagerTradeMessage {
	;
	private VillagerTrade trade;
	private int profession;

	public DoCyberVillagerTradeMessage(VillagerTrade trade, int profession) {
		this.trade = trade;
		this.profession = profession;
	}

	public static void encode(DoCyberVillagerTradeMessage pkt, PacketBuffer buf) {
		pkt.trade.toBuffer(buf);
		buf.writeInt(pkt.profession);
	}

	public static DoCyberVillagerTradeMessage decode(PacketBuffer buf) {
		return new DoCyberVillagerTradeMessage(VillagerTrade.fromBuffer(buf), buf.readInt());
	}

	public static class DoTechVillagerTradeHandler {
		public static void handle(final DoCyberVillagerTradeMessage pkt, Supplier<NetworkEvent.Context> ctx) {
			ctx.get().enqueueWork(() -> {
				PlayerInventory inv = ctx.get().getSender().inventory;
				VillagerTrade trade = pkt.trade;
				IVillagerTrust trust = ctx.get().getSender().getCapability(VillagerTrust.INSTANCE).orElse(null);
				if (trust != null) {
					if (trust.getLevel(VillagerEntity.getProfessionString(pkt.profession)) >= trade.getNeededLevel()) {
						if (PlayerInventoryUtils.enoughSpace(inv, trade.getOutputs().size())) {
							if (PlayerInventoryUtils.hasList(inv, trade.getInputs())) {
								if (PlayerInventoryUtils.enoughCredits(inv, trade.getCreditsBuy())) {
									if (trade.getCreditsSell() > 0 && inv.count(ItemInit.card) > 0) {
										if (PlayerInventoryUtils.removeCredits(inv, trade.getCreditsBuy())) {
											if (PlayerInventoryUtils.removeList(inv, trade.getInputs())) {
												PlayerInventoryUtils.addList(inv, trade.getOutputs());
												PlayerInventoryUtils.addCredits(inv, trade.getCreditsSell());
											}
										}
									} else if (trade.getCreditsSell() == 0) {
										if (PlayerInventoryUtils.removeCredits(inv, trade.getCreditsBuy())) {
											if (PlayerInventoryUtils.removeList(inv, trade.getInputs())) {
												PlayerInventoryUtils.addList(inv, trade.getOutputs());
											}
										}
									}

								}
							}
						}
					}
				}
			});
			ctx.get().setPacketHandled(true);
		}
	}
}
