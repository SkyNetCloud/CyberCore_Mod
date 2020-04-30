package xyz.skynetcloud.cybercore.init;

import net.minecraft.world.gen.feature.Feature;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import xyz.skynetcloud.cybercore.CyberCoreMain;
import xyz.skynetcloud.cybercore.world.gen.feature.LabConfig;
import xyz.skynetcloud.cybercore.world.gen.feature.LabStructure;

public class FeaturesInit {

	public static final DeferredRegister<Feature<?>> REGISTER = new DeferredRegister<>(ForgeRegistries.FEATURES,
			CyberCoreMain.MODID);

	public static final RegistryObject<LabStructure> Lab = REGISTER.register("lab",
			() -> new LabStructure(LabConfig::deserialize));

}
