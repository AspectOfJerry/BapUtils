package net.jerrydev.baputils.commands.bap;

import net.jerrydev.baputils.BapUtils;
import net.jerrydev.baputils.utils.ChatStyles.CCodes;

import java.util.Arrays;
import java.util.List;

import static net.jerrydev.baputils.utils.ChatStyles.ccolorize;

public final class BapColors {
    public static final String commandName = "colors";
    public static final List<String> commandAliases = Arrays.asList("colours", "color", "colour", "cc");
    public static final String commandUsage = ccolorize(CCodes.YELLOW, "/bap " + commandName)
        + ccolorize(CCodes.DARK_GRAY, "|" + String.join("|", commandAliases));
    public static final byte requiredParams = 0;

    public static void execute() {
        BapUtils.queueClientMessage("Minecraft color codes:");

        for(final CCodes c : CCodes.values()) {
            BapUtils.queueClientMessage(c.colorCode.replaceAll("§", "&") + " | " + ccolorize(c, c.toString()), false);
        }
    }
}
