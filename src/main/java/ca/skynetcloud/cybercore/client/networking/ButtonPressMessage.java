package ca.skynetcloud.cybercore.client.networking;

import java.util.function.Supplier;

import ca.skynetcloud.cybercore.client.techblock.ColorChangeBlockEntity;
import net.minecraft.core.BlockPos;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraftforge.fmllegacy.network.NetworkEvent;

public class ButtonPressMessage {

	private final int x, y, z, buttonId;

	public ButtonPressMessage(int x, int y, int z, int buttonId) {
		this.x = x;
		this.y = y;
		this.z = z;
		this.buttonId = buttonId;
	}

	public static void encode(ButtonPressMessage pkt, FriendlyByteBuf buf) {
		buf.writeInt(pkt.x);
		buf.writeInt(pkt.y);
		buf.writeInt(pkt.z);
		buf.writeInt(pkt.buttonId);
	}

	public static ButtonPressMessage decode(FriendlyByteBuf buf) {
		return new ButtonPressMessage(buf.readInt(), buf.readInt(), buf.readInt(), buf.readInt());
	}

	public static void handle(ButtonPressMessage pkt, Supplier<NetworkEvent.Context> ctx) {
		ctx.get().enqueueWork(() -> {

			ServerPlayer serverPlayer = ctx.get().getSender();
			BlockPos pos = new BlockPos(pkt.x, pkt.y, pkt.z);
			int buttonId = pkt.buttonId;

			BlockEntity te = serverPlayer.level.getBlockEntity(pos);
			if (te != null) {
				if (te instanceof ColorChangeBlockEntity) {
					((ColorChangeBlockEntity) te).setSelectedId(buttonId);
				}
			}

		});
		ctx.get().setPacketHandled(true);
	}
}
