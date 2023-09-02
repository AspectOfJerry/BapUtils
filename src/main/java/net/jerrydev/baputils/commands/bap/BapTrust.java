package net.jerrydev.baputils.commands.bap;

import net.jerrydev.baputils.BapUtils;
import net.jerrydev.baputils.commands.BapExecutable;
import net.jerrydev.baputils.utils.ChatUtils.CCodes;
import net.minecraft.command.CommandException;

import java.util.Arrays;
import java.util.List;

import static net.jerrydev.baputils.BapUtils.queueClientMessage;
import static net.jerrydev.baputils.utils.ChatUtils.ccolorize;

public final class BapTrust implements BapExecutable {
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
        return ccolorize(CCodes.YELLOW, "/bap " + this.getName())
            + ccolorize(CCodes.GOLD, "|" + String.join("|", this.getAliases()))
            + ccolorize(CCodes.YELLOW, " <player>");
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
    public void execute(List<String> args) throws CommandException {
        if(args.size() == 1) {
            BapUtils.throwCommandException("Your must specify a player.");
            return;
        }

        final String playerUuid = "";

        queueClientMessage(ccolorize(Arrays.asList(CCodes.GRAY, CCodes.ITALIC), "This command is currently under development... zzz..."));
    }
}
