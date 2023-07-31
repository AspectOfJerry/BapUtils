package net.jerrydev.baputils.utils;

import java.util.List;

public class ChatColors {
    public static String colorize(CCodes color, String message) {
        return color.colorCode + message + CCodes.RESET.colorCode;
    }

    public static String colorize(List<CCodes> colorsL, String message) {
        String colors = "";

        for (CCodes color : colorsL) {
            colors += color.colorCode;
        }

        return colors + message + CCodes.RESET.colorCode;
    }

    public static String colorize(CCodes color, String message, boolean addReset) {
        return color.colorCode + message + (addReset ? CCodes.RESET.colorCode : "");
    }

    /**
     * RGBfy a string with 12 colors
     *
     * @param message Input string
     * @return RGBfied message
     */
    public static String RGBfy(String message) {
        final CCodes[] rgbCCodes = {CCodes.DARK_RED, CCodes.RED, CCodes.GOLD, CCodes.YELLOW, CCodes.GREEN, CCodes.DARK_GREEN, CCodes.AQUA, CCodes.DARK_AQUA, CCodes.BLUE, CCodes.DARK_BLUE, CCodes.LIGHT_PURPLE, CCodes.DARK_PURPLE};
        return "";
    }

    /**
     * Rainbowify a string with 7 colors
     *
     * @param message Input string
     * @return rainbowified message
     */
    public static String rainbowify(String message) {
        final CCodes[] rainbowCCodes = {CCodes.RED, CCodes.GOLD, CCodes.YELLOW, CCodes.GREEN, CCodes.BLUE, CCodes.DARK_BLUE, CCodes.DARK_PURPLE};
        return "";
    }

    /**
     * Removes Minecraft color control codes from a string.
     *
     * @param message Message to clean
     * @return Cleaned string
     */
    public static String stripColorCodes(String message) {
        return message.replaceAll("§[0-9a-fk-or]", "");
    }

    public enum CCodes {
        BLACK("§0"),
        DARK_BLUE("§1"),
        DARK_GREEN("§2"),
        DARK_AQUA("§3"),
        DARK_RED("§4"),
        DARK_PURPLE("§5"),
        GOLD("§6"),
        GRAY("§7"),
        DARK_GRAY("§8"),
        BLUE("§9"),
        GREEN("§a"),
        AQUA("§b"),
        RED("§c"),
        LIGHT_PURPLE("§d"),
        YELLOW("§e"),
        WHITE("§f"),
        OBFUSCATED("§k"),
        BOLD("§l"),
        STRIKETHROUGH("§m"),
        UNDERLINE("§n"),
        ITALIC("§o"),
        RESET("§r");

        public final String colorCode;

        CCodes(String _codeCode) {
            this.colorCode = _codeCode;
        }
    }
}
