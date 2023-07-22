package net.jerrydev.baputils.commands.bap;

import net.jerrydev.baputils.BapUtils;
import net.jerrydev.baputils.utils.ChatColorCodes;
import net.jerrydev.baputils.utils.interfaces.IBapCommand;

public class BapTest implements IBapCommand {
    public static void execute() {
        BapUtils.queueClientMessage("§0Bap 0");
        BapUtils.queueClientMessage("§1Bap 1");
        BapUtils.queueClientMessage("§2Bap 2");
        BapUtils.queueClientMessage("§3Bap 3");
        BapUtils.queueClientMessage("§4Bap 4");
        BapUtils.queueClientMessage("§5Bap 5");
        BapUtils.queueClientMessage("§6Bap 6");
        BapUtils.queueClientMessage("§7Bap 7");
        BapUtils.queueClientMessage("§8Bap 8");
        BapUtils.queueClientMessage("§9Bap 9");

        BapUtils.queueClientMessage("§aBap a");
        BapUtils.queueClientMessage("§bBap b");
        BapUtils.queueClientMessage("§cBap c");
        BapUtils.queueClientMessage("§dBap d");
        BapUtils.queueClientMessage("§eBap e");
        BapUtils.queueClientMessage("§fBap f");
    }
}
