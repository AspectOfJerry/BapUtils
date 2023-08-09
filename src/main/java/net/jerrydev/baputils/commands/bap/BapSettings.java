package net.jerrydev.baputils.commands.bap;

import net.jerrydev.baputils.BapUtils;
//import net.jerrydev.baputils.core.BapSettingsGui;
import net.jerrydev.baputils.utils.ChatColors.CCodes;

import java.util.Arrays;
import java.util.List;

import static net.jerrydev.baputils.utils.ChatColors.ccolorize;

public class BapSettings {
    public static final String commandName = "settings";
    public static final List<String> commandAliases = Arrays.asList("preferences", "config", "cfg");
    public static final String commandUsage = ccolorize(CCodes.YELLOW, "/bap " + commandName)
            + ccolorize(CCodes.DARK_GRAY, "|" + String.join("|", commandAliases));
    public static byte requiredParams = 0;

    public static void execute() {
        BapUtils.queueClientMessage(ccolorize(CCodes.YELLOW, "The settings GUI is currently disabled due to game crashes."));
        //BapUtils.setActiveGui(BapSettingsGui.INSTANCE.gui());
    }
}
