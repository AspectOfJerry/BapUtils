package net.jerrydev.baputils.utils;

import net.jerrydev.baputils.AtomicMemCache;
import net.minecraft.client.Minecraft;
import net.minecraft.util.ChatComponentText;

import java.util.Arrays;
import java.util.List;

import static net.jerrydev.baputils.Constants.kClientPrefix;
import static net.jerrydev.baputils.utils.ChatStyles.ccolorize;

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

        return threadName + " (id: " + threadId + ") [total:" + totalActiveThreads + "]";
    }

    /**
     * "cout" for "client output", not "console output" from C++.
     *
     * @param message Message to be printed to the client chat.
     */
    public static void cout(String message) {
        if(AtomicMemCache.clientDebug.get()) {
            Minecraft.getMinecraft().thePlayer.addChatMessage(new ChatComponentText(ccolorize(ChatStyles.CCodes.DARK_GREEN, kClientPrefix) + " " + ccolorize(ChatStyles.CCodes.GRAY, message)));
            System.out.println(kClientPrefix + " " + message);
        }
    }
}