package xyz.skynetcloud.cybercore.api.containers;

import static xyz.skynetcloud.cybercore.CyberCoreMain.MODID;

import net.minecraft.inventory.container.ContainerType;
import xyz.skynetcloud.cybercore.util.container.LunaGenContainer;
import xyz.skynetcloud.cybercore.util.container.PowerFurnaceContainer;
import xyz.skynetcloud.cybercore.util.container.PowerStorageContainer;

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
/*
	public static final ContainerType<CyberCoreChestContainer> IRON_CHEST = new ContainerType<>(
			CyberCoreChestContainer::createIronContainer);

	public static final ContainerType<CyberCoreChestContainer> GOLD_CHEST = new ContainerType<>(
			CyberCoreChestContainer::createGoldContainer);

	public static final ContainerType<CyberCoreChestContainer> DIAMOND_CHEST = new ContainerType<>(
			CyberCoreChestContainer::createDiamondContainer);

	public static final ContainerType<CyberCoreChestContainer> CRYSTAL_CHEST = new ContainerType<>(
			CyberCoreChestContainer::createCrystalContainer);

	public static final ContainerType<CyberCoreChestContainer> COPPER_CHEST = new ContainerType<>(
			CyberCoreChestContainer::createCopperContainer);

	public static final ContainerType<CyberCoreChestContainer> SILVER_CHEST = new ContainerType<>(
			CyberCoreChestContainer::createSilverContainer);

	public static final ContainerType<CyberCoreChestContainer> OBSIDIAN_CHEST = new ContainerType<>(
			CyberCoreChestContainer::createObsidianContainer);
*/
}
