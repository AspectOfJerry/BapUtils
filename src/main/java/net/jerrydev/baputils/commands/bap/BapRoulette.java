package net.jerrydev.baputils.commands.bap;

import net.jerrydev.baputils.commands.IBapRunnable;
import net.jerrydev.baputils.utils.ChatUtils.CCodes;
import net.jerrydev.baputils.utils.Delay;
import net.minecraft.client.Minecraft;
import net.minecraft.command.CommandException;

import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static net.jerrydev.baputils.BapUtils.clientMessage;
import static net.jerrydev.baputils.utils.ChatUtils.ccolorize;

public class BapRoulette implements IBapRunnable {
    @Override
    public String getName() {
        return "roulette";
    }

    @Override
    public List<String> getAliases() {
        return Collections.emptyList();
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
        return "Rolls a number between 1 and 10. If it's 10, you crash.";
    }

    @Override
    public void run(List<String> args) throws CommandException {
        SecureRandom secureRandom;
        try {
            secureRandom = SecureRandom.getInstance("SHA1PRNG");
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
        int randomInt = secureRandom.nextInt(10) + 1; // Generates an integer in the range [1, 10]

        if (randomInt == 10) {
            clientMessage(ccolorize(Arrays.asList(CCodes.DARK_RED, CCodes.OBFUSCATED), "-")
                + ccolorize(CCodes.DARK_RED, " You rolled: " + (randomInt) + ". Goodbye! ")
                + ccolorize(Arrays.asList(CCodes.DARK_RED, CCodes.OBFUSCATED), "-"));

            Delay.setTimeout(() -> Minecraft.getMinecraft().shutdown(), 2000);
        } else {
            clientMessage(ccolorize(CCodes.GOLD, "You rolled ")
                + ccolorize(CCodes.YELLOW, String.valueOf(randomInt))
                + ccolorize(CCodes.GOLD, " and are safe."));
        }
    }
}
