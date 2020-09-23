package ca.skynetcloud.cybercore.init;

import ca.skynetcloud.cybercore.world.gen.feature.structure.Lab;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.registry.Registry;
import net.minecraft.world.gen.feature.structure.IStructurePieceType;

public class InitStructurePieceType {

	public static final IStructurePieceType LOOTABLE_lab = register(Lab.Piece::new, "cybercore:lab_piece");

	public static void init() {
	}

	private static IStructurePieceType register(IStructurePieceType type, String key) {
		return Registry.register(Registry.STRUCTURE_PIECE, new ResourceLocation(key), type);
	}

}
