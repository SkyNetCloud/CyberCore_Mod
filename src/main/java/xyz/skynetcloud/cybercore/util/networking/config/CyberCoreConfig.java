package xyz.skynetcloud.cybercore.util.networking.config;

import java.io.File;

import com.electronwill.nightconfig.core.file.CommentedFileConfig;
import com.electronwill.nightconfig.core.io.WritingMode;

import net.minecraftforge.common.ForgeConfigSpec;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import xyz.skynetcloud.cybercore.CyberCoreMain;

@EventBusSubscriber(modid = CyberCoreMain.MODID, bus = EventBusSubscriber.Bus.MOD)
public class CyberCoreConfig {

	private static final ForgeConfigSpec.Builder COMMON_BUILDER = new ForgeConfigSpec.Builder();
	public static final ForgeConfigSpec COMMON;

	private static final ForgeConfigSpec.Builder CLIENT_BUILDER = new ForgeConfigSpec.Builder();
	public static final ForgeConfigSpec CLIENT;

	static {
		ClientSideConfig.init(COMMON_BUILDER, CLIENT_BUILDER);

		COMMON = COMMON_BUILDER.build();
		CLIENT = CLIENT_BUILDER.build();
	}

	public static void loadConfig(ForgeConfigSpec config, String path) {
		final CommentedFileConfig file = CommentedFileConfig.builder(new File(path)).sync().autosave()
				.writingMode(WritingMode.REPLACE).build();
		file.load();
		config.setConfig(file);
	}
}
