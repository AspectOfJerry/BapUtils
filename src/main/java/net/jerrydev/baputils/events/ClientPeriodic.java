package net.jerrydev.baputils.events;

import net.jerrydev.baputils.utils.Debug;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiScreen;
import net.minecraftforge.fml.common.eventhandler.EventPriority;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent;
import org.jetbrains.annotations.NonBlocking;
import org.jetbrains.annotations.Nullable;

public class ClientPeriodic {
    @Nullable
    public static GuiScreen activeGui = null;

    @NonBlocking
    @SubscribeEvent(priority = EventPriority.HIGHEST)
    public void onTick(TickEvent.ClientTickEvent event) {
        if (event.phase != TickEvent.Phase.START) {
            return;
        }

        if (activeGui != null) {
            if (Minecraft.getMinecraft().thePlayer.openContainer == Minecraft.getMinecraft().thePlayer.inventoryContainer) {
                Debug.cout("Displaying gui");
                Minecraft.getMinecraft().displayGuiScreen(activeGui);
                activeGui = null;
                Debug.cout("Cleared active gui");
            }
        }
    }
}
