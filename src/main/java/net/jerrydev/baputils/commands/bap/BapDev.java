package net.jerrydev.baputils.commands.bap;

import net.jerrydev.baputils.BapUtils;
import net.jerrydev.baputils.utils.ChatColors.CCodes;
import net.jerrydev.baputils.utils.IBapCommand;

import java.util.Arrays;
import java.util.List;

import static net.jerrydev.baputils.utils.ChatColors.ccolorize;

public class BapDev implements IBapCommand {
    public static final String commandName = "dev";
    public static final List<String> commandAliases = Arrays.asList("test");
    public static final String commandUsage = ccolorize(CCodes.YELLOW, "/bap " + commandName)
            + ccolorize(CCodes.DARK_GRAY, "|" + String.join("|", commandAliases))
            + ccolorize(CCodes.YELLOW, " <...>");
    public static byte requiredParams = -1;

    public static void execute() {
        BapUtils.queueClientMessage(ccolorize(CCodes.GRAY, "zzz... nothing here..."));

        // minecraft username to uuid
        /* try {
            final String req = BapUtils.httpGetRequest("https://api.mojang.com/users/profiles/minecraft/aspectofjerry");

            System.out.println(req);
        } catch (IOException e) {
            throw new RuntimeException(e);
        } */
    }
}
