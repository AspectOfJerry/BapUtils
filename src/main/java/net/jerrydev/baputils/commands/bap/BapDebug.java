package net.jerrydev.baputils.commands.bap;

import net.jerrydev.baputils.AtomicMemCache;
import net.jerrydev.baputils.BapUtils;
import net.jerrydev.baputils.utils.ChatStyles.CCodes;

import java.util.Arrays;
import java.util.List;

import static net.jerrydev.baputils.utils.ChatStyles.autoTrueFalse;
import static net.jerrydev.baputils.utils.ChatStyles.ccolorize;

public final class BapDebug {
    public static final String commandName = "debug";
    @SuppressWarnings(value = "ArraysAsListWithZeroOrOneArgument")
    public static final List<String> commandAliases = Arrays.asList("bug");
    public static final String commandUsage = ccolorize(CCodes.YELLOW, "/bap " + commandName)
        + ccolorize(CCodes.DARK_GRAY, "|" + String.join("|", commandAliases));
    public static final byte requiredParams = 0;

    public static void execute() {
        // Toggle the clientDebug flag
        AtomicMemCache.clientDebug.set(!AtomicMemCache.clientDebug.get());

        BapUtils.queueClientMessage(ccolorize(CCodes.GRAY, "Debug mode is now set to: ") + autoTrueFalse(String.valueOf(AtomicMemCache.clientDebug.get())));
    }
}
