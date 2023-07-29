package net.jerrydev.baputils.commands.bap.hypixel;

import net.jerrydev.baputils.BapUtils;
import net.jerrydev.baputils.utils.ChatColors;
import net.jerrydev.baputils.utils.StringHex;
import net.jerrydev.baputils.utils.IBapBaseCommand;
import net.minecraft.client.Minecraft;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class BapTakeover implements IBapBaseCommand {
    public static void execute(String playerName) {
        new Thread(() -> {
            try {
                Thread.sleep(50);

                // ClientCommandHandler.instance.executeCommand(Minecraft.getMinecraft().thePlayer, "/party list");
                BapUtils.queueServerMessage("/party list", false);
                Thread.sleep(50);

                BapUtils.queueServerMessage("takeover > " + StringHex.stringToHex(playerName));
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }).start();
    }

    public static void handleChat(String message) {
        String[] messageSplit = message.split(" ");

        String playerHex = messageSplit[messageSplit.length - 1];
        String playerString = StringHex.hexToString(playerHex);
        messageSplit[messageSplit.length - 1] = playerString;
        message = String.join(" ", messageSplit);

        String regExpPattern = "(?i)Party > (.*): bap > takeover > "
                + Pattern.quote(Minecraft.getMinecraft().thePlayer.getName());

        if (message.matches(regExpPattern)) {
            Pattern regex = Pattern.compile(regExpPattern, Pattern.CASE_INSENSITIVE);
            Matcher matcher = regex.matcher(message);
            if (matcher.matches()) {
                String playerName = matcher.group(1);

                // Transfer the party
                new Thread(() -> {
                    try {
                        Thread.sleep(50);

                        BapUtils.queueServerMessage("Transferring...");
                        Thread.sleep(50);

                        BapUtils.queueServerMessage("/party transfer " + playerName, false);
                    } catch (InterruptedException err) {
                        err.printStackTrace();
                        BapUtils.queueClientMessage(ChatColors.colorize(ChatColors.CCodes.RED, "Takeover failed! An error occurred while transferring the party."));
                    }
                }).start();
            }
        }
    }
}
