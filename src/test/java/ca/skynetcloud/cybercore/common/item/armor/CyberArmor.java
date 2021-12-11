package ca.skynetcloud.cybercore.common.item.armor;

import ca.skynetcloud.cybercore.client.init.CoreInit;
import ca.skynetcloud.cybercore.common.item.ArmorItemBase;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ArmorMaterial;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;

public class CyberArmor extends ArmorItemBase {

	public CyberArmor(ArmorMaterial mat, String resString, EquipmentSlot equipmentSlotIn, Properties properties) {
		super(mat, resString, equipmentSlotIn, properties);

	}

	@Override
	public void onArmorTick(ItemStack stack, Level world, Player player) {

		if (player.getItemBySlot(EquipmentSlot.HEAD).getItem() == CoreInit.ItemInit.CYBER_HELMET.asItem()
				&& player.getItemBySlot(EquipmentSlot.CHEST).getItem() == CoreInit.ItemInit.CYBER_CHESTPLATE.asItem()
				&& player.getItemBySlot(EquipmentSlot.LEGS).getItem() == CoreInit.ItemInit.CYBER_LEGGINGS.asItem()
				&& player.getItemBySlot(EquipmentSlot.FEET).getItem() == CoreInit.ItemInit.CYBER_BOOTS.asItem()) {
			hasSendBoundMessage = true;
			hasEffectEnable = true;
			if (player instanceof LivingEntity)
				((LivingEntity) player).addEffect(
						new MobEffectInstance(MobEffects.WATER_BREATHING, (int) 2000, (int) 1, (false), (false)));
			if (player instanceof LivingEntity)
				((LivingEntity) player).addEffect(
						new MobEffectInstance(MobEffects.NIGHT_VISION, (int) 2000, (int) 1, (false), (false)));
			if (player instanceof LivingEntity)
				((LivingEntity) player).addEffect(
						new MobEffectInstance(MobEffects.REGENERATION, (int) 2000, (int) 1, (false), (false)));

		} else {
			if (player instanceof LivingEntity) {
				((LivingEntity) player).removeEffect(MobEffects.WATER_BREATHING);
			}
			if (player instanceof LivingEntity) {
				((LivingEntity) player).removeEffect(MobEffects.NIGHT_VISION);
			}
			if (player instanceof LivingEntity) {
				((LivingEntity) player).removeEffect(MobEffects.REGENERATION);
			}

		}
	}

}
