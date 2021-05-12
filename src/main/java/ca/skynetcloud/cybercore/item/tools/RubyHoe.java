package ca.skynetcloud.cybercore.item.tools;

import net.minecraft.item.HoeItem;
import net.minecraft.item.IItemTier;

public class RubyHoe extends HoeItem {


	private static final float speed = 0.4f;

	public RubyHoe(IItemTier itemTier, int attackDamage, Properties properties) {
		super(itemTier, attackDamage, speed, properties);
		
	}


}
