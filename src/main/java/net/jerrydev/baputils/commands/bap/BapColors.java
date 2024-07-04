package net.jerrydev.baputils.commands.bap;

import net.jerrydev.baputils.commands.IBapRunnable;
import net.jerrydev.baputils.utils.ChatUtils.CCodes;

import java.util.Arrays;
import java.util.List;

import static net.jerrydev.baputils.BapUtils.clientMessage;
import static net.jerrydev.baputils.utils.ChatUtils.cc;

public final class BapColors implements IBapRunnable {
    @Override
    public String getName() {
        return "colors";
    }

    @Override
    public List<String> getAliases() {
        return Arrays.asList("colours", "color", "colour", "cc");
    }

    @Override
    public String getUsage() {
        return cc(CCodes.YELLOW, "/bap " + this.getName())
            + cc(CCodes.GOLD, "|" + String.join("|", this.getAliases()));
    }

    @Override
    public byte getRequiredParams() {
        return 0;
    }

    @Override
    public String getDesc() {
        return "Displays (&) color codes";
    }

    @Override
    public void run(List<String> args) {
        clientMessage(cc(CCodes.GREEN, "Minecraft color codes:"));

        for (final CCodes c : CCodes.values()) {
            clientMessage(c.colorCode.replaceAll("§", "&") + " " + cc(c, c.toString()), false);
        }
    }
}
