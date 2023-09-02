package net.jerrydev.baputils.events.features;

import net.jerrydev.baputils.AtomicCache;
import net.jerrydev.baputils.core.BapSettingsGui;
import net.jerrydev.baputils.features.dungeons.EntityDeath;
import net.jerrydev.baputils.utils.ChatEmojis;
import net.jerrydev.baputils.utils.ChatUtils.CCodes;
import net.jerrydev.baputils.utils.Debug;
import net.minecraft.client.Minecraft;

import java.util.List;

import static net.jerrydev.baputils.BapUtils.queueClientMessage;
import static net.jerrydev.baputils.utils.ChatUtils.ccolorize;
import static net.jerrydev.baputils.utils.Debug.dout;

public final class DungeonStatus {
    public static void onRunEnd(String scoreMessage) {
        dout("Dungeon run ended. Running post-dungeon tasks.");
        if(AtomicCache.dungeonRunDeaths.get().isEmpty()) {
            dout("No skill issues this run!");
            return;
        }

        new Thread(() -> {
            try {
                dout("Sleep 500ms " + Debug.getThreadInfoFormatted());
                Thread.sleep(500);
                dout("Resume " + Debug.getThreadInfoFormatted());

                queueClientMessage(ccolorize(CCodes.YELLOW, "Dungeon run deaths breakdown:"));
                AtomicCache.dungeonRunDeaths.get().forEach((EntityDeath death) -> {
                    queueClientMessage(ccolorize(CCodes.RED, death.killer) + " " + ccolorize(CCodes.DARK_RED, ChatEmojis.LEFT_ARROW.c) + " " + ccolorize(CCodes.GREEN, death.name));
                });

                AtomicCache.dungeonRunDeaths.updateAndGet((List<EntityDeath> list) -> {
                    list.clear(); // clear the list of deaths after end of run
                    return list;
                });

                if(BapSettingsGui.INSTANCE.getAutoRequeueMaster()
                    && AtomicCache.lastPartyLeader.get().equals(Minecraft.getMinecraft().thePlayer.getName())) {
                    AutoRequeue.handle();
                }
            } catch(final InterruptedException e) {
                throw new RuntimeException(e);
            }
        }).start();
    }
}
