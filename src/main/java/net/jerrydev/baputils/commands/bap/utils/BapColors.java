package net.jerrydev.baputils.commands.bap.utils;

import net.jerrydev.baputils.BapUtils;
import net.jerrydev.baputils.utils.ChatColorCodes;
import net.jerrydev.baputils.utils.interfaces.IBapCommand;

public class BapColors implements IBapCommand {
    public static void execute() {
        BapUtils.queueClientMessage("Minecraft (§) color codes:");

        for (ChatColorCodes c : ChatColorCodes.values()) {
            BapUtils.queueClientMessage(c.toString() + ": ", false);
        }
    }
}
