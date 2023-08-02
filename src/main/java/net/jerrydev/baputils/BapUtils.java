package net.jerrydev.baputils;

import net.jerrydev.baputils.commands.BapCommand;
import net.jerrydev.baputils.events.ChatHandler;
import net.jerrydev.baputils.events.ClientPeriodic;
import net.jerrydev.baputils.utils.ChatColors;
import net.jerrydev.baputils.utils.ChatColors.CCodes;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.command.CommandException;
import net.minecraft.util.ChatComponentText;
import net.minecraftforge.client.ClientCommandHandler;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import org.apache.commons.io.IOUtils;
import org.jetbrains.annotations.NonBlocking;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;

@Mod(modid = BapUtils.MODID, version = "0.1.2-beta.2")
public class BapUtils {
    public static final String MODID = "baputils";
    public static final String chatClientPrefix = "[Bap]";
    public static final String chatServerPrefix = "bap";

    public static final boolean isDev = true;


    @Mod.EventHandler
    public void init(FMLInitializationEvent event) {
        System.out.println(chatClientPrefix + " HELLO from BapUtils! You are on Minecraft Forge version 1.8.9.");

        // Register slash (/) commands
        ClientCommandHandler.instance.registerCommand(new BapCommand());

        // Register events
        MinecraftForge.EVENT_BUS.register(new ChatHandler());
        MinecraftForge.EVENT_BUS.register(new ClientPeriodic());
    }

    @Mod.EventHandler
    public void postInit(FMLPostInitializationEvent event) {
        System.out.println(chatClientPrefix + " BapUtils has been initialized!");
    }

    // utils
    public static void setActiveGui(GuiScreen gui) {
        ClientPeriodic.activeGui = gui;
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
        Minecraft.getMinecraft().thePlayer.addChatMessage(new ChatComponentText(ChatColors.ccolorize(CCodes.AQUA, chatClientPrefix) + " " + message));
    }

    public static void queueClientMessage(String message, boolean addPrefix) {
        Minecraft.getMinecraft().thePlayer.addChatMessage(new ChatComponentText(addPrefix ? ChatColors.ccolorize(CCodes.AQUA, chatClientPrefix) + " " + message : message));
    }

    public static void throwCommandException(String message) throws CommandException {
        throw new CommandException(ChatColors.ccolorize(CCodes.AQUA, chatClientPrefix) + ChatColors.ccolorize(CCodes.RED, " CommandException: " + message));
    }

    public static void throwCommandException(String message, String causedBy) throws CommandException {
        throw new CommandException(ChatColors.ccolorize(CCodes.AQUA, chatClientPrefix) + ChatColors.ccolorize(CCodes.RED, " CommandException ", false)
                + ChatColors.ccolorize(CCodes.ITALIC, "caused by " + causedBy) + ChatColors.ccolorize(CCodes.RED, ": " + message));
    }

    @Deprecated // to avoid using it
    @NonBlocking // currently blocking
    public static String httpGetRequest(String _url) throws IOException {
        URL url = new URL(_url);
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("GET");
        connection.setInstanceFollowRedirects(false);
        connection.setReadTimeout(5000);

        return IOUtils.toString(connection.getInputStream());
    }
}
