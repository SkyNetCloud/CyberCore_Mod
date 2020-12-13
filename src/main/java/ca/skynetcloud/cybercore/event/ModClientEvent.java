package ca.skynetcloud.cybercore.event;

import ca.skynetcloud.cybercore.CyberCoreMain;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.util.text.TextFormatting;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.event.entity.EntityJoinWorldEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.ModList;
import net.minecraftforge.fml.VersionChecker;
import net.minecraftforge.fml.VersionChecker.CheckResult;
import net.minecraftforge.fml.VersionChecker.Status;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(Dist.CLIENT)
public class ModClientEvent {

	public static final ModClientEvent INSTANCE = new ModClientEvent();

	public static boolean hasSendUpdateAvailable = false;

	@SubscribeEvent
	public static void handlePlayerLoggedInEvent(EntityJoinWorldEvent event) {
		CheckResult versionRAW = VersionChecker
				.getResult(ModList.get().getModFileById(CyberCoreMain.MODID).getMods().get(0));
		Status result = versionRAW.status;

		if (!(result.equals(Status.BETA_OUTDATED) || result.equals(Status.UP_TO_DATE)
				|| result.equals(Status.AHEAD) && !hasSendUpdateAvailable)) {
			event.getEntity()
					.sendMessage(
							new StringTextComponent(
									TextFormatting.GREEN + "[" + CyberCoreMain.NAME + "] " + TextFormatting.WHITE
											+ "A new version is available (" + versionRAW.target + "), please update!"),
							null);
			event.getEntity().sendMessage(new StringTextComponent(TextFormatting.YELLOW + "Changelog:"), null);

			String changes = versionRAW.changes.get(versionRAW.target);
			if (changes != null) {
				String changesFormat[] = changes.split("\n");

				for (String change : changesFormat) {
					event.getEntity().sendMessage(new StringTextComponent(TextFormatting.WHITE + "- " + change), null);
				}
				if (versionRAW.changes.size() > 1) {
					event.getEntity().sendMessage(new StringTextComponent(TextFormatting.WHITE + "- And more..."),
							null);
				}
			}
		}
		if (result.equals(Status.UP_TO_DATE)) {
			event.getEntity()
					.sendMessage(new StringTextComponent(TextFormatting.GREEN + "[" + TextFormatting.WHITE
							+ CyberCoreMain.NAME + TextFormatting.GREEN + "] " + TextFormatting.WHITE + "No New Version"
							+ " Join Discord https://discord.gg/8jwjjyK"), null);
		}

		if (result.equals(Status.AHEAD)) {
			event.getEntity()
					.sendMessage(
							new StringTextComponent(TextFormatting.GREEN + "[" + TextFormatting.WHITE
									+ CyberCoreMain.NAME + TextFormatting.GREEN + "] " + TextFormatting.WHITE
									+ "Your a Head in The Version List" + " Join Discord https://discord.gg/8jwjjyK"),
							null);
		}
	}

}