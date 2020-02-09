package xyz.skynetcloud.cybercore.api.containers;

import static xyz.skynetcloud.cybercore.CyberCoreMain.MODID;

import net.minecraft.inventory.container.ContainerType;
import net.minecraftforge.fml.network.IContainerFactory;
import xyz.skynetcloud.cybercore.util.container.LunaGenContainer;
import xyz.skynetcloud.cybercore.util.container.PowerFurnaceContainer;
import xyz.skynetcloud.cybercore.util.container.PowerStorageContainer;
import xyz.skynetcloud.cybercore.util.container.VillagerContainer;

public class ContainerNames {

	public static class ContainerNameInit {
		public static final String LUNARGEN_CON = (MODID + ":lunar_gen_machine_con");
		public static final String POWER_BOX_CON = (MODID + ":power_box_con");
	}

	public static final ContainerType<LunaGenContainer> LUNARGEN_CON = new ContainerType<LunaGenContainer>(
			LunaGenContainer::new);
	public static final ContainerType<PowerStorageContainer> POWER_BOX_CON = new ContainerType<PowerStorageContainer>(
			PowerStorageContainer::new);

	public static final ContainerType<PowerFurnaceContainer> POWER_FURNCAE_CON = new ContainerType<PowerFurnaceContainer>(
			PowerFurnaceContainer::new);

	public static final ContainerType<VillagerContainer> VILLAGER_CON = new ContainerType<VillagerContainer>(
			(IContainerFactory<VillagerContainer>) VillagerContainer::new);

}
