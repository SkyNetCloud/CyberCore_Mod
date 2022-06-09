package ca.skynetcloud.cybercore.client.world.item.armor;

import ca.skynetcloud.cybercore.client.init.MainInit;
import ca.skynetcloud.cybercore.client.world.item.ArmorItemBase;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ArmorMaterial;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;

public class DarkSteelArmor extends ArmorItemBase {

	public DarkSteelArmor(ArmorMaterial mat, String resString, EquipmentSlot equipmentSlotIn, Properties properties) {
		super(mat, resString, equipmentSlotIn, properties);
	}

	@Override
	public void onArmorTick(ItemStack stack, Level world, Player player) {
		if (player.getItemBySlot(EquipmentSlot.HEAD).getItem() == MainInit.DARK_STEEL_HELMET.get().asItem()
				&& player.getItemBySlot(EquipmentSlot.CHEST).getItem() == MainInit.DARK_STEEL_CHESTPLATE.get().asItem()
				&& player.getItemBySlot(EquipmentSlot.LEGS).getItem() == MainInit.DARK_STEEL_LEGGINGS.get().asItem()
				&& player.getItemBySlot(EquipmentSlot.FEET).getItem() == MainInit.DARK_STEEL_BOOTS.get().asItem()) {
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
				((LivingEntity) player).removeEffect(MobEffects.NIGHT_VISION);
			}
			if (player instanceof LivingEntity) {
				((LivingEntity) player).removeEffect(MobEffects.DIG_SPEED);
			}
			if (player instanceof LivingEntity) {
				((LivingEntity) player).removeEffect(MobEffects.DIG_SPEED);
			}

		}
		super.onArmorTick(stack, world, player);
	}

}
