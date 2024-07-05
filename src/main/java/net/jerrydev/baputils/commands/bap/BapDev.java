package net.jerrydev.baputils.commands.bap;

import net.jerrydev.baputils.commands.BaseCommand;
import net.jerrydev.baputils.utils.ChatUtils.CCodes;

import java.util.Collections;
import java.util.List;

import static net.jerrydev.baputils.BapUtils.clientMessage;
import static net.jerrydev.baputils.utils.ChatUtils.cc;

public final class BapDev extends BaseCommand {
    @Override
    public String getName() {
        return "dev";
    }

    @Override
    public List<String> getAliases() {
        return Collections.singletonList("test");
    }

    @Override
    public String getUsage() {
        return cc(CCodes.YELLOW, "/bap " + this.getName())
            + cc(CCodes.GOLD, "|" + String.join("|", this.getAliases()))
            + cc(CCodes.YELLOW, " <...>");
    }

    @Override
    public byte getRequiredParams() {
        return -1;
    }

    @Override
    public String getDesc() {
        return "Experimental command";
    }

    @Override
    public void run(List<String> args) {
        clientMessage(cc(CCodes.GRAY, "zzz... nothing here..."));
    }
}
