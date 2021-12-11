package ca.skynetcloud.cybercore.common.items.armor;


import ca.skynetcloud.cybercore.client.init.ItemInit;
import ca.skynetcloud.cybercore.common.items.ArmorItemBase;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ArmorMaterial;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;

public class RubyArmor extends ArmorItemBase {

	public RubyArmor(ArmorMaterial mat, String resString, EquipmentSlot equipmentSlotIn, Properties properties) {
		super(mat, resString, equipmentSlotIn, properties);
	}

	@Override
	public void onArmorTick(ItemStack stack, Level world, Player player) {

		if (player.getItemBySlot(EquipmentSlot.HEAD).getItem() == ItemInit.RUBY_HELMET.get().asItem()
				&& player.getItemBySlot(EquipmentSlot.CHEST).getItem() == ItemInit.RUBY_CHESTPLATE.get().asItem()
				&& player.getItemBySlot(EquipmentSlot.LEGS).getItem() == ItemInit.RUBY_LEGGINGS.get().asItem()
				&& player.getItemBySlot(EquipmentSlot.FEET).getItem() == ItemInit.RUBY_BOOTS.get().asItem()) {
			hasSendBoundMessage = true;
			hasEffectEnable = true;
			if (player instanceof LivingEntity)
				((LivingEntity) player)
						.addEffect(new MobEffectInstance(MobEffects.NIGHT_VISION, (int) 2000, (int) 1, (false), (false)));
			if (player instanceof LivingEntity)
				((LivingEntity) player)
						.addEffect(new MobEffectInstance(MobEffects.DIG_SPEED, (int) 2000, (int) 1, (false), (false)));
			if (player instanceof LivingEntity)
				((LivingEntity) player)
						.addEffect(new MobEffectInstance(MobEffects.LUCK, (int) 2000, (int) 1, (false), (false)));

		} else {
			if (player instanceof LivingEntity) {
				((LivingEntity) player).removeEffect(MobEffects.NIGHT_VISION);
			}
			if (player instanceof LivingEntity) {
				((LivingEntity) player).removeEffect(MobEffects.DIG_SPEED);
			}
			if (player instanceof LivingEntity) {
				((LivingEntity) player).removeEffect(MobEffects.LUCK);
			}

		}
		super.onArmorTick(stack, world, player);
	}

}
