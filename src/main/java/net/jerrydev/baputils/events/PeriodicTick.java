package net.jerrydev.baputils.events;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiScreen;
import net.minecraftforge.fml.common.eventhandler.EventPriority;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent;
import org.jetbrains.annotations.Nullable;

public class PeriodicTick {
    @Nullable
    public static GuiScreen activeGui = null;

    @SubscribeEvent(priority = EventPriority.HIGHEST)
    public void onTick(TickEvent.ClientTickEvent event) {
        if (event.phase != TickEvent.Phase.START) {
            return;
        }

        if (activeGui != null) {
            Minecraft.getMinecraft().displayGuiScreen(activeGui);
            activeGui = null;
        }
    }
}
