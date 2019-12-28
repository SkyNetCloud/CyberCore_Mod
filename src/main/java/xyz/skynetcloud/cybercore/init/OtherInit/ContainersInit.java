package xyz.skynetcloud.cybercore.init.OtherInit;

import net.minecraft.inventory.container.ContainerType;
import net.minecraftforge.event.RegistryEvent;
import xyz.skynetcloud.cybercore.util.TE.otherclasses.ModReferences;
import xyz.skynetcloud.cybercore.util.container.LunaGenContainer;
import xyz.skynetcloud.cybercore.util.container.PowerStorageContainer;

public class ContainersInit {

	public static final ContainerType<LunaGenContainer> LUNAGEN_CON = new ContainerType<LunaGenContainer>(
			LunaGenContainer::new);
	public static final ContainerType<PowerStorageContainer> POWERSTORAGE_CON = new ContainerType<PowerStorageContainer>(
			PowerStorageContainer::new);

	public static final void registerContainers(RegistryEvent.Register<ContainerType<?>> event) {
		event.getRegistry().registerAll(LUNAGEN_CON.setRegistryName(ModReferences.LUNAGENCONTAINER),
				POWERSTORAGE_CON.setRegistryName(ModReferences.POWERSTORAGECONTAINER));

	}

}
