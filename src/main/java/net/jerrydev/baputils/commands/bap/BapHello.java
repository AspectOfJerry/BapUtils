package net.jerrydev.baputils.commands.bap;

import net.jerrydev.baputils.BapUtils;
import net.jerrydev.baputils.utils.ChatStyles;
import net.jerrydev.baputils.utils.ChatStyles.CCodes;
import net.jerrydev.baputils.utils.Debug;

import java.util.Arrays;
import java.util.List;

import static net.jerrydev.baputils.utils.ChatStyles.ccolorize;

public final class BapHello {
    public static final String commandName = "hello";
    public static final List<String> commandAliases = Arrays.asList("hi", "world");
    public static final String commandUsage = ccolorize(CCodes.YELLOW, "/bap " + commandName)
        + ccolorize(CCodes.DARK_GRAY, "|" + String.join("|", commandAliases));
    public static final byte requiredParams = 0;

    public static void execute() {
        BapUtils.queueClientMessage(ChatStyles.ccolorize(CCodes.GREEN, "Hello, World from the client!"), true);
        BapUtils.queueWarnMessage("Hello from the warning message chat!");
        BapUtils.queueErrorMessage("Hello from the error message chat!");
        BapUtils.clientVerbose("Hello from the client verbose chat!");
        Debug.dout("Hello from the client debug chat!");
        BapUtils.queueCommand("party chat bap > Hello, World!");
    }
}
