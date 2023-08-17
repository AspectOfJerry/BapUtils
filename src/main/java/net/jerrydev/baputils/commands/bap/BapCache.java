package net.jerrydev.baputils.commands.bap;

import net.jerrydev.baputils.AtomicMemCache;
import net.jerrydev.baputils.BapUtils;
import net.jerrydev.baputils.utils.ChatStyles.CCodes;

import java.util.Arrays;
import java.util.List;

import static net.jerrydev.baputils.utils.ChatStyles.autoTrueFalse;
import static net.jerrydev.baputils.utils.ChatStyles.ccolorize;

public final class BapCache {
    public static final String commandName = "cache";
    @SuppressWarnings(value = "ArraysAsListWithZeroOrOneArgument")
    public static final List<String> commandAliases = Arrays.asList("data");
    public static final String commandUsage = ccolorize(CCodes.YELLOW, "/bap " + commandName)
        + ccolorize(CCodes.DARK_GRAY, "|" + String.join("|", commandAliases));
    public static final byte requiredParams = 0;

    public static void execute() {
        for(final String s : Arrays.asList(
            "Here's are the values currently stored in our fancy AtomicMemCache",
            "- boolean clientDebug: " + autoTrueFalse(String.valueOf(AtomicMemCache.clientDebug.get())),
            "- boolean clientVerbose: " + autoTrueFalse(String.valueOf(AtomicMemCache.clientVerbose.get())),
            "- boolean isInParty: " + autoTrueFalse(String.valueOf(AtomicMemCache.isInParty.get())),
            "- String lastPartyLeader: " + AtomicMemCache.lastPartyLeader.get()
        )) {
            BapUtils.queueClientMessage(ccolorize(CCodes.GRAY, s, false));
        }
    }
}
