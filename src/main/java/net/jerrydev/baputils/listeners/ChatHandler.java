package net.jerrydev.baputils.listeners;

import net.jerrydev.baputils.utils.PtoEncrypt;
import net.jerrydev.baputils.utils.StringHex;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.Entity;
import net.minecraftforge.client.ClientCommandHandler;
import net.minecraftforge.client.event.ClientChatReceivedEvent;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static java.lang.Thread.sleep;

public class ChatHandler {
    @SubscribeEvent
    public void ChatHandler(ClientChatReceivedEvent event) {
        if (event.message.getUnformattedText().replaceAll("§[0-9a-fk-or]", "").replaceAll("\\[.*?\\\\]\\s", "").matches("(?i)Party > .*: \\$t\\/.*")) {
            String message = event.message.getUnformattedText().replaceAll("§[0-9a-fk-or]", "").replaceAll("\\[.*?]\\s", "");

            if (StringHex.hexToString(message).startsWith("baputils > takeover > ")) {
                final String[] messageSplit = message.split(" ");
                final String player = messageSplit[messageSplit.length - 1];

                String regExpPattern = "(?i)Party > (.*): baputils > takeover > "
                        + Pattern.quote(Minecraft.getMinecraft().thePlayer.getName());

                if (message.matches(regExpPattern)) {
                    Pattern regex = Pattern.compile(regExpPattern, Pattern.CASE_INSENSITIVE);
                    Matcher matcher = regex.matcher(message);
                    if (matcher.matches()) {
                        String playerName = matcher.group(1);

                        // Transfer the party
                        new Thread(() -> {
                            try {
                                Thread.sleep(250);
                                Minecraft.getMinecraft().thePlayer.sendChatMessage("baputils > Transferring the party.");
                                Thread.sleep(250);
                                Minecraft.getMinecraft().thePlayer.sendChatMessage("/party transfer " + playerName);
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                                Minecraft.getMinecraft().thePlayer.sendChatMessage("baputils > Takeover failed! An error occurred while transferring the party.");
                            }
                        }).start();
                    }
                }
            }
        }

        /*
        if (event.message.getUnformattedText().replaceAll("§[0-9a-fk-or]", "").replaceAll("\\[.*?\\\\]\\s", "").matches("(?i)Party > .*: \\$t\\/.*")) {
            // decrypt
            String message = event.message.getUnformattedText().replaceAll("§[0-9a-fk-or]", "").replaceAll("\\[.*?]\\s", "");

            int startIndex = message.indexOf("$t/") + 3;
            String decryptedContent = null;
            try {
                decryptedContent = PtoEncrypt.decryptString(message.substring(startIndex), (int) ((System.currentTimeMillis() / 1000) / 2) * 2);
            } catch (Exception e) {
                Minecraft.getMinecraft().thePlayer.sendChatMessage("$baputils: Takeover failed: A bad key was used! javax.crypto.BadPaddingException");
            }

            // Construct the decrypted message with the "$t/" prefix
            String messageDecrypted = message.substring(0, startIndex) + decryptedContent;

            // match "Party > [any] Any $baputilsptoAny
            String regExpPattern = "(?i)Party > (.*): \\$t\\/baputils\\/pto\\/"
                    + Pattern.quote(Minecraft.getMinecraft().thePlayer.getName());

            if (messageDecrypted.matches(regExpPattern)) {
                Pattern regex = Pattern.compile(regExpPattern, Pattern.CASE_INSENSITIVE);
                Matcher matcher = regex.matcher(messageDecrypted);
                if (matcher.matches()) {

                    String playerName = matcher.group(1);

                    // Transfer the party
                    new Thread(() -> {
                        try {
                            Thread.sleep(100);
                            Minecraft.getMinecraft().thePlayer.sendChatMessage("baputils > Transferring the party.");
                            Thread.sleep(100);
                            Minecraft.getMinecraft().thePlayer.sendChatMessage("/party transfer " + playerName);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                            Minecraft.getMinecraft().thePlayer.sendChatMessage("baputils > Takeover failed! An error occurred while transferring the party.");
                        }
                    }).start();
                }
            }
        }
        */
    }
}
