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

import static net.jerrydev.baputils.Constants.*;
import static net.jerrydev.baputils.utils.ChatColors.ccolorize;

@Mod(modid = Constants.kModId, version = Constants.kModVersion, clientSideOnly = true)
public class BapUtils {
    // dev
    public static final boolean isDev = true;


    @Mod.EventHandler
    public void init(FMLInitializationEvent event) {
        System.out.println(kChatClientPrefix + " HELLO from " + kModName + "! You are on Minecraft Forge version 1.8.9.");

        // Register slash (/) commands
        ClientCommandHandler.instance.registerCommand(new BapCommand());

        // Register events
        MinecraftForge.EVENT_BUS.register(new ChatHandler());
        MinecraftForge.EVENT_BUS.register(new ClientPeriodic());
    }

    @Mod.EventHandler
    public void postInit(FMLPostInitializationEvent event) {
        System.out.println(kChatClientPrefix + " BapUtils has been initialized!");
    }

    // utils
    public static void setActiveGui(GuiScreen gui) {
        ClientPeriodic.activeGui = gui;
    }

    public static void queueServerMessage(String message) {
        if (message.startsWith("/") && isDev) {
            message = message.replace("/", "./");
        }

        Minecraft.getMinecraft().thePlayer.sendChatMessage(kChatServerPrefix + " > " + message);
    }

    public static void queueServerMessage(String message, boolean addPrefix) {
        if (message.startsWith("/") && isDev) {
            message = message.replace("/", "./");
        }

        Minecraft.getMinecraft().thePlayer.sendChatMessage(addPrefix ? kChatServerPrefix + " > " + message : message);
    }

    public static void queueClientMessage(String message) {
        Minecraft.getMinecraft().thePlayer.addChatMessage(new ChatComponentText(ccolorize(CCodes.AQUA, kChatClientPrefix) + " " + message));
    }

    public static void queueClientMessage(String message, boolean addPrefix) {
        Minecraft.getMinecraft().thePlayer.addChatMessage(new ChatComponentText(addPrefix ? ccolorize(CCodes.AQUA, kChatClientPrefix) + " " + message : message));
    }

    public static void throwCommandException(String message) throws CommandException {
        throw new CommandException(ccolorize(CCodes.AQUA, kChatClientPrefix) + ccolorize(CCodes.RED, " Error: " + message));
    }

    public static void throwCommandException(String message, String causedBy) throws CommandException {
        throw new CommandException(ccolorize(CCodes.AQUA, kChatClientPrefix) + ccolorize(CCodes.RED, " Error ", false)
                + ccolorize(CCodes.ITALIC, "caused by " + causedBy) + ccolorize(CCodes.RED, ": " + message));
    }

    @Deprecated // to avoid using it
    @NonBlocking
    public static String httpGetRequest(String _url) throws IOException {
        URL url = new URL(_url);
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("GET");
        connection.setInstanceFollowRedirects(false);
        connection.setReadTimeout(5000);

        return IOUtils.toString(connection.getInputStream());
    }
}
