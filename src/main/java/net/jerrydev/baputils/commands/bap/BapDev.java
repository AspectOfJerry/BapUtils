package net.jerrydev.baputils.commands.bap;

import net.jerrydev.baputils.BapUtils;
import net.jerrydev.baputils.gui.BapGui;
import net.jerrydev.baputils.gui.BapTestGui;
import net.jerrydev.baputils.utils.IBapBaseCommand;

public class BapDev implements IBapBaseCommand {
    public static void execute() {
        // BapUtils.queueClientMessage(ChatColors.colorize(ChatColors.CCodes.GRAY, "zzz... nothing here..."));

        System.out.println("SHOW JavaTestGui");

        // BapUtils.setDisplayGui(new BapTestGui());
        BapUtils.setDisplayGui(new BapGui());
    }
}
