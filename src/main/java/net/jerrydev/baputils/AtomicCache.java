package net.jerrydev.baputils;

import net.jerrydev.baputils.features.dungeons.CatacombsFloors;
import net.jerrydev.baputils.utils.CausalRelation;
import org.jetbrains.annotations.Nullable;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicReference;

// temporary caching solution
public final class AtomicCache {
    // server
    public static final AtomicBoolean isInParty = new AtomicBoolean(false);
    @Nullable
    public static final AtomicReference<String> lastPartyLeader = new AtomicReference<>(null);

    // dungeons
    @Nullable
    public static final AtomicReference<CatacombsFloors> lastCatacombsFloor = new AtomicReference<>(null);
    @Nullable
    public static final AtomicReference<List<CausalRelation>> dungeonFails = new AtomicReference<>(new CopyOnWriteArrayList<>());
}
