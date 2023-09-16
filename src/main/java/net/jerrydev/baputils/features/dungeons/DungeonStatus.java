package net.jerrydev.baputils.features.dungeons;

import net.jerrydev.baputils.AtomicCache;
import net.jerrydev.baputils.core.BapSettingsGui;
import net.jerrydev.baputils.utils.CausalRelation;
import net.jerrydev.baputils.utils.ChatEmojis;
import net.jerrydev.baputils.utils.ChatUtils.CCodes;
import net.jerrydev.baputils.utils.Debug;
import net.minecraft.client.Minecraft;

import java.util.List;

import static net.jerrydev.baputils.BapUtils.clientMessage;
import static net.jerrydev.baputils.utils.ChatUtils.ccolorize;
import static net.jerrydev.baputils.utils.Debug.dout;

public final class DungeonStatus {
    public static void onRunEnd(String scoreMessage) {
        dout("Dungeon run ended. Running post-dungeon tasks.");
        if (AtomicCache.dungeonFails.get().isEmpty()) {
            dout("No skill issues this run!");
            return;
        }

        new Thread(() -> {
            try {
                dout("Sleep 500ms " + Debug.getThreadInfoFormatted());
                Thread.sleep(500);
                dout("Resume " + Debug.getThreadInfoFormatted());

                if (BapSettingsGui.INSTANCE.getDungeonBreakdown()) {
                    clientMessage(ccolorize(CCodes.YELLOW, "Dungeon run breakdown:"));
                    AtomicCache.dungeonFails.get().forEach((CausalRelation event) -> {
                        if (event.leftGreen) {
                            if (event.positive) {
                                // used by puzzle completions
                                clientMessage(ccolorize(CCodes.GREEN, event.actor)
                                    + " " + ccolorize(CCodes.DARK_GREEN, ChatEmojis.LEFT_ARROW.c)
                                    + " " + ccolorize(CCodes.GREEN, event.target));
                                return;
                            }
                            // used by puzzle fails
                            clientMessage(ccolorize(CCodes.GREEN, event.actor)
                                + " " + ccolorize(CCodes.DARK_RED, ChatEmojis.LEFT_ARROW.c)
                                + " " + ccolorize(CCodes.RED, event.target));
                            return;
                        }
                        if (event.positive) {
                            // heh?
                            clientMessage(ccolorize(CCodes.RED, event.actor)
                                + " " + ccolorize(CCodes.DARK_GREEN, ChatEmojis.LEFT_ARROW.c)
                                + " " + ccolorize(CCodes.GREEN, event.target));
                        }
                        // used by player deaths
                        clientMessage(ccolorize(CCodes.RED, event.actor)
                            + " " + ccolorize(CCodes.DARK_RED, ChatEmojis.LEFT_ARROW.c)
                            + " " + ccolorize(CCodes.GREEN, event.target));
                    });
                }

                AtomicCache.dungeonFails.updateAndGet((List<CausalRelation> list) -> {
                    list.clear(); // clear the list of deaths after end of run
                    return list;
                });

                if (BapSettingsGui.INSTANCE.getAutoRequeueMaster()
                    && AtomicCache.lastPartyLeader.get().equals(Minecraft.getMinecraft().thePlayer.getName())) {
                    AutoRequeue.handle();
                }
            } catch (final InterruptedException e) {
                throw new RuntimeException(e);
            }
        }).start();
    }
}
