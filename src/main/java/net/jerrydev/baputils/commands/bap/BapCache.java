package net.jerrydev.baputils.commands.bap;

import net.jerrydev.baputils.AtomicCache;
import net.jerrydev.baputils.commands.IBapRunnable;
import net.jerrydev.baputils.utils.ChatUtils.CCodes;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static net.jerrydev.baputils.BapUtils.clientMessage;
import static net.jerrydev.baputils.utils.ChatUtils.autoTF;
import static net.jerrydev.baputils.utils.ChatUtils.cc;

public final class BapCache implements IBapRunnable {
    @Override
    public String getName() {
        return "cache";
    }

    @Override
    public List<String> getAliases() {
        return Collections.singletonList("data");
    }

    @Override
    public String getUsage() {
        return cc(CCodes.YELLOW, "/bap " + this.getName())
            + cc(CCodes.GOLD, "|" + String.join("|", this.getAliases()));
    }

    @Override
    public byte getRequiredParams() {
        return 0;
    }

    @Override
    public String getDesc() {
        return "Displays currently cached info";
    }

    @Override
    public void run(List<String> args) {
        clientMessage(cc(CCodes.GREEN, "Here are the cached values:"));
        for (final String s : Arrays.asList(
            autoTF("- boolean isInParty: " + AtomicCache.isInParty.get()),
            autoTF("- String lastPartyLeader: " + AtomicCache.lastPartyLeader.get()),
            autoTF("- CatacombsFloors lastCatacombsFloor: " + AtomicCache.lastCatacombsFloor.get()),
            autoTF("- boolean inDungeon: " + AtomicCache.inDungeon.get()),
            autoTF("- List<String> serverChatQueue (size): " + AtomicCache.serverChatQueue.get().size()),
            autoTF("- double playerVelocityMPS: " + AtomicCache.playerVelocityMPS.get()),
            autoTF("- double playerVelocityKPH: " + AtomicCache.playerVelocityKPH.get())
        )) {
            clientMessage(cc(CCodes.GRAY, s, false));
        }
    }
}
