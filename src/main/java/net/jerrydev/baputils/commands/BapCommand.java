package net.jerrydev.baputils.commands;

import net.jerrydev.baputils.BapUtils;
import net.jerrydev.baputils.commands.bap.BapColors;
import net.jerrydev.baputils.commands.bap.BapDev;
import net.jerrydev.baputils.commands.bap.BapHello;
import net.jerrydev.baputils.commands.bap.BapTrust;
import net.jerrydev.baputils.commands.bap.hypixel.BapDungeonJoin;
import net.jerrydev.baputils.commands.bap.hypixel.BapTakeover;
import net.jerrydev.baputils.guis.BapGui;
import net.jerrydev.baputils.utils.ChatColors;
import net.jerrydev.baputils.utils.ChatColors.CCodes;
import net.minecraft.command.CommandBase;
import net.minecraft.command.CommandException;
import net.minecraft.command.ICommandSender;

import java.util.Arrays;
import java.util.List;

public class BapCommand extends CommandBase {
    @Override
    public String getCommandName() {
        return "bap";
    }

    @Override
    public List<String> getCommandAliases() {
        return Arrays.asList("bp", "baputils", "baputilities", "uwa", "pig", "tom", "fishing");
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
            BapUtils.queueClientMessage(
                    ChatColors.colorize(CCodes.DARK_RED, "Th")
                            + ChatColors.colorize(Arrays.asList(CCodes.DARK_RED, CCodes.OBFUSCATED), "e")
                            + ChatColors.colorize(CCodes.DARK_RED, " GUI ")
                            + ChatColors.colorize(Arrays.asList(CCodes.DARK_RED, CCodes.OBFUSCATED), "i")
                            + ChatColors.colorize(CCodes.DARK_RED, "s co")
                            + ChatColors.colorize(Arrays.asList(CCodes.DARK_RED, CCodes.OBFUSCATED), "m")
                            + ChatColors.colorize(CCodes.DARK_RED, "in")
                            + ChatColors.colorize(Arrays.asList(CCodes.DARK_RED, CCodes.OBFUSCATED), "g")
                            + ChatColors.colorize(CCodes.DARK_RED, " so")
                            + ChatColors.colorize(Arrays.asList(CCodes.DARK_RED, CCodes.OBFUSCATED), "o")
                            + ChatColors.colorize(CCodes.DARK_RED, "n...")
            );

            // Display main GUI
            BapUtils.setActiveGui(new BapGui());

            return;
        }

        switch (args[0].toLowerCase()) {
            case "aliases":
            case "alias":
            case "als":
            case "al":
            case "as":
                List<String> aliases = this.getCommandAliases();
                aliases.set(aliases.indexOf("uwa"), ChatColors.colorize(CCodes.LIGHT_PURPLE, "uwa"));
                aliases.set(aliases.indexOf("pig"), ChatColors.colorize(CCodes.AQUA, "pig"));
                aliases.set(aliases.indexOf("tom"), ChatColors.colorize(CCodes.RED, "tom"));
                aliases.set(aliases.indexOf("fishing"), ChatColors.colorize(CCodes.GREEN, "fishing"));

                BapUtils.queueClientMessage("Command aliases for " + ChatColors.colorize(CCodes.YELLOW, "/bap") + ": /" + String.join(", /", aliases));
                break;
            case "takeover":
            case "pto":
            case "to":
                if (args.length == 1) {
                    BapUtils.throwCommandException("You must specify a player.", "Takeover");
                    return;
                }

                BapTakeover.execute(args[1]);
                break;
            case "dungeonjoin":
            case "dgj":
            case "dj":
                if (args.length == 1) {
                    BapUtils.throwCommandException("You must specify a dungeon floor (^e0$|^[fm][0-7]$).", "DungeonJoin");
                } else if (args.length == 2) {
                    BapUtils.throwCommandException("You must specify a player.", "DungeonJoin");
                    return;
                }

                BapDungeonJoin.execute(args[1], args[2]);
                break;
            case "hello":
            case "hi":
                BapHello.execute();
                break;
            case "trust":
            case "allow":
                if (args.length == 1) {
                    BapUtils.throwCommandException("Your must specify a player.", "Trust");
                    return;
                }

                BapTrust.execute(args[1]);
                break;
            case "dev":
            case "test":
                BapDev.execute();
                break;
            case "colorcodes":
            case "colorcode":
            case "colors":
            case "color":
            case "ccodes":
            case "ccode":
            case "cc":
                BapColors.execute();
                break;
        }
    }
}
