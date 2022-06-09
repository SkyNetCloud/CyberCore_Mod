package ca.skynetcloud.cybercore.client.world.item.tools;


import ca.skynetcloud.cybercore.client.utilities.CyberCoreTab;
import net.minecraft.world.item.AxeItem;
import net.minecraft.world.item.Tier;

import static ca.skynetcloud.cybercore.client.utilities.CyberCoreTab.MAIN_TAB;


public class DarkSteelAxe extends AxeItem {

	public DarkSteelAxe(Tier material, float speed) {
		super(material, 6, speed,
				new Properties().tab(MAIN_TAB));
	}

	public DarkSteelAxe(Tier material, float speed, Properties properties) {
		super(material, 6, speed, properties);
	}

}
