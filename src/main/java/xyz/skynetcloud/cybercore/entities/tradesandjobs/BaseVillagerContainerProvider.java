package xyz.skynetcloud.cybercore.entities.tradesandjobs;

import java.util.List;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.container.Container;
import net.minecraft.inventory.container.INamedContainerProvider;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TranslationTextComponent;
import xyz.skynetcloud.cybercore.util.container.VillagerContainer;

public class BaseVillagerContainerProvider implements INamedContainerProvider {

	private final List<BaseVillagerTrade> list;

	public BaseVillagerContainerProvider(List<BaseVillagerTrade> list) {
		this.list = list;

	}

	@Override
	public Container createMenu(int id, PlayerInventory inv, PlayerEntity entity) {
		return new VillagerContainer(id, inv, list);
	}

	@Override
	public ITextComponent getDisplayName() {
		return new TranslationTextComponent("container.techvillager");
	}

}
