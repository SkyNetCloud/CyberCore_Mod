package ca.skynetcloud.cybercore.common.item.tools;

import net.minecraft.world.item.HoeItem;
import net.minecraft.world.item.Tier;

public class CyberHoe extends HoeItem {

	private static final float speed = 0.4f;

	public CyberHoe(Tier itemTier, int attackDamage, Properties properties) {
		super(itemTier, attackDamage, speed, properties);

	}

}
