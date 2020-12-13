package ca.skynetcloud.cybercore.item.tools;

import ca.skynetcloud.cybercore.CyberCoreMain.CyberCoreTab;
import net.minecraft.item.AxeItem;
import net.minecraft.item.IItemTier;
import net.minecraftforge.common.ToolType;

public class CyberCoreAxe extends AxeItem {

	public CyberCoreAxe(IItemTier material, float speed) {
		super(material, 6, speed,
				new Properties().group(CyberCoreTab.instance).addToolType(ToolType.AXE, material.getHarvestLevel()));
	}

	public CyberCoreAxe(IItemTier material, float speed, Properties properties) {
		super(material, 6, speed, properties.addToolType(ToolType.AXE, material.getHarvestLevel()));
	}

}