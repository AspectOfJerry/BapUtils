package net.jerrydev.baputils.commands;

import net.minecraft.command.CommandException;

import java.util.Collections;
import java.util.List;

import static net.jerrydev.baputils.utils.ChatUtils.CCodes;
import static net.jerrydev.baputils.utils.ChatUtils.cc;

public abstract class BaseCommand {
    public abstract String getName();

    public List<String> getAliases() {
        return Collections.emptyList();
    }

    public String getUsage() {
        return cc(CCodes.YELLOW, "/bap " + this.getName())
            + cc(CCodes.GOLD, "|" + String.join("|", this.getAliases()));
    }

    public byte getRequiredParams() {
        return 0;
    }

    public abstract String getDesc();

    public abstract void run(List<String> args) throws CommandException;
}
