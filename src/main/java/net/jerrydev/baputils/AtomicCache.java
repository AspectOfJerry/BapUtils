package net.jerrydev.baputils;

import net.jerrydev.baputils.features.dungeons.CatacombsFloors;
import net.jerrydev.baputils.utils.CausalRelation;
import org.jetbrains.annotations.Nullable;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicReference;

/**
 * Temporary caching solution.
 * <p>
 * Important Notice for Users:
 * <p>
 * This class is designed to provide a caching mechanism, but it is crucial to use
 * fully atomic operations when interacting with its fields to ensure proper thread safety.
 * <p>
 * Concurrency Issues:
 * When multiple threads access the same data simultaneously, there is a risk of race conditions
 * and concurrency issues. These issues can lead to unexpected behavior and data corruption.
 * <p>
 * Ensuring Thread Safety:
 * To prevent these problems, always use atomic methods provided by the AtomicBoolean and AtomicReference
 * classes when reading or modifying the fields of this class.
 * <p>
 * Example:
 * - Use AtomicBoolean.get() and AtomicBoolean.compareAndSet() for AtomicBoolean fields.
 * - Use AtomicReference.get() and AtomicReference.set() for AtomicReference fields.
 * <p>
 * By following this guideline, you can use this caching solution effectively in a multi-threaded
 * environment without compromising data integrity.
 */
public final class AtomicCache {
    // server
    public static final AtomicBoolean isInParty = new AtomicBoolean(false);
    @Nullable
    public static final AtomicReference<String> lastPartyLeader = new AtomicReference<>(null);

    // chat
    public static final AtomicReference<List<String>> serverChatQueue = new AtomicReference<>(new CopyOnWriteArrayList<>());

    // dungeons
    @Nullable
    public static final AtomicBoolean inDungeon = new AtomicBoolean(false);
    public static final AtomicReference<CatacombsFloors> lastCatacombsFloor = new AtomicReference<>(null);
    @Nullable
    public static final AtomicReference<List<CausalRelation>> dungeonFails = new AtomicReference<>(new CopyOnWriteArrayList<>());
}
