package net.jerrydev.baputils.events;

import net.jerrydev.baputils.RuntimeData;
import net.jerrydev.baputils.commands.bap.BapJoinDungeon;
import net.jerrydev.baputils.commands.bap.BapTakeover;
import net.jerrydev.baputils.utils.Debug;
import net.minecraftforge.client.event.ClientChatReceivedEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static net.jerrydev.baputils.utils.ChatColors.removeHypixelRanks;
import static net.jerrydev.baputils.utils.ChatColors.stripColorCodes;

public class ChatHandler {
    @SubscribeEvent
    public void onChatReceived(ClientChatReceivedEvent event) {
        /*
          event.type:
          Introduced in 1.8:
          0 : Standard Text Message
          1 : 'System' message, displayed as standard text.
          2 : 'Status' message, displayed above action bar, where song notifications are.
         */

        // listen for '/party list' command and set the most recent party leader
        if (event.type == 0 && removeHypixelRanks(stripColorCodes(event.message.getUnformattedText())).matches("(?i)Party leader: (.*)")) {
            String message = stripColorCodes(removeHypixelRanks(event.message.getUnformattedText()));

            String regExpPattern = "(?i)Party leader: (.*)"; // TODO: change this to the real message

            if (message.matches(regExpPattern)) {
                Pattern regex = Pattern.compile(regExpPattern, Pattern.CASE_INSENSITIVE);
                Matcher matcher = regex.matcher(message);

                final String oldLeader = RuntimeData.latestPartyLeader;
                final String newLeader = matcher.group(1);

                if (newLeader.equals(oldLeader)) {
                    return;
                }

                RuntimeData.latestPartyLeader = newLeader;
                Debug.cout("Updated latest party leader to " + newLeader + " from " + oldLeader);
            }
        }

        if (/*BapConfig.INSTANCE.getPartyTakeoverMaster() &&*/
            event.type == 0 && removeHypixelRanks(stripColorCodes(event.message.getUnformattedText())).matches("(?i)Party > .*: bap > \\$takeover")) {
            String message = stripColorCodes(removeHypixelRanks(event.message.getUnformattedText()));

            BapTakeover.handleChat(message);
            return;
        }

        if (/*BapConfig.INSTANCE.getJoinDungeonMaster() &&*/
            event.type == 0 && removeHypixelRanks(stripColorCodes(event.message.getUnformattedText())).matches("(?i)Party > .*: bap > \\$joindungeon.*")) {
            String message = stripColorCodes(removeHypixelRanks(event.message.getUnformattedText()));

            BapJoinDungeon.handleChat(message);
        }
    }
}
