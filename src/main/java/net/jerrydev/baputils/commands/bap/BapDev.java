package net.jerrydev.baputils.commands.bap;

import net.jerrydev.baputils.events.impl.DungeonStatus;
import net.jerrydev.baputils.features.dungeons.DungeonDeath;
import net.jerrydev.baputils.utils.ChatEmojis;
import net.jerrydev.baputils.utils.ChatStyles.CCodes;

import java.util.Arrays;
import java.util.List;

import static net.jerrydev.baputils.utils.ChatStyles.ccolorize;

public final class BapDev {
    public static final String commandName = "dev";
    @SuppressWarnings(value = "ArraysAsListWithZeroOrOneArgument")
    public static final List<String> commandAliases = Arrays.asList("test");
    public static final String commandUsage = ccolorize(CCodes.YELLOW, "/bap " + commandName)
        + ccolorize(CCodes.DARK_GRAY, "|" + String.join("|", commandAliases))
        + ccolorize(CCodes.YELLOW, " <...>");
    public static final byte requiredParams = -1;

    public static void execute() {
        //queueClientMessage(ccolorize(CCodes.GRAY, "zzz... nothing here..."));

        DungeonDeath.handleChat(ChatEmojis.DEATH_SKULL.emoji + "AspectOfJerry was killed by Shadow Assassin and became a ghost.");
        DungeonDeath.handleChat(ChatEmojis.DEATH_SKULL.emoji + "Tomassy was killed by Lost Adventurer and became a ghost.");
        DungeonDeath.handleChat(ChatEmojis.DEATH_SKULL.emoji + "FailingPig was killed by Angry Archeologist and became a ghost.");
        DungeonDeath.handleChat(ChatEmojis.DEATH_SKULL.emoji + "FishingIsMagic was killed by Omega-mega 2.0 and became a ghost.");
        DungeonDeath.handleChat(ChatEmojis.DEATH_SKULL.emoji + "Scarf was killed by Bonzo and became a ghost.");

        DungeonStatus.onRunEnd("263");

        // what are partial ticks?
        //WaypointRenderer.renderBeaconBeam(0, 100, 0, 0xFF00FF, 1.0f, 10);

        //queueClientMessage(ccolorize(CCodes.GRAY, "BapGui (kt) options gui test"));
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
