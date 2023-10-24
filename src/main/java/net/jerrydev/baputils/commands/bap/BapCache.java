package net.jerrydev.baputils.commands.bap;

import net.jerrydev.baputils.AtomicCache;
import net.jerrydev.baputils.commands.IBapRunnable;
import net.jerrydev.baputils.utils.ChatUtils.CCodes;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static net.jerrydev.baputils.BapUtils.clientMessage;
import static net.jerrydev.baputils.utils.ChatUtils.autoTF;
import static net.jerrydev.baputils.utils.ChatUtils.ccolorize;

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
        return ccolorize(CCodes.YELLOW, "/bap " + this.getName())
            + ccolorize(CCodes.GOLD, "|" + String.join("|", this.getAliases()));
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
        clientMessage(ccolorize(CCodes.GREEN, "Here are the cached values:"));
        for (final String s : Arrays.asList(
            "- boolean isInParty: " + autoTF(String.valueOf(AtomicCache.isInParty.get())),
            "- String lastPartyLeader: " + autoTF(AtomicCache.lastPartyLeader.get()),
            "- CatacombsFloors lastCatacombsFloor: " + autoTF(String.valueOf(AtomicCache.lastCatacombsFloor.get())),
            "- boolean inDungeon: " + autoTF(String.valueOf(AtomicCache.inDungeon.get())),
            "- List<String> serverChatQueue (size): " + AtomicCache.serverChatQueue.get().size(),
            "- double playerVelocityMPS: " + autoTF(String.valueOf(AtomicCache.playerVelocityMPS.get())),
            "- double playerVelocityKPH: " + autoTF(String.valueOf(AtomicCache.playerVelocityKPH.get()))
        )) {
            clientMessage(ccolorize(CCodes.GRAY, s, false));
        }
    }
}
