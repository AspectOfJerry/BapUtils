package net.jerrydev.baputils.commands.bap;

import net.jerrydev.baputils.BapUtils;
import net.jerrydev.baputils.commands.BapCommand;
import net.jerrydev.baputils.utils.ChatColors.CCodes;
import net.jerrydev.baputils.utils.IBapCommand;

import java.util.ArrayList;
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
        List<String> bapAliases = new ArrayList<>(BapCommand.commandAliases);

        bapAliases.set(bapAliases.indexOf("uwa"), ccolorize(CCodes.LIGHT_PURPLE, "uwa"));
        bapAliases.set(bapAliases.indexOf("pig"), ccolorize(CCodes.AQUA, "pig"));
        bapAliases.set(bapAliases.indexOf("tom"), ccolorize(CCodes.RED, "tom"));
        bapAliases.set(bapAliases.indexOf("fishing"), ccolorize(CCodes.GREEN, "fishing"));

        BapUtils.queueClientMessage("BapUtils commands with their aliases:");
        for (String s : Arrays.asList(
                ccolorize(CCodes.YELLOW, "/bap")
                        + ccolorize(CCodes.DARK_GRAY, "|" + String.join("|", bapAliases))
                        + "\n " + ccolorize(CCodes.GRAY, " - Displays the main GUI"),

                ccolorize(CCodes.YELLOW, "/bap colors")
                        + ccolorize(CCodes.DARK_GRAY, "|" + String.join("|", BapColors.commandAliases))
                        + "\n " + ccolorize(CCodes.GRAY, "- Displays (&) color codes"),

                ccolorize(CCodes.YELLOW, "/bap dev")
                        + ccolorize(CCodes.DARK_GRAY, "|" + String.join("|", BapDev.commandAliases))
                        + "\n " + ccolorize(CCodes.GRAY, "- Experimental command"),

                ccolorize(CCodes.YELLOW, "/bap dungeonjoin")
                        + ccolorize(CCodes.DARK_GRAY, "|" + String.join("|", BapDungeonJoin.commandAliases))
                        + "\n " + ccolorize(CCodes.GRAY, "- Joins a dungeon on the leader's behalf"),

                ccolorize(CCodes.YELLOW, "/bap hello")
                        + ccolorize(CCodes.DARK_GRAY, "|" + String.join("|", BapHello.commandAliases))
                        + "\n " + ccolorize(CCodes.GRAY, "- Say hello to the world"),

                ccolorize(CCodes.YELLOW, "/bap help")
                        + ccolorize(CCodes.DARK_GRAY, "|" + String.join("|", BapHelp.commandAliases))
                        + "\n " + ccolorize(CCodes.GRAY, "- Displays this help message"),

                ccolorize(CCodes.YELLOW, "/bap options")
                        + ccolorize(CCodes.DARK_GRAY, "|" + String.join("|", BapOptions.commandAliases))
                        + "\n " + ccolorize(CCodes.GRAY, "- Opens the options GUI"),

                ccolorize(CCodes.YELLOW, "/bap settings")
                        + ccolorize(CCodes.DARK_GRAY, "|" + String.join("|", BapSettings.commandAliases))
                        + "\n " + ccolorize(CCodes.GRAY, "- Opens the settings GUI"),

                ccolorize(CCodes.YELLOW, "/bap takeover")
                        + ccolorize(CCodes.DARK_GRAY, "|" + String.join("|", BapTakeover.commandAliases))
                        + "\n " + ccolorize(CCodes.GRAY, "- Takeover a player's party"),

                ccolorize(CCodes.YELLOW, "/bap trust")
                        + ccolorize(CCodes.DARK_GRAY, "|" + String.join("|", BapTrust.commandAliases))
                        + "\n " + ccolorize(CCodes.GRAY, "- Add a player to the trusted list"),

                ccolorize(CCodes.YELLOW, "/bap uuid")
                        + ccolorize(CCodes.DARK_GRAY, "|" + String.join("|", BapUuid.commandAliases))
                        + "\n " + ccolorize(CCodes.GRAY, "- Displays a player's UUID"))) {
            BapUtils.queueClientMessage(s);
        }
    }
}
