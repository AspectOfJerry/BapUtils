package net.jerrydev.baputils.commands.bap;

import net.jerrydev.baputils.commands.BaseCommand;
import net.jerrydev.baputils.core.BapSettingsGui;
import net.jerrydev.baputils.utils.Ntity;
import net.minecraft.client.Minecraft;
import net.minecraft.command.CommandException;
import net.minecraft.entity.Entity;

import java.util.Arrays;
import java.util.List;

import static net.jerrydev.baputils.BapUtils.clientMessage;
import static net.jerrydev.baputils.utils.ChatUtils.*;

public class BapRadar extends BaseCommand {
    @Override
    public String getName() {
        return "radar";
    }

    @Override
    public List<String> getAliases() {
        return Arrays.asList("scan", "r");
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
        return "Prints a list of nearby entities";
    }

    @Override
    public void run(List<String> args) throws CommandException {
        final int scanRadius = BapSettingsGui.INSTANCE.getRadarScanRange();

        clientMessage(cc(CCodes.GREEN, "Nearby entities within " + scanRadius + "m:"));
        List<Entity> nearbyEntities = Ntity.getNearbyEntities(Minecraft.getMinecraft().thePlayer, (float) scanRadius);

        if (BapSettingsGui.INSTANCE.getRadarLOSCheck()) {
            List<Boolean> losChecks = Ntity.checkLineOfSight(nearbyEntities);

            for (int i = 0; i < nearbyEntities.size(); i++) {
                clientMessage(cc(CCodes.GRAY, "(" + nearbyEntities.get(i).getEntityId() + ")") + " "
                    + cc(CCodes.GREEN, nearbyEntities.get(i).getName()) + " "
                    + cc(CCodes.GOLD, "[" + (Math.round(nearbyEntities.get(i).getDistanceToEntity(Minecraft.getMinecraft().thePlayer) * 1000.0F) / 1000.0F) + "m]"
                    + cc(CCodes.GRAY, " LOS: " + autoTF(String.valueOf(losChecks.get(i)))))
                );
            }
        } else {
            for (Entity entity : nearbyEntities) {
                clientMessage(cc(CCodes.GRAY, "(" + entity.getEntityId() + ")") + " "
                    + cc(CCodes.GREEN, entity.getName()) + " "
                    + cc(CCodes.GOLD, "[" + (Math.round(entity.getDistanceToEntity(Minecraft.getMinecraft().thePlayer) * 1000.0F) / 1000.0F) + "m]")
                );
            }
        }
    }
}
