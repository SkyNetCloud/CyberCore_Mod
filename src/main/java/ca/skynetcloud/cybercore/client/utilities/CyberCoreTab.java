package ca.skynetcloud.cybercore.client.utilities;

import ca.skynetcloud.cybercore.client.init.MainInit;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;

public class CyberCoreTab {

    public static final CreativeModeTab MAIN_TAB = new CreativeModeTab("cybercore_main"){
        @Override
        public ItemStack makeIcon() {
            return new ItemStack(MainInit.ITEM_CABLE.get());
        }
    };
    public static final CreativeModeTab BLOCKS_TAB = new CreativeModeTab("cybercore_block"){

        @Override
        public ItemStack makeIcon() {
            return new ItemStack(MainInit.ITEM_CABLE.get());
        }
    };
}
