package net.jerrydev.baputils.events;

import net.jerrydev.baputils.AtomicMemCache;
import net.jerrydev.baputils.Constants;
import net.jerrydev.baputils.commands.bap.BapCrash;
import net.jerrydev.baputils.commands.bap.BapJoinDungeon;
import net.jerrydev.baputils.commands.bap.BapTakeover;
import net.jerrydev.baputils.commands.bap.BapWarp;
import net.jerrydev.baputils.core.BapSettingsGui;
import net.jerrydev.baputils.dungeons.CatacombsFloors;
import net.minecraftforge.client.event.ClientChatReceivedEvent;
import net.minecraftforge.fml.common.eventhandler.EventPriority;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static net.jerrydev.baputils.BapUtils.queueErrorMessage;
import static net.jerrydev.baputils.Constants.*;
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
        for(final String pat : kPLeaderPats) {
            if(cleanMessage.matches(pat)) {
                final Pattern pattern = Pattern.compile(pat, Pattern.CASE_INSENSITIVE);
                final Matcher matcher = pattern.matcher(cleanMessage);

                if(matcher.find()) {
                    final String oldLeader = AtomicMemCache.lastPartyLeader.get();
                    final String newLeader = matcher.group(1);

                    if(newLeader.equals(oldLeader)) {
                        dout("Received an update for lastPartyLeader, still " + newLeader);
                    } else {
                        AtomicMemCache.lastPartyLeader.set(newLeader);
                        dout("Updated lastPartyLeader to " + newLeader + " from " + oldLeader);
                    }

                    // if there is the party leader message, we are in a party
                    if(!AtomicMemCache.isInParty.get()) {
                        AtomicMemCache.isInParty.set(true);
                        dout("Updated isInParty to true");
                    }
                }
            }
        }

        if(cleanMessage.matches(kCatacombsJoinPat)) {
            final Pattern pattern = Pattern.compile(kCatacombsJoinPat, Pattern.CASE_INSENSITIVE);
            final Matcher matcher = pattern.matcher(cleanMessage);

            if(!matcher.find()) {
                queueErrorMessage("kCatacombsJoinPat no groups found. This is impossible! Please open a bug report at " + Constants.kGitHubIssues);
                return;
            }

            final CatacombsFloors oldFloor = AtomicMemCache.lastCatacombsFloor.get();
            final CatacombsFloors newFloor = CatacombsFloors.getFloorByChatName(matcher.group(2));

            if(newFloor == null) {
                dout("Could not find a CatacombsFloor for " + matcher.group(2));
                return;
            }

            if((oldFloor != null) && newFloor.floorCode.equals(oldFloor.floorCode)) {
                dout("Received an update for lastCatacombsFloor, still " + newFloor);
            } else {
                AtomicMemCache.lastCatacombsFloor.set(newFloor);
                dout("Updated lastCatacombsFloor to " + newFloor + " from " + oldFloor);
            }
            return;
        }

        if(cleanMessage.matches(kInPartyPat)) {
            AtomicMemCache.isInParty.set(true);
            dout("Updated isInParty to " + AtomicMemCache.isInParty.get());

            final Pattern pattern = Pattern.compile(kInPartyPat, Pattern.CASE_INSENSITIVE);
            final String partyLeader = pattern.matcher(cleanMessage).group(1); // not separating matcher declaration bc y not

            AtomicMemCache.lastPartyLeader.set(partyLeader);
            dout("Updated lastPartyLeader to " + partyLeader);
            return;
        }

        for(final String pattern : kNotInPartyPats) {
            if(cleanMessage.matches(pattern)) {
                if(!AtomicMemCache.isInParty.get()) {
                    dout("Received an update for isInParty, still not in party.");
                    return;
                }

                AtomicMemCache.isInParty.set(false);
                dout("Updated isInParty to " + AtomicMemCache.isInParty.get());
                return;
            }
        }

        if(cleanMessage.matches(kTakeoverPat)) {
            if(!BapSettingsGui.INSTANCE.getPartyTakeoverMaster()) {
                dout("PartyTakeover is disabled.");
                return;
            }

            BapTakeover.handleChat(cleanMessage);
            return;
        }

        if(cleanMessage.matches(kJoinDungeonPat)) {
            if(!BapSettingsGui.INSTANCE.getJoinDungeonMaster()) {
                dout("JoinDungeon is disabled.");
                return;
            }

            BapJoinDungeon.handleChat(cleanMessage);
            return;
        }

        if(cleanMessage.matches(kAutoJoinInPat)) {
            if(!BapSettingsGui.INSTANCE.getJoinDungeonAutoJoinIn()) {
                dout("AutoJoinIn is disabled.");
                return;
            }

            BapJoinDungeon.autoJoinIn(cleanMessage);
            return;
        }

        if(cleanMessage.matches(kPartyWarpPat)) {
            if(!BapSettingsGui.INSTANCE.getPartyWarpMaster()) {
                dout("PartyWarp is disabled.");
                return;
            }

            BapWarp.handleChat(cleanMessage);
            return;
        }

        if(cleanMessage.matches(kBapCrashPat)) {

            BapCrash.handleChat(cleanMessage);
            return;
        }
    }
}
