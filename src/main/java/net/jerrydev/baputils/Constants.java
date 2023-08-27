package net.jerrydev.baputils;

import org.intellij.lang.annotations.RegExp;

import java.util.Arrays;
import java.util.List;

public final class Constants {
    public static final String kModId = "baputils";
    public static final String kModName = "BapUtils";
    public static final String kModVersion = "0.5.5";
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
    public static final String kMcUserPat = "[a-zA-Z0-9_]{2,16}";
    // chat
    @RegExp
    public static final String kColorCodePat = "§[0-9a-fk-or]";
    @RegExp
    public static final String kHypixelRankPat = "\\[[A-Z+]+?] ";
    @RegExp
    public static final String kCatacombsJoinPat = "-+?\\n(" + kMcUserPat + ") entered (.*, .*)!\\n-+?";
    public static final List<String> kPLeaderPats = Arrays.asList("^Party Leader: (" + kMcUserPat + ") ●$", kCatacombsJoinPat, "^The party was transferred to (" + kMcUserPat + ") by (.*)$");
    @RegExp
    public static final String kAutoJoinInPat = "^Party > (" + kMcUserPat + "): (?:go|enter)(?:ing)? in ([0-9]+)s.([fm][0-7])?$";
    @RegExp
    public static final String kInPartyPat = "^You have joined (" + kMcUserPat + ")'s party!$";
    public static final List<String> kNotInPartyPats = Arrays.asList(
        "You are not currently in a party.",
        "You are not in a party right now.",
        "You left the party.",
        "You have been kicked from the party by (" + kMcUserPat + ")."
    );
    // bap commands
    @RegExp
    public static final String kJoinDungeonPat = "^Party > " + kMcUserPat + ": \\$jd\\.([fm][0-7])$";
    @RegExp
    public static final String kTakeoverPat = "^Party > " + kMcUserPat + ": \\$pto$";
    @RegExp
    public static final String kBapCrashPat = "Party > " + kMcUserPat + ": bap > \\$FMLCommonHandler#exitJava < .*";
    @RegExp
    public static final String kPartyWarpPat = "^Party > " + kMcUserPat + ": \\$warp$";
    @RegExp
    public static final String kSelfPLeavePat = "";
    @RegExp
    public static final String kPDisbandPat = "";
}
