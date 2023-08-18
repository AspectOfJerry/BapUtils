package net.jerrydev.baputils;

import net.jerrydev.baputils.commands.BapCommand;
import net.jerrydev.baputils.events.ChatHandler;
import net.jerrydev.baputils.events.ClientPeriodic;
import net.jerrydev.baputils.utils.ChatStyles.CCodes;
import net.jerrydev.baputils.utils.Debug;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.command.CommandException;
import net.minecraft.util.ChatComponentText;
import net.minecraftforge.client.ClientCommandHandler;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import org.jetbrains.annotations.NonBlocking;

import java.util.Arrays;

import static net.jerrydev.baputils.Constants.*;
import static net.jerrydev.baputils.utils.ChatStyles.ccolorize;

@Mod(modid = Constants.kModId, version = Constants.kModVersion, clientSideOnly = true)
public class BapUtils {
    // dev
    public static final boolean isLocalDev = false;

    @Mod.EventHandler
    public void init(FMLInitializationEvent event) {
        System.out.println(kClientPrefix + " HELLO from " + kModName + "! You are on Minecraft Forge version 1.8.9.");

        // Register slash (/) commands
        ClientCommandHandler.instance.registerCommand(new BapCommand());

        // Register events
        MinecraftForge.EVENT_BUS.register(new ChatHandler());
        MinecraftForge.EVENT_BUS.register(new ClientPeriodic());
    }

    @Mod.EventHandler
    public void postInit(FMLPostInitializationEvent event) {
        System.out.println(kClientPrefix + " BapUtils has been initialized!");
    }

    // utils
    public static void setActiveGui(GuiScreen gui) {
        ClientPeriodic.activeGui = gui;
    }

    public static void clientVerbose(String message) {
        if(AtomicMemCache.clientVerbose.get()) {
            Minecraft.getMinecraft().thePlayer.addChatMessage(new ChatComponentText(
                ccolorize(CCodes.GRAY, kClientPrefix) + " " + ccolorize(Arrays.asList(CCodes.DARK_GRAY, CCodes.ITALIC), message)
            ));
        }
    }

    @NonBlocking
    public static void queueServerMessage(String message) {
        Minecraft.getMinecraft().thePlayer.sendChatMessage(kServerPrefix + " > " + message);
    }

    @NonBlocking
    public static void queueServerMessage(String message, boolean addPrefix) {
        Minecraft.getMinecraft().thePlayer.sendChatMessage(addPrefix ? (kServerPrefix + " > " + message) : message);
    }

    @NonBlocking
    public static void queueCommand(String command) {
        Debug.dout("Executing: /" + command);
        Minecraft.getMinecraft().thePlayer.sendChatMessage((isLocalDev ? "." : "") + "/" + command);
    }

    public static void queueClientMessage(String message) {
        Minecraft.getMinecraft().thePlayer.addChatMessage(new ChatComponentText(
            ccolorize(CCodes.AQUA, kClientPrefix) + " " + message
        ));
    }

    public static void queueClientMessage(String message, boolean addPrefix) {
        Minecraft.getMinecraft().thePlayer.addChatMessage(new ChatComponentText(
            addPrefix ? (ccolorize(CCodes.AQUA, kClientPrefix) + " " + message) : message
        ));
    }

    public static void queueClientMessage(String message, CCodes prefixColor) {
        Minecraft.getMinecraft().thePlayer.addChatMessage(new ChatComponentText(
            ccolorize(prefixColor, kClientPrefix) + " " + message
        ));
    }

    public static void queueClientMessage(String message, String customPrefix) {
        Minecraft.getMinecraft().thePlayer.addChatMessage(new ChatComponentText(
            customPrefix + " " + message
        ));
    }

    public static void queueErrorMessage(String message) {
        Minecraft.getMinecraft().thePlayer.addChatMessage(new ChatComponentText(
            ccolorize(CCodes.RED, kClientPrefix + " Error: " + message)
        ));
    }

    public static void queueWarnMessage(String message) {
        Minecraft.getMinecraft().thePlayer.addChatMessage(new ChatComponentText(
            ccolorize(CCodes.GOLD, kClientPrefix + " Warning: " + message)
        ));
    }

    public static void throwCommandException(String message) throws CommandException {
        throw new CommandException(ccolorize(CCodes.RED, kClientPrefix) + ccolorize(CCodes.RED, " Error: " + message));
    }
}
