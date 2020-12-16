package ca.skynetcloud.cybercore.api.tileentity;

import ca.skynetcloud.cybercore.CyberCoreMain;
import ca.skynetcloud.cybercore.api.blocks.BlockInit;
import ca.skynetcloud.cybercore.util.TE.techblock.PowredFurnaceTileEntity;
import net.minecraft.tileentity.TileEntityType;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.registries.ObjectHolder;

@ObjectHolder(CyberCoreMain.MODID)
public class TileEntityNames {


	public static final TileEntityType<PowredFurnaceTileEntity> POWER_FURNACE_TE = TileEntityType.Builder
			.create(PowredFurnaceTileEntity::new, BlockInit.POWER_FURNACE_BLOCK).build(null);
	

	public static void registerTileEntities(RegistryEvent.Register<TileEntityType<?>> event) {


	}

}
