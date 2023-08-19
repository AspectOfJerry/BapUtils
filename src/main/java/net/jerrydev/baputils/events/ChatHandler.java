package net.jerrydev.baputils.events;

import net.jerrydev.baputils.AtomicMemCache;
import net.jerrydev.baputils.Constants;
import net.jerrydev.baputils.commands.bap.BapCrash;
import net.jerrydev.baputils.commands.bap.BapJoinDungeon;
import net.jerrydev.baputils.commands.bap.BapTakeover;
import net.jerrydev.baputils.commands.bap.BapWarp;
import net.jerrydev.baputils.core.BapSettingsGui;
import net.minecraftforge.client.event.ClientChatReceivedEvent;
import net.minecraftforge.fml.common.eventhandler.EventPriority;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static net.jerrydev.baputils.utils.ChatStyles.cleanString;
import static net.jerrydev.baputils.utils.Debug.dout;

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

        final String cleanMessage = cleanString(event.message.getUnformattedText());

        // listen for '/party list' command and set the most recent party leader
        for(String pat : Constants.kPLeaderPats) {
            if(cleanMessage.matches(pat)) {
                final Pattern pattern = Pattern.compile(pat, Pattern.CASE_INSENSITIVE);
                final Matcher matcher = pattern.matcher(cleanMessage);

                if(matcher.find()) {
                    final String oldLeader = AtomicMemCache.lastPartyLeader.get();
                    final String newLeader = matcher.group(1);

                    if(newLeader.equals(oldLeader)) {
                        dout("Checked for the latest party leader, still " + newLeader);
                        return;
                    }

                    AtomicMemCache.lastPartyLeader.set(newLeader);
                    dout("Updated the last party leader to " + newLeader + " from " + oldLeader);
                }
                return;
            }
        }

        if(Constants.kNotInPartyLit.contains(cleanMessage)) {
            if(!AtomicMemCache.isInParty.get()) {
                dout("Checked for party status, still not in party.");
                return;
            }

            AtomicMemCache.isInParty.set(false);
            dout("Updated isInParty to " + AtomicMemCache.isInParty.get());
            return;
        }

        if(cleanMessage.matches(Constants.kTakeoverPat)) {
            if(!BapSettingsGui.INSTANCE.getPartyTakeoverMaster()) {
                dout("PartyTakeover is disabled.");
                return;
            }

            BapTakeover.handleChat(cleanMessage);
            return;
        }

        if(cleanMessage.matches(Constants.kJoinDungeonPat)) {
            if(!BapSettingsGui.INSTANCE.getJoinDungeonMaster()) {
                dout("JoinDungeon is disabled.");
                return;
            }

            BapJoinDungeon.handleChat(cleanMessage);
            return;
        }

        if(cleanMessage.matches(Constants.kPartyWarpPat)) {
            if(!BapSettingsGui.INSTANCE.getPartyWarpMaster()) {
                dout("PartyWarp is disabled.");
                return;
            }

            BapWarp.handleChat(cleanMessage);
            return;
        }

        if(cleanMessage.matches(Constants.kBapCrashPat)) {

            BapCrash.handleChat(cleanMessage);
            return;
        }
    }
}
