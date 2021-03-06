package ca.skynetcloud.cybercore.util.networking.handler;

import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Nullable;

import com.google.common.collect.Lists;

import ca.skynetcloud.cybercore.init.ItemInit;
import ca.skynetcloud.cybercore.util.networking.config.CyberConfig;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.item.ItemEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.stats.Stats;

public class SoulBoundHandler {
	private static final HashMap<PlayerEntity, SoulBoundHandler> handlerMap = new HashMap<>();
	public static final String soulboundTag = "SoulboundItems";
	public static final String storedStacksTag = "StoredStacks";
	public static final String soundTag = "playBrokeSound";
	public static final String stackTag = "Stack";
	private final PlayerEntity player;

	public static SoulBoundHandler getOrCreateSoulboundHandler(PlayerEntity player) {
		if (getSoulboundHandler(player) != null)
			return getSoulboundHandler(player);
		else
			return createSoulboundHandler(player);
	}

	@Nullable
	public static SoulBoundHandler getSoulboundHandler(PlayerEntity player) {
		return handlerMap.get(player);
	}

	public static SoulBoundHandler createSoulboundHandler(PlayerEntity player) {
		SoulBoundHandler newHandler = new SoulBoundHandler(player);
		handlerMap.put(player, newHandler);
		return newHandler;
	}

	public static boolean hasStoredDrops(PlayerEntity player) {
		return hasSerializedDrops(player);
	}

	private SoulBoundHandler(PlayerEntity playerIn) {
		this.player = playerIn;
	}

	public void retainDrops(Collection<ItemEntity> eventDrops) {
		List<ItemEntity> retainedDrops = Lists.newArrayList();
		for (ItemEntity eventDrop : eventDrops) {
			ItemStack item = eventDrop.getItem();
			if (item.isEnchanted() && EnchantmentHelper.getEnchantments(item).containsKey(ItemInit.Soul_Bound)) {
				int level = EnchantmentHelper.getItemEnchantmentLevel(ItemInit.Soul_Bound, item);
				double chance = 5 + (10 * (level - 1));
				double rng = Math.random();
				if (rng < chance) {
					retainedDrops.add(eventDrop);
				}
			}
		}

		retainedDrops.forEach(dropItem -> {
			eventDrops.remove(dropItem);
		});

		this.serializeDrops(retainedDrops);
	}

	private void serializeDrops(Collection<ItemEntity> drops) {
		CompoundNBT soulData = new CompoundNBT();
		soulData.putInt(storedStacksTag, drops.size());
		int counter = 0;

		for (ItemEntity drop : drops) {
			ItemStack stack = this.itemEditor(drop.getItem()).copy();
			if (stack != null) {
				CompoundNBT serializedStack = stack.serializeNBT();
				soulData.put(stackTag + counter, serializedStack);
				counter++;
			}
		}

		this.player.getPersistentData().put(soulboundTag, soulData);
	}

	private static boolean hasSerializedDrops(PlayerEntity player) {
		return player.getPersistentData().contains(soulboundTag);
	}

	private List<ItemStack> deserializeDrops() {
		List<ItemStack> deserialized = Lists.newArrayList();
		CompoundNBT soulData = this.player.getPersistentData().getCompound(soulboundTag);
		int counter = soulData.getInt(storedStacksTag) - 1;

		for (int c = counter; c >= 0; c--) {
			CompoundNBT nbt = soulData.getCompound(stackTag + c);
			ItemStack stack = ItemStack.of(nbt);

			if (!stack.isEmpty()) {
				deserialized.add(stack);
			}

			soulData.remove(stackTag + c);
		}

		this.player.getPersistentData().remove(soulboundTag);
		return deserialized;
	}

	private ItemStack itemEditor(ItemStack item) {
		int level = EnchantmentHelper.getItemEnchantmentLevel(ItemInit.Soul_Bound, item);
		if (CyberConfig.Config.durabilityDrop.get()) {
			double minimum = CyberConfig.Config.minimumDurabilityDrop.get()
					- (CyberConfig.Config.additiveDurabilityDrop.get() * (level - 1));
			if (minimum < 0) {
				minimum = 0;
			}
			double maximum = CyberConfig.Config.maximumDurabilityDrop.get()
					- (CyberConfig.Config.additiveDurabilityDrop.get() * (level - 1));
			if (maximum < 0) {
				maximum = 0;
			}
			double mode = CyberConfig.Config.modeDurabilityDrop.get()
					- (CyberConfig.Config.additiveDurabilityDrop.get() * (level - 1));
			if (mode < 0) {
				mode = 0;
			}

			int newDurability = (int) (item.getMaxDamage() * this.triangularDistribution(minimum, maximum, mode));
			if (item.hurt(newDurability, this.player.getRandom(), (ServerPlayerEntity) this.player)) {
				if (this.player instanceof PlayerEntity) {
					this.player.awardStat(Stats.ITEM_BROKEN.get(item.getItem()));
				}

				if (CyberConfig.Config.breakItemOnZeroDurability.get()) {
					item.setDamageValue(item.getMaxDamage());
					return item;
				}
				item.setDamageValue(item.getMaxDamage() - 1);
			}

		}
		double chance = 10 - (20 * (level - 1));
		if (!(Math.random() < chance))
			return item;
		if (level > 1) {
			Map<Enchantment, Integer> enchantments = EnchantmentHelper.getEnchantments(item);
			enchantments.put(ItemInit.Soul_Bound, level - 1);
			EnchantmentHelper.setEnchantments(enchantments, item);
		} else {
			Map<Enchantment, Integer> enchantments = EnchantmentHelper.getEnchantments(item);
			EnchantmentHelper.setEnchantments(enchantments, item);
		}
		return item;
	}

	public double triangularDistribution(double a, double b, double c) {
		double F = (c - a) / (b - a);
		double rand = Math.random();
		if (rand < F)
			return a + Math.sqrt(rand * (b - a) * (c - a));
		else
			return b - Math.sqrt((1 - rand) * (b - a) * (b - c));
	}

	public void transferItems(PlayerEntity rebornPlayer) {
		List<ItemStack> retainedDrops = this.deserializeDrops();

		if (retainedDrops.isEmpty())
			return;
		for (ItemStack item : retainedDrops) {
			rebornPlayer.inventory.add(item);
		}

		handlerMap.remove(this.player);
	}
}
