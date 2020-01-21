package xyz.skynetcloud.cybercore.util.routing;

import net.minecraft.util.math.BlockPos;

public class PosAndDist implements Comparable<PosAndDist>
{
	public BlockPos pos;
	public int dist;
	
	public PosAndDist(BlockPos pos)
	{
		this(pos, Integer.MAX_VALUE);
	}
	
	public PosAndDist(BlockPos pos, int dist)
	{
		this.pos = pos;
		this.dist = dist;
	}

	@Override
	public int compareTo(PosAndDist other)
	{
		// TODO Auto-generated method stub
		return this.dist - other.dist;
	}
	
	
}