package net.jerrydev.baputils.commands;

import net.minecraft.command.CommandException;

import java.util.List;

public interface BapExecutable {
    String getName();

    List<String> getAliases();

    String getUsage();

    byte getRequiredParams();

    String getDesc();

    void execute(List<String> args) throws CommandException;
}
