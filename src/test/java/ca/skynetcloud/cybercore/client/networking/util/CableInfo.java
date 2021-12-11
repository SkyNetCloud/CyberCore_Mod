package ca.skynetcloud.cybercore.client.networking.util;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.NbtUtils;

import java.util.ArrayList;
import java.util.HashSet;

public class CableInfo {
	public BlockPos masterPos = BlockPos.ZERO;
	public boolean isMaster = false;
	public HashSet<BlockPos> slaves = new HashSet<>();
	public HashSet<StaticConnection> producers = new HashSet<>();
	public HashSet<StaticConnection> consumers = new HashSet<>();
	public HashSet<StaticConnection> storages = new HashSet<>();
	public int[] connections = new int[] { 0, 0, 0, 0, 0, 0 };

	public CableInfo() {
	}

	public CableInfo(CompoundTag compound) {
		this.masterPos = NbtUtils.readBlockPos(compound);
		this.isMaster = compound.getBoolean("ismaster");
		this.connections = compound.getIntArray("connections");
		if (this.isMaster) {
			this.slaves = readBlockPosList(compound, "slaves");
			this.producers = readBlockPosWithDirectionList(compound, "producers");
			this.consumers = readBlockPosWithDirectionList(compound, "consumers");
			this.storages = readBlockPosWithDirectionList(compound, "storages");
		}
	}

	public CompoundTag write() {
		CompoundTag compound = NbtUtils.writeBlockPos(masterPos);
		compound.putBoolean("ismaster", isMaster);
		compound.putIntArray("connections", connections);
		if (isMaster) {
			writeBlockPosList(compound, "slaves", slaves);
			writeBlockPosWithDirectionList(compound, "producers", producers, true);
			writeBlockPosWithDirectionList(compound, "consumers", consumers, true);
			writeBlockPosWithDirectionList(compound, "storages", storages, true);
		}
		return compound;
	}

	private void writeBlockPosList(CompoundTag compound, String key, HashSet<BlockPos> targetList) {
		HashSet<StaticConnection> connections = new HashSet<>();
		targetList.forEach((pos) -> connections.add(new StaticConnection(pos, Direction.UP)));
		writeBlockPosWithDirectionList(compound, key, connections, false);
	}

	private void writeBlockPosWithDirectionList(CompoundTag compound, String key, HashSet<StaticConnection> targetList,
			boolean direction) {
		CompoundTag subCompound = new CompoundTag();
		ArrayList<Integer> x = new ArrayList<>();
		ArrayList<Integer> y = new ArrayList<>();
		ArrayList<Integer> z = new ArrayList<>();
		ArrayList<Integer> d = new ArrayList<>();
		targetList.forEach((connection) -> {
			BlockPos blockPos = connection.blockPos;
			x.add(blockPos.getX());
			y.add(blockPos.getY());
			z.add(blockPos.getZ());
			if (direction)
				d.add(connection.direction.get2DDataValue());
		});
		subCompound.putIntArray("x", x);
		subCompound.putIntArray("y", y);
		subCompound.putIntArray("z", z);
		if (direction)
			subCompound.putIntArray("d", d);
		compound.put(key, subCompound);
	}

	private HashSet<StaticConnection> readBlockPosWithDirectionList(CompoundTag compound, String key) {
		HashSet<StaticConnection> connections = new HashSet<>();
		CompoundTag subCompound = compound.getCompound(key);
		int[] x = subCompound.getIntArray("x");
		int[] y = subCompound.getIntArray("y");
		int[] z = subCompound.getIntArray("z");
		int[] d = subCompound.getIntArray("d");
		for (int i = 0; i < subCompound.getIntArray("x").length; i++)
			connections.add(new StaticConnection(new BlockPos(x[i], y[i], z[i]), Direction.from3DDataValue(d[i])));
		return connections;
	}

	private HashSet<BlockPos> readBlockPosList(CompoundTag compound, String key) {
		HashSet<BlockPos> blockPos = new HashSet<>();
		CompoundTag subCompound = compound.getCompound(key);
		int[] x = subCompound.getIntArray("x");
		int[] y = subCompound.getIntArray("y");
		int[] z = subCompound.getIntArray("z");
		for (int i = 0; i < subCompound.getIntArray("x").length; i++)
			blockPos.add(new BlockPos(x[i], y[i], z[i]));
		return blockPos;
	}

	public CableInfo clear(boolean keepConnection) {
		CableInfo newInfo = new CableInfo();
		if (keepConnection)
			newInfo.connections = connections.clone();
		return newInfo;
	}

	public static class StaticConnection {
		public BlockPos blockPos = BlockPos.ZERO;
		public Direction direction = Direction.UP;

		public StaticConnection() {
		}

		public StaticConnection(BlockPos blockPos, Direction direction) {
			this.blockPos = blockPos;
			this.direction = direction;
		}
	}
}
