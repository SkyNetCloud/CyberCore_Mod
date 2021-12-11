package ca.skynetcloud.cybercore.client.utilities.blocks;

import ca.skynetcloud.cybercore.client.utilities.CyberCoreTab;
import net.minecraft.world.item.CreativeModeTab;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(value= RetentionPolicy.RUNTIME)
@Target(value= ElementType.FIELD)
public @interface HasItem {
    enum Tab{
        MAIN(CyberCoreTab.MAIN),
        BLOCKS(CyberCoreTab.BLOCKS);
        private CreativeModeTab tab;

        Tab(CreativeModeTab tab){
            this.tab = tab;
        }

        public CreativeModeTab getTab(){
            return tab;
        }

    }
    boolean isWIP() default false;
    Tab tab() default Tab.BLOCKS;
}
