package xyz.skynetcloud.cybercore.init;

import java.util.function.BiFunction;

import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;
import net.minecraft.world.dimension.Dimension;
import net.minecraft.world.dimension.DimensionType;
import net.minecraftforge.common.DimensionManager;
import net.minecraftforge.common.ModDimension;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.IForgeRegistry;
import xyz.skynetcloud.cybercore.CyberCoreMain;
import xyz.skynetcloud.cybercore.world.dim.CyberDimesion;

@SuppressWarnings("deprecation")
@Mod.EventBusSubscriber(modid = CyberCoreMain.MODID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class DimInit {

	public static final ModDimension CYBERLAND = new ModDimension() {
		@Override
		public BiFunction<World, DimensionType, ? extends Dimension> getFactory() {
			return CyberDimesion::new;
		}
	};

	private static final ResourceLocation CYBERLAND_ID = new ResourceLocation(CyberCoreMain.MODID, "ultraamplified");

	private static class ForgeEvents {

		public static void registerDimensions(IForgeRegistry<ModDimension> iForgeRegistry) {
			if (DimensionType.byName(CYBERLAND_ID) == null) {
				DimensionManager.registerDimension(CYBERLAND_ID, CYBERLAND, null, true);
			}
		}
	}

	@SubscribeEvent
	public static void registerModDimensions(RegistryEvent.Register<ModDimension> event) {
		ForgeEvents.registerDimensions(event.getRegistry());
	}

}
