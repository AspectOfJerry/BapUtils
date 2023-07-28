package net.jerrydev.baputils.commands.bap.hypixel;

import net.jerrydev.baputils.BapUtils;
import net.jerrydev.baputils.utils.ChatColors;
import net.jerrydev.baputils.utils.ChatColors.CCodes;
import net.jerrydev.baputils.utils.DungeonsCata;
import net.jerrydev.baputils.utils.IBapBaseCommand;
import net.jerrydev.baputils.utils.StringHex;
import net.minecraft.client.Minecraft;

import java.util.Arrays;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class BapDungeonJoin implements IBapBaseCommand {
    public static void execute(String floorName, String playerName) {
        new Thread(() -> {
            try {
                Thread.sleep(50);

                BapUtils.queueServerMessage("/party list", false);
                Thread.sleep(50);

                BapUtils.queueServerMessage("joindungeon." + floorName + " > " + StringHex.stringToHex(playerName));
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }).start();
    }

    public static void handleChat(String message) {
        String[] messageSplit = message.split(" ");

        String playerHex = messageSplit[messageSplit.length - 1];
        String playerString = StringHex.hexToString(playerHex);
        messageSplit[messageSplit.length - 1] = playerString;
        message = String.join(" ", messageSplit);

        String regExpPattern = "(?i)Party > (.*): bap > dungeonjoin(.*) > "
                + Pattern.quote(Minecraft.getMinecraft().thePlayer.getName());

        if (message.matches(regExpPattern)) {
            Pattern regex = Pattern.compile(regExpPattern, Pattern.CASE_INSENSITIVE);
            Matcher matcher = regex.matcher(message);
            if (matcher.matches()) {
                final String sender = matcher.group(1);
                final String floorName = matcher.group(2);
                // @SuppressWarnings("ConstantConditions")
                DungeonsCata.CatacombsFloors floor = DungeonsCata.CatacombsFloors.getFloorByName(floorName.replace(".", ""));

                if (floor == null) {
                    BapUtils.queueClientMessage(ChatColors.colorize(CCodes.RED, "Error: "
                            + ChatColors.colorize(Arrays.asList(CCodes.RED, CCodes.ITALIC), "at DungeonJoin") + ". Unknown floor name of " + floorName + "."));
                    return;
                }

                // Join a run
                new Thread(() -> {
                    try {
                        Thread.sleep(50);

                        BapUtils.queueServerMessage("/party list");
                        Thread.sleep(50);

                        BapUtils.queueClientMessage(ChatColors.colorize(Arrays.asList(CCodes.GRAY, CCodes.ITALIC), sender + "joined"
                                + (floor.isMaster ? "master" : "") + " catacombs floor " + floor.floorId + ".") + "on your behalf."); // debug verbose
                        BapUtils.queueServerMessage("Acknowledged, joining" + (floor.isMaster ? "master" : "") + " catacombs floor " + floor.floorId + "in 3 seconds...");
                        Thread.sleep(3000);

                        BapUtils.queueServerMessage("/joindungeon " + (floor.isMaster ? "master_catacombs" : "catacombs") + floor.floorId, false);
                    } catch (InterruptedException err) {
                        err.printStackTrace();
                        BapUtils.queueClientMessage(ChatColors.colorize(CCodes.RED, "DungeonJoin failed! An unknown error occurred while transferring the party."));
                    }
                }).start();
            }
        }
    }
}
