package xyz.skynetcloud.cybercore;

import java.util.UUID;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.RenderTypeLookup;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.Biomes;
import net.minecraft.world.gen.GenerationStage;
import net.minecraft.world.gen.feature.ConfiguredFeature;
import net.minecraft.world.gen.feature.structure.Structure;
import net.minecraft.world.gen.placement.IPlacementConfig;
import net.minecraft.world.gen.placement.Placement;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.entity.player.PlayerEvent.PlayerLoggedInEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.DistExecutor;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.config.ModConfig;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.fml.loading.FMLPaths;
import net.minecraftforge.items.ItemHandlerHelper;
import xyz.skynetcloud.cybercore.api.Names;
import xyz.skynetcloud.cybercore.api.blocks.BlockInit;
import xyz.skynetcloud.cybercore.api.items.ItemInit;
import xyz.skynetcloud.cybercore.event.ModClientEvent;
import xyz.skynetcloud.cybercore.event.ModSoulBoundEvent;
import xyz.skynetcloud.cybercore.handlers.CapabilityHandler;
import xyz.skynetcloud.cybercore.init.FeaturesInit;
import xyz.skynetcloud.cybercore.init.InitStructurePieceType;
import xyz.skynetcloud.cybercore.init.RendererInit;
import xyz.skynetcloud.cybercore.init.ScreenInit;
import xyz.skynetcloud.cybercore.packets.CyberCorePacketHandler;
import xyz.skynetcloud.cybercore.util.networking.config.ConfigLoadder;
import xyz.skynetcloud.cybercore.util.networking.config.CyberCoreConfig;
import xyz.skynetcloud.cybercore.world.gen.OreGen;
import xyz.skynetcloud.cybercore.world.gen.feature.LabConfig;
import xyz.skynetcloud.cybercore.world.gen.feature.structure.Lab;

@Mod("cybercore")
public class CyberCoreMain {

	public static final String NAME = Names.NAME;
	public static final String MODID = Names.MODID;
	public static final Logger LOGGER = LogManager.getLogger();

	public CyberCoreMain() {
		ModLoadingContext.get().registerConfig(ModConfig.Type.SERVER, ConfigLoadder.COMMON, Names.Server_CONFIG);
		ModLoadingContext.get().registerConfig(ModConfig.Type.CLIENT, ConfigLoadder.CLIENT, Names.Client_CONFIG);

		ConfigLoadder.loadConfig(ConfigLoadder.CLIENT,
				FMLPaths.CONFIGDIR.get().resolve(Names.Client_CONFIG).toString());
		ConfigLoadder.loadConfig(ConfigLoadder.COMMON,
				FMLPaths.CONFIGDIR.get().resolve(Names.Server_CONFIG).toString());

		IEventBus modBus = FMLJavaModLoadingContext.get().getModEventBus();
		modBus.addListener(this::setup);

		MinecraftForge.EVENT_BUS.register(ModSoulBoundEvent.DEATH_INSTANCE);
		MinecraftForge.EVENT_BUS.addListener(this::entityJoinWorld);

		FeaturesInit.REGISTER.register(modBus);

		InitStructurePieceType.init();

		MinecraftForge.EVENT_BUS.addListener(this::cpotd);
		DistExecutor.runWhenOn(Dist.CLIENT, () -> () -> {
			MinecraftForge.EVENT_BUS.register(ModClientEvent.INSTANCE);
			modBus.addListener(this::doClientStuff);
		});
	}

	private void setup(FMLCommonSetupEvent event) {

		CapabilityHandler.registerAll();
		CyberCorePacketHandler.register();
		this.addLab(Biomes.BIRCH_FOREST, Lab.Lab_Build_ONE);
		this.addLab(Biomes.PLAINS, Lab.Lab_Build_ONE);
	}

	private void doClientStuff(final FMLClientSetupEvent event) {

		RendererInit.registerEntityRenderer();
		ScreenInit.registerGUI();
		OreGen.setupOreGeneration();
		DistExecutor.runWhenOn(Dist.CLIENT, () -> () -> {
			RenderTypeLookup.setRenderLayer(BlockInit.LETTUCE_CROP, RenderType.func_228643_e_());
			RenderTypeLookup.setRenderLayer(BlockInit.TOMATO_CROP, RenderType.func_228643_e_());
		});

	}

	public static class CyberCoreTab extends ItemGroup {

		public static final CyberCoreTab instance = new CyberCoreTab(ItemGroup.GROUPS.length, Names.CyberTAB);

		private CyberCoreTab(int index, String label) {
			super(index, label);
		}

		@Override
		public ItemStack createIcon() {

			return new ItemStack(ItemInit.power_box);
		}
	}

	private void entityJoinWorld(PlayerLoggedInEvent event) {

		PlayerEntity e = event.getPlayer();
		if (!CyberCoreConfig.GivenOnFirstJoin.get() == true) {

			ItemHandlerHelper.giveItemToPlayer(e, new ItemStack(ItemInit.lettuce_seed));
			ItemHandlerHelper.giveItemToPlayer(e, new ItemStack(ItemInit.tomato_seed));

			event.getPlayer()
					.sendMessage(new StringTextComponent(TextFormatting.BLUE + "[" + TextFormatting.WHITE + Names.INFO
							+ TextFormatting.BLUE + "] " + TextFormatting.WHITE
							+ "You will be given Lettuce Seeds and Tomato Seeds. Everytime you log into this world"));

		} else if (!CyberCoreConfig.GivenOnFirstJoin.get() == false) {

			event.getPlayer()
					.sendMessage(new StringTextComponent(
							TextFormatting.RED + "[" + TextFormatting.WHITE + Names.INFO + TextFormatting.RED + "] "
									+ TextFormatting.WHITE + "Login Item Has Been Disable in config"));

		}

	}

	private void addLab(Biome biome, ResourceLocation templateLocation) {
		ConfiguredFeature<LabConfig, ? extends Structure<LabConfig>> lootableLabFeature = FeaturesInit.Lab
				.get().func_225566_b_(new LabConfig(CyberCoreConfig.LabGenerateChance.get(), templateLocation));
		biome.func_226711_a_(lootableLabFeature);
		biome.addFeature(GenerationStage.Decoration.SURFACE_STRUCTURES,
				lootableLabFeature.func_227228_a_(Placement.NOPE.func_227446_a_(IPlacementConfig.NO_PLACEMENT_CONFIG)));
	}

	private void cpotd(PlayerLoggedInEvent event) {
		if (event.getEntity() instanceof PlayerEntity && UUID.fromString("1f8f49b7-bca5-4bff-a2ca-588e7f330465")
				.equals(((PlayerEntity) event.getEntity()).getUniqueID())) {
			event.getPlayer().sendMessage(new StringTextComponent(TextFormatting.RED + "[" + TextFormatting.WHITE
					+ "Thank You" + TextFormatting.RED + "] " + TextFormatting.WHITE + "I implemented your idea"));
			event.getPlayer().addItemStackToInventory(new ItemStack(ItemInit.cyber_bits, 5));
			event.getPlayer().addItemStackToInventory(new ItemStack(ItemInit.cyber_blend, 5));

		}
	}

}
