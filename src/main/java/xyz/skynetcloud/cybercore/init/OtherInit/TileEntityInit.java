package xyz.skynetcloud.cybercore.init.OtherInit;

import net.minecraft.tileentity.TileEntityType;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.IForgeRegistry;
import xyz.skynetcloud.cybercore.CyberCoreMain;
import xyz.skynetcloud.cybercore.init.BlockInit;
import xyz.skynetcloud.cybercore.util.TE.cable.CableTileEntity;

@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD)
public class TileEntityInit {

	public static final TileEntityType<?> CABLE_TE = TileEntityType.Builder
			.create(CableTileEntity::new, BlockInit.power_cable).build(null);
	public static final TileEntityType<?> LUNARGEN_GEN_TE = TileEntityType.Builder
			.create(CableTileEntity::new, BlockInit.lunargen_block).build(null);
	public static final TileEntityType<?> POWER_BOX_TE = TileEntityType.Builder
			.create(CableTileEntity::new, BlockInit.lunargen_block).build(null);

	public static final void register(IForgeRegistry<TileEntityType<?>> registry) {
		CABLE_TE.setRegistryName("cable_te");
		LUNARGEN_GEN_TE.setRegistryName("lunarsolargenerator_block_te");
		POWER_BOX_TE.setRegistryName("power_box_te");

	}

	@SubscribeEvent
	public static void registerTileEntities(RegistryEvent.Register<TileEntityType<?>> event) {
		TileEntityInit.register(event.getRegistry());

		CyberCoreMain.LOGGER.info("Loaded TileEntity");
	}

}
