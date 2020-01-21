package xyz.skynetcloud.cybercore.util.helper;

import javax.annotation.Nullable;

import net.minecraft.util.Direction;
import net.minecraft.util.math.BlockPos;

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
