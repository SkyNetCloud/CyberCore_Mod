package xyz.skynetcloud.cybercore.item.tools;

import net.minecraft.item.HoeItem;
import net.minecraft.item.IItemTier;

public class CyberCoreHoe extends HoeItem {

	private static final float speed = 0.4f;

	public CyberCoreHoe(IItemTier tier, Properties builder) {
		super(tier, speed, builder);

	}

}
