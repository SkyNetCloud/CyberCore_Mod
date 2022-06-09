package ca.skynetcloud.cybercore.client.init;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvent;

import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;


import static ca.skynetcloud.cybercore.CyberCore.MODID;


public class SoundInit {

    public static final DeferredRegister<SoundEvent> SOUND_EVENT_DEFERRED = DeferredRegister.create(ForgeRegistries.SOUND_EVENTS, MODID);

    public static final RegistryObject<SoundEvent> ELECTRIC_FENCE_SPARK;
    public static final RegistryObject<SoundEvent> ELECTRIC_FENCE_IDLE;

    static {
        ELECTRIC_FENCE_SPARK = createSoundEvent("electric_fence_spark");
        ELECTRIC_FENCE_IDLE = createSoundEvent("electric_fence_idle");
    }

    private static RegistryObject<SoundEvent> createSoundEvent(final String soundName) {
        ResourceLocation resourceLocation = new ResourceLocation(MODID, soundName);
        return SOUND_EVENT_DEFERRED.register(soundName, () -> new SoundEvent(resourceLocation));
    }
}
