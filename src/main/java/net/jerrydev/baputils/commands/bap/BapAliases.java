package net.jerrydev.baputils.commands.bap;

import net.jerrydev.baputils.BapUtils;
import net.jerrydev.baputils.commands.BapCommand;
import net.jerrydev.baputils.utils.ChatColors.CCodes;
import net.jerrydev.baputils.utils.IBapCommand;

import java.util.Arrays;
import java.util.List;

import static net.jerrydev.baputils.utils.ChatColors.ccolorize;

public class BapAliases implements IBapCommand {
    public static final String commandName = "aliases";
    public static final List<String> commandAliases = Arrays.asList("alias", "als", "al", "as");
    public static final String commandUsage = ccolorize(CCodes.YELLOW, "/bap " + commandName)
            + ccolorize(CCodes.DARK_GRAY, "|" + String.join("|", commandAliases));
    public static byte requiredParams = 0;

    public static void execute() {
        List<String> bapAliases = BapCommand.commandAliases;
        bapAliases.set(bapAliases.indexOf("uwa"), ccolorize(CCodes.LIGHT_PURPLE, "uwa"));
        bapAliases.set(bapAliases.indexOf("pig"), ccolorize(CCodes.AQUA, "pig"));
        bapAliases.set(bapAliases.indexOf("tom"), ccolorize(CCodes.RED, "tom"));
        bapAliases.set(bapAliases.indexOf("fishing"), ccolorize(CCodes.GREEN, "fishing"));

        BapUtils.queueClientMessage("Command aliases for " + ccolorize(CCodes.YELLOW, "/bap") + ": /" + String.join(", /", bapAliases));
    }
}
