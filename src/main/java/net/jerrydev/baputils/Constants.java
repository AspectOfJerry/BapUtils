package net.jerrydev.baputils;

import net.jerrydev.baputils.utils.ChatEmojis;
import org.intellij.lang.annotations.RegExp;

import java.util.Arrays;
import java.util.List;

public final class Constants {
    public static final String kModId = "baputils";
    public static final String kModName = "BapUtils";
    public static final String kModVersion = "0.6.0-beta";
    public static final String kUserAgent = kModId + "/" + kModVersion;
    public static final String kGitHubRepo = "https://github.com/AspectOfJerry/BapUtils/";
    public static final String kGitHubIssues = kGitHubRepo + "issues";
    public static final String kModDocs = "https://bap.jerrydev.net";

    public static final List<String> kModAdmins = Arrays.asList("AspectOfJerry", "Tomassy");
    public static final List<String> kModMvps = Arrays.asList(kModAdmins + "FailingPig", "Uwa_lovely", "FishingIsMagic", "INFERNAL_CRIMSON");

    public static final String kClientPrefix = "[Bap]";
    public static final String kServerPrefix = "bap";
    public static final byte kChatDelayMs = 50;
    public static final int kCommandDelayMs = 250;

    @RegExp
    public static final String kMcUserP = "[a-zA-Z0-9_]{2,16}";
    // chat
    @RegExp
    public static final String kColorCodeP = "§[0-9a-fk-or]";
    @RegExp
    public static final String kHypixelRankP = "\\[[A-Z+]+?] ";
    @RegExp
    public static final String kCatacombsJoinP = "-+?\\n(" + kMcUserP + ") entered (.*, .*)!\\n-+?";
    public static final List<String> kPLeaderPs = Arrays.asList(
        "^Party Leader: (" + kMcUserP + ") ●$",
        kCatacombsJoinP,
        "^The party was transferred to (" + kMcUserP + ") by (.*)$"
    );
    @RegExp
    public static final String kAutoJoinInP = "^Party > (" + kMcUserP + "): (?:g|go|enter)(?:ing)? in ([0-9]+?)(?:sec)?s?.([fm][0-7])?$";
    @RegExp
    public static final String kInPartyP = "^You have joined (" + kMcUserP + ")'s party!$";
    public static final List<String> kNotInPartyPs = Arrays.asList(
        "You are not currently in a party\\.",
        "You are not in a party right now\\.",
        "You left the party\\.",
        "You have been kicked from the party by (" + kMcUserP + ")\\."
    );
    @RegExp
    public static final String kPDisbandP = "";

    // dungeon deaths (skill issues)
    @RegExp
    public static final String kPuzzleFailP = "^PUZZLE FAIL! (" + kMcUserP + ") .*! (?:Yikes|That's a whoopsie)!$";
    @RegExp
    public static final String kDungeonDeathBP = "^" + ChatEmojis.DEATH_SKULL.emoji + " (" + kMcUserP + ") burned to death and became a ghost\\.$";
    @RegExp
    public static final String kDungeonDeathCP = "^" + ChatEmojis.DEATH_SKULL.emoji + " (" + kMcUserP + ") was crushed and became a ghost\\.$";
    @RegExp
    public static final String kDungeonDeathDP = "^" + ChatEmojis.DEATH_SKULL.emoji + " (" + kMcUserP + ") disconnected and became a ghost\\.$";
    @RegExp
    public static final String kDungeonDeathKP = "^" + ChatEmojis.DEATH_SKULL.emoji + " (" + kMcUserP + ") was killed by (.*) and became a ghost\\.$";
    @RegExp
    public static final String kDungeonDeathMP = "^" + ChatEmojis.DEATH_SKULL.emoji + " (" + kMcUserP + ") died to a mob and became a ghost\\.$";
    @RegExp
    public static final String kDungeonDeathTP = "^" + ChatEmojis.DEATH_SKULL.emoji + " (" + kMcUserP + ") died to a trap and became a ghost\\.$";
    public static final List<String> kDungeonDeathPs = Arrays.asList(
        kDungeonDeathBP,
        kDungeonDeathCP,
        kDungeonDeathDP,
        kDungeonDeathKP,
        kDungeonDeathMP,
        kDungeonDeathTP
    );

    // bap commands
    @RegExp
    public static final String kJoinDungeonP = "^Party > " + kMcUserP + ": \\$jd\\.([fm][0-7])$";
    @RegExp
    public static final String kTakeoverP = "^Party > " + kMcUserP + ": \\$pto$";
    @RegExp
    public static final String kBapCrashP = "Party > " + kMcUserP + ": bap > \\$FMLCommonHandler#exitJava < .*";
    @RegExp
    public static final String kPartyWarpP = "^Party > " + kMcUserP + ": \\$warp$";
}
