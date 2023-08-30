package net.jerrydev.baputils.events.impl;

import net.jerrydev.baputils.AtomicCache;
import net.jerrydev.baputils.features.dungeons.DungeonDeath;
import net.jerrydev.baputils.utils.ChatEmojis;
import net.jerrydev.baputils.utils.ChatStyles.CCodes;

import java.util.List;

import static net.jerrydev.baputils.BapUtils.queueClientMessage;
import static net.jerrydev.baputils.utils.ChatStyles.ccolorize;
import static net.jerrydev.baputils.utils.Debug.dout;

public final class DungeonStatus {
    public static void onRunEnd(String scoreMessage) {
        dout("Dungeon run ended. Running post-dungeon tasks.");
        if(AtomicCache.dungeonRunDeaths.get().isEmpty()) {
            dout("No skill issues this run!");
            return;
        }

        queueClientMessage(ccolorize(CCodes.YELLOW, "Dungeon run deaths breakdown:"));
        AtomicCache.dungeonRunDeaths.get().forEach((DungeonDeath death) -> {
            queueClientMessage(ccolorize(CCodes.DARK_RED, death.suspect) + " " + ccolorize(CCodes.RED, ChatEmojis.LEFT_ARROW.emoji) + " " + ccolorize(CCodes.GREEN, death.playerName));
        });

        AtomicCache.dungeonRunDeaths.updateAndGet((List<DungeonDeath> list) -> {
            list.clear(); // clear the list of deaths after end of run
            return list;
        });
    }
}
