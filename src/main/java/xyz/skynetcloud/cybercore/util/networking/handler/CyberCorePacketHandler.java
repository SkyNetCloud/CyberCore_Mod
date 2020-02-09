package xyz.skynetcloud.cybercore.util.networking.handler;

import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.common.util.FakePlayer;
import net.minecraftforge.fml.network.NetworkDirection;
import net.minecraftforge.fml.network.NetworkRegistry;
import net.minecraftforge.fml.network.simple.SimpleChannel;
import xyz.skynetcloud.cybercore.CyberCoreMain;
import xyz.skynetcloud.cybercore.util.networking.handler.messages.CyberSyncTrustMessage;
import xyz.skynetcloud.cybercore.util.networking.handler.messages.DoCyberVillagerTaskMessage;
import xyz.skynetcloud.cybercore.util.networking.handler.messages.DoCyberVillagerTradeMessage;

public class CyberCorePacketHandler {

	private static final String PROTOCOL_VERSION = Integer.toString(1);
	private static final SimpleChannel INSTANCE = NetworkRegistry.ChannelBuilder
			.named(new ResourceLocation(CyberCoreMain.MODID, "main_channel"))
			.clientAcceptedVersions(PROTOCOL_VERSION::equals).serverAcceptedVersions(PROTOCOL_VERSION::equals)
			.networkProtocolVersion(() -> PROTOCOL_VERSION).simpleChannel();

	public static final void register() {
		int i = 0;
		INSTANCE.registerMessage(i++, DoCyberVillagerTradeMessage.class, DoCyberVillagerTradeMessage::encode,
				DoCyberVillagerTradeMessage::decode, DoCyberVillagerTradeMessage.DoTechVillagerTradeHandler::handle);
		INSTANCE.registerMessage(i++, DoCyberVillagerTaskMessage.class, DoCyberVillagerTaskMessage::encode,
				DoCyberVillagerTaskMessage::decode, DoCyberVillagerTaskMessage.DoTechVillagerTaskHandler::handle);
		INSTANCE.registerMessage(i++, CyberSyncTrustMessage.class, CyberSyncTrustMessage::encode,
				CyberSyncTrustMessage::decode, CyberSyncTrustMessage.SyncTrustHandler::handle);
	}

	public static void sendToServer(Object msg) {
		INSTANCE.sendToServer(msg);
	}

	public static void sendTo(Object msg, ServerPlayerEntity player) {
		if (!(player instanceof FakePlayer)) {
			INSTANCE.sendTo(msg, player.connection.netManager, NetworkDirection.PLAY_TO_CLIENT);
		}
	}

}
