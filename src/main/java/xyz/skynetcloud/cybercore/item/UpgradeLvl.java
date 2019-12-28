package xyz.skynetcloud.cybercore.item;

public class UpgradeLvl extends ItemBaseCore {

	private int level;
	private int itemtype;

	public UpgradeLvl(Properties property, int level, int itemtype) {
		super(property);
		this.level = level;
		this.itemtype = itemtype;
	}

	public int getLevel() {
		return level;
	}

	public int getItemType() {
		return itemtype;
	}

}
