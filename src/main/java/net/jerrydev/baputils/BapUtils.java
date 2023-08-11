package net.jerrydev.baputils;

import net.jerrydev.baputils.commands.BapCommand;
import net.jerrydev.baputils.events.ChatHandler;
import net.jerrydev.baputils.events.ClientPeriodic;
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
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;

import static net.jerrydev.baputils.Constants.*;
import static net.jerrydev.baputils.utils.ChatColors.ccolorize;

@Mod(modid = Constants.kModId, version = Constants.kModVersion, clientSideOnly = true)
public class BapUtils {
    // dev
    public static final boolean isDev = false;

    private static final CloseableHttpClient httpClient = HttpClients.createDefault();

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

    public static void queueErrorMessage(String message) {
        Minecraft.getMinecraft().thePlayer.addChatMessage(new ChatComponentText(ccolorize(CCodes.DARK_RED, kChatClientPrefix) + " Error: " + ccolorize(CCodes.RED, message)));
    }

    public static void queueWarnMessage(String message) {
        Minecraft.getMinecraft().thePlayer.addChatMessage(new ChatComponentText(ccolorize(CCodes.RED, kChatClientPrefix) + " Error: " + ccolorize(CCodes.GOLD, message)));
    }

    public static void throwCommandException(String message) throws CommandException {
        throw new CommandException(ccolorize(CCodes.RED, kChatClientPrefix) + ccolorize(CCodes.RED, " Error: " + message));
    }
}
