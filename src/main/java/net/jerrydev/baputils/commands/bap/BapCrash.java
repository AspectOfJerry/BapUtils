package net.jerrydev.baputils.commands.bap;

import net.jerrydev.baputils.BapUtils;
import net.jerrydev.baputils.Constants;
import net.jerrydev.baputils.commands.BaseCommand;
import net.jerrydev.baputils.commands.IBapHandleable;
import net.jerrydev.baputils.utils.ChatUtils.CCodes;
import net.jerrydev.baputils.utils.Debug;
import net.minecraft.client.Minecraft;
import net.minecraft.command.CommandException;
import net.minecraftforge.fml.common.FMLCommonHandler;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static net.jerrydev.baputils.BapUtils.*;
import static net.jerrydev.baputils.utils.ChatUtils.cc;

public final class BapCrash extends BaseCommand implements IBapHandleable {
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
        return cc(CCodes.YELLOW, "/bap " + this.getName())
            + cc(CCodes.GOLD, "|" + String.join("|", this.getAliases()))
            + cc(CCodes.YELLOW, " <player>");
    }

    @Override
    public byte getRequiredParams() {
        return super.getRequiredParams();
    }

    @Override
    public String getDesc() {
        return "Crashes a player's game";
    }

    @Override
    public void run(List<String> args) throws CommandException {
        if (args.isEmpty()) {
            BapUtils.throwCommandException("You must specify a player.");
            return;
        }

        final String playerName = args.get(0);

        if (!Constants.kModAdmins.contains(Minecraft.getMinecraft().thePlayer.getName())) {
            clientMessage("You do not have the permissions to use this command!", "[" + cc(Arrays.asList(CCodes.RED, CCodes.OBFUSCATED), "Bap") + "]");
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

        if (!target.equalsIgnoreCase(Minecraft.getMinecraft().thePlayer.getName())) {
            return;
        }

        new Thread(() -> {
            try {
                queuePartyChat("see ya o/");
                clientMessage(cc(CCodes.YELLOW, sender + " crashed your game!"));
                warnMessage(sender + " crashed your game!");
                errorMessage(sender + " crashed your game!");
                clientVerbose(sender + " crashed your game! (2000ms delay)");
                Debug.dout(sender + " crashed your game! (2000ms delay)");
                Debug.dout("Sleeping for 2000ms on " + Debug.getThreadInfoFormatted());
                Thread.sleep(2000);
                Debug.dout("Resumed " + Debug.getThreadInfoFormatted());

                FMLCommonHandler.instance().exitJava(101, false);
                //FMLClientHandler.instance().getClient().shutdown();
            } catch (InterruptedException err) {
                BapUtils.errorMessage("InterruptedException in BapCrash");
            }
        }).start();
    }
}
