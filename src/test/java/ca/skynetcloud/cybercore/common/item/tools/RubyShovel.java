package ca.skynetcloud.cybercore.common.item.tools;

import ca.skynetcloud.cybercore.CyberCoreTab;
import net.minecraft.world.item.ShovelItem;
import net.minecraft.world.item.Tier;
import net.minecraftforge.common.ToolType;

public class RubyShovel extends ShovelItem {

	public RubyShovel(Tier material, float speed) {
		super(material, 1.5F, speed,
				new Properties().tab(CyberCoreTab.instance).addToolType(ToolType.SHOVEL, material.getLevel()));
	}

	public RubyShovel(Tier material, float speed, Properties properties) {
		super(material, 1.5F, speed, properties.addToolType(ToolType.SHOVEL, material.getLevel()));
	}
}
