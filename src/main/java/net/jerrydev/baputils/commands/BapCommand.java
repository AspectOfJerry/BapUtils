package net.jerrydev.baputils.commands;

import net.jerrydev.baputils.BapUtils;
import net.jerrydev.baputils.commands.bap.*;
import net.jerrydev.baputils.utils.ChatColors.CCodes;
import net.minecraft.command.CommandBase;
import net.minecraft.command.CommandException;
import net.minecraft.command.ICommandSender;

import java.util.Arrays;
import java.util.List;

import static net.jerrydev.baputils.utils.ChatColors.ccolorize;

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
        if (args.length == 0) {
            BapUtils.queueClientMessage(
                ccolorize(CCodes.DARK_RED, "Th")
                    + ccolorize(Arrays.asList(CCodes.DARK_RED, CCodes.OBFUSCATED), "e")
                    + ccolorize(CCodes.DARK_RED, " GUI ")
                    + ccolorize(Arrays.asList(CCodes.DARK_RED, CCodes.OBFUSCATED), "i")
                    + ccolorize(CCodes.DARK_RED, "s co")
                    + ccolorize(Arrays.asList(CCodes.DARK_RED, CCodes.OBFUSCATED), "m")
                    + ccolorize(CCodes.DARK_RED, "in")
                    + ccolorize(Arrays.asList(CCodes.DARK_RED, CCodes.OBFUSCATED), "g")
                    + ccolorize(CCodes.DARK_RED, " so")
                    + ccolorize(Arrays.asList(CCodes.DARK_RED, CCodes.OBFUSCATED), "o")
                    + ccolorize(CCodes.DARK_RED, "n...")
            );

            // Display main GUI
            //BapUtils.setActiveGui(new BapGui()); // crashes the game outside of development

            BapUtils.queueClientMessage(ccolorize(CCodes.GRAY, "The GUI is currently disabled due to game crashes."));
            BapUtils.queueClientMessage("BapUtils' configuration is set to:");
            BapUtils.queueClientMessage(ccolorize(CCodes.GRAY, "- Global toggle: ") + ccolorize(CCodes.GREEN, "true"));
            BapUtils.queueClientMessage(ccolorize(CCodes.GRAY, "- Allow Party Takeover: ") + ccolorize(CCodes.GREEN, "true"));
            BapUtils.queueClientMessage(ccolorize(CCodes.GRAY, "- Party Takeover trusted only: ") + ccolorize(CCodes.RED, "false"));
            BapUtils.queueClientMessage(ccolorize(CCodes.GRAY, "- Allow JoinDungeon: ") + ccolorize(CCodes.GREEN, "true"));
            BapUtils.queueClientMessage(ccolorize(CCodes.GRAY, "- JoinDungeon trusted only: ") + ccolorize(CCodes.RED, "false"));
            return;
        }

        final String subcommand = args[0].toLowerCase();

        if (BapCache.commandName.equals(subcommand) || BapCache.commandAliases.contains(subcommand)) {
            // bap cache
            checkArgsLen(args.length - 1, BapCache.requiredParams, BapCache.commandUsage);

            BapCache.execute();
        } else if (BapColors.commandName.equals(subcommand) || BapColors.commandAliases.contains(subcommand)) {
            // bap colors
            checkArgsLen(args.length - 1, BapColors.requiredParams, BapColors.commandUsage);

            BapColors.execute();
        } else if (BapDebug.commandName.equals(subcommand) || BapDebug.commandAliases.contains(subcommand)) {
            // bap debug
            checkArgsLen(args.length - 1, BapDebug.requiredParams, BapDebug.commandUsage);

            BapDebug.execute();
        } else if (BapDev.commandName.equals(subcommand) || BapDev.commandAliases.contains(subcommand)) {
            // bap dev
            checkArgsLen(args.length - 1, BapDev.requiredParams, BapDev.commandUsage);

            BapDev.execute();
        } else if (BapJoinDungeon.commandName.equals(subcommand) || BapJoinDungeon.commandAliases.contains(subcommand)) {
            // bap joindungeon <String>
            checkArgsLen(args.length - 1, BapJoinDungeon.requiredParams, BapJoinDungeon.commandUsage);

            if (args.length == 1) {
                BapUtils.throwCommandException("You must specify a dungeon floor (^e0$|^[fm][0-7]$).");
                return;
            }

            BapJoinDungeon.execute(args[1]);
        } else if (BapHello.commandName.equals(subcommand) || BapHello.commandAliases.contains(subcommand)) {
            // bap hello
            checkArgsLen(args.length - 1, BapHello.requiredParams, BapHello.commandUsage);

            BapHello.execute();
        } else if (BapHelp.commandName.equals(subcommand) || BapHelp.commandAliases.contains(subcommand)) {
            // bap help
            checkArgsLen(args.length - 1, BapHelp.requiredParams, BapHelp.commandUsage);

            BapHelp.execute();
        } else if (BapOptions.commandName.equals(subcommand) || BapOptions.commandAliases.contains(subcommand)) {
            // bap options
            checkArgsLen(args.length - 1, BapOptions.requiredParams, BapOptions.commandUsage);

            BapOptions.execute();
        } else if (BapSettings.commandName.equals(subcommand) || BapSettings.commandAliases.contains(subcommand)) {
            // bap settings
            checkArgsLen(args.length - 1, BapSettings.requiredParams, BapSettings.commandUsage);

            BapSettings.execute();
        } else if (BapTakeover.commandName.equals(subcommand) || BapTakeover.commandAliases.contains(subcommand)) {
            // bap takeover
            checkArgsLen(args.length - 1, BapTakeover.requiredParams, BapTakeover.commandUsage);

            BapTakeover.execute();
        } else if (BapTrust.commandName.equals(subcommand) || BapTrust.commandAliases.contains(subcommand)) {
            // bap trust <String>
            checkArgsLen(args.length - 1, BapTrust.requiredParams, BapTrust.commandUsage);

            if (args.length == 1) {
                BapUtils.throwCommandException("Your must specify a player.");
                return;
            }

            BapTrust.execute(args[1]);
        } else if (BapUuid.commandName.equals(subcommand) || BapUuid.commandAliases.contains(subcommand)) {
            // bap uuid <String>
            checkArgsLen(args.length - 1, BapUuid.requiredParams, BapUuid.commandUsage);

            if (args.length == 1) {
                BapUtils.throwCommandException("You must specify a player.");
                return;
            }

            BapUuid.execute(args[1]);
        } else {
            BapUtils.throwCommandException("Unknown subcommand: " + subcommand);
            BapUtils.queueClientMessage(ccolorize(CCodes.DARK_GRAY, "Use /bap help for a list of commands."));
        }
    }


    public static void checkArgsLen(int input, int expected) {
        if ((input > expected) && (expected > 0)) {
            BapUtils.queueClientMessage(ccolorize(Arrays.asList(CCodes.DARK_GRAY, CCodes.ITALIC),
                "Warning: expected " + expected + " arguments, but got " + input + "."));
        }
        if (input < expected) {
            BapUtils.queueClientMessage(ccolorize(Arrays.asList(CCodes.GRAY, CCodes.ITALIC),
                "Expected " + expected + " arguments, but got " + input + "."));
        }
    }

    public static void checkArgsLen(int input, int expected, String usage) {
        if ((input == 0) && (expected > 0)) {
            BapUtils.queueClientMessage("Command usage: " + usage);
            return;
        }

        // Method overloading
        checkArgsLen(input, expected);
    }
}
