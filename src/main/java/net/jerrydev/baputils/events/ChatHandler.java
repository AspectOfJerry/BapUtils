package net.jerrydev.baputils.events;

import net.jerrydev.baputils.commands.bap.hypixel.BapTakeover;
import net.minecraftforge.client.event.ClientChatReceivedEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

import static java.lang.Thread.sleep;

public class ChatHandler {
    @SubscribeEvent
    public void onChatReceived(ClientChatReceivedEvent event) {
        if (event.message.getUnformattedText().replaceAll("§[0-9a-fk-or]", "").replaceAll("\\[.*?\\\\]\\s", "").matches("(?i)Party > .*: bap > takeover > .*")) {
            String message = event.message.getUnformattedText().replaceAll("§[0-9a-fk-or]", "").replaceAll("\\[.*?]\\s", "");
            BapTakeover.handleChat(message);
        }
    }
}
