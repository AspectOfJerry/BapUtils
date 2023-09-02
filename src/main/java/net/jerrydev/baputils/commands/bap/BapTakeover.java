package net.jerrydev.baputils.commands.bap;

import net.jerrydev.baputils.AtomicCache;
import net.jerrydev.baputils.BapUtils;
import net.jerrydev.baputils.Constants;
import net.jerrydev.baputils.commands.BapExecutable;
import net.jerrydev.baputils.commands.BapHandleable;
import net.jerrydev.baputils.core.BapSettingsGui;
import net.jerrydev.baputils.utils.ChatUtils.CCodes;
import net.jerrydev.baputils.utils.Debug;
import net.minecraft.client.Minecraft;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static net.jerrydev.baputils.BapUtils.*;
import static net.jerrydev.baputils.utils.ChatUtils.ccolorize;
import static net.jerrydev.baputils.utils.Debug.dout;

public final class BapTakeover implements BapExecutable, BapHandleable {
    @Override
    public String getName() {
        return "takeover";
    }

    @Override
    public List<String> getAliases() {
        return Arrays.asList("ptake", "take", "pto", "to");
    }

    @Override
    public String getUsage() {
        return ccolorize(CCodes.YELLOW, "/bap " + this.getName())
            + ccolorize(CCodes.GOLD, "|" + String.join("|", this.getAliases()));
    }

    @Override
    public byte getRequiredParams() {
        return 0;
    }

    @Override
    public String getDesc() {
        return "Takeover the leader's party";
    }

    @Override
    public void execute(List<String> args) {
        new Thread(() -> {
            try {
                queueCommand("party list");
                dout("Sleep " + Constants.kCommandDelayMs + "ms " + Debug.getThreadInfoFormatted());
                Thread.sleep(Constants.kCommandDelayMs);
                dout("Resume " + Debug.getThreadInfoFormatted());

                if((AtomicCache.lastPartyLeader.get() != null)
                    && AtomicCache.lastPartyLeader.get().equals(Minecraft.getMinecraft().thePlayer.getName())) {
                    queueErrorMessage("You are already the party leader!");
                    return;
                }

                queuePartyChat("$pto");
            } catch(InterruptedException e) {
                throw new RuntimeException(e);
            }
        }).start();
    }

    @Override
    public List<String> getPatterns() {
        return Collections.singletonList(Constants.kTakeoverP);
    }

    @Override
    public void handle(List<String> args) {
        if(!BapSettingsGui.INSTANCE.getPartyTakeoverMaster()) {
            dout("PartyTakeover is disabled.");
            return;
        }

        final String cleanMessage = args.get(0);

        final String[] messageSplit = cleanMessage.split(" ");
        final String sender = messageSplit[2].replaceAll(":", "");

        if(sender.equals(Minecraft.getMinecraft().thePlayer.getName())) {
            dout("We are the sender, ignoring");
            return;
        }

        new Thread(() -> {
            try {
                queueCommand("party list");
                dout("Sleep " + Constants.kCommandDelayMs + "ms " + Debug.getThreadInfoFormatted());
                Thread.sleep(Constants.kCommandDelayMs);
                dout("Resume " + Debug.getThreadInfoFormatted());

                if(AtomicCache.lastPartyLeader.get() == null) {
                    queueWarnMessage("Couldn't find the latest party leader, what's going on!? Continuing execution anyway.");
                    dout("Check the cached party leader using /bap cache");
                }

                if(!AtomicCache.lastPartyLeader.get().equals(Minecraft.getMinecraft().thePlayer.getName())
                    && (AtomicCache.lastPartyLeader.get() != null)) {
                    clientVerbose("We are not party leader");
                    dout("We are not leader; not transferring. Latest party leader is: " + AtomicCache.lastPartyLeader);
                    return;
                }

                queueCommand("party transfer " + sender);
            } catch(InterruptedException err) {
                BapUtils.queueErrorMessage("InterruptedException: Takeover failed! An error occurred while transferring the party.");
            }
        }).start();
    }
}
