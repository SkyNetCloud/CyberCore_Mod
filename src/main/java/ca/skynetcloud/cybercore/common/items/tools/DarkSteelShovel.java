package ca.skynetcloud.cybercore.common.items.tools;


import ca.skynetcloud.cybercore.client.utilities.CyberCoreTab;
import net.minecraft.world.item.ShovelItem;
import net.minecraft.world.item.Tier;


public class DarkSteelShovel extends ShovelItem {

	public DarkSteelShovel(Tier material, float speed) {
		super(material, 1.5F, speed,
				new Properties().tab(CyberCoreTab.MAIN));
	}

	public DarkSteelShovel(Tier material, float speed, Properties properties) {
		super(material, 1.5F, speed, properties);
	}
}
