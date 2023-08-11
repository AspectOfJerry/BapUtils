package net.jerrydev.baputils.commands.bap;

import net.jerrydev.baputils.BapUtils;
import net.jerrydev.baputils.RuntimeData;
import net.jerrydev.baputils.utils.ChatColors.CCodes;

import java.util.Arrays;
import java.util.List;

import static net.jerrydev.baputils.utils.ChatColors.ccolorize;

public class BapDebug {
    public static final String commandName = "debug";
    @SuppressWarnings("ArraysAsListWithZeroOrOneArgument")
    public static final List<String> commandAliases = Arrays.asList("verbose");
    public static final String commandUsage = ccolorize(CCodes.YELLOW, "/bap " + commandName)
            + ccolorize(CCodes.DARK_GRAY, "|" + String.join("|", commandAliases));
    public static byte requiredParams = 0;

    public static void execute() {
        RuntimeData.clientDebugVerbose = !RuntimeData.clientDebugVerbose;
        BapUtils.queueClientMessage(ccolorize(CCodes.GRAY, "Debug mode is now set to: ", false)
                + (RuntimeData.clientDebugVerbose ? ccolorize(CCodes.GREEN, "true") : ccolorize(CCodes.RED, "false")));
    }
}
