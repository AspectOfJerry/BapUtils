package net.jerrydev.baputils;

import net.minecraft.init.Blocks;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;

@Mod(modid = BapUtils.MODID, version = "1.0.0")
public class BapUtils {
    public static final String MODID = "baputils";

    @Mod.EventHandler
    public void init(FMLInitializationEvent event) {
        System.out.println("HELLO from BapUtils! You are on Forge version 1.8.9.");
    }
}
