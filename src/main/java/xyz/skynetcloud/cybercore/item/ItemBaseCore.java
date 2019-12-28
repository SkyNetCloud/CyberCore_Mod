package xyz.skynetcloud.cybercore.item;

import net.minecraft.item.Item;

public class ItemBaseCore extends Item {

	public ItemBaseCore(String name, Properties property) {
		super(property);
		setRegistryName(name);

	}

}
