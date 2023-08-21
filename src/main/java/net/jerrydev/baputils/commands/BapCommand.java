package net.jerrydev.baputils.commands;

import net.jerrydev.baputils.BapUtils;
import net.jerrydev.baputils.commands.bap.*;
import net.jerrydev.baputils.guis.BapGui;
import net.minecraft.command.CommandBase;
import net.minecraft.command.CommandException;
import net.minecraft.command.ICommandSender;

import java.util.Arrays;
import java.util.List;

import static net.jerrydev.baputils.BapUtils.clientVerbose;
import static net.jerrydev.baputils.utils.ChatStyles.CCodes;
import static net.jerrydev.baputils.utils.ChatStyles.ccolorize;

public class BapCommand extends CommandBase {
    public static final List<String> commandAliases = Arrays.asList("bp", "baputils", "baputilities", "uwa", "pig", "tom", "fishing");

    @Override
    public String getCommandName() {
        return "bap";
    }

    @Override
    public List<String> getCommandAliases() {
        return commandAliases;
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
        if(args.length == 0) {
            // Display main/options GUI
            BapUtils.setActiveGui(new BapGui());

            /*queueClientMessage(
                ccolorize(CCodes.DARK_RED, "Th")
                    + ccolorize(Arrays.asList(CCodes.DARK_RED, CCodes.OBFUSCATED), "e")
                    + ccolorize(CCodes.DARK_RED, " GUI ")
                    + ccolorize(Arrays.asList(CCodes.DARK_RED, CCodes.OBFUSCATED), "i")
                    + ccolorize(CCodes.DARK_RED, "s h")
                    + ccolorize(Arrays.asList(CCodes.DARK_RED, CCodes.OBFUSCATED), "e")
                    + ccolorize(CCodes.DARK_RED, "re.")
            );*/
            return;
        }

        final String subcommand = args[0].toLowerCase();

        if(BapCache.commandName.equals(subcommand) || BapCache.commandAliases.contains(subcommand)) {
            // bap cache

            BapCache.execute();
        } else if(BapColors.commandName.equals(subcommand) || BapColors.commandAliases.contains(subcommand)) {
            // bap colors

            BapColors.execute();
        } else if(BapCrash.commandName.equals(subcommand) || BapCrash.commandAliases.contains(subcommand)) {
            // bap crash <String>

            if(args.length == 1) {
                BapUtils.throwCommandException("You must specify a player.");
                return;
            }

            BapCrash.execute(args[1]);
        } else if(BapDev.commandName.equals(subcommand) || BapDev.commandAliases.contains(subcommand)) {
            // bap dev

            BapDev.execute();
        } else if(BapJoinDungeon.commandName.equals(subcommand) || BapJoinDungeon.commandAliases.contains(subcommand)) {
            // bap joindungeon <String>

            if(args.length == 1) {
                BapUtils.throwCommandException("You must specify a dungeon floor ([fm][0-7])");
                return;
            }

            BapJoinDungeon.execute(args[1]);
        } else if(BapHello.commandName.equals(subcommand) || BapHello.commandAliases.contains(subcommand)) {
            // bap hello

            BapHello.execute();
        } else if(BapHelp.commandName.equals(subcommand) || BapHelp.commandAliases.contains(subcommand)) {
            // bap help

            BapHelp.execute();
        } else if(BapOptions.commandName.equals(subcommand) || BapOptions.commandAliases.contains(subcommand)) {
            // bap options

            BapOptions.execute();
        } else if(BapSettings.commandName.equals(subcommand) || BapSettings.commandAliases.contains(subcommand)) {
            // bap settings

            BapSettings.execute();
        } else if(BapTakeover.commandName.equals(subcommand) || BapTakeover.commandAliases.contains(subcommand)) {
            // bap takeover

            BapTakeover.execute();
        } else if(BapTrust.commandName.equals(subcommand) || BapTrust.commandAliases.contains(subcommand)) {
            // bap trust <String>

            if(args.length == 1) {
                BapUtils.throwCommandException("Your must specify a player.");
                return;
            }

            BapTrust.execute(args[1]);
        } else if(BapUuid.commandName.equals(subcommand) || BapUuid.commandAliases.contains(subcommand)) {
            // bap uuid <String>

            if(args.length == 1) {
                BapUtils.throwCommandException("You must specify a player.");
                return;
            }

            BapUuid.execute(args[1]);
        } else if(BapWarp.commandName.equals(subcommand) || BapWarp.commandAliases.contains(subcommand)) {
            // bap warp

            BapWarp.execute();
        } else {
            BapUtils.throwCommandException("Unknown subcommand: " + subcommand);
            clientVerbose(ccolorize(CCodes.GRAY, "Use /bap help for a list of commands."));
        }
    }
}
