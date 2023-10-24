package net.jerrydev.baputils.commands;

import net.jerrydev.baputils.BapUtils;
import net.jerrydev.baputils.Constants;
import net.jerrydev.baputils.commands.bap.*;
import net.jerrydev.baputils.guis.BapGui;
import net.minecraft.command.CommandBase;
import net.minecraft.command.CommandException;
import net.minecraft.command.ICommandSender;

import java.util.Arrays;
import java.util.List;

import static net.jerrydev.baputils.BapUtils.clientVerbose;
import static net.jerrydev.baputils.utils.ChatUtils.CCodes;
import static net.jerrydev.baputils.utils.ChatUtils.ccolorize;
import static net.jerrydev.baputils.utils.Debug.dout;

public class BapCommand extends CommandBase {
    public static final List<String> commandAliases = Arrays.asList("baputils", "baputilities", "uwa", "pig", "tom", "fishing");
    public static final List<IBapRunnable> subcommands = Arrays.asList(
        new BapCache(),
        new BapColors(),
        new BapCrash(),
        new BapDev(),
        new BapHello(),
        new BapHelp(),
        new BapJoinDungeon(),
        new BapOptions(),
        new BapRoulette(),
        new BapSettings(),
        new BapTakeover(),
        new BapTrust(),
        new BapUuid(),
        new BapWarp()
    );

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
            // Display main/options GUI
            dout("You are on version " + Constants.kModVersion + "!");
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

        for (final IBapRunnable subCmd : subcommands) {
            if (subCmd.getName().equals(subcommand) || subCmd.getAliases().contains(subcommand)) {
                subCmd.run(Arrays.asList(args).subList(1, args.length)); // remove subcommand name from args
                return;
            }
        }

        BapUtils.throwCommandException("Unknown subcommand: " + subcommand);
        clientVerbose(ccolorize(CCodes.GRAY, "Use /bap help for a list of commands."));
    }
}
