package net.jerrydev.baputils.commands.bap;

import net.jerrydev.baputils.BapUtils;
import net.jerrydev.baputils.utils.ChatColors;
import net.jerrydev.baputils.utils.ChatColors.CCodes;
import net.jerrydev.baputils.utils.IBapBaseCommand;

import java.util.Arrays;

public class BapTrust implements IBapBaseCommand {
    public static void execute(String playerName) {
        final String playerUUID = "";

        BapUtils.queueClientMessage(ChatColors.colorize(Arrays.asList(CCodes.GRAY, CCodes.ITALIC), "This command is currently under development... zzz..."));
    }
}
