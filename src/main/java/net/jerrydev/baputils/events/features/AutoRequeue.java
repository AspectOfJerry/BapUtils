package net.jerrydev.baputils.events.features;

import net.jerrydev.baputils.AtomicCache;
import net.jerrydev.baputils.Constants;
import net.jerrydev.baputils.commands.BapHandleable;
import net.jerrydev.baputils.core.BapSettingsGui;
import net.jerrydev.baputils.features.dungeons.CatacombsFloors;
import net.jerrydev.baputils.utils.Debug;

import java.util.List;

import static net.jerrydev.baputils.BapUtils.*;
import static net.jerrydev.baputils.utils.Debug.dout;

public final class AutoRequeue implements BapHandleable {
    public static void handle() {
        new Thread(() -> {
            try {
                final CatacombsFloors floor = AtomicCache.lastCatacombsFloor.get();

                if(floor == null) {
                    queueErrorMessage("Couldn't find the last catacombs floor. This is impossible!" +
                        " Please open a bug report at " + Constants.kGitHubIssues);
                    return;
                }

                dout("Sleep 3000ms " + Debug.getThreadInfoFormatted());
                Thread.sleep(3000);
                dout("Resume " + Debug.getThreadInfoFormatted());

                queueServerMessage("Auto requeuing " + floor.shortName + " in " + BapSettingsGui.INSTANCE.getAutoRequeueDelay() + "s.", false);

                dout("Sleep " + BapSettingsGui.INSTANCE.getAutoRequeueDelay() * 1000 + "ms " + Debug.getThreadInfoFormatted());
                Thread.sleep(BapSettingsGui.INSTANCE.getAutoRequeueDelay() * 1000L);
                dout("Resume " + Debug.getThreadInfoFormatted());

                queueCommand("joindungeon " + floor.commandCode);
            } catch(InterruptedException e) {
                throw new RuntimeException(e);
            }
        }).start();
    }

    @Override
    public List<String> getPatterns() {
        return null;
    }

    @Override
    public void handle(List<String> args) {
    }
}
