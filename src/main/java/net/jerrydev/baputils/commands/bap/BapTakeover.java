package net.jerrydev.baputils.commands.bap;

import net.jerrydev.baputils.BapUtils;
import net.jerrydev.baputils.utils.ChatColors.CCodes;
import net.jerrydev.baputils.utils.IBapCommand;
import net.jerrydev.baputils.utils.StringHex;
import net.minecraft.client.Minecraft;

import java.util.Arrays;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static net.jerrydev.baputils.utils.ChatColors.ccolorize;

public class BapTakeover implements IBapCommand {
    public static final String commandName = "takeover";
    public static final List<String> commandAliases = Arrays.asList("ptake", "take", "pto", "to");
    public static final String commandUsage = ccolorize(CCodes.YELLOW, "/bap " + commandName)
            + ccolorize(CCodes.DARK_GRAY, "|" + String.join("|", commandAliases))
            + ccolorize(CCodes.YELLOW, " <player>");
    public static byte requiredParams = 1;

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
                        BapUtils.queueClientMessage(ccolorize(CCodes.RED, "Takeover failed! An error occurred while transferring the party."));
                    }
                }).start();
            }
        }
    }
}
