package ca.skynetcloud.cybercore.item.tools;

import ca.skynetcloud.cybercore.CyberCoreTab;
import net.minecraft.item.IItemTier;
import net.minecraft.item.ShovelItem;
import net.minecraftforge.common.ToolType;

public class CyberShovel extends ShovelItem {

	public CyberShovel(IItemTier material, float speed) {
		super(material, 1.5F, speed,
				new Properties().tab(CyberCoreTab.instance).addToolType(ToolType.SHOVEL, material.getLevel()));
	}

	public CyberShovel(IItemTier material, float speed, Properties properties) {
		super(material, 1.5F, speed, properties.addToolType(ToolType.SHOVEL, material.getLevel()));
	}
}
