package xyz.skynetcloud.cybercore.entities.tradesandjobs;

import java.util.ArrayList;
import java.util.List;

import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;

public class BaseVillagerTradePools {
	private static List<BaseVillagerTradePool> TradePool;

	public static List<BaseVillagerTradePool> getSCIENTISTS() {
		if (TradePool == null) {
			initScientists();
		}
		return TradePool;
	}

	private static void initScientists() {

		new BaseVillagerTradePool("Sell apples",
				generateStackList(new ItemStackWithRandomSize(new ItemStack(Items.APPLE), 5, 10)),
				generateStackList(new ItemStackWithRandomSize(new ItemStack(Items.ACACIA_SLAB), 5, 10)), 100, 0);
		new BaseVillagerTradePool("Sell logs",
				generateStackList(new ItemStackWithRandomSize(new ItemStack(Items.ACACIA_LOG), 5, 10)),
				generateStackList(new ItemStackWithRandomSize(new ItemStack(Items.CROSSBOW), 5, 10)), 200, 0);

		new BaseVillagerTradePool("Buy saplings",
				generateStackList(new ItemStackWithRandomSize(new ItemStack(Items.ACACIA_SAPLING), 5, 10)),
				generateStackList(new ItemStackWithRandomSize(new ItemStack(Items.BLUE_SHULKER_BOX), 5, 10)), 0, 300);

		new BaseVillagerTradePool("Buy wood",
				generateStackList(new ItemStackWithRandomSize(new ItemStack(Items.BIRCH_WOOD), 5, 10)),
				generateStackList(new ItemStackWithRandomSize(new ItemStack(Items.BLACK_WOOL), 5, 10)), 0, 400);

		new BaseVillagerTradePool("Sell bookshell",
				generateStackList(new ItemStackWithRandomSize(new ItemStack(Items.BOOKSHELF), 5, 10)),
				generateStackList(new ItemStackWithRandomSize(new ItemStack(Items.BIRCH_STAIRS), 5, 10)), 500, 0);

		new BaseVillagerTradePool("Sell bow",
				generateStackList(new ItemStackWithRandomSize(new ItemStack(Items.BOW), 5, 10)),
				generateStackList(new ItemStackWithRandomSize(new ItemStack(Items.BLACK_STAINED_GLASS_PANE), 5, 10)),
				600, 0);

		new BaseVillagerTradePool("Buy carpet",
				generateStackList(new ItemStackWithRandomSize(new ItemStack(Items.BLUE_CARPET), 5, 10)),
				generateStackList(new ItemStackWithRandomSize(new ItemStack(Items.BARREL), 5, 10)), 0, 700);

		new BaseVillagerTradePool("Buy dye",
				generateStackList(new ItemStackWithRandomSize(new ItemStack(Items.BLACK_DYE), 5, 10)),
				generateStackList(new ItemStackWithRandomSize(new ItemStack(Items.ENDER_CHEST), 5, 10)), 0, 800);

		new BaseVillagerTradePool("Sell sign",
				generateStackList(new ItemStackWithRandomSize(new ItemStack(Items.BIRCH_SIGN), 5, 10)),
				generateStackList(new ItemStackWithRandomSize(new ItemStack(Items.DRAGON_HEAD), 5, 10)), 900, 0);

		new BaseVillagerTradePool("Sell brick",
				generateStackList(new ItemStackWithRandomSize(new ItemStack(Items.BRICK), 5, 10)),
				generateStackList(new ItemStackWithRandomSize(new ItemStack(Items.BRICKS), 5, 10)), 1000, 0);

		new BaseVillagerTradePool("Buy blaze rods",
				generateStackList(new ItemStackWithRandomSize(new ItemStack(Items.BLAZE_ROD), 5, 10)),
				generateStackList(new ItemStackWithRandomSize(new ItemStack(Items.BIRCH_WOOD), 5, 10)), 0, 1100);

		new BaseVillagerTradePool("Buy chest",
				generateStackList(new ItemStackWithRandomSize(new ItemStack(Items.CHEST), 5, 10)),
				generateStackList(new ItemStackWithRandomSize(new ItemStack(Items.LIGHT_GRAY_BED), 5, 10)), 0, 1200);
	}

	private static List<ItemStackWithRandomSize> generateStackList(ItemStackWithRandomSize... stacks) {
		List<ItemStackWithRandomSize> list = new ArrayList<ItemStackWithRandomSize>();
		for (int i = 0; i < stacks.length; i++) {
			list.add(stacks[i]);
		}
		return list;
	}
}
