package ca.skynetcloud.cybercore.item.tools;

import ca.skynetcloud.cybercore.CyberCoreMain.CyberCoreTab;
import net.minecraft.item.IItemTier;
import net.minecraft.item.SwordItem;

public class CyberCoreSword extends SwordItem {

	public CyberCoreSword(IItemTier material, float speed) {
		super(material, 3, speed, new Properties().group(CyberCoreTab.instance));
	}

	public CyberCoreSword(IItemTier material, float speed, Properties properties) {
		super(material, 3, speed, properties);
	}

}
