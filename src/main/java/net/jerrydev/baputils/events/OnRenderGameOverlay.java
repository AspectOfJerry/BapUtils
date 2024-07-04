package net.jerrydev.baputils.events;

import net.jerrydev.baputils.AtomicCache;
import net.jerrydev.baputils.core.BapSettingsGui;
import net.jerrydev.baputils.utils.Ntity;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.Entity;
import net.minecraftforge.client.event.RenderGameOverlayEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

import java.util.List;

public class OnRenderGameOverlay {
    @SubscribeEvent
    public void onRenderGameOverlay(RenderGameOverlayEvent.Post event) {
        if (event.type != RenderGameOverlayEvent.ElementType.TEXT) {
            return;
        }

        // Top left corner
        if (BapSettingsGui.INSTANCE.getSpeedometer()) {
            Minecraft.getMinecraft().fontRendererObj.drawString(
                "spd: " + String.format("%04.1f", AtomicCache.playerVelocityMPS.get()) + "m/s ("
                    + String.format("%02.0f", AtomicCache.playerVelocityKPH.get()) + "km/h) avg: "
                    + String.format("%04.1f", AtomicCache.playerAvgVelocityMPS.get()) + "m/s",
                2,
                2,
                0xEEEEEE,
                true
            );
        }

        // Bottom left corner
        if (BapSettingsGui.INSTANCE.getDisplayEntityCount()) {
            final List<Entity> entities = Ntity.getNearbyEntities(Minecraft.getMinecraft().thePlayer, BapSettingsGui.INSTANCE.getRadarScanRange());
            Minecraft.getMinecraft().fontRendererObj.drawString(
                "Entities >" + BapSettingsGui.INSTANCE.getRadarScanRange() + "m: "
                    + entities.size() + (BapSettingsGui.INSTANCE.getRadarLOSCheck()
                    ? " (" + Ntity.checkLineOfSight(entities).stream().filter((v) -> v).count() + " LOS)" : ""),
                2,
                12,
                0xEEEEEE,
                true
            );
        }
    }
}
