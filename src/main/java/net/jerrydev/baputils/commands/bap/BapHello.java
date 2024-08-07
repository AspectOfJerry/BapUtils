package net.jerrydev.baputils.commands.bap;

import net.jerrydev.baputils.BapUtils;
import net.jerrydev.baputils.Constants;
import net.jerrydev.baputils.commands.BaseCommand;
import net.jerrydev.baputils.utils.ChatUtils.CCodes;
import net.jerrydev.baputils.utils.Debug;

import java.util.Arrays;
import java.util.List;

import static net.jerrydev.baputils.utils.ChatUtils.cc;

public final class BapHello extends BaseCommand {
    @Override
    public String getName() {
        return "hello";
    }

    @Override
    public List<String> getAliases() {
        return Arrays.asList("hi", "world");
    }

    @Override
    public String getUsage() {
        return super.getUsage();
    }

    @Override
    public byte getRequiredParams() {
        return super.getRequiredParams();
    }

    @Override
    public String getDesc() {
        return "Say hello to the world";
    }

    @Override
    public void run(List<String> args) {
        BapUtils.clientMessage(cc(CCodes.GREEN, "Hello, World from the client! You are on version " + Constants.kModVersion), true);
        BapUtils.warnMessage("Hello from the warning message chat!");
        BapUtils.commandError("Hello from the error message chat!");
        BapUtils.clientVerbose("Hello from the client verbose chat!");
        Debug.dout("Hello from the client debug chat!");
        BapUtils.queuePartyChat("Hello, World!", true);
    }
}
