package ca.skynetcloud.cybercore.client.proxy;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.Set;

import com.google.common.collect.ImmutableSet;


import net.minecraft.core.BlockPos;
import net.minecraft.world.level.ChunkPos;
import net.minecraftforge.fml.DistExecutor;

public class ClientProxy {
    public static Optional<ClientProxy> INSTANCE = DistExecutor
            .unsafeRunForDist(() -> () -> ClientProxy.makeClientProxy(), () -> () -> Optional.empty());

    private static final Set<BlockPos> NO_TUBES = ImmutableSet.of();

    private boolean isHoldingSprint = false;

    private Map<ChunkPos, Set<BlockPos>> tubesInChunk = new HashMap<>();

    public static Optional<ClientProxy> makeClientProxy() {
        return Optional.of(new ClientProxy());
    }

    public boolean getWasSprinting() {
        return this.isHoldingSprint;
    }

    public void updateTubesInChunk(ChunkPos pos, Set<BlockPos> tubes) {
        this.tubesInChunk.put(pos, tubes);
    }

    public Set<BlockPos> getTubesInChunk(ChunkPos pos) {
        return this.tubesInChunk.getOrDefault(pos, NO_TUBES);
    }
}
