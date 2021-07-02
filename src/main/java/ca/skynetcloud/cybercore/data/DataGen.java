package ca.skynetcloud.cybercore.data;

import ca.skynetcloud.cybercore.CyberCoreMain;
import net.minecraft.data.DataGenerator;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.GatherDataEvent;

@Mod.EventBusSubscriber(modid = CyberCoreMain.MODID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class DataGen {

	@SubscribeEvent
	public static void gatherData(GatherDataEvent event) {
		DataGenerator gen = event.getGenerator();
		//ExistingFileHelper existingFileHelper = event.getExistingFileHelper();
		if (event.includeServer()) {
			gen.addProvider(new LootTables(gen));
		}
	}

}
