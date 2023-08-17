package net.jerrydev.baputils.utils;

import net.jerrydev.baputils.Constants;

import java.text.Normalizer;
import java.util.List;

public final class ChatStyles {
    public static String ccolorize(CCodes color, String message) {
        return color.colorCode + message + CCodes.RESET.colorCode;
    }

    public static String ccolorize(List<CCodes> colorsL, String message) {
        final StringBuilder colors = new StringBuilder();

        for(final CCodes color : colorsL) {
            colors.append(color.colorCode);
        }

        return colors + message + CCodes.RESET.colorCode;
    }

    public static String ccolorize(CCodes color, String message, boolean addReset) {
        return color.colorCode + message + (addReset ? CCodes.RESET.colorCode : "");
    }

    public static String autoTrueFalse(String tf) {
        if(tf == null) {
            return null;
        }

        if(tf.equalsIgnoreCase("true")) {
            return ccolorize(CCodes.GREEN, tf);
        }
        if(tf.equalsIgnoreCase("false")) {
            return ccolorize(CCodes.RED, tf);
        }
        throw new IllegalArgumentException("Value must be \"true\" or \"false\" (String).");
    }

    /**
     * RGBfy a string with 12 colors
     *
     * @param message Input string
     * @return RGBfied message
     */
    public static String RGBfy(String message) {
        final CCodes[] rgbCCodes = {CCodes.DARK_RED, CCodes.RED, CCodes.GOLD, CCodes.YELLOW, CCodes.GREEN, CCodes.DARK_GREEN,
            CCodes.AQUA, CCodes.DARK_AQUA, CCodes.BLUE, CCodes.DARK_BLUE, CCodes.LIGHT_PURPLE, CCodes.DARK_PURPLE};

        final StringBuilder rgbMessage = new StringBuilder();

        for(int i = 0; i < message.length(); i++) {
            final char character = message.charAt(i);
            final CCodes color = rgbCCodes[i % rgbCCodes.length];
            rgbMessage.append(color.colorCode).append(character);
        }

        return rgbMessage + CCodes.RESET.colorCode;
    }

    /**
     * Rainbowify a string with 7 colors
     *
     * @param message Input string
     * @return rainbowified message
     */
    public static String rainbowify(String message) {
        final CCodes[] rainbowCCodes = {CCodes.RED, CCodes.GOLD, CCodes.YELLOW, CCodes.GREEN, CCodes.BLUE, CCodes.DARK_BLUE, CCodes.DARK_PURPLE};

        final StringBuilder rainbowMessage = new StringBuilder();

        for(int i = 0; i < message.length(); i++) {
            final char character = message.charAt(i);
            final CCodes color = rainbowCCodes[i % rainbowCCodes.length];
            rainbowMessage.append(color.colorCode).append(character);
        }

        return rainbowMessage + CCodes.RESET.colorCode;
    }

    /**
     * Removes Minecraft color control codes, removes Hypixel rank prefixes, and applies character normalization.
     *
     * @param message Message to clean
     * @return Cleaned string
     */
    public static String cleanString(String message) {
        String cleanMessage = stripColorCodes(message);
        cleanMessage = removeHypixelRanks(cleanMessage);
        Normalizer.normalize(cleanMessage, Normalizer.Form.NFKD);
        return cleanMessage;
    }

    /**
     * Removes Minecraft color control codes from a string.
     *
     * @param message Message to clean
     * @return Cleaned string
     */
    public static String stripColorCodes(String message) {
        return message.replaceAll(Constants.kColorCodePat, "");
    }

    /**
     * Removes Hypixel's rank prefixes from a string.
     *
     * @param message Message to clean
     * @return Cleaned string
     */
    public static String removeHypixelRanks(String message) {
        return message.replaceFirst(Constants.kHypixelRankPat, "");
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
