package ca.skynetcloud.cybercore.client.utilities.blocks.itemcables.wrapper;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;


public class PlayerDataWrapper
{
    // players that are currently holding the sprint key
    private static final Set<UUID> sprintingPlayers = new HashSet<UUID>();

    public static void setSprinting(UUID id, boolean isSprinting)
    {
        if (isSprinting)
        {
            sprintingPlayers.add(id);
        }
        else
        {
            sprintingPlayers.remove(id);
        }
    }

    public static boolean getSprinting(UUID id)
    {
        return sprintingPlayers.contains(id);
    }
}
