package ca.skynetcloud.cybercore.init;

import ca.skynetcloud.cybercore.util.container.ColorChangeContainer;
import ca.skynetcloud.cybercore.util.container.PowerCubeCon;
import ca.skynetcloud.cybercore.util.container.PowredFurnaceContainer;
import net.minecraft.world.inventory.MenuType;

public class ContainerInit {

	public static final MenuType<PowredFurnaceContainer> POWER_FURNCAE_CON = new MenuType<PowredFurnaceContainer>(
			PowredFurnaceContainer::new);

	public static final MenuType<PowerCubeCon> POWER_CUBE_CON = new MenuType<PowerCubeCon>(PowerCubeCon::new);

	public static final MenuType<ColorChangeContainer> c_changer_CON = new MenuType<ColorChangeContainer>(
			ColorChangeContainer::new);

}
