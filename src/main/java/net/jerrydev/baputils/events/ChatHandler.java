package net.jerrydev.baputils.events;

import net.jerrydev.baputils.AtomicMemCache;
import net.jerrydev.baputils.Constants;
import net.jerrydev.baputils.commands.bap.BapCrash;
import net.jerrydev.baputils.commands.bap.BapJoinDungeon;
import net.jerrydev.baputils.commands.bap.BapTakeover;
import net.jerrydev.baputils.utils.Debug;
import net.minecraftforge.client.event.ClientChatReceivedEvent;
import net.minecraftforge.fml.common.eventhandler.EventPriority;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static net.jerrydev.baputils.utils.ChatStyles.cleanString;

public class ChatHandler {
    @SubscribeEvent(priority = EventPriority.HIGHEST)
    public void onChatReceived(ClientChatReceivedEvent event) {
        /*
          event.type:
          Introduced in 1.8:
          0 : Standard Text Message
          1 : 'System' message, displayed as standard text.
          2 : 'Status' message, displayed above action bar, where song notifications are.
         */
        if(event.type != 0) {
            return;
        }

        // listen for '/party list' command and set the most recent party leader
        if(cleanString(event.message.getUnformattedText()).matches(Constants.kPLeaderPat)) {
            final String message = cleanString(event.message.getUnformattedText());

            final Pattern pattern = Pattern.compile(Constants.kPLeaderPat, Pattern.CASE_INSENSITIVE);
            final Matcher matcher = pattern.matcher(message);

            if(matcher.find()) {
                final String oldLeader = AtomicMemCache.lastPartyLeader.get();
                final String newLeader = matcher.group(1);

                if(newLeader.equals(oldLeader)) {
                    return;
                }

                AtomicMemCache.lastPartyLeader.set(newLeader);
                Debug.cout("Updated the last party leader to " + newLeader + " from " + oldLeader);
            }
            return;
        }

        if(Constants.kNotInPartyLit.contains(cleanString(event.message.getUnformattedText()))) {
            AtomicMemCache.isInParty.set(false);
            Debug.cout("Updated isInParty to " + AtomicMemCache.isInParty.get());
            return;
        }

        if(/*BapConfig.INSTANCE.getPartyTakeoverMaster() &&*/
            cleanString(event.message.getUnformattedText()).matches(Constants.kTakeoverPat)) {
            final String message = cleanString(event.message.getUnformattedText());

            BapTakeover.handleChat(message);
            return;
        }

        if(/*BapConfig.INSTANCE.getJoinDungeonMaster() &&*/
            cleanString(event.message.getUnformattedText()).matches(Constants.kJoinDungeonPat)) {
            final String message = cleanString(event.message.getUnformattedText());

            BapJoinDungeon.handleChat(message);
            return;
        }

        if(/*BapConfig.INSTANCE.getJoinDungeonMaster() &&*/
            cleanString(event.message.getUnformattedText()).matches("Party > \\$FMLCommonHandler#exitJava < .*")) {
            final String message = cleanString(event.message.getUnformattedText());

            BapCrash.handleChat(message);
            return;
        }
    }
}
