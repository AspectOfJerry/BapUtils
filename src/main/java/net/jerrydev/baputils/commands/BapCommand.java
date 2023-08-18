package net.jerrydev.baputils.commands;

import net.jerrydev.baputils.BapUtils;
import net.jerrydev.baputils.commands.bap.*;
import net.minecraft.command.CommandBase;
import net.minecraft.command.CommandException;
import net.minecraft.command.ICommandSender;

import java.util.Arrays;
import java.util.List;

import static net.jerrydev.baputils.BapUtils.queueClientMessage;
import static net.jerrydev.baputils.utils.ChatStyles.*;

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
            // Display main GUI
            //BapUtils.setActiveGui(new BapGui()); // crashes the game outside of development

            for(final String s : Arrays.asList(
                ccolorize(CCodes.GRAY, "The GUI is currently disabled due to game crashes."),
                rainbowify("BapUtils") + "' defualt configuration is set to:",
                ccolorize(CCodes.GRAY, "- Global toggle: ") + autoTrueFalse("true"),
                ccolorize(CCodes.GRAY, "- Allow Party Takeover: ") + autoTrueFalse("true"),
                ccolorize(CCodes.GRAY, "- Party Takeover trusted only: ") + autoTrueFalse("false"),
                ccolorize(CCodes.GRAY, "- Allow JoinDungeon: ") + autoTrueFalse("true"),
                ccolorize(CCodes.GRAY, "- JoinDungeon trusted only: ") + autoTrueFalse("false"),
                ccolorize(CCodes.GRAY, "- Client debug (/debug): ") + autoTrueFalse("false"),
                ccolorize(CCodes.GRAY, "- Client verbose: ") + autoTrueFalse("true"))) {
                queueClientMessage(s);
            }

            queueClientMessage(
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
            return;
        }

        final String subcommand = args[0].toLowerCase();

        if(BapCache.commandName.equals(subcommand) || BapCache.commandAliases.contains(subcommand)) {
            // bap cache
            verifyArgsLen(args.length - 1, BapCache.requiredParams, BapCache.commandUsage);

            BapCache.execute();
        } else if(BapColors.commandName.equals(subcommand) || BapColors.commandAliases.contains(subcommand)) {
            // bap colors
            verifyArgsLen(args.length - 1, BapColors.requiredParams, BapColors.commandUsage);

            BapColors.execute();
        } else if(BapCrash.commandName.equals(subcommand) || BapCrash.commandAliases.contains(subcommand)) {
            // bap crash <String>
            verifyArgsLen(args.length - 1, BapCrash.requiredParams, BapCrash.commandUsage);

            if(args.length == 1) {
                BapUtils.throwCommandException("You must specify a player.");
                return;
            }

            BapCrash.execute(args[1]);
        } else if(BapDebug.commandName.equals(subcommand) || BapDebug.commandAliases.contains(subcommand)) {
            // bap debug
            verifyArgsLen(args.length - 1, BapDebug.requiredParams, BapDebug.commandUsage);

            BapDebug.execute();
        } else if(BapDev.commandName.equals(subcommand) || BapDev.commandAliases.contains(subcommand)) {
            // bap dev
            verifyArgsLen(args.length - 1, BapDev.requiredParams, BapDev.commandUsage);

            BapDev.execute();
        } else if(BapJoinDungeon.commandName.equals(subcommand) || BapJoinDungeon.commandAliases.contains(subcommand)) {
            // bap joindungeon <String>
            verifyArgsLen(args.length - 1, BapJoinDungeon.requiredParams, BapJoinDungeon.commandUsage);

            if(args.length == 1) {
                BapUtils.throwCommandException("You must specify a dungeon floor ([fm][0-7])");
                return;
            }

            BapJoinDungeon.execute(args[1]);
        } else if(BapHello.commandName.equals(subcommand) || BapHello.commandAliases.contains(subcommand)) {
            // bap hello
            verifyArgsLen(args.length - 1, BapHello.requiredParams, BapHello.commandUsage);

            BapHello.execute();
        } else if(BapHelp.commandName.equals(subcommand) || BapHelp.commandAliases.contains(subcommand)) {
            // bap help
            verifyArgsLen(args.length - 1, BapHelp.requiredParams, BapHelp.commandUsage);

            BapHelp.execute();
        } else if(BapOptions.commandName.equals(subcommand) || BapOptions.commandAliases.contains(subcommand)) {
            // bap options
            verifyArgsLen(args.length - 1, BapOptions.requiredParams, BapOptions.commandUsage);

            BapOptions.execute();
        } else if(BapSettings.commandName.equals(subcommand) || BapSettings.commandAliases.contains(subcommand)) {
            // bap settings
            verifyArgsLen(args.length - 1, BapSettings.requiredParams, BapSettings.commandUsage);

            BapSettings.execute();
        } else if(BapTakeover.commandName.equals(subcommand) || BapTakeover.commandAliases.contains(subcommand)) {
            // bap takeover
            verifyArgsLen(args.length - 1, BapTakeover.requiredParams, BapTakeover.commandUsage);

            BapTakeover.execute();
        } else if(BapTrust.commandName.equals(subcommand) || BapTrust.commandAliases.contains(subcommand)) {
            // bap trust <String>
            verifyArgsLen(args.length - 1, BapTrust.requiredParams, BapTrust.commandUsage);

            if(args.length == 1) {
                BapUtils.throwCommandException("Your must specify a player.");
                return;
            }

            BapTrust.execute(args[1]);
        } else if(BapUuid.commandName.equals(subcommand) || BapUuid.commandAliases.contains(subcommand)) {
            // bap uuid <String>
            verifyArgsLen(args.length - 1, BapUuid.requiredParams, BapUuid.commandUsage);

            if(args.length == 1) {
                BapUtils.throwCommandException("You must specify a player.");
                return;
            }

            BapUuid.execute(args[1]);
        } else if(BapWarp.commandName.equals(subcommand) || BapWarp.commandAliases.contains(subcommand)) {
            // bap warp
            verifyArgsLen(args.length - 1, BapWarp.requiredParams, BapWarp.commandUsage);

            BapWarp.execute();
        } else {
            BapUtils.throwCommandException("Unknown subcommand: " + subcommand);
            queueClientMessage(ccolorize(CCodes.DARK_GRAY, "Use /bap help for a list of commands."));
        }
    }

    public static void verifyArgsLen(int input, int expected) {
        if((input > expected) && (expected > 0)) {
            BapUtils.clientVerbose("Warning: expected " + expected + " arguments, but got " + input + ".");
        }
        if(input < expected) {
            queueClientMessage(ccolorize(Arrays.asList(CCodes.GRAY, CCodes.ITALIC),
                "Expected " + expected + " arguments, but got " + input + "."));
        }
    }

    public static void verifyArgsLen(int input, int expected, String usage) {
        if((input == 0) && (expected > 0)) {
            queueClientMessage("Command usage: " + usage);
            return;
        }

        // Overload
        verifyArgsLen(input, expected);
    }
}
