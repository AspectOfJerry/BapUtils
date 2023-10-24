package net.jerrydev.baputils.commands.bap;

import net.jerrydev.baputils.BapUtils;
import net.jerrydev.baputils.commands.IBapRunnable;
import net.jerrydev.baputils.utils.ChatUtils.CCodes;
import net.minecraft.command.CommandException;

import java.util.Arrays;
import java.util.List;

import static net.jerrydev.baputils.BapUtils.clientMessage;
import static net.jerrydev.baputils.utils.ChatUtils.ccolorize;

public final class BapUuid implements IBapRunnable {
    @Override
    public String getName() {
        return "uuid";
    }

    @Override
    public List<String> getAliases() {
        return Arrays.asList("player", "id");
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
        return "Displays a player's UUID";
    }

    @Override
    public void run(List<String> args) throws CommandException {
        if (args.isEmpty()) {
            BapUtils.throwCommandException("You must specify a player.");
            return;
        }

        clientMessage(ccolorize(Arrays.asList(CCodes.GRAY, CCodes.ITALIC), "This command is currently under development... zzz..."));
    }
}
