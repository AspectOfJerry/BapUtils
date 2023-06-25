package net.jerrydev.baputils.commands;

import net.minecraft.command.CommandBase;
import net.minecraft.command.CommandException;
import net.minecraft.command.ICommandSender;

import java.util.Arrays;
import java.util.List;

public class BapCommand extends CommandBase {
    @Override
    public String getCommandName() {
        return "bap";
    }

    @Override
    public List<String> getCommandAliases(){
        return Arrays.asList("hipig");
    }

    @Override
    public String getCommandUsage(ICommandSender sender) {
        return "/" + this.getCommandName();
    }

    @Override
    public int getRequiredPermissionLevel() { return 0; }

    @Override
    public void processCommand(ICommandSender sender, String[] args) throws CommandException {
        System.out.println("BAP$MAIN COMMAND EXEC");
    }
}
