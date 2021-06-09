package ca.skynetcloud.cybercore.event;

import ca.skynetcloud.cybercore.CyberCoreMain;
import ca.skynetcloud.cybercore.CyberCoreTab;
import ca.skynetcloud.cybercore.entites.hostile.RobotEnemy;
import ca.skynetcloud.cybercore.init.BlockInit;
import ca.skynetcloud.cybercore.init.ContainerInit;
import ca.skynetcloud.cybercore.init.EntityInit;
import ca.skynetcloud.cybercore.init.ItemInit;
import ca.skynetcloud.cybercore.init.TileEntityInit;
import ca.skynetcloud.cybercore.util.crafting.ModedRecipeSerializers;
import ca.skynetcloud.cybercore.util.networking.helper.Names;
import ca.skynetcloud.cybercore.world.biome.DecayedBiome;
import net.fabricmc.api.ModInitializer;
import net.minecraft.block.Block;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.ai.attributes.GlobalEntityTypeAttributes;
import net.minecraft.inventory.container.ContainerType;
import net.minecraft.item.Item;
import net.minecraft.item.SpawnEggItem;
import net.minecraft.item.crafting.IRecipeSerializer;
import net.minecraft.sound.SoundEvent;
import net.minecraft.tileentity.TileEntityType;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.biome.Biome;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;

public class ModRegistryEvent implements ModInitializer {

	@Override
	public void onInitialize() {

	}

}