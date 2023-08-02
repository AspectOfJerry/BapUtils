package net.jerrydev.baputils.commands.bap;

import net.jerrydev.baputils.BapUtils;
import net.jerrydev.baputils.utils.ChatColors;
import net.jerrydev.baputils.utils.ChatColors.CCodes;
import net.jerrydev.baputils.utils.IBapCommand;

import java.util.Arrays;
import java.util.List;

import static net.jerrydev.baputils.utils.ChatColors.ccolorize;

public class BapHelp implements IBapCommand {
    public static final String commandName = "help";
    public static final List<String> commandAliases = Arrays.asList("?");
    public static final String commandUsage = ccolorize(CCodes.YELLOW, "/bap " + commandName)
            + ccolorize(CCodes.DARK_GRAY, "|" + String.join("|", commandAliases));
    public static byte requiredParams = 0;

    public static void execute() {
        BapUtils.queueClientMessage("BapUtils commands with their easiest alias:");
        for (String s : Arrays.asList(ccolorize(CCodes.YELLOW, "/bap") + "  -  Display main GUI",
                ccolorize(CCodes.YELLOW, "/bap dev") + "  -  Experimental command",
                ccolorize(CCodes.YELLOW, "/bap hello") + "  -  Say hello to the world",
                ccolorize(CCodes.YELLOW, "/bap trust") + "  -  Add a player to the trust list",
                ccolorize(CCodes.YELLOW, "/bap colors") + ccolorize(CCodes.GRAY, "|cc") + "  -  Displays (&) color codes",
                ccolorize(CCodes.YELLOW, "/bap takeover") + ccolorize(CCodes.GRAY, "|to") + "  -  Takeover a player's party",
                ccolorize(CCodes.YELLOW, "/bap dungeonjoin") + ccolorize(CCodes.GRAY, "|dj") + "  -  Joins a dungeon on the leader's behalf",
                ccolorize(CCodes.YELLOW, "/bap aliases") + ccolorize(CCodes.GRAY, "|as") + "  -  Displays command aliases")) {
            BapUtils.queueClientMessage(s);
        }
    }
}
