package ca.skynetcloud.cybercore.util.networking.helper;

import javax.annotation.Nullable;

import net.minecraft.util.Direction;
import net.minecraft.util.math.BlockPos;

public class PosHelper {

	@Nullable
	public static Direction getTravelDirectionFromTo(BlockPos startPos, BlockPos nextPos) {
		for (Direction face : Direction.values()) {
			if (startPos.relative(face).equals(nextPos)) {
				return face;
			}
		}
		return null;
	}
}
