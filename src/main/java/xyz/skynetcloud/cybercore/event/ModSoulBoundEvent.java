package xyz.skynetcloud.cybercore.event;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.item.ItemEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraftforge.event.entity.living.LivingDeathEvent;
import net.minecraftforge.event.entity.living.LivingDropsEvent;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;

public class ModSoulBoundEvent {

	public static final ModSoulBoundEvent DEATH_INSTANCE = new ModSoulBoundEvent();

	private Map<String, ItemStack[]> itemsToRestore = new HashMap<String, ItemStack[]>();

	@SubscribeEvent
	public void death(LivingDeathEvent event) {

	}

	@SubscribeEvent
	public void respawn(PlayerEvent.Clone event) {
		PlayerEntity player = event.getPlayer();
		if (event.isWasDeath() && itemsToRestore.containsKey(player.getUniqueID().toString())) {
			ItemStack[] itemsPerPlayer = itemsToRestore.get(player.getUniqueID().toString());
			System.arraycopy(itemsPerPlayer, player.inventory.armorInventory.size(), player.inventory.mainInventory, 0,
					player.inventory.mainInventory.size());
			System.arraycopy(itemsPerPlayer, 0, player.inventory.armorInventory, 0,
					player.inventory.armorInventory.size());
			itemsToRestore.remove(player.getUniqueID().toString());

		}
	}

	@SubscribeEvent
	public <itemsToRestore> void drop(LivingDropsEvent event) {
		LivingEntity player = event.getEntityLiving();
		if (itemsToRestore.containsKey(player.getUniqueID().toString())) {
			final List<ItemStack> listPerPlayer = Arrays.asList(itemsToRestore.get(player.getUniqueID().toString()));
			Stream<ItemEntity> stream = StreamSupport.stream(event.getDrops().spliterator(), false);
			Set<ItemEntity> itemsToRemove = stream
					.filter(itemToFilter -> listPerPlayer.contains(itemToFilter.getItem())).collect(Collectors.toSet());
			event.getDrops().removeAll(itemsToRemove);
		}

	}

}
