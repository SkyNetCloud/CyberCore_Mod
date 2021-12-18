package ca.skynetcloud.cybercore.common.items;

import net.minecraft.world.item.Item;

public class ItemTier extends Item

{
    private int tier;
    private ItemType itemtype;

    public ItemTier(Properties property, int tier, ItemType itemtype)
    {
        super(property);
        this.tier = tier;
        this.itemtype = itemtype;
    }

    public int getTier()
    {
        return tier;
    }

    public ItemType getItemType()
    {
        return itemtype;
    }

    public enum ItemType
    {
        SOLAR_LENS,
        SPEED_UP,
        POWER_STORAGE_UP

    }
}
