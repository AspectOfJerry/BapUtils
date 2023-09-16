package net.jerrydev.baputils.features.dungeons;

import net.jerrydev.baputils.AtomicCache;
import net.jerrydev.baputils.Constants;
import net.jerrydev.baputils.commands.BapHandleable;
import net.jerrydev.baputils.utils.CausalRelation;

import java.util.Collections;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static net.jerrydev.baputils.utils.Debug.dout;

public class PuzzleSolved implements BapHandleable {
    @Override
    public List<String> getPatterns() {
        return Collections.singletonList(Constants.kPuzzleSolvedP);
    }

    @Override
    public void handle(List<String> args) {
        final String cleanMessage = args.get(0);
        Pattern pattern = Pattern.compile(Constants.kPuzzleSolvedP);
        Matcher matcher = pattern.matcher(cleanMessage);

        if (matcher.find()) {
            AtomicCache.dungeonFails.updateAndGet((List<CausalRelation> list) -> {
                list.add(new CausalRelation(matcher.group(1), "Puzzle", true, true));
                return list;
            });
            dout("Completion registered");
        }
    }
}
