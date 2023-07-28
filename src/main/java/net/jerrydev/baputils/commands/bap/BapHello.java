package net.jerrydev.baputils.commands.bap;

import net.jerrydev.baputils.BapUtils;
import net.jerrydev.baputils.utils.ChatColors;
import net.jerrydev.baputils.utils.IBapBaseCommand;

public class BapHello implements IBapBaseCommand {
    public static void execute() {
        BapUtils.queueClientMessage(ChatColors.colorize(ChatColors.CCodes.GREEN, "Hello, World from the client!"), true);
        BapUtils.queueServerMessage("Hello, World!", true);
    }
}
