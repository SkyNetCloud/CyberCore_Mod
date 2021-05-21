package ca.skynetcloud.cybercore.item;

import net.minecraft.entity.Entity;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.ArmorItem;
import net.minecraft.item.IArmorMaterial;
import net.minecraft.item.ItemStack;

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
