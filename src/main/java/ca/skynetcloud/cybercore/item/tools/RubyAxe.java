package ca.skynetcloud.cybercore.item.tools;

import ca.skynetcloud.cybercore.CyberCoreTab;
import net.minecraft.world.item.AxeItem;
import net.minecraft.world.item.Tier;
import net.minecraftforge.common.ToolType;

public class RubyAxe extends AxeItem {

	public RubyAxe(Tier material, float speed) {
		super(material, 6, speed,
				new Properties().tab(CyberCoreTab.instance).addToolType(ToolType.AXE, material.getLevel()));
	}

	public RubyAxe(Tier material, float speed, Properties properties) {
		super(material, 6, speed, properties.addToolType(ToolType.AXE, material.getLevel()));
	}

}
