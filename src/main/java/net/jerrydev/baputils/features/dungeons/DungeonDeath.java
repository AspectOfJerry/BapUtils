package net.jerrydev.baputils.features.dungeons;

import net.jerrydev.baputils.AtomicCache;
import net.jerrydev.baputils.BapUtils;
import net.jerrydev.baputils.Constants;
import net.jerrydev.baputils.core.BapSettingsGui;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static net.jerrydev.baputils.utils.Debug.dout;

public class DungeonDeath {
    public String playerName;
    public String suspect;

    public DungeonDeath(String _playerName, String _suspect) {
        this.playerName = _playerName;
        this.suspect = _suspect;
    }


    public static void handleChat(String cleanMessage) {
        if(!BapSettingsGui.INSTANCE.getDungeonDeathMsg().isEmpty()) {
            BapUtils.queueServerMessage(BapSettingsGui.INSTANCE.getDungeonDeathMsg(), false);
        }

        for(final String patternStr : Constants.kDungeonDeathPs) {
            if(cleanMessage.matches(patternStr)) {
                final Pattern pattern = Pattern.compile(patternStr);
                final Matcher matcher = pattern.matcher(cleanMessage);

                if(matcher.find()) {
                    final String suspect = getSuspect(patternStr, matcher);

                    AtomicCache.dungeonRunDeaths.updateAndGet((List<DungeonDeath> list) -> {
                        list.add(new DungeonDeath(matcher.group(1), suspect));
                        return list;
                    });
                    dout("Death registered.");
                }
            }
        }
    }

    private static String getSuspect(String patternStr, Matcher matcher) {
        if(patternStr.equals(Constants.kDungeonDeathBP)) {
            return "Fire";
        } else if(patternStr.equals(Constants.kDungeonDeathCP)) {
            return "Crusher";
        } else if(patternStr.equals(Constants.kDungeonDeathDP)) {
            return "Disconnect";
        } else if(patternStr.equals(Constants.kDungeonDeathMP)) {
            return "Mob";
        } else if(patternStr.equals(Constants.kDungeonDeathTP)) {
            return "Trap";
        } else {
            return matcher.group(2);
        }
    }
}
