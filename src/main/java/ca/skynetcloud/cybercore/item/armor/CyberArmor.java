package ca.skynetcloud.cybercore.item.armor;

import ca.skynetcloud.cybercore.init.ItemInit;
import ca.skynetcloud.cybercore.item.ArmorItemBase;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.IArmorMaterial;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.Effects;
import net.minecraft.world.World;

public class CyberArmor extends ArmorItemBase {

	public CyberArmor(IArmorMaterial mat, String resString, EquipmentSlotType equipmentSlotIn, Properties properties) {
		super(mat, resString, equipmentSlotIn, properties);

	}

	@Override
	public void onArmorTick(ItemStack stack, World world, PlayerEntity player) {

		if (player.getItemBySlot(EquipmentSlotType.HEAD).getItem() == ItemInit.CYBER_HELMET.getItem()
				&& player.getItemBySlot(EquipmentSlotType.CHEST).getItem() == ItemInit.CYBER_CHESTPLATE.getItem()
				&& player.getItemBySlot(EquipmentSlotType.LEGS).getItem() == ItemInit.CYBER_LEGGINGS.getItem()
				&& player.getItemBySlot(EquipmentSlotType.FEET).getItem() == ItemInit.CYBER_BOOTS.getItem()) {
			hasSendBoundMessage = true;
			hasEffectEnable = true;
			if (player instanceof LivingEntity)
				((LivingEntity) player)
						.addEffect(new EffectInstance(Effects.WATER_BREATHING, (int) 2000, (int) 1, (false), (false)));
			if (player instanceof LivingEntity)
				((LivingEntity) player)
						.addEffect(new EffectInstance(Effects.NIGHT_VISION, (int) 2000, (int) 1, (false), (false)));
			if (player instanceof LivingEntity)
				((LivingEntity) player)
						.addEffect(new EffectInstance(Effects.REGENERATION, (int) 2000, (int) 1, (false), (false)));

		} else {
			if (player instanceof LivingEntity) {
				((LivingEntity) player).removeEffect(Effects.WATER_BREATHING);
			}
			if (player instanceof LivingEntity) {
				((LivingEntity) player).removeEffect(Effects.NIGHT_VISION);
			}
			if (player instanceof LivingEntity) {
				((LivingEntity) player).removeEffect(Effects.REGENERATION);
			}

		}
	}

}
