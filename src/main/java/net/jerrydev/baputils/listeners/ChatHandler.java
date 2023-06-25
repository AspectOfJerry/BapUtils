package net.jerrydev.baputils.listeners;

import net.minecraftforge.client.event.ClientChatReceivedEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class ChatHandler {
    @SubscribeEvent
    public void ChatHandler(ClientChatReceivedEvent event){
        if(event.message.getUnformattedText().toLowerCase().contains("hello")){
            System.out.println(event.message.getUnformattedText());
            System.out.println("Hello, World!");
        }
    }
}
