package net.jerrydev.baputils;

import org.intellij.lang.annotations.RegExp;

import java.util.Arrays;
import java.util.List;

public final class Constants {
    public static final String kModId = "baputils";
    public static final String kModName = "BapUtils";
    public static final String kModVersion = "0.5.0";
    public static final String kUserAgent = kModId + "/" + kModVersion;
    public static final String kBugReportUrl = "https://github.com/AspectOfJerry/BapUtils/issues";

    public static final List<String> kModAdmins = Arrays.asList("AspectOfJerry", "Tomassy");
    public static final List<String> kModMvps = Arrays.asList(kModAdmins + "FailingPig", "Uwa_lovely", "FishingIsMagic", "INFERNAL_CRIMSON");

    public static final String kClientPrefix = "[Bap]";
    public static final String kServerPrefix = "bap";
    public static final byte kChatDelayMs = 50;
    public static final int kCommandDelayMs = 250;

    @RegExp
    public static final String kColorCodePat = "§[0-9a-fk-or]";
    @RegExp
    public static final String kHypixelRankPat = "\\[[A-Z+]+?] ";
    @RegExp
    public static final String kJoinDungeonPat = "^Party > .*: \\$jd\\.([fm][0-7])$";
    @RegExp
    public static final String kCatacombsJoinPat = "-+?\\n(.*) entered (.*), (.*)!\\n-+?";
    public static final List<String> kPLeaderPats = Arrays.asList("^Party Leader: (.*) ●$", kCatacombsJoinPat, "^The party was transferred to (.*) by (.*)$");
    @RegExp
    public static final String kTakeoverPat = "^Party > .*: \\$pto$";
    @RegExp
    public static final String kBapCrashPat = "Party > .*: bap > \\$FMLCommonHandler#exitJava < .*";
    @RegExp
    public static final String kPartyWarpPat = "^Party > .*: \\$warp$";
    @RegExp
    public static final String kSelfPLeavePat = "";
    @RegExp
    public static final String kPDisbandPat = "";

    public static final List<String> kNotInPartyLit = Arrays.asList("You are not currently in a party.", "You are not in a party right now.");
}
