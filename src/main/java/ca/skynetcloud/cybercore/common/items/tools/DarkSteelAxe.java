package ca.skynetcloud.cybercore.common.items.tools;


import ca.skynetcloud.cybercore.client.utilities.CyberCoreTab;
import net.minecraft.world.item.AxeItem;
import net.minecraft.world.item.Tier;


public class DarkSteelAxe extends AxeItem {

	public DarkSteelAxe(Tier material, float speed) {
		super(material, 6, speed,
				new Properties().tab(CyberCoreTab.MAIN));
	}

	public DarkSteelAxe(Tier material, float speed, Properties properties) {
		super(material, 6, speed, properties);
	}

}
