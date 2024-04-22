package net.jerrydev.baputils.commands.bap;

import net.jerrydev.baputils.commands.IBapRunnable;
import net.jerrydev.baputils.utils.ChatUtils;
import net.jerrydev.baputils.utils.Ntity;
import net.minecraft.client.Minecraft;
import net.minecraft.command.CommandException;
import net.minecraft.entity.Entity;

import java.util.Collections;
import java.util.List;

import static net.jerrydev.baputils.BapUtils.clientMessage;
import static net.jerrydev.baputils.utils.ChatUtils.ccolorize;

public class BapRadar implements IBapRunnable {
    @Override
    public String getName() {
        return "radar";
    }

    @Override
    public List<String> getAliases() {
        return Collections.singletonList("scan");
    }

    @Override
    public String getUsage() {
        return ccolorize(ChatUtils.CCodes.YELLOW, "/bap " + this.getName())
            + ccolorize(ChatUtils.CCodes.GOLD, "|" + String.join("|", this.getAliases()));
    }

    @Override
    public byte getRequiredParams() {
        return 0;
    }

    @Override
    public String getDesc() {
        return "";
    }

    @Override
    public void run(List<String> args) throws CommandException {
        clientMessage(ccolorize(ChatUtils.CCodes.GREEN, "Nearby mobs within 15m:"));
        List<Entity> nearbyMobs = Ntity.getNearbyMobs(Minecraft.getMinecraft().thePlayer, 15F);

        // TODO: Add line of sight (LOS) check

        for (Entity mob : nearbyMobs) {
            clientMessage(
                ccolorize(ChatUtils.CCodes.GRAY, "(" + mob.getEntityId() + ")") + " "
                    + ccolorize(ChatUtils.CCodes.GREEN, mob.getName()) + " "
                    + ccolorize(ChatUtils.CCodes.GOLD, "[" + (Math.round(mob.getDistanceToEntity(Minecraft.getMinecraft().thePlayer) * 1000.0F) / 1000.0F) + "m]")
            );
        }
    }
}
