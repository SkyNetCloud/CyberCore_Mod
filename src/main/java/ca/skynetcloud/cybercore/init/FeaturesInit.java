package ca.skynetcloud.cybercore.init;

import ca.skynetcloud.cybercore.CyberCoreMain;
import ca.skynetcloud.cybercore.world.gen.feature.LabConfig;
import ca.skynetcloud.cybercore.world.gen.feature.LabStructure;
import net.minecraft.world.gen.feature.Feature;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class FeaturesInit {

	@SuppressWarnings("deprecation")
	public static final DeferredRegister<Feature<?>> REGISTER = new DeferredRegister<>(ForgeRegistries.FEATURES,
			CyberCoreMain.MODID);

	public static final RegistryObject<LabStructure> Lab = REGISTER.register("lab",
			() -> new LabStructure(LabConfig::deserialize));

}
