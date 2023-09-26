package net.jerrydev.baputils.commands.bap;

import net.jerrydev.baputils.BapUtils;
import net.jerrydev.baputils.commands.BapExecutable;
import net.jerrydev.baputils.utils.ChatUtils;
import net.jerrydev.baputils.utils.MsDelay;
import net.minecraft.client.Minecraft;
import net.minecraft.command.CommandException;

import java.util.Arrays;
import java.util.List;
import java.util.Random;

import static net.jerrydev.baputils.utils.ChatUtils.ccolorize;

public class BapRoulette implements BapExecutable {
    @Override
    public String getName() {
        return "roulette";
    }

    @Override
    public List<String> getAliases() {
        return Arrays.asList("rlt", "rou");
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
        return "Rolls a number between 1 and 10. If It's 10, you crash.";
    }

    @Override
    public void execute(List<String> args) throws CommandException {
        Random random = new Random();

        int rolledNumber = random.nextInt(10) + 1;
        if (rolledNumber == 10) {
            BapUtils.clientMessage("§4You Rolled §1" + rolledNumber+1 + "§4. Crashing in 2 seconds.");
            new MsDelay(() -> {
            }, 2000);
            Minecraft.getMinecraft().shutdown();
        } else {
            BapUtils.clientMessage("§bYou Rolled §1" + rolledNumber+1 + "§b. You survived.");
        }
    }
}
