package xyz.skynetcloud.cybercore.event;

import net.minecraft.util.text.StringTextComponent;
import net.minecraft.util.text.TextFormatting;
import net.minecraftforge.client.event.ClientPlayerNetworkEvent.LoggedInEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.ModList;
import net.minecraftforge.fml.VersionChecker;
import net.minecraftforge.fml.VersionChecker.CheckResult;
import net.minecraftforge.fml.VersionChecker.Status;
import xyz.skynetcloud.cybercore.CyberCoreMain;

public class ModClientEvent {

	public static final ModClientEvent INSTANCE = new ModClientEvent();

	@SubscribeEvent
	public void handlePlayerLoggedInEvent(LoggedInEvent event) {
		CheckResult versionRAW = VersionChecker
				.getResult(ModList.get().getModFileById(CyberCoreMain.MODID).getMods().get(0));
		Status result = versionRAW.status;

		if (!(result.equals(Status.BETA_OUTDATED) || result.equals(Status.UP_TO_DATE) || result.equals(Status.AHEAD))) {
			event.getPlayer().sendMessage(new StringTextComponent(TextFormatting.GREEN + "[" + CyberCoreMain.NAME + "] "
					+ TextFormatting.WHITE + "A new version is available (" + versionRAW.target + "), please update!"));
			event.getPlayer().sendMessage(new StringTextComponent(TextFormatting.YELLOW + "Changelog:"));

			String changes = versionRAW.changes.get(versionRAW.target);
			if (changes != null) {
				String changesFormat[] = changes.split("\n");

				for (String change : changesFormat) {
					event.getPlayer().sendMessage(new StringTextComponent(TextFormatting.WHITE + "- " + change));
				}
				if (versionRAW.changes.size() > 1) {
					event.getPlayer().sendMessage(new StringTextComponent(TextFormatting.WHITE + "- And more..."));
				}
			}
		}
		if (result.equals(Status.UP_TO_DATE)) {
			event.getPlayer()
					.sendMessage(new StringTextComponent(TextFormatting.GREEN + "[" + TextFormatting.WHITE
							+ CyberCoreMain.NAME + TextFormatting.GREEN + "] " + TextFormatting.WHITE + "No New Version"
							+ " Join Discord https://discord.gg/8jwjjyK"));
		}

		if (result.equals(Status.AHEAD)) {
			event.getPlayer()
					.sendMessage(new StringTextComponent(TextFormatting.GREEN + "[" + TextFormatting.WHITE
							+ CyberCoreMain.NAME + TextFormatting.GREEN + "] " + TextFormatting.WHITE
							+ "Your a Head in The Version List" + " Join Discord https://discord.gg/8jwjjyK"));
		}
	}

}