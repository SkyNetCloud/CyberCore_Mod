package ca.skynetcloud.cybercore.packets;

import ca.skynetcloud.cybercore.CyberCoreMain;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.common.util.FakePlayer;
import net.minecraftforge.fml.network.NetworkDirection;
import net.minecraftforge.fml.network.NetworkRegistry;
import net.minecraftforge.fml.network.simple.SimpleChannel;

public class CyberCorePacketHandler {
	private static final String PROTOCOL_VERSION = Integer.toString(1);
	public static final SimpleChannel INSTANCE = NetworkRegistry.ChannelBuilder
			.named(new ResourceLocation(CyberCoreMain.MODID, "main_channel"))
			.networkProtocolVersion(() -> PROTOCOL_VERSION).clientAcceptedVersions(PROTOCOL_VERSION::equals)
			.serverAcceptedVersions(PROTOCOL_VERSION::equals).simpleChannel();

	public static final void register() {

		INSTANCE.registerMessage(0, ButtonPressMessage.class, ButtonPressMessage::encode, ButtonPressMessage::decode,
				ButtonPressMessage::handle);
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
