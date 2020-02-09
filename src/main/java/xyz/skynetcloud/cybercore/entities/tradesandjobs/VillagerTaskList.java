package xyz.skynetcloud.cybercore.entities.tradesandjobs;

import java.util.ArrayList;
import java.util.List;

import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import xyz.skynetcloud.cybercore.entities.passive.VillagerEntity;

public class VillagerTaskList {

	private static List<List<VillagerTask>> lists;

	public static int maxid = 0;

	public static List<VillagerTask> getTaskList(int i) {
		if (lists == null) {
			initLists();
		}
		return lists.get(i);
	}

	public static void initLists() {
		lists = new ArrayList<List<VillagerTask>>();
		int id = 0;
		List<VillagerTask> listScientists = new ArrayList<VillagerTask>();
		listScientists.add(new VillagerTask(id++, "Get lunch", VillagerEntity.SCIENTIST,
				generateStackList(new ItemStack(Items.APPLE, 1), new ItemStack(Items.POTATO, 1),
						new ItemStack(Items.CARROT, 1)),
				10, 0, 1));
		listScientists.add(new VillagerTask(id++, "Kill 5 ghasts", VillagerEntity.SCIENTIST,
				generateStackList(new ItemStack(Items.GHAST_TEAR, 5)), 100, 1, 2));
		listScientists.add(new VillagerTask(id++, "DIAMONDS", VillagerEntity.SCIENTIST,
				generateStackList(new ItemStack(Items.DIAMOND, 64)), 1000, 2, 5));

		lists.add(listScientists);
		maxid = id;

	}

	public static VillagerTask getByID(int id) {
		if (lists == null) {
			initLists();
		}

		for (List<VillagerTask> list : lists) {
			for (VillagerTask task : list) {
				if (id == task.getID()) {
					return task;
				}
			}
		}
		return null;

	}

	private static List<ItemStack> generateStackList(ItemStack... stacks) {
		List<ItemStack> list = new ArrayList<ItemStack>();
		for (int i = 0; i < stacks.length; i++) {
			list.add(stacks[i]);
		}
		return list;
	}
}
