package net.jerrydev.baputils.utils;

public final class StringHex {
    // string to hex
    public static String stringToHex(String input) {
        final StringBuilder stringBuilder = new StringBuilder();
        for (final char c : input.toCharArray()) {
            stringBuilder.append(String.format("%02X", (int) c));
        }

        return stringBuilder.toString();
    }

    // hex to string
    public static String hexToString(String input) {
        final StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < input.length(); i += 2) {
            stringBuilder.append((char) Integer.parseInt(input.substring(i, i + 2), 16));
        }

        return stringBuilder.toString();
    }
}
