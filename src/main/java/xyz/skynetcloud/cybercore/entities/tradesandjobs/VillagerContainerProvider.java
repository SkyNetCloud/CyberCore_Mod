package xyz.skynetcloud.cybercore.entities.tradesandjobs;

import java.util.List;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.container.Container;
import net.minecraft.inventory.container.INamedContainerProvider;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TranslationTextComponent;
import xyz.skynetcloud.cybercore.util.container.VillagerContainer;

public class VillagerContainerProvider implements INamedContainerProvider {

	private final List<VillagerTrade> list;
	private final int profession;

	public VillagerContainerProvider(List<VillagerTrade> list, int profession) {
		this.list = list;
		this.profession = profession;
	}

	@Override
	public Container createMenu(int id, PlayerInventory inv, PlayerEntity entity) {
		return new VillagerContainer(id, inv, list, profession);
	}

	@Override
	public ITextComponent getDisplayName() {
		return new TranslationTextComponent("container.cybervillager");
	}

}
