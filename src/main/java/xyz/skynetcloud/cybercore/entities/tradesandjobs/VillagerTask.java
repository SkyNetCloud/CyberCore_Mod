package xyz.skynetcloud.cybercore.entities.tradesandjobs;

import java.util.List;

import net.minecraft.item.ItemStack;

public class VillagerTask
{
	private final String name; 
	private final List<ItemStack> inputs; 
	private final int id, profession, trust, minTrustLevel, maxTrustLevel; 
	
	public VillagerTask(int id, String name, int profession, List<ItemStack> inputs,  int trust, int minTrustLevel, int maxTrustLevel)
	{
		this.id = id; 
		this.name = name;
		this.profession = profession; 
		this.inputs = inputs;
		this.trust = trust; 
		this.minTrustLevel = minTrustLevel;
		this.maxTrustLevel = maxTrustLevel; 
	}

	public int getID()
	{
		return id; 
	}
	public String getName()
	{
		return name;
	}

	public List<ItemStack> getInputs()
	{
		return inputs;
	}

	public int getTrust()
	{
		return trust;
	}

	public int getMinTrustLevel()
	{
		return minTrustLevel;
	}

	public int getMaxTrustLevel()
	{
		return maxTrustLevel;
	}

	public int getProfession()
	{
		return profession;
	}
}
