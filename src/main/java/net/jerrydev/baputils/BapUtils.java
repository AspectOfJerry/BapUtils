package net.jerrydev.baputils;

import gg.essential.universal.ChatColor;
import net.jerrydev.baputils.commands.Bap;
import net.jerrydev.baputils.events.ChatHandler;
import net.jerrydev.baputils.utils.ChatColorCodes;
import net.jerrydev.baputils.utils.ChatColors;
import net.minecraft.client.Minecraft;
import net.minecraft.util.ChatComponentText;
import net.minecraftforge.client.ClientCommandHandler;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;

@Mod(modid = BapUtils.MODID, version = "0.1.0")
public class BapUtils {
    public static final String MODID = "baputils";
    public static final String chatClientPrefix = "[Bap]";
    public static final String chatServerPrefix = "bap";

    @Mod.EventHandler
    public void init(FMLInitializationEvent event) {
        System.out.println("bap >>> HELLO from BapUtils! You are on Minecraft Forge version 1.8.9.");

        // Register slash (/) commands
        ClientCommandHandler.instance.registerCommand(new Bap());

        // Register events
        MinecraftForge.EVENT_BUS.register(new ChatHandler());
    }

    // utils
    public static void queueServerMessage(String message) {
        Minecraft.getMinecraft().thePlayer.sendChatMessage(chatServerPrefix + " > " + message);
    }

    public static void queueServerMessage(String message, boolean addPrefix) {
        Minecraft.getMinecraft().thePlayer.sendChatMessage(addPrefix ? chatServerPrefix + " > " + message : message);
    }

    public static void queueClientMessage(String message) {
        Minecraft.getMinecraft().thePlayer.addChatMessage(new ChatComponentText(ChatColors.colorize(ChatColorCodes.AQUA, chatClientPrefix) + " " + message));
    }

    public static void queueClientMessage(String message, boolean addPrefix) {
        Minecraft.getMinecraft().thePlayer.addChatMessage(new ChatComponentText(addPrefix ? ChatColors.colorize(ChatColorCodes.AQUA, chatClientPrefix) + " " + message : message));
    }
}
