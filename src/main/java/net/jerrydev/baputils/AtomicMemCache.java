package net.jerrydev.baputils;

import net.jerrydev.baputils.dungeons.CatacombsFloors;
import org.jetbrains.annotations.Nullable;

import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicReference;

// temporary caching solution
public final class AtomicMemCache {
    // server
    public static final AtomicBoolean isInParty = new AtomicBoolean(false);
    @Nullable
    public static final AtomicReference<String> lastPartyLeader = new AtomicReference<>(null);

    // dungeons
    @Nullable
    public static final AtomicReference<CatacombsFloors> lastCatacombsFloor = new AtomicReference<>(null);
}
