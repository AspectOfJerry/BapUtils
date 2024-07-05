package net.jerrydev.baputils.utils;

import net.jerrydev.baputils.core.BapSettingsGui;
import net.minecraft.client.Minecraft;
import net.minecraft.util.ChatComponentText;
import org.apache.logging.log4j.Level;

import java.util.Arrays;
import java.util.List;

import static net.jerrydev.baputils.BapUtils.logger;
import static net.jerrydev.baputils.Constants.kClientPrefix;
import static net.jerrydev.baputils.utils.ChatUtils.CCodes;
import static net.jerrydev.baputils.utils.ChatUtils.cc;

public final class Debug {
    /**
     * Returns a list of strings containing information about the current thread.
     *
     * @return List of strings containing information about the current thread.
     * [threadId, threadName, threadState, threadPriority]
     */
    public static List<String> getThreadInfo() {
        final String threadId = String.valueOf(Thread.currentThread().getId());
        final String threadName = Thread.currentThread().getName();
        final String threadState = String.valueOf(Thread.currentThread().getState());
        final String threadPriority = String.valueOf(Thread.currentThread().getPriority());

        return Arrays.asList(threadId, threadName, threadState, threadPriority);
    }

    public static String getThreadInfoFormatted() {
        final String threadId = String.valueOf(Thread.currentThread().getId());
        final String threadName = Thread.currentThread().getName();
        // final String threadPriority = String.valueOf(Thread.currentThread().getPriority());
        // final String threadGroupMaxPriority = String.valueOf(Thread.currentThread().getThreadGroup().getMaxPriority());
        final String totalActiveThreads = String.valueOf(Thread.activeCount());

        return threadName + " (id:" + threadId + ") [total:" + totalActiveThreads + "]";
    }

    /**
     * Client debug messages
     *
     * @param message Message to be printed to the client chat.
     */
    public static void dout(String message) {
        logger.log(Level.INFO, "[DEBUG] " + message);

        if (BapSettingsGui.INSTANCE.getClientChatDebug()) {
            Minecraft.getMinecraft().thePlayer.addChatMessage(new ChatComponentText(cc(CCodes.DARK_GREEN, kClientPrefix) + " " + cc(CCodes.GRAY, message)));
        }
    }
}
