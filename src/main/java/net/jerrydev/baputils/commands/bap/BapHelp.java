package net.jerrydev.baputils.commands.bap;

import net.jerrydev.baputils.Constants;
import net.jerrydev.baputils.commands.BapExecutable;
import net.jerrydev.baputils.commands.BapCommand;
import net.jerrydev.baputils.utils.ChatUtils.CCodes;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static net.jerrydev.baputils.BapUtils.clientMessage;
import static net.jerrydev.baputils.BapUtils.errorMessage;
import static net.jerrydev.baputils.utils.ChatUtils.ccolorize;

public final class BapHelp implements BapExecutable {
    @Override
    public String getName() {
        return "help";
    }

    @Override
    public List<String> getAliases() {
        return Collections.singletonList("?");
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
        return "Displays this help message";
    }

    @Override
    public void execute(List<String> args) {
        new Thread(() -> {
            try {
                clientMessage(ccolorize(CCodes.YELLOW, "lmao just look at the code"));

                Thread.sleep(2000);

                clientMessage("...");

                Thread.sleep(1500);

                clientMessage(ccolorize(CCodes.YELLOW, "ok fine maybe i'll help you out a bit"));

                Thread.sleep(1500);

                final List<String> bapAliases = new ArrayList<>(BapCommand.commandAliases);

                bapAliases.set(bapAliases.indexOf("uwa"), ccolorize(CCodes.LIGHT_PURPLE, "uwa"));
                bapAliases.set(bapAliases.indexOf("pig"), ccolorize(CCodes.AQUA, "pig"));
                bapAliases.set(bapAliases.indexOf("tom"), ccolorize(CCodes.RED, "tom"));
                bapAliases.set(bapAliases.indexOf("fishing"), ccolorize(CCodes.GREEN, "fishing"));

                clientMessage(ccolorize(CCodes.GREEN, "Commands with their aliases (v" + Constants.kModVersion + "):"));

                clientMessage(ccolorize(CCodes.YELLOW, "/bap")
                    + ccolorize(CCodes.GOLD, "|" + String.join("|", bapAliases))
                    + "\n" + ccolorize(CCodes.GRAY, "  - Displays the main GUI"));

                for (final BapExecutable subCmd : BapCommand.subcommands) {
                    clientMessage(subCmd.getUsage() + "\n" + ccolorize(CCodes.GRAY, "  - " + subCmd.getDesc()));
                }
            } catch (final InterruptedException err) {
                errorMessage("InterruptedException in BapHelp. How did you even manage to do that?");
            }
        }).start();
    }
}
