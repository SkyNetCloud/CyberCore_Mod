package ca.skynetcloud.cybercore.item.tools;

import java.util.List;

import ca.skynetcloud.cybercore.CyberCoreClient.CyberCoreTab;
import ca.skynetcloud.cybercore.CyberCoreMain;
import ca.skynetcloud.cybercore.api.items.ItemInit;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.IItemTier;
import net.minecraft.item.ItemStack;
import net.minecraft.item.PickaxeItem;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.Effects;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.World;
import net.minecraftforge.common.ToolType;

public class CyberCorePickaxe extends PickaxeItem {

	public CyberCorePickaxe(IItemTier material, float speed) {
		super(material, 1, speed, new Properties().group(CyberCoreTab.instance).addToolType(ToolType.PICKAXE,
				material.getHarvestLevel()));
	}

	public CyberCorePickaxe(IItemTier material, float speed, Properties properties) {
		super(material, 1, speed, properties.addToolType(ToolType.PICKAXE, material.getHarvestLevel()));
	}

	@Override
	public void addInformation(ItemStack stack, World worldIn, List<ITextComponent> tooltip, ITooltipFlag flagIn) {

		if (stack.isItemEqual(new ItemStack(ItemInit.cyber_pickaxe))) {
			tooltip.add(new StringTextComponent("Hmm someone is watching you"));

			tooltip.add(new StringTextComponent(
					TextFormatting.GREEN + "NIGHT VISION" + " " + TextFormatting.AQUA + "24 Sec"));

			tooltip.add(new StringTextComponent(TextFormatting.GREEN + "HASTE" + " " + TextFormatting.AQUA + "12 Sec"));

		}

		if (stack.isItemEqual(new ItemStack(ItemInit.ruby_pickaxe))) {
			tooltip.add(new StringTextComponent(TextFormatting.LIGHT_PURPLE + "Seem To Be Good"));

			tooltip.add(new StringTextComponent(
					TextFormatting.GREEN + "NIGHT VISION" + " " + TextFormatting.AQUA + "24 Sec"));

			tooltip.add(new StringTextComponent(
					TextFormatting.GREEN + "HERO OF THE VILLAGE" + " " + TextFormatting.AQUA + "12 Sec"));

		}

		if (stack.isItemEqual(new ItemStack(ItemInit.dark_steel_pickaxe))) {
			tooltip.add(new StringTextComponent(
					TextFormatting.DARK_PURPLE + "A shadow loom over you while you hold this tool"));

			tooltip.add(
					new StringTextComponent(TextFormatting.RED + "BLINDNESS" + " " + TextFormatting.AQUA + "12 Sec"));

			tooltip.add(new StringTextComponent(
					TextFormatting.GREEN + "HEALTH BOOST" + " " + TextFormatting.AQUA + "12 Sec"));

			tooltip.add(
					new StringTextComponent(TextFormatting.RED + "SLOWNESS" + " " + TextFormatting.AQUA + "12 Sec"));
		}

		super.addInformation(stack, worldIn, tooltip, flagIn);
	}

	@Override
	public ActionResult<ItemStack> onItemRightClick(World worldIn, PlayerEntity playerIn, Hand handIn) {

		if (playerIn.getItemStackFromSlot(EquipmentSlotType.MAINHAND).getItem() == ItemInit.cyber_pickaxe.getItem()) {
			if (worldIn.isRemote) {
				playerIn.sendMessage(new StringTextComponent(TextFormatting.GREEN + "[" + CyberCoreMain.NAME + "] "
						+ TextFormatting.RED + "Hmm What Did It added to you"), null);
			}

			playerIn.addPotionEffect(new EffectInstance(Effects.NIGHT_VISION, 500, 1, false, false, true));
			playerIn.addPotionEffect(new EffectInstance(Effects.HASTE, 500, 1, false, false, true));
		}

		if (playerIn.getItemStackFromSlot(EquipmentSlotType.MAINHAND).getItem() == ItemInit.ruby_pickaxe.getItem()) {
			if (worldIn.isRemote) {
				playerIn.sendMessage(new StringTextComponent(TextFormatting.GREEN + "[" + CyberCoreMain.NAME + "] "
						+ TextFormatting.RED + "This Tool Seem To Like You"), null);
			}
			playerIn.addPotionEffect(new EffectInstance(Effects.NIGHT_VISION, 500, 1, false, false, true));
			playerIn.addPotionEffect(new EffectInstance(Effects.HERO_OF_THE_VILLAGE, 255, 1, false, false, true));
		}

		if (playerIn.getItemStackFromSlot(EquipmentSlotType.MAINHAND).getItem() == ItemInit.dark_steel_pickaxe
				.getItem()) {
			if (worldIn.isRemote) {
				playerIn.sendMessage(new StringTextComponent(TextFormatting.GREEN + "[" + CyberCoreMain.NAME + "] "
						+ TextFormatting.RED + "The Shadow Are Coming For You"), null);
			}
			playerIn.addPotionEffect(new EffectInstance(Effects.SLOWNESS, 255, 1, false, false, true));
			playerIn.addPotionEffect(new EffectInstance(Effects.REGENERATION, 255, 1, false, false, true));
			playerIn.addPotionEffect(new EffectInstance(Effects.BLINDNESS, 255, 1, false, false, true));
		}

		return super.onItemRightClick(worldIn, playerIn, handIn);
	}

}
