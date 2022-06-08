package ca.skynetcloud.cybercore.client.events;

import ca.skynetcloud.cybercore.CyberCore;
import ca.skynetcloud.cybercore.client.init.BlockInit;
import ca.skynetcloud.cybercore.client.utilities.blocks.BlockRenderLayer;
import net.minecraft.client.renderer.ItemBlockRenderTypes;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.registries.RegistryObject;

import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Mod.EventBusSubscriber(modid= CyberCore.MODID, value= Dist.CLIENT, bus= Mod.EventBusSubscriber.Bus.MOD)
public class ClientEvent {

    @SubscribeEvent
    public static void onFMLClientSetup(FMLClientSetupEvent event) throws IllegalAccessException {
        List<Field> list = Arrays.asList(BlockInit.class.getDeclaredFields()).stream().filter(f -> f.isAnnotationPresent(BlockRenderLayer.class)).collect(Collectors.toList());
        for(Field field: list){
            BlockRenderLayer annotation = field.getAnnotation(BlockRenderLayer.class);
            RegistryObject<Block> registryObject = (RegistryObject<Block>) field.get(RegistryObject.class);
            Block block = registryObject.get();
            switch(annotation.layer()){
                case CUTOUT ->  ItemBlockRenderTypes.setRenderLayer(block, RenderType.cutout());
                case TRANSLUCENT -> ItemBlockRenderTypes.setRenderLayer(block, RenderType.translucent());
            }
        }
    }
}
