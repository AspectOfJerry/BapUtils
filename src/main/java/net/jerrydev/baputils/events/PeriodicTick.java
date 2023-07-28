package net.jerrydev.baputils.events;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiScreen;
import net.minecraftforge.fml.common.eventhandler.EventPriority;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent;

import javax.annotation.Nullable;

public class PeriodicTick {
    @Nullable
    public static GuiScreen displayGui = null;

    @SubscribeEvent(priority = EventPriority.HIGHEST)
    public void onTick(TickEvent.ClientTickEvent event) {
        if (event.phase != TickEvent.Phase.START) {
            return;
        }

        if (displayGui != null) {
            Minecraft.getMinecraft().displayGuiScreen(displayGui);
            displayGui = null;
        }
    }
}
