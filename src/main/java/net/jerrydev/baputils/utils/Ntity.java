package net.jerrydev.baputils.utils;

import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;

import java.util.ArrayList;
import java.util.List;

public class Ntity {
    public static List<Entity> getNearbyMobs(EntityPlayer player, float range) {
        if (player == null || player.getEntityWorld() == null) {
            return null;
        }
        
        List<Entity> nearbyMobs = new ArrayList<>();

        List<Entity> entities = player.getEntityWorld().getEntitiesWithinAABBExcludingEntity(player,
            player.getEntityBoundingBox().expand(range, range, range));

        for (Entity entity : entities) {
            if (entity instanceof EntityPlayer || entity.getName().startsWith("item.") || entity.getName().startsWith("tile.") || entity.getName().startsWith("entity.")) {
                continue;
            }
            nearbyMobs.add(entity);
        }

        return nearbyMobs;
    }
}
