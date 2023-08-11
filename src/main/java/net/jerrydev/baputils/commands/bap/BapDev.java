package net.jerrydev.baputils.commands.bap;

import net.jerrydev.baputils.BapUtils;
import net.jerrydev.baputils.render.BeaconBeamRenderer;
import net.jerrydev.baputils.utils.ChatColors.CCodes;
import net.jerrydev.baputils.utils.Debug;
import net.jerrydev.baputils.utils.IBapCommand;

import java.awt.*;
import java.util.Arrays;
import java.util.List;

import static net.jerrydev.baputils.utils.ChatColors.ccolorize;

public class BapDev implements IBapCommand {
    public static final String commandName = "dev";
    @SuppressWarnings("ArraysAsListWithZeroOrOneArgument")
    public static final List<String> commandAliases = Arrays.asList("test");
    public static final String commandUsage = ccolorize(CCodes.YELLOW, "/bap " + commandName)
            + ccolorize(CCodes.DARK_GRAY, "|" + String.join("|", commandAliases))
            + ccolorize(CCodes.YELLOW, " <...>");
    public static byte requiredParams = -1;

    public static void execute() {
        //BapUtils.queueClientMessage(ccolorize(CCodes.GRAY, "zzz... nothing here..."));

        BeaconBeamRenderer.renderBeaconBeam(0, 0, 100, 0xFF00FF, 1);

        new Thread(() -> {
            BapUtils.queueClientMessage("Hello, ");
            Debug.cout("Sleeping for 1000ms on " + Debug.getThreadInfoFormatted());
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            Debug.cout("Resumed " + Debug.getThreadInfoFormatted());
            BapUtils.queueClientMessage("World!");
        }).start();

        //BapUtils.queueClientMessage(ccolorize(CCodes.GRAY, "BapGui (kt) options gui test"));
        //BapUtils.setActiveGui(new BapGui());


        // minecraft username to uuid
        /* try {
            final String req = BapUtils.httpGetRequest("https://api.mojang.com/users/profiles/minecraft/aspectofjerry");

            System.out.println(req);
        } catch (IOException e) {
            throw new RuntimeException(e);
        } */
    }
}
