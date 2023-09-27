package net.jerrydev.baputils.commands.bap;

import net.jerrydev.baputils.commands.BapExecutable;
import net.jerrydev.baputils.utils.ChatUtils.CCodes;

import java.util.Arrays;
import java.util.List;

import static net.jerrydev.baputils.BapUtils.clientMessage;
import static net.jerrydev.baputils.utils.ChatUtils.ccolorize;

public final class BapColors implements BapExecutable {
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
        return ccolorize(CCodes.YELLOW, "/bap " + this.getName())
            + ccolorize(CCodes.GOLD, "|" + String.join("|", this.getAliases()));
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
    public void execute(List<String> args) {
        clientMessage(ccolorize(CCodes.GREEN, "Minecraft color codes:"));

        for (final CCodes c : CCodes.values()) {
            clientMessage(c.colorCode.replaceAll("§", "&") + " " + ccolorize(c, c.toString()), false);
        }
    }
}
