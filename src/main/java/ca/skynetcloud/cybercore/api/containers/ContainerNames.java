package ca.skynetcloud.cybercore.api.containers;

import static ca.skynetcloud.cybercore.CyberCoreMain.MODID;

import ca.skynetcloud.cybercore.util.container.LunaGenContainer;
import ca.skynetcloud.cybercore.util.container.PainterContainer;
import ca.skynetcloud.cybercore.util.container.PowerFurnaceContainer;
import ca.skynetcloud.cybercore.util.container.PowerStorageContainer;
import net.minecraft.inventory.container.ContainerType;

public class ContainerNames {

	public static class ContainerNameInit {
		public static final String LUNARGEN_CON = (MODID + ":lunar_gen_machine_con");
		public static final String POWER_BOX_CON = (MODID + ":power_box_con");
	}

	public static final ContainerType<LunaGenContainer> LUNARGEN_CON = new ContainerType<LunaGenContainer>(
			LunaGenContainer::new);
	public static final ContainerType<PowerStorageContainer> POWER_BOX_CON = new ContainerType<PowerStorageContainer>(
			PowerStorageContainer::new);

	public static final ContainerType<PainterContainer> CABLE_PAINTER_CON = new ContainerType<PainterContainer>(
			PainterContainer::new);

	public static final ContainerType<PowerFurnaceContainer> POWER_FURNCAE_CON = new ContainerType<PowerFurnaceContainer>(
			PowerFurnaceContainer::new);

}
