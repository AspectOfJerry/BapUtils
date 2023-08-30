package net.jerrydev.baputils.commands.bap;

import net.jerrydev.baputils.AtomicCache;
import net.jerrydev.baputils.Constants;
import net.jerrydev.baputils.features.dungeons.CatacombsFloors;
import net.jerrydev.baputils.utils.ChatStyles.CCodes;
import net.jerrydev.baputils.utils.Debug;
import net.minecraft.client.Minecraft;

import java.util.Arrays;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static net.jerrydev.baputils.BapUtils.*;
import static net.jerrydev.baputils.utils.ChatStyles.ccolorize;
import static net.jerrydev.baputils.utils.Debug.dout;

public final class BapJoinDungeon {
    public static final String commandName = "joindungeon";
    public static final List<String> commandAliases = Arrays.asList("dung", "join", "run", "jd");
    public static final String commandUsage = ccolorize(CCodes.YELLOW, "/bap " + commandName)
        + ccolorize(CCodes.DARK_GRAY, "|" + String.join("|", commandAliases))
        + ccolorize(CCodes.YELLOW, " <floor>");
    public static final byte requiredParams = 1;

    public static void execute(String floorName) {
        final CatacombsFloors floor = CatacombsFloors.getFloorByCode(floorName);

        if(floor == null) {
            queueErrorMessage("Unknown floor name of " + floorName + ". Valid floor names are [fm][0-7].");
            return;
        }

        new Thread(() -> {
            try {
                queueCommand("party list");
                dout("Sleeping for " + Constants.kCommandDelayMs + "ms on " + Debug.getThreadInfoFormatted());
                Thread.sleep(Constants.kCommandDelayMs);
                dout("Resumed " + Debug.getThreadInfoFormatted());

                if((AtomicCache.lastPartyLeader.get() != null)
                    && AtomicCache.lastPartyLeader.get().equals(Minecraft.getMinecraft().thePlayer.getName())) {
                    queueErrorMessage("You are already the party leader!");
                    return;
                }

                queuePartyChat("$jd." + floorName, false);
            } catch(final InterruptedException e) {
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

        final Pattern pattern = Pattern.compile(Constants.kJoinDungeonP);
        final Matcher matcher = pattern.matcher(cleanMessage);

        if(!matcher.find()) {
            queueErrorMessage("JoinDungeon no groups found. This is impossible! Please open a bug report at " + Constants.kGitHubIssues);
            return;
        }

        final String floorName = matcher.group(1);
        final CatacombsFloors floor = CatacombsFloors.getFloorByCode(floorName);

        if(floor == null) {
            queueErrorMessage("Unknown floor name of " + floorName + ".");
            return;
        }

        new Thread(() -> {
            try {
                queueCommand("party list");
                dout("Sleeping for " + Constants.kChatDelayMs + "ms on " + Debug.getThreadInfoFormatted());
                Thread.sleep(Constants.kChatDelayMs);
                dout("Resumed " + Debug.getThreadInfoFormatted());

                if(AtomicCache.lastPartyLeader.get() == null) {
                    queueWarnMessage("Couldn't find the latest party leader, what's going on!? Continuing execution anyway.");
                    dout("Check the cached party leader using /bap cache");
                }

                if(!AtomicCache.lastPartyLeader.get().equals(Minecraft.getMinecraft().thePlayer.getName())
                    && (AtomicCache.lastPartyLeader.get() != null)) {
                    clientVerbose("We are not party leader");
                    dout("We are not leader; not entering a run. Latest party leader is: " + AtomicCache.lastPartyLeader);
                    return;
                }

                final String shortFloorName = floor.chatName.replace("The Catacombs, ", "").replace(" Catacombs, ", "");

                //queueServerMessage("okay! Joining " + (floor.isMaster ? "master " : "") + "catacombs floor " + floor.floorCode.charAt(1) + " in 3s...");
                queuePartyChat("Joining " + shortFloorName + " in 3s...");
                dout("Sleeping for 3000ms on " + Debug.getThreadInfoFormatted());
                Thread.sleep(3000);
                dout("Resumed " + Debug.getThreadInfoFormatted());

                queueCommand("joindungeon " + floor.commandCode);
            } catch(final InterruptedException err) {
                queueErrorMessage("InterruptedException: JoinDungeon failed! An error occurred while transferring the party.");
            }
        }).start();
    }

    public static void autoJoinIn(String cleanMessage) {
        final Pattern pattern = Pattern.compile(Constants.kAutoJoinInP);
        final Matcher matcher = pattern.matcher(cleanMessage);

        if(!matcher.find()) {
            queueErrorMessage("AutoJoinIn no groups found. This is impossible! Please open a bug report at " + Constants.kGitHubIssues);
            return;
        }

        new Thread(() -> {
            try {
                if(AtomicCache.lastPartyLeader.get() == null) {
                    queueCommand("party list");
                    dout("Sleeping for " + Constants.kCommandDelayMs + "ms on" + Debug.getThreadInfoFormatted());
                    Thread.sleep(Constants.kCommandDelayMs);
                    dout("Resumed " + Debug.getThreadInfoFormatted());
                }
                if(!AtomicCache.lastPartyLeader.get().equals(Minecraft.getMinecraft().thePlayer.getName())) {
                    dout("We are not the party leader, ignoring");
                    return;
                }

                if(Math.abs(Integer.parseInt(matcher.group(2))) > 127) {
                    queueWarnMessage("AutoJoinIn delay exceeds 127 seconds, ignoring");
                    return;
                }

                final byte delaySeconds = (byte) Math.abs(Byte.parseByte(matcher.group(2)));
                CatacombsFloors floor = AtomicCache.lastCatacombsFloor.get();
                // Group 3 may or may not be provided
                if(matcher.group(3) != null) {
                    final String floorName = matcher.group(3);
                    floor = CatacombsFloors.getFloorByCode(floorName);
                }

                if(floor == null) {
                    queueErrorMessage("Couldn't find the latest catacombs floor and no floor was provided.");
                    return;
                }

                final String shortFloorName = floor.chatName.replace("The Catacombs, ", "").replace(" Catacombs, ", "");

                queuePartyChat("Joining " + shortFloorName + " in " + delaySeconds + "s!");
                dout("Sleeping for 3000ms on " + Debug.getThreadInfoFormatted());
                Thread.sleep(delaySeconds * 1000);
                dout("Resumed " + Debug.getThreadInfoFormatted());
                queueCommand("joindungeon " + floor.commandCode);
            } catch(InterruptedException e) {
                throw new RuntimeException(e);
            }
        }).start();
    }
}
