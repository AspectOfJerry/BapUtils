package net.jerrydev.baputils.commands.bap;

import net.jerrydev.baputils.BapUtils;
import net.jerrydev.baputils.utils.interfaces.IBapCommand;

public class BapHello implements IBapCommand {
    public static void execute() {
        BapUtils.queueClientMessage("Hello, World from the client!", true);
        BapUtils.queueServerMessage("Hello, World!", true);
    }
}
