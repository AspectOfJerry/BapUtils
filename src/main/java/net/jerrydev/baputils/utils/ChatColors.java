package net.jerrydev.baputils.utils;

public class ChatColors {
    public static String colorize(ChatColorCodes color, String message) {
        return "";
    }

    public static String stripColorCodes(String message) {
        return message.replaceAll("§[0-9a-fk-or]", "");
    }
}
