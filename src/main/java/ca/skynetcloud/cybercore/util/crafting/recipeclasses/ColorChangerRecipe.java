package ca.skynetcloud.cybercore.util.crafting.recipeclasses;

import com.google.gson.JsonObject;

import ca.skynetcloud.cybercore.util.TagUtils;
import ca.skynetcloud.cybercore.util.crafting.ModedRecipeSerializers;
import ca.skynetcloud.cybercore.util.crafting.ModedRecipeTypes;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.IRecipe;
import net.minecraft.item.crafting.IRecipeSerializer;
import net.minecraft.item.crafting.IRecipeType;
import net.minecraft.network.PacketBuffer;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionUtils;
import net.minecraft.util.JSONUtils;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.ForgeRegistryEntry;

public class ColorChangerRecipe implements IRecipe<IInventory> {
	private final ResourceLocation id;
	private final ItemStack input;
	private final ItemStack output;

	public ColorChangerRecipe(ResourceLocation id, ItemStack input, ItemStack output) {
		this.id = id;
		this.input = input;
		this.output = output;
	}

	@Override
	public boolean matches(IInventory inv, World worldIn) {
		return input.getItem() == inv.getItem(0).getItem();
	}

	@Override
	public ItemStack assemble(IInventory inv) {
		return output.copy();
	}

	public ItemStack getInput() {
		return input;
	}

	@Override
	public boolean canCraftInDimensions(int width, int height) {
		return true;
	}

	@Override
	public ItemStack getResultItem() {
		return output.copy();
	}

	public int getAmountInput() {
		return input.getCount();
	}

	@Override
	public ResourceLocation getId() {
		return id;
	}

	@Override
	public IRecipeSerializer<?> getSerializer() {
		return ModedRecipeSerializers.COLORCHNAGER;
	}

	@Override
	public IRecipeType<?> getType() {
		return ModedRecipeTypes.COLORING;
	}
	public static class Serializer extends ForgeRegistryEntry<IRecipeSerializer<?>>
			implements IRecipeSerializer<ColorChangerRecipe> {
		
		// private static ResourceLocation NAME = new
		// ResourceLocation(PlantTechMain.MODID, "compressing");

		@Override
		public ColorChangerRecipe fromJson(ResourceLocation recipeId, JsonObject json) {

			JsonObject inputobject = json.getAsJsonObject("input");
			Item inputitem = null;
			Potion effect = null;
			Enchantment enchantType = null;
			int enchantLevel = 0;

			if (inputobject.has("item")) {
				inputitem = ForgeRegistries.ITEMS.getValue(new ResourceLocation(inputobject.get("item").getAsString()));
			} else if (inputobject.has("block"))// Just in case
			{
				inputitem = ForgeRegistries.ITEMS
						.getValue(new ResourceLocation(inputobject.get("block").getAsString()));
			} else if (inputobject.has("tag")) {
				inputitem = TagUtils.getAnyTagItem(new ResourceLocation(inputobject.get("tag").getAsString()));
			}
			ItemStack inputstack = null;
			if (inputitem != null) {
				inputstack = new ItemStack(inputitem, JSONUtils.getAsInt(inputobject, "amount", 1));
			}

			JsonObject resultobject = json.getAsJsonObject("result");
			Item resultitem = null;
			if (resultobject.has("item")) {
				resultitem = ForgeRegistries.ITEMS
						.getValue(new ResourceLocation(resultobject.get("item").getAsString()));

				if (resultobject.has("potion_effect")) {
					effect = ForgeRegistries.POTION_TYPES
							.getValue(new ResourceLocation(resultobject.get("potion_effect").getAsString()));
				}
				if (resultobject.has("enchantment")) {
					JsonObject enchantment = resultobject.getAsJsonObject("enchantment");
					enchantType = ForgeRegistries.ENCHANTMENTS
							.getValue(new ResourceLocation(enchantment.get("type").getAsString()));
					enchantLevel = JSONUtils.getAsInt(enchantment, "level", 1);
				}

			} else if (resultobject.has("block"))// Just in case
			{
				resultitem = ForgeRegistries.ITEMS
						.getValue(new ResourceLocation(resultobject.get("block").getAsString()));
			} else if (resultobject.has("tag")) {
				resultitem = TagUtils.getAnyTagItem(new ResourceLocation(resultobject.get("tag").getAsString()));
			}

			ItemStack resultstack = null;
			if (resultitem != null) {
				resultstack = new ItemStack(resultitem, JSONUtils.getAsInt(resultobject, "amount", 1));
				if (effect != null) {
					PotionUtils.setPotion(resultstack, effect);
				} else if (enchantType != null) {
					resultstack.enchant(enchantType, enchantLevel);
				}
			}

			if (inputstack != null && resultstack != null) {
				// System.out.println(recipeId);
				return new ColorChangerRecipe(recipeId, inputstack, resultstack);
			} else {
				throw new IllegalStateException("Item did not exist:" + recipeId.toString());
			}
		}

		@Override
		public ColorChangerRecipe fromNetwork(ResourceLocation recipeId, PacketBuffer buffer) {
			ItemStack input = buffer.readItem();
			ItemStack result = buffer.readItem();
			return new ColorChangerRecipe(recipeId, input, result);
		}

		@Override
		public void toNetwork(PacketBuffer buffer, ColorChangerRecipe recipe) {
			buffer.writeItem(recipe.input);
			buffer.writeItem(recipe.output);
		}



	}

}
