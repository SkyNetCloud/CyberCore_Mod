package ca.skynetcloud.cybercore.util;

import java.util.Collection;

import net.minecraft.item.Item;
import net.minecraft.item.Items;
import net.minecraft.tags.ItemTags;
import net.minecraft.util.ResourceLocation;

public class TagUtils {
	public static Item getAnyTagItem(ResourceLocation res) {
		Item result = Items.AIR;
		Collection<Item> items = ItemTags.getAllTags().getTag(res).getValues();
		for (Item item : items) {
			result = item;
		}
		return result;
	}
}