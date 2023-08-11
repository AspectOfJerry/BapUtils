package net.jerrydev.baputils.utils;

import net.jerrydev.baputils.RuntimeData;
import net.minecraft.client.Minecraft;
import net.minecraft.util.ChatComponentText;

import java.util.Arrays;
import java.util.List;

import static net.jerrydev.baputils.Constants.kChatClientPrefix;
import static net.jerrydev.baputils.utils.ChatColors.ccolorize;

public class Debug {
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
        if (RuntimeData.clientDebugVerbose) {
            Minecraft.getMinecraft().thePlayer.addChatMessage(new ChatComponentText(ccolorize(ChatColors.CCodes.DARK_GREEN, kChatClientPrefix) + " " + ccolorize(ChatColors.CCodes.GRAY, message)));
            System.out.println(kChatClientPrefix + " " + message);
        }
    }
}
