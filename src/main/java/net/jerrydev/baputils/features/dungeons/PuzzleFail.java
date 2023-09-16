package net.jerrydev.baputils.features.dungeons;

import net.jerrydev.baputils.AtomicCache;
import net.jerrydev.baputils.Constants;
import net.jerrydev.baputils.commands.BapHandleable;
import net.jerrydev.baputils.core.BapSettingsGui;
import net.jerrydev.baputils.utils.CausalRelation;

import java.util.Collections;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static net.jerrydev.baputils.BapUtils.queueServerMessage;
import static net.jerrydev.baputils.utils.Debug.dout;

public final class PuzzleFail implements BapHandleable {
    @Override
    public List<String> getPatterns() {
        return Collections.singletonList(Constants.kPuzzleFailP);
    }

    @Override
    public void handle(List<String> args) {
        final String cleanMessage = args.get(0);

        if (!BapSettingsGui.INSTANCE.getPuzzleFailMsg().isEmpty()) {
            queueServerMessage(BapSettingsGui.INSTANCE.getPuzzleFailMsg(), false);
        }

        Pattern pattern = Pattern.compile(Constants.kPuzzleFailP);
        Matcher matcher = pattern.matcher(cleanMessage);

        if (matcher.find()) {
            AtomicCache.dungeonFails.updateAndGet((List<CausalRelation> list) -> {
                list.add(new CausalRelation(matcher.group(1), "Puzzle", true, false));
                return list;
            });
            dout("Fail registered");
        }
    }
}
