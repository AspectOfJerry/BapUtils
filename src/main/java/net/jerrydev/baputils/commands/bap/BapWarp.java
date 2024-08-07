package net.jerrydev.baputils.commands.bap;

import net.jerrydev.baputils.AtomicCache;
import net.jerrydev.baputils.BapUtils;
import net.jerrydev.baputils.Constants;
import net.jerrydev.baputils.commands.BaseCommand;
import net.jerrydev.baputils.commands.IBapHandleable;
import net.jerrydev.baputils.core.BapSettingsGui;
import net.jerrydev.baputils.utils.Debug;
import net.minecraft.client.Minecraft;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static net.jerrydev.baputils.BapUtils.*;
import static net.jerrydev.baputils.utils.Debug.dout;

public final class BapWarp extends BaseCommand implements IBapHandleable {
    @Override
    public String getName() {
        return "warp";
    }

    @Override
    public List<String> getAliases() {
        return Arrays.asList("pwarp", "pw", "w");
    }

    @Override
    public String getUsage() {
        return super.getUsage();
    }

    @Override
    public byte getRequiredParams() {
        return super.getRequiredParams();
    }

    @Override
    public String getDesc() {
        return "Warps the party on the leader's behalf";
    }

    @Override
    public void run(List<String> args) {
        sendCommand("party list", true);

        if ((AtomicCache.lastPartyLeader.get() != null)
            && AtomicCache.lastPartyLeader.get().equals(Minecraft.getMinecraft().thePlayer.getName())) {
            BapUtils.commandError("You are already the party leader!");
            return;
        }

        queuePartyChat("$warp");
    }

    @Override
    public List<String> getPatterns() {
        return Collections.singletonList(Constants.kPartyWarpP);
    }

    @Override
    public void handle(List<String> args) {
        if (!BapSettingsGui.INSTANCE.getPartyWarpMaster()) {
            dout("PartyWarp is disabled.");
            return;
        }

        final String cleanMessage = args.get(0);

        final String[] messageSplit = cleanMessage.split(" ");
        final String sender = messageSplit[2].replaceAll(":", "");

        if (sender.equals(Minecraft.getMinecraft().thePlayer.getName())) {
            dout("We are the sender, ignoring");
            return;
        }

        new Thread(() -> {
            try {
                sendCommand("party list", true);

                if (AtomicCache.lastPartyLeader.get() == null) {
                    warnMessage("Couldn't find the latest party leader, what's going on!? Continuing execution anyway.");
                    dout("Check the cached party leader using /bap cache");
                }

                if (!AtomicCache.lastPartyLeader.get().equals(Minecraft.getMinecraft().thePlayer.getName())
                    && (AtomicCache.lastPartyLeader.get() != null)) {
                    clientVerbose("We are not party leader");
                    dout("We are not the party leader (+" + AtomicCache.lastPartyLeader + ", ignoring.");
                    return;
                }

                queuePartyChat("Warping the party in 2s");
                Debug.dout("Sleep 2000ms " + Debug.getThreadInfoFormatted());
                Thread.sleep(2000);
                Debug.dout("Resume " + Debug.getThreadInfoFormatted());

                sendCommand("party warp");
            } catch (InterruptedException err) {
                BapUtils.errorMessage("InterruptedException: Takeover failed! An error occurred while transferring the party.");
            }
        }).start();
    }
}
