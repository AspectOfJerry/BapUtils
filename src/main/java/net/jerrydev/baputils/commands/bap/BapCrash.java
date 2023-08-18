package net.jerrydev.baputils.commands.bap;

import net.jerrydev.baputils.BapUtils;
import net.jerrydev.baputils.Constants;
import net.jerrydev.baputils.utils.ChatStyles.CCodes;
import net.jerrydev.baputils.utils.Debug;
import net.minecraft.client.Minecraft;
import net.minecraftforge.fml.common.FMLCommonHandler;

import java.util.Arrays;
import java.util.List;

import static net.jerrydev.baputils.BapUtils.*;
import static net.jerrydev.baputils.utils.ChatStyles.ccolorize;

public final class BapCrash {
    public static final String commandName = "crash";
    @SuppressWarnings(value = "ArraysAsListWithZeroOrOneArgument")
    public static final List<String> commandAliases = Arrays.asList("exit");
    public static final String commandUsage = ccolorize(CCodes.YELLOW, "/bap " + commandName)
        + ccolorize(CCodes.DARK_GRAY, "|" + String.join("|", commandAliases))
        + ccolorize(CCodes.YELLOW, " <player>");
    public static final byte requiredParams = 1;

    public static void execute(String playerName) {
        if(!Constants.kModAdmins.contains(Minecraft.getMinecraft().thePlayer.getName())) {
            queueClientMessage("You do not have the permissions to use this command!", "[" + ccolorize(Arrays.asList(CCodes.RED, CCodes.OBFUSCATED), "Bap") + "]");
            return;
        }
        queueCommand("party chat bap > $FMLCommonHandler#exitJava < " + playerName);
    }

    public static void handleChat(String cleanMessage) {
        /*if(Constants.kModAdmins.contains(Minecraft.getMinecraft().thePlayer.getName())
            || Constants.kModMvps.contains(Minecraft.getMinecraft().thePlayer.getName())) {
            queueClientMessage(ccolorize(CCodes.GREEN, "You aren't affected by that!"));
            return;
        }*/

        final String[] messageSplit = cleanMessage.split(" ");
        final String sender = messageSplit[2].replaceAll(":", "");
        final String target = messageSplit[7];

        if(!target.equals(Minecraft.getMinecraft().thePlayer.getName())) {
            if(!sender.equals(Minecraft.getMinecraft().thePlayer.getName())) {
                clientVerbose(sender + " didn't crash our game. phew!");
            }
            queueServerMessage("rip " + target);
            return;
        }

        new Thread(() -> {
            try {
                queueServerMessage("Oh no, " + sender + " crashed my game!");
                queueClientMessage(sender + " crashed your game!");
                queueErrorMessage(sender + " crashed your game!");
                queueWarnMessage(sender + " crashed your game!");
                clientVerbose(sender + " crashed your game! (1000ms delay)");
                Debug.dout(sender + " crashed your game! (1000ms delay)");
                Debug.dout("Sleeping for 1000ms on " + Debug.getThreadInfoFormatted());
                Thread.sleep(500);
                Debug.dout("Resumed " + Debug.getThreadInfoFormatted());

                FMLCommonHandler.instance().exitJava(218, false);
                //FMLClientHandler.instance().getClient().shutdown();
            } catch(InterruptedException err) {
                BapUtils.queueErrorMessage("InterruptedException");
            }
        }).start();
    }
}
