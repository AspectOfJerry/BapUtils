package net.jerrydev.baputils.commands.bap;

import net.jerrydev.baputils.BapUtils;
import net.jerrydev.baputils.Constants;
import net.jerrydev.baputils.commands.IBapRunnable;
import net.jerrydev.baputils.guis.BapGui;
import net.jerrydev.baputils.utils.ChatUtils.CCodes;

import java.util.Arrays;
import java.util.List;

import static net.jerrydev.baputils.utils.ChatUtils.ccolorize;
import static net.jerrydev.baputils.utils.Debug.dout;

public final class BapOptions implements IBapRunnable {
    @Override
    public String getName() {
        return "options";
    }

    @Override
    public List<String> getAliases() {
        return Arrays.asList("menu", "gui");
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
        return "Opens the options GUI";
    }

    @Override
    public void run(List<String> args) {
        dout("You are on version " + Constants.kModVersion + "!");
        BapUtils.setActiveGui(new BapGui());
    }
}
