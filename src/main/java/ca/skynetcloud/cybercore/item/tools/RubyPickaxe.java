package ca.skynetcloud.cybercore.item.tools;

import java.util.List;

import ca.skynetcloud.cybercore.CyberCoreMain;
import ca.skynetcloud.cybercore.CyberCoreTab;
import ca.skynetcloud.cybercore.init.ItemInit;
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

public class RubyPickaxe extends PickaxeItem {

	public RubyPickaxe(IItemTier material, float speed) {
		super(material, 1, speed,
				new Properties().tab(CyberCoreTab.instance).addToolType(ToolType.PICKAXE, material.getLevel()));
	}

	public RubyPickaxe(IItemTier material, float speed, Properties properties) {
		super(material, 1, speed, properties.addToolType(ToolType.PICKAXE, material.getLevel()));
	}

	@Override
	public void appendHoverText(ItemStack stack, World worldIn, List<ITextComponent> tooltip, ITooltipFlag flagIn) {

		if (stack.sameItem(new ItemStack(ItemInit.ruby_pickaxe))) {
			tooltip.add(new StringTextComponent(TextFormatting.LIGHT_PURPLE + "Seem To Be Good"));

			tooltip.add(new StringTextComponent(
					TextFormatting.GREEN + "NIGHT VISION" + " " + TextFormatting.AQUA + "24 Sec"));

			tooltip.add(new StringTextComponent(
					TextFormatting.GREEN + "HERO OF THE VILLAGE" + " " + TextFormatting.AQUA + "12 Sec"));

		}

		super.appendHoverText(stack, worldIn, tooltip, flagIn);
	}

	@Override
	public ActionResult<ItemStack> use(World worldIn, PlayerEntity playerIn, Hand handIn) {

		playerIn.getCooldowns().addCooldown(this, 600);
		if (playerIn.getItemBySlot(EquipmentSlotType.MAINHAND).getItem() == ItemInit.ruby_pickaxe.getItem()) {
			if (worldIn.isClientSide) {
				playerIn.sendMessage(new StringTextComponent(TextFormatting.GREEN + "[" + CyberCoreMain.NAME + "] "
						+ TextFormatting.RED + "This Tool Seem To Like You"), null);
			}
			playerIn.addEffect(new EffectInstance(Effects.NIGHT_VISION, 500, 1, false, false, true));
			playerIn.addEffect(new EffectInstance(Effects.HERO_OF_THE_VILLAGE, 255, 1, false, false, true));
		}

		return super.use(worldIn, playerIn, handIn);
	}

}
