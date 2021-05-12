package ca.skynetcloud.cybercore.item.tools;

import ca.skynetcloud.cybercore.CyberCoreTab;
import net.minecraft.item.AxeItem;
import net.minecraft.item.IItemTier;
import net.minecraftforge.common.ToolType;

public class CyberAxe extends AxeItem {

	public CyberAxe(IItemTier material, float speed) {
		super(material, 6, speed,
				new Properties().tab(CyberCoreTab.instance).addToolType(ToolType.AXE, material.getLevel()));
	}

	public CyberAxe(IItemTier material, float speed, Properties properties) {
		super(material, 6, speed, properties.addToolType(ToolType.AXE, material.getLevel()));
	}

}
