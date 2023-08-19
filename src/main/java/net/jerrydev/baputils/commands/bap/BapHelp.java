package net.jerrydev.baputils.commands.bap;

import net.jerrydev.baputils.BapUtils;
import net.jerrydev.baputils.commands.BapCommand;
import net.jerrydev.baputils.utils.ChatStyles.CCodes;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static net.jerrydev.baputils.utils.ChatStyles.ccolorize;

public final class BapHelp {
    public static final String commandName = "help";
    @SuppressWarnings(value = "ArraysAsListWithZeroOrOneArgument")
    public static final List<String> commandAliases = Arrays.asList("?");
    public static final String commandUsage = ccolorize(CCodes.YELLOW, "/bap " + commandName)
        + ccolorize(CCodes.DARK_GRAY, "|" + String.join("|", commandAliases));
    public static final byte requiredParams = 0;

    public static void execute() {
        final List<String> bapAliases = new ArrayList<>(BapCommand.commandAliases);

        bapAliases.set(bapAliases.indexOf("uwa"), ccolorize(CCodes.LIGHT_PURPLE, "uwa"));
        bapAliases.set(bapAliases.indexOf("pig"), ccolorize(CCodes.AQUA, "pig"));
        bapAliases.set(bapAliases.indexOf("tom"), ccolorize(CCodes.RED, "tom"));
        bapAliases.set(bapAliases.indexOf("fishing"), ccolorize(CCodes.GREEN, "fishing"));

        BapUtils.queueClientMessage("BapUtils commands with their aliases:");
        for(final String s : Arrays.asList(
            ccolorize(CCodes.YELLOW, "/bap")
                + ccolorize(CCodes.DARK_GRAY, "|" + String.join("|", bapAliases))
                + "\n " + ccolorize(CCodes.GRAY, "- Displays the main GUI"),

            BapCache.commandUsage
                + "\n " + ccolorize(CCodes.GRAY, "- Displays currently cached info"),

            BapColors.commandUsage
                + "\n " + ccolorize(CCodes.GRAY, "- Displays (&) color codes"),

            BapDev.commandUsage
                + "\n " + ccolorize(CCodes.GRAY, "- Experimental command"),

            BapHello.commandUsage
                + "\n " + ccolorize(CCodes.GRAY, "- Say hello to the world"),

            BapHelp.commandUsage
                + "\n " + ccolorize(CCodes.GRAY, "- Displays this help message"),

            BapJoinDungeon.commandUsage
                + "\n " + ccolorize(CCodes.GRAY, "- Joins a dungeon on the leader's behalf"),

            BapOptions.commandUsage
                + "\n " + ccolorize(CCodes.GRAY, "- Opens the options GUI"),

            BapSettings.commandUsage
                + "\n " + ccolorize(CCodes.GRAY, "- Opens the settings GUI"),

            BapTakeover.commandUsage
                + "\n " + ccolorize(CCodes.GRAY, "- Takeover a player's party"),

            BapTrust.commandUsage
                + "\n " + ccolorize(CCodes.GRAY, "- Add a player to the trusted list"),

            BapUuid.commandUsage
                + "\n " + ccolorize(CCodes.GRAY, "- Displays a player's UUID"))) {
            BapUtils.queueClientMessage(s);
        }
    }
}
