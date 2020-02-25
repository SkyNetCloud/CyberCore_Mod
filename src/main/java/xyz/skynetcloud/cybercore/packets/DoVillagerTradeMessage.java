package xyz.skynetcloud.cybercore.packets;

import java.util.function.Supplier;

import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.network.PacketBuffer;
import net.minecraftforge.fml.network.NetworkEvent;
import xyz.skynetcloud.cybercore.api.items.ItemInit;
import xyz.skynetcloud.cybercore.entities.tradesandjobs.BaseVillagerTrade;
import xyz.skynetcloud.cybercore.util.networking.handler.PlayerInventoryUtils;

public class DoVillagerTradeMessage {
	;
	private BaseVillagerTrade trade;

	public DoVillagerTradeMessage(BaseVillagerTrade trade) {
		this.trade = trade;
	}

	public static void encode(DoVillagerTradeMessage pkt, PacketBuffer buf) {
		pkt.trade.toBuffer(buf);
	}

	public static DoVillagerTradeMessage decode(PacketBuffer buf) {
		return new DoVillagerTradeMessage(BaseVillagerTrade.fromBuffer(buf));
	}

	public static class DoVillagerTradeHandler {
		public static void handle(final DoVillagerTradeMessage pkt, Supplier<NetworkEvent.Context> ctx) {
			ctx.get().enqueueWork(() -> {
				PlayerInventory inv = ctx.get().getSender().inventory;
				BaseVillagerTrade trade = pkt.trade;
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

			});
			ctx.get().setPacketHandled(true);
		}
	}
}
