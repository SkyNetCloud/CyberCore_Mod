package ca.skynetcloud.cybercore.client.world.item.tools;

import net.minecraft.world.item.HoeItem;
import net.minecraft.world.item.Tier;

public class DarkSteelHoe extends HoeItem {


	private static final float speed = 0.4f;

	public DarkSteelHoe(Tier itemTier, int attackDamage, Properties properties) {
		super(itemTier, attackDamage, speed, properties);
		
	}


}
