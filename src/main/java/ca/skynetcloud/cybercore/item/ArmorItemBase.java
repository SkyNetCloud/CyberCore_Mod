package ca.skynetcloud.cybercore.item;

import ca.skynetcloud.cybercore.CyberCoreMain;
import ca.skynetcloud.cybercore.api.items.ItemInit;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.ArmorItem;
import net.minecraft.item.IArmorMaterial;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.Effects;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.World;

public class ArmorItemBase extends ArmorItem {
	private String resString;

	public static boolean hasSendBoundMessage = false;
	
	public static boolean hasTakenOff = false;

	public ArmorItemBase(IArmorMaterial mat, String resString, EquipmentSlotType equipmentSlotIn,
			Properties properties) {
		super(mat, equipmentSlotIn, properties);
		this.resString = resString;
	}

	@Override
	public void onArmorTick(ItemStack stack, World world, PlayerEntity player) {

		if (player.getItemStackFromSlot(EquipmentSlotType.FEET).getItem() == ItemInit.CYBER_BOOTS.getItem()
				&& player.getItemStackFromSlot(EquipmentSlotType.CHEST).getItem() == ItemInit.CYBER_CHESTPLATE.getItem()
				&& player.getItemStackFromSlot(EquipmentSlotType.HEAD).getItem() == ItemInit.CYBER_HELMET.getItem()
				&& player.getItemStackFromSlot(EquipmentSlotType.LEGS).getItem() == ItemInit.CYBER_LEGGINGS.getItem()) {
			player.addPotionEffect(new EffectInstance(Effects.FIRE_RESISTANCE, 255, 1, false, false, true));
			player.addPotionEffect(new EffectInstance(Effects.HASTE, 255, 1, false, false, true));
			player.addPotionEffect(new EffectInstance(Effects.REGENERATION, 11, 1, false, false, true));
			player.addPotionEffect(new EffectInstance(Effects.HERO_OF_THE_VILLAGE, 255, 1, false, false, true));
			player.addPotionEffect(new EffectInstance(Effects.NIGHT_VISION, 255, 1, false, false, true));
		
		} else {
			if (player.inventory.armorInventory != null) {
				player.clearActivePotions();
				
			}
		}
		

		if (player.getItemStackFromSlot(EquipmentSlotType.FEET).getItem() == ItemInit.DARK_STEEL_BOOTS.getItem()
				&& player.getItemStackFromSlot(EquipmentSlotType.CHEST).getItem() == ItemInit.DARK_STEEL_CHESTPLATE
						.getItem()
				&& player.getItemStackFromSlot(EquipmentSlotType.HEAD).getItem() == ItemInit.DARK_STEEL_HELMET.getItem()
				&& player.getItemStackFromSlot(EquipmentSlotType.LEGS).getItem() == ItemInit.DARK_STEEL_LEGGINGS
						.getItem()) {
			if (world.isRemote) {

			}
			player.addPotionEffect(new EffectInstance(Effects.WATER_BREATHING, 200000, 1, false, false, false));
			player.addPotionEffect(new EffectInstance(Effects.NIGHT_VISION, 200000, 1, false, false, false));
			player.addPotionEffect(new EffectInstance(Effects.REGENERATION, 200000, 1, false, false, false));
		} else if (player.inventory.armorInventory != null) {

			player.clearActivePotions();

		}

		if (player.getItemStackFromSlot(EquipmentSlotType.FEET).getItem() == ItemInit.RUBY_BOOTS.getItem()
				&& player.getItemStackFromSlot(EquipmentSlotType.CHEST).getItem() == ItemInit.RUBY_CHESTPLATE.getItem()
				&& player.getItemStackFromSlot(EquipmentSlotType.HEAD).getItem() == ItemInit.RUBY_HELMET.getItem()
				&& player.getItemStackFromSlot(EquipmentSlotType.LEGS).getItem() == ItemInit.RUBY_LEGGINGS.getItem()) {
			if (!hasSendBoundMessage) {
				player.sendMessage(
						new StringTextComponent(TextFormatting.GREEN + "[" + CyberCoreMain.NAME + "] "
								+ TextFormatting.RED
								+ "The Armor is Bound to it's rightful owner. While you seem to get some cool effect"),
						null);
			}
			hasSendBoundMessage = true;
			player.addPotionEffect(new EffectInstance(Effects.SATURATION, 200000, 1, false, false, false));
			player.addPotionEffect(new EffectInstance(Effects.HASTE, 200000, 1, false, false, false));
			player.addPotionEffect(new EffectInstance(Effects.REGENERATION, 200000, 1, false, false, false));
		} else if (player.inventory.armorInventory != null) {

			player.clearActivePotions();

		}

	}

	@Override
	public String getArmorTexture(ItemStack stack, Entity entity, EquipmentSlotType slot, String type) {
		if (slot == EquipmentSlotType.HEAD || slot == EquipmentSlotType.CHEST || slot == EquipmentSlotType.FEET) {
			return "cybercore:textures/models/armor/" + resString + "_layer_1.png";
		} else if (slot == EquipmentSlotType.LEGS) {
			return "cybercore:textures/models/armor/" + resString + "_layer_2.png";
		} else {
			return null;
		}
	}

}
