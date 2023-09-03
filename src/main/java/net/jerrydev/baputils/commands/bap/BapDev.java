package net.jerrydev.baputils.commands.bap;

import net.jerrydev.baputils.commands.BapExecutable;
import net.jerrydev.baputils.utils.ChatUtils.CCodes;

import java.util.Collections;
import java.util.List;

import static net.jerrydev.baputils.BapUtils.clientMessage;
import static net.jerrydev.baputils.utils.ChatUtils.ccolorize;

public final class BapDev implements BapExecutable {
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
    public void execute(List<String> args) {
        clientMessage(ccolorize(CCodes.GRAY, "zzz... nothing here..."));

        // what are partial ticks?
        //WaypointRenderer.renderBeaconBeam(0, 100, 0, 0xFF00FF, 1.0f, 10);

        //queueClientMessage(ccolorize(CCodes.GRAY, "BapGui (kt) options gui test"));
        //BapUtils.setActiveGui(new BapGui());


        // minecraft username to uuid
        /* try {
            final String req = BapUtils.httpGetRequest("https://api.mojang.com/users/profiles/minecraft/aspectofjerry");

            System.out.println(req);
        } catch (IOException e) {
            throw new RuntimeException(e);
        } */
    }
}
