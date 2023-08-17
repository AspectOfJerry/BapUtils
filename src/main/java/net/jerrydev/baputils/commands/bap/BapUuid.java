package net.jerrydev.baputils.commands.bap;

import net.jerrydev.baputils.BapUtils;
import net.jerrydev.baputils.utils.ChatStyles.CCodes;

import java.util.Arrays;
import java.util.List;

import static net.jerrydev.baputils.utils.ChatStyles.ccolorize;

public final class BapUuid {
    public static final String commandName = "uuid";
    public static final List<String> commandAliases = Arrays.asList("player", "id");
    public static final String commandUsage = ccolorize(CCodes.YELLOW, "/bap " + commandName)
        + ccolorize(CCodes.DARK_GRAY, "|" + String.join("|", commandAliases))
        + ccolorize(CCodes.YELLOW, " <player>");
    public static final byte requiredParams = 1;

    public static void execute(String playerName) {
        BapUtils.queueClientMessage(ccolorize(Arrays.asList(CCodes.GRAY, CCodes.ITALIC), "This command is currently under development... zzz..."));
    }
}
