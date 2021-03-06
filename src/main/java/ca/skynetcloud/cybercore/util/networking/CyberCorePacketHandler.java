package ca.skynetcloud.cybercore.util.networking;

import static java.util.Optional.of;

import ca.skynetcloud.cybercore.CyberCoreMain;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.common.util.FakePlayer;
import net.minecraftforge.fml.network.NetworkDirection;
import net.minecraftforge.fml.network.NetworkRegistry;
import net.minecraftforge.fml.network.simple.SimpleChannel;

public class CyberCorePacketHandler {
	private static final String PROTOCOL_VERSION = Integer.toString(1);
	private static final SimpleChannel INSTANCE = NetworkRegistry.ChannelBuilder
			.named(new ResourceLocation(CyberCoreMain.MODID, "main_channel"))
			.clientAcceptedVersions(PROTOCOL_VERSION::equals).serverAcceptedVersions(PROTOCOL_VERSION::equals)
			.networkProtocolVersion(() -> PROTOCOL_VERSION).simpleChannel();
	static int ID = 0;

	public static void register() {
		INSTANCE.registerMessage(ID++, ButtonPressMessage.class, ButtonPressMessage::encode, ButtonPressMessage::decode,
				ButtonPressMessage::handle, of(NetworkDirection.PLAY_TO_SERVER));
	}

	public static void sendToServer(Object msg) {
		INSTANCE.sendToServer(msg);
	}

	public static void sendTo(Object msg, ServerPlayerEntity player) {
		if (!(player instanceof FakePlayer)) {
			INSTANCE.sendTo(msg, player.connection.connection, NetworkDirection.PLAY_TO_CLIENT);
		}
	}
}
