package net.jerrydev.baputils.commands;

import net.jerrydev.baputils.gui.DemoGui;
import net.jerrydev.baputils.utils.PtoEncrypt;
import net.jerrydev.baputils.utils.StringHex;
import net.minecraft.client.Minecraft;
import net.minecraft.command.CommandBase;
import net.minecraft.command.CommandException;
import net.minecraft.command.ICommandSender;
import net.minecraft.util.ChatComponentText;
import net.minecraftforge.client.ClientCommandHandler;

import java.util.Arrays;
import java.util.List;

public class BapCommand extends CommandBase {
    @Override
    public String getCommandName() {
        return "bap";
    }

    @Override
    public List<String> getCommandAliases() {
        return Arrays.asList("hipig");
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
            Minecraft.getMinecraft().thePlayer.addChatMessage(new ChatComponentText("Nothing for the main command yet."));
            return;
        }
        switch (args[0]) {
            case "takeover":
            case "to":
                if (args.length == 1) {
                    System.out.println("You must specify a player");
                    Minecraft.getMinecraft().thePlayer.addChatMessage(new ChatComponentText("You must specify a player."));
                } else {
                    // encrypt using
                    new Thread(() -> {
                        try {
                            // ClientCommandHandler.instance.executeCommand(Minecraft.getMinecraft().thePlayer, "/party list");
                            Thread.sleep(50);
                            Minecraft.getMinecraft().thePlayer.sendChatMessage("/party list");
                            Thread.sleep(50);
                            Minecraft.getMinecraft().thePlayer.sendChatMessage("baputils > takeover > " + StringHex.stringToHex(args[1]));
                            /*
                            try {
                                Minecraft.getMinecraft().thePlayer.sendChatMessage("/party chat " + "$t/" + PtoEncrypt.encryptString("baputils/pto/" + args[1], (int) ((System.currentTimeMillis() / 1000) / 2) * 2));
                            } catch (Exception e) {
                                throw new RuntimeException(e);
                            }
                             */
                        } catch (InterruptedException e) {
                            throw new RuntimeException(e);
                        }
                    }).start();
                }
                break;
            case "hello":
            case "hi":
                Minecraft.getMinecraft().thePlayer.sendChatMessage("baputils > Hello, World!");
                break;
        }
    }
}
