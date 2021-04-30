package ca.skynetcloud.cybercore.item;

import ca.skynetcloud.cybercore.CyberCoreMain;
import ca.skynetcloud.cybercore.api.items.ItemInit;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.Enchantments;
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

	public static boolean hasSendBoundMessage = true;
	
	public static boolean hasEffectEnable = false;
	

	public static boolean hasTakenOff = true;

	public ArmorItemBase(IArmorMaterial mat, String resString, EquipmentSlotType equipmentSlotIn,
			Properties properties) {
		super(mat, equipmentSlotIn, properties);
		this.resString = resString;
	}

	@Override
	public void onArmorTick(ItemStack stack, World world, PlayerEntity player) {
		if (world.isClientSide) {
			if (player.getItemBySlot(EquipmentSlotType.FEET).getItem() == ItemInit.CYBER_BOOTS.getItem()
					&& player.getItemBySlot(EquipmentSlotType.CHEST).getItem() == ItemInit.CYBER_CHESTPLATE.getItem()
					&& player.getItemBySlot(EquipmentSlotType.HEAD).getItem() == ItemInit.CYBER_HELMET.getItem()
					&& player.getItemBySlot(EquipmentSlotType.LEGS).getItem() == ItemInit.CYBER_LEGGINGS.getItem()) {
				hasSendBoundMessage = true;
				hasEffectEnable = true;
				player.addEffect(new EffectInstance(Effects.FIRE_RESISTANCE, 0, 1, true, true, true));
				player.addEffect(new EffectInstance(Effects.DIG_SPEED, 0, 1, true, true, true));
				player.addEffect(new EffectInstance(Effects.REGENERATION, 11, 1, true, true, true));
				player.addEffect(new EffectInstance(Effects.HERO_OF_THE_VILLAGE, 0, 1, true, true, true));
				player.addEffect(new EffectInstance(Effects.NIGHT_VISION, 0, 1, true, true, true));

			}

			if (player.getItemBySlot(EquipmentSlotType.FEET).getItem() == ItemInit.DARK_STEEL_BOOTS.getItem()
					&& player.getItemBySlot(EquipmentSlotType.CHEST).getItem() == ItemInit.DARK_STEEL_CHESTPLATE
							.getItem()
					&& player.getItemBySlot(EquipmentSlotType.HEAD).getItem() == ItemInit.DARK_STEEL_HELMET.getItem()
					&& player.getItemBySlot(EquipmentSlotType.LEGS).getItem() == ItemInit.DARK_STEEL_LEGGINGS
							.getItem()) {
				if (world.isClientSide) {

				}
				hasSendBoundMessage = true;
				hasEffectEnable = true;
				player.addEffect(new EffectInstance(Effects.WATER_BREATHING, 0, 1, true, true, true));
				player.addEffect(new EffectInstance(Effects.NIGHT_VISION, 0, 1, true, true, true));
				player.addEffect(new EffectInstance(Effects.REGENERATION, 0, 1, true, true, true));
			}

			if (player.getItemBySlot(EquipmentSlotType.FEET).getItem() == ItemInit.RUBY_BOOTS.getItem()
					&& player.getItemBySlot(EquipmentSlotType.CHEST).getItem() == ItemInit.RUBY_CHESTPLATE.getItem()
					&& player.getItemBySlot(EquipmentSlotType.HEAD).getItem() == ItemInit.RUBY_HELMET.getItem()
					&& player.getItemBySlot(EquipmentSlotType.LEGS).getItem() == ItemInit.RUBY_LEGGINGS.getItem()) {
				if (!hasSendBoundMessage) {
					player.sendMessage(new StringTextComponent(TextFormatting.GREEN + "[" + CyberCoreMain.NAME + "] "
							+ TextFormatting.RED
							+ "The Armor is Bound to it's rightful owner. While you seem to get some cool effect"),
							null);
				}
				hasSendBoundMessage = true;
				hasEffectEnable = true;
				player.addEffect(new EffectInstance(Effects.SATURATION, 0, 1, true, true, true));
				player.addEffect(new EffectInstance(Effects.DIG_SPEED, 0, 1, true, true, true));
				player.addEffect(new EffectInstance(Effects.REGENERATION, 0, 1, true, true, true));
			}
			
			
			
			if (player.inventory.armor.isEmpty() && hasEffectEnable == false) {
				
				player.removeAllEffects();
				player.sendMessage(new StringTextComponent(TextFormatting.RED + "Effect Have Gone away"), null);
			}

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
