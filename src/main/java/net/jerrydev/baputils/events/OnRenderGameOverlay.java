package net.jerrydev.baputils.events;

import net.jerrydev.baputils.AtomicCache;
import net.jerrydev.baputils.core.BapSettingsGui;
import net.minecraft.client.Minecraft;
import net.minecraftforge.client.event.RenderGameOverlayEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class OnRenderGameOverlay {
    @SubscribeEvent
    public void onRenderGameOverlay(RenderGameOverlayEvent.Post event) {
        if (event.type != RenderGameOverlayEvent.ElementType.TEXT) {
            return;
        }

        if (BapSettingsGui.INSTANCE.getSpeedometer()) {
            Minecraft.getMinecraft().fontRendererObj.drawString(
                "spd: " + String.format("%.2f", AtomicCache.playerVelocityMPS.get()) + "m/s ("
                    + String.format("%.1f", AtomicCache.playerVelocityKPH.get()) + "km/h) avg: " + String.format("%.2f", AtomicCache.playerAvgVelocityMPS.get()) + "m/s",
                2,
                2,
                0xFFFFFF,
                true
            );
        }
    }
}
