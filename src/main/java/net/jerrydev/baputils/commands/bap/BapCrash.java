package net.jerrydev.baputils.commands.bap;

import net.jerrydev.baputils.BapUtils;
import net.jerrydev.baputils.Constants;
import net.jerrydev.baputils.commands.BapExecutable;
import net.jerrydev.baputils.commands.BapHandleable;
import net.jerrydev.baputils.utils.ChatUtils;
import net.jerrydev.baputils.utils.ChatUtils.CCodes;
import net.jerrydev.baputils.utils.Debug;
import net.minecraft.client.Minecraft;
import net.minecraft.command.CommandException;
import net.minecraftforge.fml.common.FMLCommonHandler;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static net.jerrydev.baputils.BapUtils.*;

public final class BapCrash implements BapExecutable, BapHandleable {
    @Override
    public String getName() {
        return "crash";
    }

    @Override
    public List<String> getAliases() {
        return Collections.singletonList("exit");
    }

    @Override
    public String getUsage() {
        return ChatUtils.ccolorize(CCodes.YELLOW, "/bap " + this.getName())
            + ChatUtils.ccolorize(CCodes.GOLD, "|" + String.join("|", this.getAliases()))
            + ChatUtils.ccolorize(CCodes.YELLOW, " <player>");
    }

    @Override
    public byte getRequiredParams() {
        return 0;
    }

    @Override
    public String getDesc() {
        return "Crashes a player's game";
    }

    @Override
    public void execute(List<String> args) throws CommandException {
        if(args.size() == 1) {
            BapUtils.throwCommandException("You must specify a player.");
            return;
        }

        final String playerName = args.get(0);

        if(!Constants.kModAdmins.contains(Minecraft.getMinecraft().thePlayer.getName())) {
            queueClientMessage("You do not have the permissions to use this command!", "[" + ChatUtils.ccolorize(Arrays.asList(CCodes.RED, CCodes.OBFUSCATED), "Bap") + "]");
            return;
        }
        queuePartyChat("$FMLCommonHandler#exitJava < " + playerName, true);
    }

    @Override
    public List<String> getPatterns() {
        return Collections.singletonList(Constants.kBapCrashP);
    }

    @Override
    public void handle(List<String> args) {
        final String cleanMessage = args.get(0);
        /*if(Constants.kModAdmins.contains(Minecraft.getMinecraft().thePlayer.getName())
            || Constants.kModMvps.contains(Minecraft.getMinecraft().thePlayer.getName())) {
            queueClientMessage(ccolorize(CCodes.GREEN, "You aren't affected by that!"));
            return;
        }*/

        final String[] messageSplit = cleanMessage.split(" ");
        final String sender = messageSplit[2].replaceAll(":", "");
        final String target = messageSplit[7];

        if(!target.equals(Minecraft.getMinecraft().thePlayer.getName())) {
            queuePartyChat("rip " + target);
            return;
        }

        new Thread(() -> {
            try {
                queueClientMessage(sender + " crashed your game!");
                queueErrorMessage(sender + " crashed your game!");
                queueWarnMessage(sender + " crashed your game!");
                clientVerbose(sender + " crashed your game! (1000ms delay)");
                Debug.dout(sender + " crashed your game! (1000ms delay)");
                Debug.dout("Sleeping for 1000ms on " + Debug.getThreadInfoFormatted());
                Thread.sleep(1000);
                Debug.dout("Resumed " + Debug.getThreadInfoFormatted());

                FMLCommonHandler.instance().exitJava(218, false);
                //FMLClientHandler.instance().getClient().shutdown();
            } catch(InterruptedException err) {
                BapUtils.queueErrorMessage("InterruptedException");
            }
        }).start();
    }
}
