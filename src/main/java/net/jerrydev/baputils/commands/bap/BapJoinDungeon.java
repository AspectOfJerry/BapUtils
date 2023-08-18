package net.jerrydev.baputils.commands.bap;

import net.jerrydev.baputils.AtomicMemCache;
import net.jerrydev.baputils.Constants;
import net.jerrydev.baputils.dungeons.CatacombsFloors;
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
        final CatacombsFloors floor = CatacombsFloors.getFloorByName(floorName);

        if(floor == null) {
            queueErrorMessage("Unknown floor name of " + floorName + ". Valid floor names are [fm][0-7].");
            return;
        }

        new Thread(() -> {
            try {
                queueCommand("party list");
                Debug.dout("Sleeping for " + Constants.kCommandDelayMs + "ms on " + Debug.getThreadInfoFormatted());
                Thread.sleep(Constants.kCommandDelayMs);
                Debug.dout("Resumed " + Debug.getThreadInfoFormatted());

                if((AtomicMemCache.lastPartyLeader.get() != null)
                    && AtomicMemCache.lastPartyLeader.get().equals(Minecraft.getMinecraft().thePlayer.getName())) {
                    queueErrorMessage("You are already the party leader!");
                    return;
                }

                queueCommand("party chat $jd." + floorName);
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

        final Pattern pattern = Pattern.compile(Constants.kJoinDungeonPat);
        final Matcher matcher = pattern.matcher(cleanMessage);

        if(matcher.find()) {
            final String floorName = matcher.group(1);
            final CatacombsFloors floor = CatacombsFloors.getFloorByName(floorName);

            if(floor == null) {
                queueErrorMessage("Unknown floor name of " + floorName + ".");
                // if the user did not use a command, this might happen
                /*Debug.dout("Something's really wrong! How is this possible? We've already checked for the validity of the floor name!");
                queueClientMessage(ccolorize(CCodes.YELLOW, "Please open bug report at https://github.com/AspectOfJerry/BapUtils/issues."));*/
                return;
            }

            new Thread(() -> {
                try {
                    queueCommand("party list");
                    Debug.dout("Sleeping for " + Constants.kChatDelayMs + "ms on " + Debug.getThreadInfoFormatted());
                    Thread.sleep(Constants.kChatDelayMs);
                    Debug.dout("Resumed " + Debug.getThreadInfoFormatted());

                    if(AtomicMemCache.lastPartyLeader.get() == null) {
                        queueWarnMessage("Couldn't find the latest party leader, what's going on!? Continuing execution anyway.");
                        Debug.dout("Check the cached party leader using /bap cache");
                    }

                    if(!AtomicMemCache.lastPartyLeader.get().equals(Minecraft.getMinecraft().thePlayer.getName())
                        && (AtomicMemCache.lastPartyLeader.get() != null)) {
                        clientVerbose("We are not party leader");
                        Debug.dout("We are not leader; not entering a run. Latest party leader is: " + AtomicMemCache.lastPartyLeader);
                        return;
                    }

                    queueServerMessage("okay! Joining " + (floor.isMaster ? "master " : "") + "catacombs floor " + floor.floorCode.charAt(1) + " in 3s...");
                    Debug.dout("Sleeping for 3000ms on " + Debug.getThreadInfoFormatted());
                    Thread.sleep(3000);
                    Debug.dout("Resumed " + Debug.getThreadInfoFormatted());

                    queueCommand("joindungeon " + floor.commandCode);
                } catch(final InterruptedException err) {
                    queueErrorMessage("InterruptedException: JoinDungeon failed! An error occurred while transferring the party.");
                }
            }).start();
        }
    }
}
