package net.jerrydev.baputils.commands.bap;

import net.jerrydev.baputils.commands.IBapRunnable;
import net.jerrydev.baputils.utils.ChatUtils.CCodes;
import net.jerrydev.baputils.utils.Ntity;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.Entity;

import java.util.Collections;
import java.util.List;

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

        clientMessage(ccolorize(CCodes.GREEN, "Nearby mobs:"));
        List<Entity> nearbyMobs = Ntity.getNearbyMobs(Minecraft.getMinecraft().thePlayer, 10F);

        for (Entity mob : nearbyMobs) {
            clientMessage(
                ccolorize(CCodes.GRAY, "(" + mob.getEntityId() + ")") + " "
                    + ccolorize(CCodes.GREEN, mob.getName()) + " "
                    + ccolorize(CCodes.GOLD, "[" + (Math.round(mob.getDistanceToEntity(Minecraft.getMinecraft().thePlayer) * 1000.0F) / 1000.0F) + "m]")
            );
        }

        // what are partial ticks?
        //WaypointRenderer.renderBeaconBeam(0, 100, 0, 0xFF00FF, 1.0f, 10);

        // minecraft username to uuid
        /* try {
            final String req = BapUtils.httpGetRequest("https://api.mojang.com/users/profiles/minecraft/aspectofjerry");

            System.out.println(req);
        } catch (IOException e) {
            throw new RuntimeException(e);
        } */
    }
}
