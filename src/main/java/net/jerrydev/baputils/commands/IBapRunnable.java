package net.jerrydev.baputils.commands;

import net.minecraft.command.CommandException;

import java.util.List;

public interface IBapRunnable {
    String getName();

    List<String> getAliases();

    String getUsage();

    byte getRequiredParams();

    String getDesc();

    void run(List<String> args) throws CommandException;
}
