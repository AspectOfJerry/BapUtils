package net.jerrydev.baputils.commands.bap;

import net.jerrydev.baputils.BapUtils;
import net.jerrydev.baputils.RuntimeData;
import net.jerrydev.baputils.utils.ChatColors.CCodes;

import java.util.Arrays;
import java.util.List;

import static net.jerrydev.baputils.utils.ChatColors.ccolorize;

public class BapCache {
    public static final String commandName = "cache";
    public static final List<String> commandAliases = Arrays.asList("runtime", "data");
    public static final String commandUsage = ccolorize(CCodes.YELLOW, "/bap " + commandName)
        + ccolorize(CCodes.DARK_GRAY, "|" + String.join("|", commandAliases));
    public static byte requiredParams = 0;

    public static void execute() {
        for (String s : Arrays.asList(
            "RuntimeData/cache:",
            "- boolean clientDebugVerbose: " + RuntimeData.clientDebugVerbose,
            "- boolean isInParty: " + RuntimeData.isInParty,
            "- String latestPartyLeader: " + RuntimeData.latestPartyLeader
        )) {
            BapUtils.queueClientMessage(ccolorize(CCodes.GRAY, s, false));
        }
    }
}
