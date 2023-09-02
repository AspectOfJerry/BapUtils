package net.jerrydev.baputils.features.dungeons;

import net.jerrydev.baputils.Constants;
import net.jerrydev.baputils.commands.BapHandleable;
import net.jerrydev.baputils.core.BapSettingsGui;

import java.util.Collections;
import java.util.List;

import static net.jerrydev.baputils.BapUtils.queueServerMessage;
import static net.jerrydev.baputils.utils.Debug.dout;

public final class PuzzleFail implements BapHandleable {
    @Override
    public List<String> getPatterns() {
        return Collections.singletonList(Constants.kPuzzleFailP);
    }

    @Override
    public void handle(List<String> args) {
        dout("Fail detected");
        if(BapSettingsGui.INSTANCE.getPuzzleFailMsg().isEmpty()) {
            return;
        }
        queueServerMessage(BapSettingsGui.INSTANCE.getPuzzleFailMsg(), false);
    }
}
