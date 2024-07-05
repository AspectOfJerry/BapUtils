package net.jerrydev.baputils.commands.bap;

import net.jerrydev.baputils.BapUtils;
import net.jerrydev.baputils.commands.BaseCommand;
import net.jerrydev.baputils.utils.ChatUtils.CCodes;
import net.minecraft.command.CommandException;

import java.util.Arrays;
import java.util.List;

import static net.jerrydev.baputils.BapUtils.clientMessage;
import static net.jerrydev.baputils.utils.ChatUtils.cc;

public final class BapTrust extends BaseCommand {
    @Override
    public String getName() {
        return "trust";
    }

    @Override
    public List<String> getAliases() {
        return Arrays.asList("friend", "allow", "add");
    }

    @Override
    public String getUsage() {
        return cc(CCodes.YELLOW, "/bap " + this.getName())
            + cc(CCodes.GOLD, "|" + String.join("|", this.getAliases()))
            + cc(CCodes.YELLOW, " <player>");
    }

    @Override
    public byte getRequiredParams() {
        return 1;
    }

    @Override
    public String getDesc() {
        return "Add a player to the trusted list";
    }

    @Override
    public void run(List<String> args) throws CommandException {
        if (args.isEmpty()) {
            BapUtils.throwCommandException("Your must specify a player.");
            return;
        }

        clientMessage(cc(Arrays.asList(CCodes.GRAY, CCodes.ITALIC), "This command is currently under development... zzz..."));
    }
}
