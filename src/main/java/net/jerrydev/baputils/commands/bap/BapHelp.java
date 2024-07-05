package net.jerrydev.baputils.commands.bap;

import net.jerrydev.baputils.Constants;
import net.jerrydev.baputils.commands.BapCommand;
import net.jerrydev.baputils.commands.BaseCommand;
import net.jerrydev.baputils.utils.ChatUtils.CCodes;

import java.util.Collections;
import java.util.List;

import static net.jerrydev.baputils.BapUtils.clientMessage;
import static net.jerrydev.baputils.BapUtils.errorMessage;
import static net.jerrydev.baputils.utils.ChatUtils.cc;

public final class BapHelp extends BaseCommand {
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
        return super.getUsage();
    }

    @Override
    public byte getRequiredParams() {
        return super.getRequiredParams();
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

                clientMessage(cc(CCodes.GREEN, "Commands with their aliases (v" + Constants.kModVersion + "):"));

                clientMessage(cc(CCodes.YELLOW, "/bap")
                    + cc(CCodes.GOLD, "|" + String.join("|", BapCommand.commandAliases))
                    + "\n" + cc(CCodes.GRAY, "  - Displays the main GUI"));

                for (final BaseCommand subCmd : BapCommand.subcommands) {
                    clientMessage(subCmd.getUsage() + "\n" + cc(CCodes.GRAY, "  - " + subCmd.getDesc()));
                }
            } catch (final InterruptedException err) {
                errorMessage("InterruptedException in BapHelp. How did you even manage to do that?");
            }
        }).start();
    }
}
