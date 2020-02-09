package xyz.skynetcloud.cybercore.entities.capabilities.villagertrust;

import java.util.List;
import java.util.Map;

public interface IVillagerTrust
{
	public void increaseTrust(String profession, int trust, int maxLevel);

	public void decreaseTrust(String profession, int trust, int minLevel);

	public void setTrust(String profession, int trust);

	public int getTrust(String profession);
	
	public int getMaxLevel();

	public int getLevel(String profession);

	public Map<String, Integer> getTrustsMap();

	public int getLevelTrust(int level);
	
	public List<String> getKeys(); 
}
