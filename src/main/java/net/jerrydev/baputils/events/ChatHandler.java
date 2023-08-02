package net.jerrydev.baputils.events;

import net.jerrydev.baputils.commands.bap.BapDungeonJoin;
import net.jerrydev.baputils.commands.bap.BapTakeover;
import net.jerrydev.baputils.core.BapConfig;
import net.jerrydev.baputils.utils.ChatColors;
import net.minecraftforge.client.event.ClientChatReceivedEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class ChatHandler {
    @SubscribeEvent
    public void onChatReceived(ClientChatReceivedEvent event) {
        if (BapConfig.INSTANCE.getPartyTakeoverMaster() && ChatColors.stripColorCodes(event.message.getUnformattedText())
                .replaceAll("\\[.*?\\\\]\\s", "").matches("(?i)Party > .*: bap > takeover > .*")) {

            String message = ChatColors.stripColorCodes(event.message.getUnformattedText()).replaceAll("\\[.*?]\\s", "");
            BapTakeover.handleChat(message);
            return;
        }

        if (BapConfig.INSTANCE.getDungeonJoinMaster() && ChatColors.stripColorCodes(event.message.getUnformattedText())
                .replaceAll("\\[.*?\\\\]\\s", "").matches("(?i)Party > .*: bap > dungeonjoin.* > .*")) {

            String message = ChatColors.stripColorCodes(event.message.getUnformattedText()).replaceAll("\\[.*?]\\s", "");
            BapDungeonJoin.handleChat(message);
        }
    }
}
