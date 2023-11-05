package net.jerrydev.baputils.events;

import net.jerrydev.baputils.AtomicCache;
import net.jerrydev.baputils.utils.Debug;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.Vec3;
import net.minecraftforge.fml.common.eventhandler.EventPriority;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent;
import org.jetbrains.annotations.Nullable;

import static net.jerrydev.baputils.Constants.kServerChatDelayMs;

public class ClientPeriodic {
    @Nullable
    public static volatile GuiScreen activeGui = null;
    private static byte serverChatCd = 5;
    private Vec3 lastPos;
    private int lastTick = 0;

    private double totalDistance = 0.0; // Total distance traveled
    private int totalTicks = 0; // Total ticks elapsed

    @SubscribeEvent(priority = EventPriority.HIGHEST)
    public void onTick(TickEvent.ClientTickEvent event) {
        if (event.phase != TickEvent.Phase.START) {
            return;
        }

        EntityPlayer player = Minecraft.getMinecraft().thePlayer;

        if (activeGui != null) {
            if (player.openContainer == player.inventoryContainer) {
                Minecraft.getMinecraft().displayGuiScreen(activeGui);
                activeGui = null;
                Debug.dout("Queued gui is active, clearing queue");
            }
        }

        if (serverChatCd == 0) {
            if (!AtomicCache.serverChatQueue.get().isEmpty()) {
                Minecraft.getMinecraft().thePlayer.sendChatMessage(AtomicCache.serverChatQueue.get().remove(0));
            }
            serverChatCd = kServerChatDelayMs / 50;
        }
        serverChatCd--;

        if (player != null && player.getEntityWorld() != null) {
            Vec3 currentPos = new Vec3(player.posX, player.posY, player.posZ);

            // Calculate player's velocity using ticks
            int currentTick = player.ticksExisted;
            if (lastTick != 0) {
                int ticksElapsed = currentTick - lastTick;
                double distance = currentPos.distanceTo(lastPos);

                double velocityInBlocksPerTick = distance / ticksElapsed;
                double velocityInBlocksPerSecond = velocityInBlocksPerTick * 20; // Assuming 20 client TPS
                double velocityInKPH = velocityInBlocksPerSecond * 3.6;

                AtomicCache.playerVelocityMPS.set(Math.round(velocityInBlocksPerSecond * 100000.0) / 100000.0);
                AtomicCache.playerVelocityKPH.set(Math.round(velocityInKPH * 100000.0) / 100000.0);

                // Calculate average velocity using a tick window
                if (Math.abs(totalTicks) > 30) {
                    totalDistance = 0;
                    totalTicks = 0;
                }
                totalDistance += distance;
                totalTicks += ticksElapsed;
                double averageSpeedMPS = totalDistance / ((double) totalTicks / 20); // Convert to m/s
                AtomicCache.playerAvgVelocityMPS.set(Math.round(averageSpeedMPS * 100000.0) / 100000.0);
            }

            lastPos = currentPos;
            lastTick = currentTick;
        }
    }
}
