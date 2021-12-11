package ca.skynetcloud.cybercore.common.items.tools;

import net.minecraft.world.item.HoeItem;
import net.minecraft.world.item.Tier;

public class RubyHoe extends HoeItem {


	private static final float speed = 0.4f;

	public RubyHoe(Tier itemTier, int attackDamage, Properties properties) {
		super(itemTier, attackDamage, speed, properties);
		
	}


}
