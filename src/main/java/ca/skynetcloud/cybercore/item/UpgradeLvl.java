package ca.skynetcloud.cybercore.item;

public class UpgradeLvl extends ItemBaseCore {

	private int level;
	private ItemType itemtype;

	public UpgradeLvl(Properties property, int level, ItemType itemtype) {
		super(property);
		this.level = level;
		this.itemtype = itemtype;
	}

	public int getLevel() {
		return level;
	}

	public ItemType getItemType() {
		return itemtype;
	}

	public enum ItemType {
		SOLAR_FOCUS, RANGE_UPGRADE, SPEED_UPGRADE,
	}
}
