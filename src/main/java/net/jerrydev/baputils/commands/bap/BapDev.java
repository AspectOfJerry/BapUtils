package net.jerrydev.baputils.commands.bap;

import net.jerrydev.baputils.BapUtils;
import net.jerrydev.baputils.utils.ChatColors;
import net.jerrydev.baputils.utils.IBapBaseCommand;

public class BapDev implements IBapBaseCommand {
    public static void execute() {
        BapUtils.queueClientMessage(ChatColors.colorize(ChatColors.CCodes.GRAY, "zzz... nothing here..."));
    }
}
