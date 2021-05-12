package ca.skynetcloud.cybercore.item;

import ca.skynetcloud.cybercore.CyberCoreMain;
import ca.skynetcloud.cybercore.init.ItemInit;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.Enchantments;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.ArmorItem;
import net.minecraft.item.IArmorMaterial;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
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
