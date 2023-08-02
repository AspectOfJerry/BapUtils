package net.jerrydev.baputils.commands.bap;

import net.jerrydev.baputils.BapUtils;
import net.jerrydev.baputils.utils.ChatColors;
import net.jerrydev.baputils.utils.ChatColors.CCodes;
import net.jerrydev.baputils.utils.IBapCommand;

import java.util.Arrays;
import java.util.List;

import static net.jerrydev.baputils.utils.ChatColors.ccolorize;

public class BapHello implements IBapCommand {
    public static final String commandName = "hello";
    public static final List<String> commandAliases = Arrays.asList("hi", "hey", "world");
    public static final String commandUsage = ccolorize(CCodes.YELLOW, "/bap " + commandName)
            + ccolorize(CCodes.DARK_GRAY, "|" + String.join("|", commandAliases));
    public static byte requiredParams = 0;

    public static void execute() {
        BapUtils.queueClientMessage(ChatColors.ccolorize(CCodes.GREEN, "Hello, World from the client!"), true);
        BapUtils.queueServerMessage("Hello, World!", true);
    }
}
