package xyz.skynetcloud.cybercore.util.networking.handler.messages;

import java.util.function.Supplier;

import net.minecraft.client.Minecraft;
import net.minecraft.network.PacketBuffer;
import net.minecraftforge.fml.network.NetworkEvent;
import xyz.skynetcloud.cybercore.entities.capabilities.villagertrust.IVillagerTrust;
import xyz.skynetcloud.cybercore.entities.capabilities.villagertrust.VillagerTrust;

public class CyberSyncTrustMessage {

	private String profession;
	private int value;

	public CyberSyncTrustMessage(String profession, int value)
	{
		this.profession = profession; 
		this.value = value;
	}

	public static void encode(CyberSyncTrustMessage pkt, PacketBuffer buf) {
		buf.writeString(pkt.profession);
		buf.writeInt(pkt.value);
	}

	public static CyberSyncTrustMessage decode(PacketBuffer buf) {
		return new CyberSyncTrustMessage(buf.readString(), buf.readInt());
	}

	public static class SyncTrustHandler {
		public static void handle(final CyberSyncTrustMessage pkt, Supplier<NetworkEvent.Context> ctx) {
			ctx.get().enqueueWork(() -> {
				IVillagerTrust trust = Minecraft.getInstance().player.getCapability(VillagerTrust.INSTANCE)
						.orElse(null);
				if (trust != null) {
					trust.setTrust(pkt.profession, pkt.value);
				}
			});
			ctx.get().setPacketHandled(true);
		}
	}

}
