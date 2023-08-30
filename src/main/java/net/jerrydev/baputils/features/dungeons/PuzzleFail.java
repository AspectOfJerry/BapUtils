package net.jerrydev.baputils.features.dungeons;

import net.jerrydev.baputils.core.BapSettingsGui;

import static net.jerrydev.baputils.BapUtils.queueServerMessage;

public final class PuzzleFail {
    public static void handleChat() {
        queueServerMessage(BapSettingsGui.INSTANCE.getPuzzleFailMsg(), false);
    }
}
