package ca.skynetcloud.cybercore.util.networking.util;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.Set;

import com.google.common.collect.ImmutableSet;

import ca.skynetcloud.cybercore.util.networking.CyberCorePacketHandler;
import ca.skynetcloud.cybercore.util.networking.helper.IsWasSprintPacket;
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

	public void setIsSprintingAndNotifyServer(boolean isSprinting) {
		// mark the capability on the client and send a packet to the server to do the
		// same
		this.isHoldingSprint = isSprinting;
		CyberCorePacketHandler.sendToServer(new IsWasSprintPacket(isSprinting));
	}

	public void updateTubesInChunk(ChunkPos pos, Set<BlockPos> tubes) {
		this.tubesInChunk.put(pos, tubes);
	}

	public Set<BlockPos> getTubesInChunk(ChunkPos pos) {
		return this.tubesInChunk.getOrDefault(pos, NO_TUBES);
	}
}
