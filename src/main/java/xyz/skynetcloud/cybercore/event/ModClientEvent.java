package xyz.skynetcloud.cybercore.event;

import net.minecraft.client.entity.player.ClientPlayerEntity;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraft.util.text.event.ClickEvent;
import net.minecraft.util.text.event.HoverEvent;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.event.entity.EntityJoinWorldEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.ModList;
import net.minecraftforge.fml.VersionChecker;
import net.minecraftforge.fml.common.Mod;
import xyz.skynetcloud.cybercore.CyberCoreMain;

@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD)
public class ModClientEvent {

	@OnlyIn(Dist.CLIENT)
	@SubscribeEvent
	public static void onWorldStart(EntityJoinWorldEvent evt) {
		VersionChecker.CheckResult res = VersionChecker
				.getResult(ModList.get().getModContainerById(CyberCoreMain.MODID).get().getModInfo());
		if (evt.getEntity() instanceof ClientPlayerEntity && res.status == VersionChecker.Status.OUTDATED
				&& !CyberCoreMain.hasSendUpdateAvailable) {
			CyberCoreMain.hasSendUpdateAvailable = true;

			ITextComponent info = new TranslationTextComponent("cybercore.update.available");
			ITextComponent link = new TranslationTextComponent("cybercore.update.click");

			link.getStyle()
					.setClickEvent(new ClickEvent(ClickEvent.Action.OPEN_URL,
							"https://www.curseforge.com/minecraft/mc-mods/cybercore-mod/files"))
					.setHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT,
							new TranslationTextComponent("cybercore.update.tooltip")))
					.setColor(TextFormatting.DARK_GREEN).setUnderlined(true);
			evt.getEntity().sendMessage(info.appendSibling(link));
		}

	}

	@OnlyIn(Dist.CLIENT)
	@SubscribeEvent
	public static void DiscordLinkOnWorldLoad(EntityJoinWorldEvent event) {

		ITextComponent info = new TranslationTextComponent("cybercore.info.click");
		ITextComponent link = new TranslationTextComponent("cybercore.info.link");

		link.getStyle().setClickEvent(new ClickEvent(ClickEvent.Action.OPEN_URL, "https://discord.gg/WbHWbm8"))
				.setColor(TextFormatting.DARK_GREEN).setUnderlined(true);
		event.getEntity().sendMessage(info.appendSibling(link));

	}

}
