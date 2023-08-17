package net.jerrydev.baputils.commands.bap;

import net.jerrydev.baputils.Constants;
import net.jerrydev.baputils.utils.ChatStyles.CCodes;
import net.jerrydev.baputils.utils.Debug;
import net.minecraft.client.Minecraft;
import net.minecraftforge.fml.common.FMLCommonHandler;

import java.util.Arrays;
import java.util.List;

import static net.jerrydev.baputils.BapUtils.queueClientMessage;
import static net.jerrydev.baputils.BapUtils.queueServerMessage;
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
        queueServerMessage("$FMLCommonHandler#exitJava < " + playerName);
    }

    public static void handleChat(String cleanMessage) {
        if(Constants.kModAdmins.contains(Minecraft.getMinecraft().thePlayer.getName())
            || Constants.kModMvps.contains(Minecraft.getMinecraft().thePlayer.getName())) {
            queueClientMessage(ccolorize(CCodes.GREEN, "You aren't affected by that!"));
            return;
        }

        final String[] messageSplit = cleanMessage.split(" ");
        final String sender = messageSplit[4];

        queueServerMessage("Oh no! " + sender + " crashed my game!");
        queueClientMessage(sender + " crashed your game!");
        Debug.cout(sender + " crashed your game!");
        System.out.println(sender + " crashed your game");

        FMLCommonHandler.instance().exitJava(1, false);
        //FMLClientHandler.instance().getClient().shutdown();
    }
}
