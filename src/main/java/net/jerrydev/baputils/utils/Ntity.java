package net.jerrydev.baputils.utils;

import net.minecraft.client.Minecraft;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.Vec3;

import java.util.ArrayList;
import java.util.List;

public class Ntity {
    public static List<Entity> getNearbyEntities(EntityPlayer player, float range) {
        if (player == null || player.getEntityWorld() == null) {
            return null;
        }

        List<Entity> nearbyEntities = new ArrayList<>();

        List<Entity> entities = player.getEntityWorld()
            .getEntitiesWithinAABBExcludingEntity(player, player.getEntityBoundingBox().expand(range, range, range));

        for (Entity entity : entities) {
            if (entity instanceof EntityPlayer
                || entity.getName().startsWith("item.")
                || entity.getName().startsWith("tile.")
                || entity.getName().startsWith("entity.")
            ) {
                continue;
            }
            nearbyEntities.add(entity);
        }

        return nearbyEntities;
    }

    public static List<Entity> getNearbyMobs(EntityPlayer player, float range) {
        if (player == null || player.getEntityWorld() == null) {
            return null;
        }

        List<Entity> nearbyMobs = new ArrayList<>();

        List<Entity> entities = player.getEntityWorld()
            .getEntitiesWithinAABBExcludingEntity(player, player.getEntityBoundingBox().expand(range, range, range));

        for (Entity entity : entities) {
            if (entity instanceof EntityPlayer) {
                continue;
            }
            nearbyMobs.add(entity);
        }

        return nearbyMobs;
    }

    public static List<Boolean> checkLineOfSight(List<Entity> entities) {
        List<Boolean> lineOfSightResults = new ArrayList<>();
        EntityPlayer player = Minecraft.getMinecraft().thePlayer;

        for (Entity entity : entities) {
            // Start vector is the player's eye position
            Vec3 startVec = new Vec3(player.posX, player.posY + player.getEyeHeight(), player.posZ);
            // End vector is the entity's position
            Vec3 endVec = new Vec3(entity.posX, entity.posY + entity.getEyeHeight(), entity.posZ);

            // Perform the line of sight check
            boolean inSight = player.getEntityWorld().rayTraceBlocks(startVec, endVec) == null;
            lineOfSightResults.add(inSight);
        }

        return lineOfSightResults;
    }
}
