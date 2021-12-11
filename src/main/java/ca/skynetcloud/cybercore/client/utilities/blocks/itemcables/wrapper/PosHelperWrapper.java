package ca.skynetcloud.cybercore.client.utilities.blocks.itemcables.wrapper;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;

import javax.annotation.Nullable;

public class PosHelperWrapper {

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
