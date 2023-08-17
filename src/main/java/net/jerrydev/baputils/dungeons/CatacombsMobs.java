package net.jerrydev.baputils.dungeons;

public enum CatacombsMobs {
    UNDEAD_SKELETON;

    public static String getFriendlyName(CatacombsMobs mob) {
        return mob.name().replaceAll("_", "").toLowerCase();
    }
}
