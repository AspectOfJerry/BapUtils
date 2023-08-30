package net.jerrydev.baputils.events;

import net.jerrydev.baputils.AtomicCache;
import net.jerrydev.baputils.Constants;
import net.jerrydev.baputils.commands.bap.BapCrash;
import net.jerrydev.baputils.commands.bap.BapJoinDungeon;
import net.jerrydev.baputils.commands.bap.BapTakeover;
import net.jerrydev.baputils.commands.bap.BapWarp;
import net.jerrydev.baputils.core.BapSettingsGui;
import net.jerrydev.baputils.features.dungeons.CatacombsFloors;
import net.jerrydev.baputils.features.dungeons.DungeonDeath;
import net.jerrydev.baputils.features.dungeons.PuzzleFail;
import net.minecraftforge.client.event.ClientChatReceivedEvent;
import net.minecraftforge.fml.common.eventhandler.EventPriority;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static net.jerrydev.baputils.BapUtils.queueErrorMessage;
import static net.jerrydev.baputils.Constants.*;
import static net.jerrydev.baputils.utils.ChatStyles.cleanString;
import static net.jerrydev.baputils.utils.Debug.dout;

public class ChatListener {
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
        for(final String pat : kPLeaderPs) {
            if(cleanMessage.matches(pat)) {
                final Pattern pattern = Pattern.compile(pat, Pattern.CASE_INSENSITIVE);
                final Matcher matcher = pattern.matcher(cleanMessage);

                if(matcher.find()) {
                    final String oldLeader = AtomicCache.lastPartyLeader.get();
                    final String newLeader = matcher.group(1);

                    if(newLeader.equals(oldLeader)) {
                        dout("Received an update for lastPartyLeader, still " + newLeader);
                    } else {
                        AtomicCache.lastPartyLeader.set(newLeader);
                        dout("Updated lastPartyLeader to " + newLeader + " from " + oldLeader);
                    }

                    // if there is the party leader message, we are in a party
                    if(!AtomicCache.isInParty.get()) {
                        AtomicCache.isInParty.set(true);
                        dout("Updated isInParty to true");
                    }
                }
            }
        }

        if(cleanMessage.matches(kCatacombsJoinP)) {
            final Pattern pattern = Pattern.compile(kCatacombsJoinP, Pattern.CASE_INSENSITIVE);
            final Matcher matcher = pattern.matcher(cleanMessage);

            if(!matcher.find()) {
                queueErrorMessage("kCatacombsJoinPat no groups found. This is impossible! Please open a bug report at " + Constants.kGitHubIssues);
                return;
            }

            final CatacombsFloors oldFloor = AtomicCache.lastCatacombsFloor.get();
            final CatacombsFloors newFloor = CatacombsFloors.getFloorByChatName(matcher.group(2));

            if(newFloor == null) {
                dout("Could not find a CatacombsFloor for " + matcher.group(2));
                return;
            }

            if((oldFloor != null) && newFloor.floorCode.equals(oldFloor.floorCode)) {
                dout("Received an update for lastCatacombsFloor, still " + newFloor);
            } else {
                AtomicCache.lastCatacombsFloor.set(newFloor);
                dout("Updated lastCatacombsFloor to " + newFloor + " from " + oldFloor);
            }
            return;
        }

        if(cleanMessage.matches(kInPartyP)) {
            AtomicCache.isInParty.set(true);
            dout("Updated isInParty to " + AtomicCache.isInParty.get());

            System.out.println(cleanMessage);

            final Pattern pattern = Pattern.compile(kInPartyP);
            final Matcher matcher = pattern.matcher(cleanMessage);
            if(matcher.find()) {
                final String partyLeader = matcher.group(1);
                //final String partyLeader = pattern.matcher(cleanMessage).group(1); // not separating matcher declaration bc y not

                AtomicCache.lastPartyLeader.set(partyLeader);
                dout("Updated lastPartyLeader to " + partyLeader);
                return;
            }
        }

        for(final String pattern : kNotInPartyPs) {
            if(cleanMessage.matches(pattern)) {
                if(!AtomicCache.isInParty.get()) {
                    dout("Received an update for isInParty, still not in party.");
                    return;
                }

                AtomicCache.isInParty.set(false);
                dout("Updated isInParty to " + AtomicCache.isInParty.get());
                return;
            }
        }

        for(final String pattern : kDungeonDeathPs) {
            if(cleanMessage.matches(pattern)) {
                DungeonDeath.handleChat(cleanMessage);
                return;
            }
        }

        if(cleanMessage.matches(kPuzzleFailP)) {
            if(BapSettingsGui.INSTANCE.getPuzzleFailMsg().isEmpty()) {
                dout("PuzzleFailMsg is disabled.");
                return;
            }

            PuzzleFail.handleChat();
        }

        if(cleanMessage.matches(kTakeoverP)) {
            if(!BapSettingsGui.INSTANCE.getPartyTakeoverMaster()) {
                dout("PartyTakeover is disabled.");
                return;
            }

            BapTakeover.handleChat(cleanMessage);
            return;
        }

        if(cleanMessage.matches(kJoinDungeonP)) {
            if(!BapSettingsGui.INSTANCE.getJoinDungeonMaster()) {
                dout("JoinDungeon is disabled.");
                return;
            }

            BapJoinDungeon.handleChat(cleanMessage);
            return;
        }

        if(cleanMessage.matches(kAutoJoinInP)) {
            if(!BapSettingsGui.INSTANCE.getJoinDungeonAutoJoinIn()) {
                dout("AutoJoinIn is disabled.");
                return;
            }

            BapJoinDungeon.autoJoinIn(cleanMessage);
            return;
        }

        if(cleanMessage.matches(kPartyWarpP)) {
            if(!BapSettingsGui.INSTANCE.getPartyWarpMaster()) {
                dout("PartyWarp is disabled.");
                return;
            }

            BapWarp.handleChat(cleanMessage);
            return;
        }

        if(cleanMessage.matches(kBapCrashP)) {

            BapCrash.handleChat(cleanMessage);
            return;
        }
    }
}
