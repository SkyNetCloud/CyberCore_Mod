package ca.skynetcloud.cybercore.api.containers;

import ca.skynetcloud.cybercore.util.container.PowredFurnaceContainer;
import net.minecraft.inventory.container.ContainerType;

public class ContainerNames {

	public static final ContainerType<PowredFurnaceContainer> POWER_FURNCAE_CON = new ContainerType<PowredFurnaceContainer>(
			PowredFurnaceContainer::new);

}
