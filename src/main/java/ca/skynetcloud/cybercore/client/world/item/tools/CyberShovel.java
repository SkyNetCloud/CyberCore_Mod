package ca.skynetcloud.cybercore.client.world.item.tools;


import ca.skynetcloud.cybercore.client.utilities.CyberCoreTab;
import net.minecraft.world.item.ShovelItem;
import net.minecraft.world.item.Tier;

import static ca.skynetcloud.cybercore.client.utilities.CyberCoreTab.MAIN_TAB;

public class CyberShovel extends ShovelItem {

	public CyberShovel(Tier material, float speed) {
		super(material, 1.5F, speed,
				new Properties().tab(MAIN_TAB));
	}

	public CyberShovel(Tier material, float speed, Properties properties) {
		super(material, 1.5F, speed, properties);
	}
}
