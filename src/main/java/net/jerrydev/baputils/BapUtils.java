package net.jerrydev.baputils;

import net.jerrydev.baputils.commands.BapCommand;
import net.jerrydev.baputils.core.BapSettingsGui;
import net.jerrydev.baputils.events.ChatListener;
import net.jerrydev.baputils.events.ClientPeriodic;
import net.jerrydev.baputils.utils.ChatUtils.CCodes;
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
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Arrays;

import static net.jerrydev.baputils.Constants.*;
import static net.jerrydev.baputils.utils.ChatUtils.ccolorize;

@Mod(modid = Constants.kModId, version = Constants.kModVersion, clientSideOnly = true)
public class BapUtils {
    // dev
    public static final boolean isLocalDev = false;

    public static final Logger logger = LogManager.getLogger(kModId);

    @Mod.EventHandler
    public void init(FMLInitializationEvent event) {
        logger.log(Level.INFO, "HELLO from " + kModName + "! You are on Minecraft Forge version 1.8.9.");

        // Register slash (/) commands
        ClientCommandHandler.instance.registerCommand(new BapCommand());

        // Register events
        MinecraftForge.EVENT_BUS.register(new ChatListener());
        MinecraftForge.EVENT_BUS.register(new ClientPeriodic());
    }

    @Mod.EventHandler
    public void postInit(FMLPostInitializationEvent event) {
        logger.log(Level.INFO, "BapUtils has been initialized!");
    }

    // utils
    public static void setActiveGui(GuiScreen gui) {
        ClientPeriodic.activeGui = gui;
    }

    public static void clientVerbose(String message) {
        if (BapSettingsGui.INSTANCE.getClientChatVerbose()) {
            Minecraft.getMinecraft().thePlayer.addChatMessage(new ChatComponentText(
                ccolorize(CCodes.GRAY, kClientPrefix) + " " + ccolorize(Arrays.asList(CCodes.DARK_GRAY, CCodes.ITALIC), message)
            ));
        }
    }


    public static void queueServerMessage(String message) {
        AtomicCache.serverChatQueue.updateAndGet((list) -> {
            list.add(message);
            return list;
        });
    }

    public static void queueServerMessage(String message, boolean addPrefix) {
        AtomicCache.serverChatQueue.updateAndGet((list) -> {
            list.add(addPrefix ? (kServerPrefix + " > " + message) : message);
            return list;
        });
    }

    public static void queuePartyChat(String message) {
        AtomicCache.serverChatQueue.updateAndGet((list) -> {
            list.add((isLocalDev ? "." : "") + "/party chat " + message);
            return list;
        });
    }

    public static void queuePartyChat(String message, boolean addPrefix) {
        AtomicCache.serverChatQueue.updateAndGet((list) -> {
            list.add((isLocalDev ? "." : "") + "/party chat " + (addPrefix ? kServerPrefix + " > " : "") + message);
            return list;
        });
    }

    public static void sendCommand(String command) {
        Debug.dout("Executing: /" + command);
        Minecraft.getMinecraft().thePlayer.sendChatMessage((isLocalDev ? "." : "") + "/" + command);
    }

    public static void sendCommand(String command, boolean pushToQueue) {
        if (pushToQueue) {
            Debug.dout("Executing: /" + command);
            AtomicCache.serverChatQueue.updateAndGet((list) -> {
                list.add((isLocalDev ? "." : "") + "/" + command);
                return list;
            });
        } else {
            sendCommand(command);
        }
    }

    public static void clientMessage(String message) {
        Minecraft.getMinecraft().thePlayer.addChatMessage(new ChatComponentText(
            ccolorize(CCodes.AQUA, kClientPrefix) + " " + message
        ));
    }

    public static void clientMessage(String message, boolean addPrefix) {
        Minecraft.getMinecraft().thePlayer.addChatMessage(new ChatComponentText(
            addPrefix ? (ccolorize(CCodes.AQUA, kClientPrefix) + " " + message) : message
        ));
    }

    public static void clientMessage(String message, CCodes prefixColor) {
        Minecraft.getMinecraft().thePlayer.addChatMessage(new ChatComponentText(
            ccolorize(prefixColor, kClientPrefix) + " " + message
        ));
    }

    public static void clientMessage(String message, String customPrefix) {
        Minecraft.getMinecraft().thePlayer.addChatMessage(new ChatComponentText(
            customPrefix + " " + message
        ));
    }

    public static void commandError(String message) {
        clientMessage(ccolorize(CCodes.RED, message), CCodes.RED);
    }

    public static void errorMessage(String message) {
        Minecraft.getMinecraft().thePlayer.addChatMessage(new ChatComponentText(
            ccolorize(CCodes.RED, kClientPrefix + " Error: " + message)
        ));
        logger.log(Level.ERROR, message);
    }

    public static void warnMessage(String message) {
        Minecraft.getMinecraft().thePlayer.addChatMessage(new ChatComponentText(
            ccolorize(CCodes.GOLD, kClientPrefix + " Warning: " + message)
        ));
        logger.log(Level.WARN, message);
    }

    public static void throwCommandException(String message) throws CommandException {
        throw new CommandException(ccolorize(CCodes.RED, kClientPrefix) + ccolorize(CCodes.RED, " Error: " + message));
    }
}
