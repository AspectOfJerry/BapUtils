package net.jerrydev.baputils.commands.bap;

import net.jerrydev.baputils.BapUtils;
import net.jerrydev.baputils.utils.ChatColors;
import net.jerrydev.baputils.utils.ChatColors.CCodes;
import net.jerrydev.baputils.utils.IBapCommand;

import java.util.Arrays;
import java.util.List;

import static net.jerrydev.baputils.utils.ChatColors.ccolorize;

public class BapTrust implements IBapCommand {
    public static final String commandName = "trust";
    public static final List<String> commandAliases = Arrays.asList("friend", "allow", "add");
    public static final String commandUsage = ccolorize(CCodes.YELLOW, "/bap " + commandName)
            + ccolorize(CCodes.DARK_GRAY, "|" + String.join("|", commandAliases))
            + ccolorize(CCodes.YELLOW, " <player>");
    public static byte requiredParams = 1;

    public static void execute(String playerName) {
        final String playerUuid = "";

        BapUtils.queueClientMessage(ChatColors.ccolorize(Arrays.asList(CCodes.GRAY, CCodes.ITALIC), "This command is currently under development... zzz..."));
    }
}
