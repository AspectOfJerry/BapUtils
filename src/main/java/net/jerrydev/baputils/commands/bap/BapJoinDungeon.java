package net.jerrydev.baputils.commands.bap;

import net.jerrydev.baputils.RuntimeData;
import net.jerrydev.baputils.utils.CatacombsFloors;
import net.jerrydev.baputils.utils.ChatColors.CCodes;
import net.jerrydev.baputils.utils.Debug;
import net.jerrydev.baputils.utils.IBapCommand;
import net.minecraft.client.Minecraft;

import java.util.Arrays;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static net.jerrydev.baputils.BapUtils.*;
import static net.jerrydev.baputils.utils.ChatColors.ccolorize;

public class BapJoinDungeon implements IBapCommand {
    public static final String commandName = "joindungeon";
    public static final List<String> commandAliases = Arrays.asList("dung", "join", "run", "jd");
    public static final String commandUsage = ccolorize(CCodes.YELLOW, "/bap " + commandName)
        + ccolorize(CCodes.DARK_GRAY, "|" + String.join("|", commandAliases))
        + ccolorize(CCodes.YELLOW, " <floor>");
    public static byte requiredParams = 1;

    public static void execute(String floorName) {
        new Thread(() -> {
            try {
                queueCommand("party list");
                Debug.cout("Sleeping for 100ms on " + Debug.getThreadInfoFormatted());
                Thread.sleep(100);
                Debug.cout("Resumed " + Debug.getThreadInfoFormatted());

                queueServerMessage("$joindungeon." + floorName);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }).start();
    }

    public static void handleChat(String message) {
        String[] messageSplit = message.split(" ");

        final String sender = messageSplit[2];

        String regExpPattern = "(?i)Party > .*: bap > \\$joindungeon.(.*)"
            + Pattern.quote(Minecraft.getMinecraft().thePlayer.getName());

        if (message.matches(regExpPattern)) {
            Pattern regex = Pattern.compile(regExpPattern, Pattern.CASE_INSENSITIVE);
            Matcher matcher = regex.matcher(message);
            if (matcher.matches()) {
                final String floorName = matcher.group(1);
                CatacombsFloors floor = CatacombsFloors.getFloorByName(floorName.replace(".", ""));

                if (floor == null) {
                    queueErrorMessage("Unknown floor name of " + floorName + ".");
                    return;
                }

                new Thread(() -> {
                    try {
                        queueCommand("party list");
                        Debug.cout("Sleeping for 100ms on " + Debug.getThreadInfoFormatted());
                        Thread.sleep(100);
                        Debug.cout("Resumed " + Debug.getThreadInfoFormatted());

                        if (RuntimeData.latestPartyLeader == null) {
                            queueWarnMessage("Couldn't find the latest party leader, what's going on!? Attempting a transfer anyway.");
                            Debug.cout("Check the cached party leader using /bap cache");
                        }

                        if (!RuntimeData.latestPartyLeader.equals(Minecraft.getMinecraft().thePlayer.getName())
                            && RuntimeData.latestPartyLeader != null) {
                            clientVerbose("We are not party leader");
                            Debug.cout("We are not leader; not attempting a transfer. Latest party leader is: " + RuntimeData.latestPartyLeader);
                            return;
                        }

                        queueServerMessage("okay! Joining" + (floor.isMaster ? "master" : "") + " catacombs floor " + floor.floorId + "in 3 seconds...");
                        Debug.cout("Sleeping for 3000ms on " + Debug.getThreadInfoFormatted());
                        Thread.sleep(3000);
                        Debug.cout("Resumed " + Debug.getThreadInfoFormatted());

                        queueCommand("joindungeon " + (floor.isMaster ? "master_catacombs" : "catacombs") + floor.floorId);
                    } catch (InterruptedException err) {
                        queueErrorMessage("JoinDungeon failed! An unknown error occurred while transferring the party.");
                    }
                }).start();
            }
        }
    }
}
