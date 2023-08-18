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
import static net.jerrydev.baputils.utils.Debug.dout;

public final class BapWarp {
    public static final String commandName = "warp";
    public static final List<String> commandAliases = Arrays.asList("pwarp", "pw", "w");
    public static final String commandUsage = ccolorize(CCodes.YELLOW, "/bap " + commandName)
        + ccolorize(CCodes.DARK_GRAY, "|" + String.join("|", commandAliases));
    public static final byte requiredParams = 0;

    public static void execute() {
        new Thread(() -> {
            try {
                // ClientCommandHandler.instance.executeCommand(Minecraft.getMinecraft().thePlayer, "/party list");
                queueCommand("party list");
                dout("Sleeping for 250ms on " + Debug.getThreadInfoFormatted());
                Thread.sleep(250);
                dout("Resumed " + Debug.getThreadInfoFormatted());

                if((AtomicMemCache.lastPartyLeader.get() != null)
                    && AtomicMemCache.lastPartyLeader.get().equals(Minecraft.getMinecraft().thePlayer.getName())) {
                    queueErrorMessage("You are already the party leader!");
                    return;
                }

                queueCommand("party chat $warp");
            } catch(InterruptedException e) {
                throw new RuntimeException(e);
            }
        }).start();
    }

    public static void handleChat(String cleanMessage) {
        final String[] messageSplit = cleanMessage.split(" ");
        final String sender = messageSplit[2].replaceAll(":", "");

        if(sender.equals(Minecraft.getMinecraft().thePlayer.getName())) {
            dout("We are the sender, ignoring");
            return;
        }

        new Thread(() -> {
            try {
                queueCommand("party list");
                dout("Sleeping for " + Constants.kCommandDelayMs + "ms on " + Debug.getThreadInfoFormatted());
                Thread.sleep(Constants.kCommandDelayMs);
                dout("Resumed " + Debug.getThreadInfoFormatted());

                if(AtomicMemCache.lastPartyLeader.get() == null) {
                    queueWarnMessage("Couldn't find the latest party leader, what's going on!? Continuing execution anyway.");
                    dout("Check the cached party leader using /bap cache");
                }

                if(!AtomicMemCache.lastPartyLeader.get().equals(Minecraft.getMinecraft().thePlayer.getName())
                    && (AtomicMemCache.lastPartyLeader.get() != null)) {
                    clientVerbose("We are not party leader");
                    dout("We are not leader; not warping. Latest party leader is: " + AtomicMemCache.lastPartyLeader);
                    return;
                }

                queueCommand("party chat bap > okay! Warping the party in 2.5s...");
                Debug.dout("Sleeping for 2500ms on " + Debug.getThreadInfoFormatted());
                Thread.sleep(2500);
                Debug.dout("Resumed " + Debug.getThreadInfoFormatted());

                queueCommand("party warp");
            } catch(InterruptedException err) {
                BapUtils.queueErrorMessage("InterruptedException: Takeover failed! An error occurred while transferring the party.");
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
