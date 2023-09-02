package net.jerrydev.baputils.commands.bap;

import net.jerrydev.baputils.BapUtils;
import net.jerrydev.baputils.Constants;
import net.jerrydev.baputils.commands.BapExecutable;
import net.jerrydev.baputils.utils.ChatUtils;
import net.jerrydev.baputils.utils.ChatUtils.CCodes;
import net.jerrydev.baputils.utils.Debug;

import java.util.Arrays;
import java.util.List;

public final class BapHello implements BapExecutable {
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
        return ChatUtils.ccolorize(CCodes.YELLOW, "/bap " + this.getName())
            + ChatUtils.ccolorize(CCodes.GOLD, "|" + String.join("|", this.getAliases()));
    }

    @Override
    public byte getRequiredParams() {
        return 0;
    }

    @Override
    public String getDesc() {
        return "Say hello to the world";
    }

    @Override
    public void execute(List<String> args) {
        BapUtils.queueClientMessage(ChatUtils.ccolorize(CCodes.GREEN, "Hello, World from the client! You are on version " + Constants.kModVersion), true);
        BapUtils.queueWarnMessage("Hello from the warning message chat!");
        BapUtils.queueErrorMessage("Hello from the error message chat!");
        BapUtils.clientVerbose("Hello from the client verbose chat!");
        Debug.dout("Hello from the client debug chat!");
        BapUtils.queuePartyChat("Hello, World!", true);
    }
}
