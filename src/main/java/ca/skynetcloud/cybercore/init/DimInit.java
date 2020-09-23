package ca.skynetcloud.cybercore.init;

import java.lang.reflect.Field;
import java.util.function.BiFunction;
import java.util.function.IntSupplier;

import org.apache.commons.lang3.tuple.Pair;

import com.google.common.collect.ImmutableList;

import ca.skynetcloud.cybercore.CyberCoreMain;
import ca.skynetcloud.cybercore.util.networking.util.RegUtil;
import ca.skynetcloud.cybercore.world.dim.CyberDimesion;
import net.minecraft.network.PacketBuffer;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;
import net.minecraft.world.dimension.Dimension;
import net.minecraft.world.dimension.DimensionType;
import net.minecraftforge.common.DimensionManager;
import net.minecraftforge.common.ModDimension;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.event.world.RegisterDimensionsEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.network.FMLHandshakeMessages;
import net.minecraftforge.fml.network.FMLNetworkConstants;
import net.minecraftforge.fml.network.simple.SimpleChannel;
import net.minecraftforge.registries.ForgeRegistries;

@SuppressWarnings("deprecation")
@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD)
public class DimInit {

	public static final ModDimension CYBERLAND = new ModDimension() {
		@Override
		public BiFunction<World, DimensionType, ? extends Dimension> getFactory() {
			return CyberDimesion::new;
		}
	};

	public static final ResourceLocation CYBERLAND_ID = new ResourceLocation(CyberCoreMain.MODID, "cyberland");

	// registers the dimension
	@Mod.EventBusSubscriber(modid = CyberCoreMain.MODID)
	private static class ForgeEvents {
		@SubscribeEvent
		public static void registerDimensions(RegisterDimensionsEvent event) {
			if (DimensionType.byName(CYBERLAND_ID) == null) {
				DimensionManager.registerDimension(CYBERLAND_ID, CYBERLAND, null, true);
			}
		}
	}

	@SubscribeEvent
	public static void registerModDimensions(RegistryEvent.Register<ModDimension> event) {
		RegUtil.generic(event.getRegistry()).add("cyberland", CYBERLAND);
	}

	static {

		try {
			Field channelField = FMLNetworkConstants.class.getDeclaredField("handshakeChannel");
			channelField.setAccessible(true);

			SimpleChannel handshakeChannel = (SimpleChannel) channelField.get(null);
			handshakeChannel.messageBuilder(S2CDimensionSync.class, 100)
					.loginIndex(S2CDimensionSync::getLoginIndex, S2CDimensionSync::setLoginIndex)
					.decoder(S2CDimensionSync::decode).encoder(S2CDimensionSync::encode)
					.buildLoginPacketList(isLocal -> {
						if (isLocal)
							return ImmutableList.of();
						return ImmutableList
								.of(Pair.of("Ultra Amplified Dim Sync", new S2CDimensionSync(DimInit.cyberland())));
					}).consumer((msg, ctx) -> {
						if (DimensionManager.getRegistry().getByValue(msg.id) == null) {
							DimensionManager.registerDimensionInternal(msg.id, msg.name, msg.dimension, null, true);
						}
						ctx.get().setPacketHandled(true);
						handshakeChannel.reply(new FMLHandshakeMessages.C2SAcknowledge(), ctx.get());
					}).add();
		} catch (ReflectiveOperationException e) {
			CyberCoreMain.LOGGER.error("Failed to add dimension sync to handshake channel", e);
		}
	}

	public static DimensionType cyberland() {
		return DimensionType.byName(CYBERLAND_ID);
	}

	public static class S2CDimensionSync implements IntSupplier {
		final int id;
		final ResourceLocation name;
		final ModDimension dimension;

		private int loginIndex;

		public S2CDimensionSync(DimensionType dimensionType) {
			this.id = dimensionType.getId() + 1;
			this.name = DimensionType.getKey(dimensionType);
			this.dimension = dimensionType.getModType();

		}

		S2CDimensionSync(int id, ResourceLocation name, ModDimension dimension, boolean skyLight) {
			this.id = id;
			this.name = name;
			this.dimension = dimension;

		}

		void setLoginIndex(final int loginIndex) {
			this.loginIndex = loginIndex;
		}

		int getLoginIndex() {
			return this.loginIndex;
		}

		void encode(PacketBuffer buffer) {
			buffer.writeInt(this.id);
			buffer.writeResourceLocation(this.name);
			buffer.writeResourceLocation(this.dimension.getRegistryName());
		}

		public static S2CDimensionSync decode(PacketBuffer buffer) {
			int id = buffer.readInt();
			ResourceLocation name = buffer.readResourceLocation();
			ModDimension dimension = ForgeRegistries.MOD_DIMENSIONS.getValue(buffer.readResourceLocation());

			return new S2CDimensionSync(id, name, dimension, true);
		}

		@Override
		public int getAsInt() {
			return this.getLoginIndex();
		}
	}
}
