package net.jerrydev.baputils.commands.bap;

import net.jerrydev.baputils.AtomicCache;
import net.jerrydev.baputils.commands.IBapRunnable;
import net.jerrydev.baputils.utils.ChatUtils.CCodes;
import net.jerrydev.baputils.utils.Ntity;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.Entity;

import java.io.IOException;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.atomic.AtomicReference;

import static net.jerrydev.baputils.BapUtils.clientMessage;
import static net.jerrydev.baputils.utils.ChatUtils.ccolorize;

public final class BapDev implements IBapRunnable {
    @Override
    public String getName() {
        return "dev";
    }

    @Override
    public List<String> getAliases() {
        return Collections.singletonList("test");
    }

    @Override
    public String getUsage() {
        return ccolorize(CCodes.YELLOW, "/bap " + this.getName())
            + ccolorize(CCodes.GOLD, "|" + String.join("|", this.getAliases()))
            + ccolorize(CCodes.YELLOW, " <...>");
    }

    @Override
    public byte getRequiredParams() {
        return -1;
    }

    @Override
    public String getDesc() {
        return "Experimental command";
    }

    @Override
    public void run(List<String> args) {
        // clientMessage(ccolorize(CCodes.GRAY, "zzz... nothing here..."));
        AtomicCache.lastPartyLeader.set("AspectOfJerry");
    }
}
