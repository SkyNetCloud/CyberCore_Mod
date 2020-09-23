package ca.skynetcloud.cybercore;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import ca.skynetcloud.cybercore.api.Names;
import ca.skynetcloud.cybercore.api.blocks.BlockInit;
import ca.skynetcloud.cybercore.api.items.ItemInit;
import ca.skynetcloud.cybercore.event.ModClientEvent;
import ca.skynetcloud.cybercore.init.FeaturesInit;
import ca.skynetcloud.cybercore.init.InitStructurePieceType;
import ca.skynetcloud.cybercore.init.RendererInit;
import ca.skynetcloud.cybercore.init.ScreenInit;
import ca.skynetcloud.cybercore.packets.CyberCorePacketHandler;
import ca.skynetcloud.cybercore.recipes.CyberRecipeTypes;
import ca.skynetcloud.cybercore.util.networking.config.ConfigLoadder;
import ca.skynetcloud.cybercore.util.networking.config.CyberCoreConfig;
import ca.skynetcloud.cybercore.world.gen.OreGen;
import ca.skynetcloud.cybercore.world.gen.feature.LabConfig;
import ca.skynetcloud.cybercore.world.gen.feature.structure.Lab;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.RenderTypeLookup;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.Biomes;
import net.minecraft.world.gen.GenerationStage;
import net.minecraft.world.gen.feature.ConfiguredFeature;
import net.minecraft.world.gen.feature.structure.Structure;
import net.minecraft.world.gen.placement.IPlacementConfig;
import net.minecraft.world.gen.placement.Placement;
import net.minecraftforge.client.model.obj.OBJLoader;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.config.ModConfig;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.fml.loading.FMLPaths;
import net.minecraftforge.fml.network.NetworkRegistry;
import net.minecraftforge.fml.network.simple.SimpleChannel;

@Mod("cybercore")
public class CyberCoreMain {
	private static final String PROTOCOL_VERSION = "1";
	public static final SimpleChannel PACKET_HANDLER = NetworkRegistry.newSimpleChannel(
			new ResourceLocation("cybercore", "cybercore"), () -> PROTOCOL_VERSION, PROTOCOL_VERSION::equals,
			PROTOCOL_VERSION::equals);
	public static final String NAME = Names.NAME;
	public static final String MODID = Names.MODID;
	public static final Logger LOGGER = LogManager.getLogger();

	public CyberCoreMain() {

		IEventBus modBus = FMLJavaModLoadingContext.get().getModEventBus();
		modBus.addListener(this::setup);

		FeaturesInit.REGISTER.register(modBus);

		InitStructurePieceType.init();

		ModLoadingContext.get().registerConfig(ModConfig.Type.SERVER, ConfigLoadder.COMMON, Names.Server_CONFIG);
		ConfigLoadder.loadConfig(ConfigLoadder.COMMON,
				FMLPaths.CONFIGDIR.get().resolve(Names.Server_CONFIG).toString());

		MinecraftForge.EVENT_BUS.register(ModClientEvent.INSTANCE);
		modBus.addListener(this::doClientStuff);

		ModLoadingContext.get().registerConfig(ModConfig.Type.CLIENT, ConfigLoadder.CLIENT, Names.Client_CONFIG);
		ConfigLoadder.loadConfig(ConfigLoadder.CLIENT,
				FMLPaths.CONFIGDIR.get().resolve(Names.Client_CONFIG).toString());

	}

	private void setup(FMLCommonSetupEvent event) {

		new CyberRecipeTypes();
		this.addLab(Biomes.FOREST, Lab.Lab_Oak);
		this.addLab(Biomes.DESERT, Lab.Lab_Desert);
		this.addLab(Biomes.DARK_FOREST, Lab.Lab_Dark_Oak);
		CyberCorePacketHandler.register();

	}

	private void doClientStuff(final FMLClientSetupEvent event) {

		RendererInit.registerEntityRenderer();
		ScreenInit.registerGUI();
		OreGen.setupOreGeneration();

		RenderTypeLookup.setRenderLayer(BlockInit.POWER_BOX, RenderType.getCutoutMipped());
		RenderTypeLookup.setRenderLayer(BlockInit.LETTUCE_CROP, RenderType.getCutout());
		RenderTypeLookup.setRenderLayer(BlockInit.TOMATO_CROP, RenderType.getCutout());

	}

	public static class CyberCoreTab extends ItemGroup {

		public static final CyberCoreTab instance = new CyberCoreTab(ItemGroup.GROUPS.length, Names.CyberTAB);

		private CyberCoreTab(int index, String label) {
			super(index, label);
		}

		@Override
		public ItemStack createIcon() {

			return new ItemStack(ItemInit.cyber_ingot);
		}
	}

	private void addLab(Biome biome, ResourceLocation templateLocation) {
		ConfiguredFeature<LabConfig, ? extends Structure<LabConfig>> lootableLabFeature = FeaturesInit.Lab.get()
				.withConfiguration(new LabConfig(CyberCoreConfig.LabGenerateChance.get(), templateLocation));
		biome.addStructure(lootableLabFeature);
		biome.addFeature(GenerationStage.Decoration.SURFACE_STRUCTURES,
				lootableLabFeature.withPlacement(Placement.NOPE.configure(IPlacementConfig.NO_PLACEMENT_CONFIG)));
	}

}
