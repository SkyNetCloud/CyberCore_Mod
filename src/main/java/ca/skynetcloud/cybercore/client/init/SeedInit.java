package ca.skynetcloud.cybercore.client.init;

import ca.skynetcloud.cybercore.client.utilities.CyberCoreTab;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemNameBlockItem;
import net.minecraft.world.level.block.Block;

import static ca.skynetcloud.cybercore.client.utilities.CyberCoreTab.MAIN_TAB;

public class SeedInit extends ItemNameBlockItem {
    public SeedInit(Block crop) {
        super(crop, new Item.Properties().tab(MAIN_TAB));

    }

}
