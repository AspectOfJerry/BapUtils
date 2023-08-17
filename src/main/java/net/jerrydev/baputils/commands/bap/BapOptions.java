package net.jerrydev.baputils.commands.bap;

import net.jerrydev.baputils.BapUtils;
import net.jerrydev.baputils.utils.ChatStyles.CCodes;

import java.util.Arrays;
import java.util.List;

import static net.jerrydev.baputils.utils.ChatStyles.ccolorize;

public final class BapOptions {
    public static final String commandName = "options";
    public static final List<String> commandAliases = Arrays.asList("menu", "gui");
    public static final String commandUsage = ccolorize(CCodes.YELLOW, "/bap " + commandName)
        + ccolorize(CCodes.DARK_GRAY, "|" + String.join("|", commandAliases));
    public static final byte requiredParams = 0;

    public static void execute() {
        BapUtils.queueClientMessage(ccolorize(CCodes.YELLOW, "The options GUI is currently disabled due to game crashes."));
        //BapUtils.setActiveGui(new BapGui());
    }
}
