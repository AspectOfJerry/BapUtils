package net.jerrydev.baputils.commands;

import net.jerrydev.baputils.BapUtils;
import net.jerrydev.baputils.commands.bap.BapHello;
import net.jerrydev.baputils.commands.bap.BapTest;
import net.jerrydev.baputils.commands.bap.BapTrust;
import net.jerrydev.baputils.commands.bap.hypixel.BapTakeover;
import net.jerrydev.baputils.commands.bap.utils.BapColors;
import net.minecraft.command.CommandBase;
import net.minecraft.command.CommandException;
import net.minecraft.command.ICommandSender;

import java.util.Arrays;
import java.util.List;

public class Bap extends CommandBase {
    @Override
    public String getCommandName() {
        return "bap";
    }

    @Override
    public List<String> getCommandAliases() {
        return Arrays.asList("bp", "baputils", "baputilities", "hipig");
    }

    @Override
    public String getCommandUsage(ICommandSender sender) {
        return "/" + this.getCommandName();
    }

    @Override
    public int getRequiredPermissionLevel() {
        return 0;
    }

    @Override
    public void processCommand(ICommandSender sender, String[] args) throws CommandException {
        if (args.length == 0) {
            // not working atm
            // System.out.println("SHOW TESTGUI");
            // Minecraft.getMinecraft().displayGuiScreen(new DemoGui());
            BapUtils.queueClientMessage("Nothing for the main command yet.");
            return;
        }

        switch (args[0]) {
            case "takeover":
            case "pto":
            case "to":
                if (args.length <= 1) {
                    System.out.println("bap > takeover> You must specify a player name.");
                    BapUtils.queueClientMessage("You must specify a player.");
                } else {
                    BapTakeover.execute(args[1]);
                }
                break;
            case "hello":
            case "hi":
                BapHello.execute();
                break;
            case "trust":
            case "allow":
                if (args.length <= 1) {
                    System.out.println("bap > trust > You must specify a player name.");
                    BapUtils.queueClientMessage("You must specify a player.");
                } else {
                    BapTrust.execute(args[1]);
                }
                break;
            case "test":
            case "dev":
                // BapUtils.queueClientMessage("Nothing here...");
                // return;
                BapTest.execute();
                break;
            case "colors":
            case "color":
            case "colorcode":
            case "colorcodes":
                BapColors.execute();
                break;
        }
    }
}
