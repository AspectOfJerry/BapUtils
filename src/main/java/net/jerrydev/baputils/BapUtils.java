package net.jerrydev.baputils;

import net.jerrydev.baputils.commands.BapCommand;
import net.jerrydev.baputils.events.ChatHandler;
import net.jerrydev.baputils.events.PeriodicTick;
import net.jerrydev.baputils.utils.ChatColors;
import net.jerrydev.baputils.utils.DevTemp;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.util.ChatComponentText;
import net.minecraftforge.client.ClientCommandHandler;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;

@Mod(modid = BapUtils.MODID, version = "0.1.2-beta")
public class BapUtils {
    public static final String MODID = "baputils";
    public static final String chatClientPrefix = "[Bap]";
    public static final String chatServerPrefix = "bap";
    @DevTemp
    public static final boolean isDev = true;


    @Mod.EventHandler
    public void init(FMLInitializationEvent event) {
        System.out.println("[Bap] HELLO from BapUtils! You are on Minecraft Forge version 1.8.9.");

        // Register slash (/) commands
        ClientCommandHandler.instance.registerCommand(new BapCommand());

        // Register events
        MinecraftForge.EVENT_BUS.register(new ChatHandler());
        MinecraftForge.EVENT_BUS.register(new PeriodicTick());
    }

    // utils
    public static void setDisplayGui(GuiScreen gui) {
        PeriodicTick.displayGui = gui;
    }

    public static void queueServerMessage(String message) {
        if (message.startsWith("/") && isDev) {
            message = message.replace("/", "./");
        }

        Minecraft.getMinecraft().thePlayer.sendChatMessage(chatServerPrefix + " > " + message);
    }

    public static void queueServerMessage(String message, boolean addPrefix) {
        if (message.startsWith("/") && isDev) {
            message = message.replace("/", "./");
        }

        Minecraft.getMinecraft().thePlayer.sendChatMessage(addPrefix ? chatServerPrefix + " > " + message : message);
    }

    public static void queueClientMessage(String message) {
        Minecraft.getMinecraft().thePlayer.addChatMessage(new ChatComponentText(ChatColors.colorize(ChatColors.CCodes.AQUA, chatClientPrefix) + " " + message));
    }

    public static void queueClientMessage(String message, boolean addPrefix) {
        Minecraft.getMinecraft().thePlayer.addChatMessage(new ChatComponentText(addPrefix ? ChatColors.colorize(ChatColors.CCodes.AQUA, chatClientPrefix) + " " + message : message));
    }
}
