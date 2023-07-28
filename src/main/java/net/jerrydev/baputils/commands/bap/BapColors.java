package net.jerrydev.baputils.commands.bap;

import net.jerrydev.baputils.BapUtils;
import net.jerrydev.baputils.utils.ChatColors;
import net.jerrydev.baputils.utils.IBapBaseCommand;

public class BapColors implements IBapBaseCommand {
    public static void execute() {
        BapUtils.queueClientMessage("Minecraft color codes:");

        for (ChatColors.CCodes c : ChatColors.CCodes.values()) {
            BapUtils.queueClientMessage(c.colorCode.replaceAll("§", "&") + " | " + ChatColors.colorize(c, c.toString()), false);
        }
    }
}
