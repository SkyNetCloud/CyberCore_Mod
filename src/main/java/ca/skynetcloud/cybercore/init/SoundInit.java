package ca.skynetcloud.cybercore.init;

import ca.skynetcloud.cybercore.CyberCoreMain;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvent;
import net.minecraftforge.registries.IForgeRegistry;
import net.minecraftforge.registries.ObjectHolder;

@ObjectHolder(CyberCoreMain.MODID)
public class SoundInit {

	@ObjectHolder("electric_fence_idle")
	public static SoundEvent ELECTRIC_FENCE_IDLE;

	@ObjectHolder("robot_hurt")
	public static SoundEvent Robot_Hurt_Noise;

	@ObjectHolder("robot_death")
	public static SoundEvent Robot_Death_Noise;

	@ObjectHolder("robot_walk")
	public static SoundEvent Robot_Walk_Noise;

	public static void registerAll(IForgeRegistry<SoundEvent> registry) {
		registry.register(make("electric_fence_idle"));
		registry.register(make("robot_hurt"));
		registry.register(make("robot_death"));
		registry.register(make("robot_walk"));
	}

	private static SoundEvent make(String soundName) {
		return new SoundEvent(new ResourceLocation(CyberCoreMain.MODID, soundName)).setRegistryName(soundName);
	}
}
