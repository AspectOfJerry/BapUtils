package net.jerrydev.baputils.commands.bap;

import net.jerrydev.baputils.AtomicCache;
import net.jerrydev.baputils.BapUtils;
import net.jerrydev.baputils.Constants;
import net.jerrydev.baputils.commands.BapExecutable;
import net.jerrydev.baputils.commands.BapHandleable;
import net.jerrydev.baputils.core.BapSettingsGui;
import net.jerrydev.baputils.features.dungeons.CatacombsFloors;
import net.jerrydev.baputils.utils.ChatUtils.CCodes;
import net.jerrydev.baputils.utils.Debug;
import net.minecraft.client.Minecraft;
import net.minecraft.command.CommandException;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static net.jerrydev.baputils.BapUtils.*;
import static net.jerrydev.baputils.utils.ChatUtils.ccolorize;
import static net.jerrydev.baputils.utils.Debug.dout;

public final class BapJoinDungeon implements BapExecutable, BapHandleable {
    @Override
    public String getName() {
        return "joindungeon";
    }

    @Override
    public List<String> getAliases() {
        return Arrays.asList("dung", "join", "run", "jd");
    }

    @Override
    public String getUsage() {
        return ccolorize(CCodes.YELLOW, "/bap " + this.getName())
            + ccolorize(CCodes.GOLD, "|" + String.join("|", this.getAliases()))
            + ccolorize(CCodes.YELLOW, " <floor>");
    }

    @Override
    public byte getRequiredParams() {
        return 1;
    }

    @Override
    public String getDesc() {
        return "Joins a dungeon run on the leader's behalf";
    }

    @Override
    public void execute(List<String> args) throws CommandException {
        if(args.isEmpty()) {
            BapUtils.throwCommandException("You must specify a dungeon floor ([fm][0-7])");
            return;
        }

        final String floorName = args.get(0);

        final CatacombsFloors floor = CatacombsFloors.getFloorByCode(floorName);

        if(floor == null) {
            commandError("Unknown floor name of " + floorName + ". Valid floor names are [fm][0-7].");
            return;
        }

        new Thread(() -> {
            try {
                queueCommand("party list");
                dout("Sleep " + Constants.kCommandDelayMs + "ms " + Debug.getThreadInfoFormatted());
                Thread.sleep(Constants.kCommandDelayMs);
                dout("Resume " + Debug.getThreadInfoFormatted());

                if((AtomicCache.lastPartyLeader.get() != null)
                    && AtomicCache.lastPartyLeader.get().equals(Minecraft.getMinecraft().thePlayer.getName())) {
                    commandError("You are already the party leader!");
                    return;
                }

                queuePartyChat("$jd." + floorName);
            } catch(final InterruptedException e) {
                throw new RuntimeException(e);
            }
        }).start();
    }

    @Override
    public List<String> getPatterns() {
        return Collections.singletonList(Constants.kJoinDungeonP);
    }

    @Override
    public void handle(List<String> args) {
        if(!BapSettingsGui.INSTANCE.getJoinDungeonMaster()) {
            dout("JoinDungeon is disabled.");
            return;
        }

        final String cleanMessage = args.get(0);

        final String[] messageSplit = cleanMessage.split(" ");
        final String sender = messageSplit[2].replaceAll(":", "");

        if(sender.equals(Minecraft.getMinecraft().thePlayer.getName())) {
            dout("We are the sender, ignoring");
            return;
        }

        final Pattern pattern = Pattern.compile(Constants.kJoinDungeonP);
        final Matcher matcher = pattern.matcher(cleanMessage);

        if(!matcher.find()) {
            errorMessage("JoinDungeon no groups found. This is impossible! Please open a bug report at " + Constants.kGitHubIssues);
            return;
        }

        final String floorName = matcher.group(1);
        final CatacombsFloors floor = CatacombsFloors.getFloorByCode(floorName);

        if(floor == null) {
            commandError("Unknown floor name of " + floorName + ".");
            return;
        }

        new Thread(() -> {
            try {
                queueCommand("party list");
                dout("Sleep " + Constants.kChatDelayMs + "ms " + Debug.getThreadInfoFormatted());
                Thread.sleep(Constants.kChatDelayMs);
                dout("Resume " + Debug.getThreadInfoFormatted());

                if(AtomicCache.lastPartyLeader.get() == null) {
                    warnMessage("Couldn't find the latest party leader, what's going on!? Continuing execution anyway.");
                    dout("Check the cached party leader using /bap cache");
                }

                if(!AtomicCache.lastPartyLeader.get().equals(Minecraft.getMinecraft().thePlayer.getName())
                    && (AtomicCache.lastPartyLeader.get() != null)) {
                    clientVerbose("We are not party leader");
                    dout("We are not leader; not entering a run. Latest party leader is: " + AtomicCache.lastPartyLeader);
                    return;
                }

                final String shortFloorName = floor.chatName.replace("The Catacombs, ", "").replace(" Catacombs, ", "");

                queuePartyChat("Joining " + shortFloorName + " in 3s...", false);

                dout("Sleep 3000ms " + Debug.getThreadInfoFormatted());
                Thread.sleep(3000);
                dout("Resume " + Debug.getThreadInfoFormatted());

                queueCommand("joindungeon " + floor.commandCode);
            } catch(final InterruptedException err) {
                errorMessage("InterruptedException: JoinDungeon failed! An error occurred while transferring the party.");
            }
        }).start();
    }

    public static void autoJoinIn(String cleanMessage) {
        final Pattern pattern = Pattern.compile(Constants.kAutoJoinInP);
        final Matcher matcher = pattern.matcher(cleanMessage);

        if(!matcher.find()) {
            errorMessage("AutoJoinIn, no groups found. This is impossible! Please open a bug report at " + Constants.kGitHubIssues);
            return;
        }

        new Thread(() -> {
            try {
                if(AtomicCache.lastPartyLeader.get() == null) {
                    queueCommand("party list");
                    dout("Sleep " + Constants.kCommandDelayMs + "ms " + Debug.getThreadInfoFormatted());
                    Thread.sleep(Constants.kCommandDelayMs);
                    dout("Resume " + Debug.getThreadInfoFormatted());
                }
                if(!AtomicCache.lastPartyLeader.get().equals(Minecraft.getMinecraft().thePlayer.getName())) {
                    dout("We are not the party leader, ignoring");
                    return;
                }

                if(Math.abs(Integer.parseInt(matcher.group(2))) > 127) {
                    warnMessage("AutoJoinIn delay exceeds 127 seconds, ignoring");
                    return;
                }

                final byte delaySeconds = (byte) Math.abs(Byte.parseByte(matcher.group(2)));
                CatacombsFloors floor = AtomicCache.lastCatacombsFloor.get();
                // Group 3 may or may not be provided
                if(matcher.group(3) != null) {
                    final String floorName = matcher.group(3);
                    floor = CatacombsFloors.getFloorByCode(floorName);
                }

                if(floor == null) {
                    commandError("Couldn't find the last catacombs floor and no floor was provided.");
                    return;
                }

                queuePartyChat("Joining " + floor.shortName + " in " + delaySeconds + "s!", false);

                dout("Sleep 3000ms " + Debug.getThreadInfoFormatted());
                Thread.sleep(delaySeconds * 1000);
                dout("Resume " + Debug.getThreadInfoFormatted());

                queueCommand("joindungeon " + floor.commandCode);
            } catch(InterruptedException e) {
                throw new RuntimeException(e);
            }
        }).start();
    }
}
