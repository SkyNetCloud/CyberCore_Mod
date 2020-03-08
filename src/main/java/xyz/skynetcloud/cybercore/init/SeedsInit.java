package xyz.skynetcloud.cybercore.init;

import net.minecraft.block.Block;
import net.minecraft.client.Minecraft;
import net.minecraft.item.BlockNamedItem;
import net.minecraftforge.common.MinecraftForge;

public class SeedsInit extends BlockNamedItem {

	public SeedsInit(Block crop) {
		super(crop, new Properties().maxStackSize(64));
		
      		
	}
	

}
