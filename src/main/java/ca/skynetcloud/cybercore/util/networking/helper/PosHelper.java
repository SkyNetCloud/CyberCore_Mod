package ca.skynetcloud.cybercore.util.networking.helper;

import org.jetbrains.annotations.Nullable;

import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;

public class PosHelper {

	@Nullable
	public static Direction getTravelDirectionFromTo(BlockPos startPos, BlockPos nextPos) {
		for (Direction face : Direction.values()) {
			if (startPos.offset(face).equals(nextPos)) {
				return face;
			}
		}
		return null;
	}
}
