package ca.skynetcloud.cybercore.client.init.material;

import ca.skynetcloud.cybercore.client.init.CoreInit;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.util.LazyLoadedValue;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.item.ArmorMaterial;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

import java.util.function.Supplier;

public enum CustomArmorMaterial implements ArmorMaterial {

	Ruby("ruby", 5, new int[] { 1, 2, 3, 1 }, 15, SoundEvents.ARMOR_EQUIP_DIAMOND, 0.0F, 0.0F, () -> {
		return Ingredient.of(CoreInit.ItemInit.ruby_ingot);
	}), Dark_Steel("dark_steel", 15, new int[] { 1, 4, 5, 2 }, 12, SoundEvents.ARMOR_EQUIP_DIAMOND, 0.0F, 0.0F, () -> {
		return Ingredient.of(CoreInit.ItemInit.dark_steel_ingot);
	}),
	Cyber_Ingot("cyber_ingot", 15, new int[] { 10, 34, 25, 14 }, 9, SoundEvents.ARMOR_EQUIP_DIAMOND, 0.0F, 0.0F, () -> {
		return Ingredient.of(CoreInit.ItemInit.cyber_ingot);
	});

	private static final int[] MAX_DAMAGE_ARRAY = new int[] { 13, 15, 16, 11 };
	private final String name;
	private final int maxDamageFactor;
	private final int[] damageReductionAmountArray;
	private final int enchantability;
	private final SoundEvent soundEvent;
	private final float toughness;
	private final float knockback;
	private final LazyLoadedValue<Ingredient> repairMaterial;

	private CustomArmorMaterial(String nameIn, int maxDamageFactor, int[] damageReductionAmountArray,
			int enchantability, SoundEvent soundEvent, float toughness, float knockback,
			Supplier<Ingredient> repairMaterial) {
		this.name = nameIn;
		this.maxDamageFactor = maxDamageFactor;
		this.damageReductionAmountArray = damageReductionAmountArray;
		this.enchantability = enchantability;
		this.soundEvent = soundEvent;
		this.toughness = toughness;
		this.knockback = knockback;
		this.repairMaterial = new LazyLoadedValue<>(repairMaterial);
	}

	@Override
	public int getDurabilityForSlot(EquipmentSlot slotIn) {

		return MAX_DAMAGE_ARRAY[slotIn.getIndex()] * this.maxDamageFactor;
	}

	@Override
	public int getDefenseForSlot(EquipmentSlot slotIn) {

		return this.damageReductionAmountArray[slotIn.getIndex()];
	}

	@Override
	public int getEnchantmentValue() {

		return this.enchantability;
	}

	@Override
	public SoundEvent getEquipSound() {

		return this.soundEvent;
	}

	@Override
	public Ingredient getRepairIngredient() {

		return this.repairMaterial.get();
	}

	@OnlyIn(Dist.CLIENT)
	@Override
	public String getName() {

		return this.name;
	}

	@Override
	public float getToughness() {

		return this.toughness;
	}

	@Override
	public float getKnockbackResistance() {
		return this.knockback;
	}

}
