package net.jerrydev.baputils.events;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiScreen;
import net.minecraftforge.fml.common.eventhandler.EventPriority;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent;
import org.jetbrains.annotations.NonBlocking;
import org.jetbrains.annotations.Nullable;

import java.net.MalformedURLException;

public class ClientPeriodic {
    @Nullable
    public static GuiScreen activeGui = null;

    @NonBlocking
    @SubscribeEvent(priority = EventPriority.HIGHEST)
    public void onTick(TickEvent.ClientTickEvent event) throws MalformedURLException {
        if (event.phase != TickEvent.Phase.START) {
            return;
        }

        if (activeGui != null) {
            if (Minecraft.getMinecraft().thePlayer.openContainer == Minecraft.getMinecraft().thePlayer.inventoryContainer) {
                Minecraft.getMinecraft().displayGuiScreen(activeGui);
                activeGui = null;
            }
        }
    }
}
