package ca.skynetcloud.cybercore.common.items.tools;

import ca.skynetcloud.cybercore.client.utilities.CyberCoreTab;
import net.minecraft.world.item.AxeItem;
import net.minecraft.world.item.Tier;

public class RubyAxe extends AxeItem {

	public RubyAxe(Tier material, float speed) {
		super(material, 6, speed,
				new Properties().tab(CyberCoreTab.MAIN));
	}

	public RubyAxe(Tier material, float speed, Properties properties) {
		super(material, 6, speed, properties);
	}

}
