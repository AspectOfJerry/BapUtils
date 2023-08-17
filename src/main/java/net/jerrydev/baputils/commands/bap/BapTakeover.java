package net.jerrydev.baputils.commands.bap;

import net.jerrydev.baputils.AtomicMemCache;
import net.jerrydev.baputils.BapUtils;
import net.jerrydev.baputils.Constants;
import net.jerrydev.baputils.utils.ChatStyles.CCodes;
import net.jerrydev.baputils.utils.Debug;
import net.minecraft.client.Minecraft;

import java.util.Arrays;
import java.util.List;

import static net.jerrydev.baputils.BapUtils.*;
import static net.jerrydev.baputils.utils.ChatStyles.ccolorize;

public final class BapTakeover {
    public static final String commandName = "takeover";
    public static final List<String> commandAliases = Arrays.asList("ptake", "take", "pto", "to");
    public static final String commandUsage = ccolorize(CCodes.YELLOW, "/bap " + commandName)
        + ccolorize(CCodes.DARK_GRAY, "|" + String.join("|", commandAliases));
    public static final byte requiredParams = 0;

    public static void execute() {
        new Thread(() -> {
            try {
                // ClientCommandHandler.instance.executeCommand(Minecraft.getMinecraft().thePlayer, "/party list");
                queueCommand("party list");
                Debug.cout("Sleeping for 100ms on " + Debug.getThreadInfoFormatted());
                Thread.sleep(100);
                Debug.cout("Resumed " + Debug.getThreadInfoFormatted());

                if((AtomicMemCache.lastPartyLeader.get() != null)
                    && AtomicMemCache.lastPartyLeader.get().equals(Minecraft.getMinecraft().thePlayer.getName())) {
                    queueErrorMessage("You are already the party leader!");
                    return;
                }

                queueCommand("party chat $pto");
            } catch(InterruptedException e) {
                throw new RuntimeException(e);
            }
        }).start();
    }

    public static void handleChat(String cleanMessage) {
        final String[] messageSplit = cleanMessage.split(" ");

        final String sender = messageSplit[2];

        new Thread(() -> {
            try {
                Debug.cout("Sleeping for " + Constants.kChatDelayMs + "ms on " + Debug.getThreadInfoFormatted());
                Thread.sleep(Constants.kChatDelayMs);
                Debug.cout("Resumed " + Debug.getThreadInfoFormatted());
                queueCommand("party list");
                Debug.cout("Sleeping for 150ms on " + Debug.getThreadInfoFormatted());
                Thread.sleep(150);
                Debug.cout("Resumed " + Debug.getThreadInfoFormatted());

                if(AtomicMemCache.lastPartyLeader.get() == null) {
                    queueWarnMessage("Couldn't find the latest party leader, what's going on!? Attempting a transfer anyway.");
                    Debug.cout("Check the cached party leader using /bap cache");
                }

                if(!AtomicMemCache.lastPartyLeader.get().equals(Minecraft.getMinecraft().thePlayer.getName())
                    && (AtomicMemCache.lastPartyLeader.get() != null)) {
                    clientVerbose("We are not party leader");
                    Debug.cout("We are not leader; not attempting a transfer. Latest party leader is: " + AtomicMemCache.lastPartyLeader);
                    return;
                }

                queueCommand("party transfer " + sender);
            } catch(InterruptedException err) {
                BapUtils.queueErrorMessage("Takeover failed! An unknown error occurred while transferring the party.");
            }
        }).start();
    }

    /*@Deprecated
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
                        BapUtils.queueServerMessage("transferring!");
                        BapUtils.debugOut("Sleeping for " + Constants.kChatDelayMs + "ms on " + Debug.getThreadInfoFormatted());
                        Thread.sleep(Constants.kChatDelayMs);
                        BapUtils.debugOut("Resumed " + Debug.getThreadInfoFormatted());

                        BapUtils.queueServerMessage("/party transfer " + playerName, false);
                    } catch (InterruptedException err) {
                        BapUtils.queueClientMessage(ccolorize(CCodes.RED, "Takeover failed! An error occurred while transferring the party."));
                    }
                }).start();
            }
        }
    }*/
}
