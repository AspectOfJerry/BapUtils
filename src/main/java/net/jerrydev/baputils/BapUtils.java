package net.jerrydev.baputils;

import net.jerrydev.baputils.commands.BapCommand;
import net.jerrydev.baputils.gui.DemoGui;
import net.jerrydev.baputils.listeners.ChatHandler;
import net.minecraft.init.Blocks;
import net.minecraftforge.client.ClientCommandHandler;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.eventhandler.EventBus;

@Mod(modid = BapUtils.MODID, version = "1.0.0")
public class BapUtils {
    public static final String MODID = "baputils";

    @Mod.EventHandler
    public void init(FMLInitializationEvent event) {
        System.out.println("Bap$HELLO from BapUtils! You are on Forge version 1.8.9.");

        // Register slash (/) commands
        ClientCommandHandler.instance.registerCommand(new BapCommand());

        // Register events
        MinecraftForge.EVENT_BUS.register(new ChatHandler());
    }
}
