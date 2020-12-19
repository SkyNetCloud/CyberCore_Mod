package ca.skynetcloud.cybercore.util.networking;

import java.util.function.Supplier;

import ca.skynetcloud.cybercore.util.TE.techblock.ColorChangeTileEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.network.PacketBuffer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraftforge.fml.network.NetworkEvent;

public class ButtonPressMessage {

	private final int x, y, z, buttonId;

	public ButtonPressMessage(int x, int y, int z, int buttonId) {
		this.x = x;
		this.y = y;
		this.z = z;
		this.buttonId = buttonId;
	}

	public static void encode(ButtonPressMessage pkt, PacketBuffer buf) {
		buf.writeInt(pkt.x);
		buf.writeInt(pkt.y);
		buf.writeInt(pkt.z);
		buf.writeInt(pkt.buttonId);
	}

	public static ButtonPressMessage decode(PacketBuffer buf) {
		return new ButtonPressMessage(buf.readInt(), buf.readInt(), buf.readInt(), buf.readInt());
	}

	public static void handle(ButtonPressMessage pkt, Supplier<NetworkEvent.Context> ctx) {
		ctx.get().enqueueWork(() -> {

			ServerPlayerEntity serverPlayer = ctx.get().getSender();
			BlockPos pos = new BlockPos(pkt.x, pkt.y, pkt.z);
			int buttonId = pkt.buttonId;

			TileEntity te = serverPlayer.world.getTileEntity(pos);
			if (te != null) {
				if (te instanceof ColorChangeTileEntity) {
					((ColorChangeTileEntity) te).setSelectedId(buttonId);
				}
			}

		});
		ctx.get().setPacketHandled(true);
	}
}
