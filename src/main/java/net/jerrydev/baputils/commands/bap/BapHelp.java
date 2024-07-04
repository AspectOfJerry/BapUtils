package net.jerrydev.baputils.commands.bap;

import net.jerrydev.baputils.Constants;
import net.jerrydev.baputils.commands.BapCommand;
import net.jerrydev.baputils.commands.IBapRunnable;
import net.jerrydev.baputils.utils.ChatUtils.CCodes;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static net.jerrydev.baputils.BapUtils.clientMessage;
import static net.jerrydev.baputils.BapUtils.errorMessage;
import static net.jerrydev.baputils.utils.ChatUtils.cc;

public final class BapHelp implements IBapRunnable {
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
        return cc(CCodes.YELLOW, "/bap " + this.getName())
            + cc(CCodes.GOLD, "|" + String.join("|", this.getAliases()));
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
    public void run(List<String> args) {
        new Thread(() -> {
            try {
                clientMessage(cc(CCodes.YELLOW, "lmao just look at the code"));

                Thread.sleep(2000);

                clientMessage("...");

                Thread.sleep(1500);

                clientMessage(cc(CCodes.YELLOW, "just kidding here's some helpful information"));

                Thread.sleep(1500);

                final List<String> bapAliases = new ArrayList<>(BapCommand.commandAliases);

                bapAliases.set(bapAliases.indexOf("jerry"), cc(CCodes.AQUA, "pig"));
                bapAliases.set(bapAliases.indexOf("pig"), cc(CCodes.LIGHT_PURPLE, "pig"));
                bapAliases.set(bapAliases.indexOf("tom"), cc(CCodes.RED, "tom"));
                bapAliases.set(bapAliases.indexOf("fishing"), cc(CCodes.GREEN, "fishing"));

                clientMessage(cc(CCodes.GREEN, "Commands with their aliases (v" + Constants.kModVersion + "):"));

                clientMessage(cc(CCodes.YELLOW, "/bap")
                    + cc(CCodes.GOLD, "|" + String.join("|", bapAliases))
                    + "\n" + cc(CCodes.GRAY, "  - Displays the main GUI"));

                for (final IBapRunnable subCmd : BapCommand.subcommands) {
                    clientMessage(subCmd.getUsage() + "\n" + cc(CCodes.GRAY, "  - " + subCmd.getDesc()));
                }
            } catch (final InterruptedException err) {
                errorMessage("InterruptedException in BapHelp. How did you even manage to do that?");
            }
        }).start();
    }
}
