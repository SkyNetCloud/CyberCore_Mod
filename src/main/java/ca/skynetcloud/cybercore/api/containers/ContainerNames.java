package ca.skynetcloud.cybercore.api.containers;

import ca.skynetcloud.cybercore.util.container.ColorChangeContainer;
import ca.skynetcloud.cybercore.util.container.PowerCubeCon;
import ca.skynetcloud.cybercore.util.container.PowredFurnaceContainer;
import net.minecraft.inventory.container.ContainerType;

public class ContainerNames {

	public static final ContainerType<PowredFurnaceContainer> POWER_FURNCAE_CON = new ContainerType<PowredFurnaceContainer>(
			PowredFurnaceContainer::new);

	public static final ContainerType<PowerCubeCon> POWER_CUBE_CON = new ContainerType<PowerCubeCon>(PowerCubeCon::new);

	public static final ContainerType<ColorChangeContainer> c_changer_CON = new ContainerType<ColorChangeContainer>(
			ColorChangeContainer::new);
}
