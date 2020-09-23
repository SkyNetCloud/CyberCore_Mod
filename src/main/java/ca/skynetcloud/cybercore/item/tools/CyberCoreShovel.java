package ca.skynetcloud.cybercore.item.tools;

import ca.skynetcloud.cybercore.CyberCoreMain.CyberCoreTab;
import net.minecraft.item.IItemTier;
import net.minecraft.item.ShovelItem;
import net.minecraftforge.common.ToolType;

public class CyberCoreShovel extends ShovelItem {

	public CyberCoreShovel(IItemTier material, float speed) {
		super(material, 1.5F, speed,
				new Properties().group(CyberCoreTab.instance).addToolType(ToolType.SHOVEL, material.getHarvestLevel()));
	}

	public CyberCoreShovel(IItemTier material, float speed, Properties properties) {
		super(material, 1.5F, speed, properties.addToolType(ToolType.SHOVEL, material.getHarvestLevel()));
	}
}
