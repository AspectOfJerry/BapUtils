package net.jerrydev.baputils.commands.bap;

import net.jerrydev.baputils.BapUtils;
import net.jerrydev.baputils.utils.ChatColors;
import net.jerrydev.baputils.utils.IBapCommand;

import java.util.Arrays;
import java.util.List;

import static net.jerrydev.baputils.utils.ChatColors.ccolorize;

public class BapUuid implements IBapCommand {
    public static final String commandName = "uuid";
    public static final List<String> commandAliases = Arrays.asList("player", "id");
    public static final String commandUsage = ccolorize(ChatColors.CCodes.YELLOW, "/bap " + commandName)
            + ccolorize(ChatColors.CCodes.DARK_GRAY, "|" + String.join("|", commandAliases))
            + ccolorize(ChatColors.CCodes.YELLOW, " <player>");
    public static byte requiredParams = 1;

    public static void execute(String playerName) {
        BapUtils.queueClientMessage(ChatColors.ccolorize(Arrays.asList(ChatColors.CCodes.GRAY, ChatColors.CCodes.ITALIC), "This command is currently under development... zzz..."));
    }
}
