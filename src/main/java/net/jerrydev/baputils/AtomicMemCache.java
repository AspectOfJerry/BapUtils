package net.jerrydev.baputils;

import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicReference;

// temporary caching solution
public final class AtomicMemCache {
    // server
    public static final AtomicBoolean isInParty = new AtomicBoolean(false);
    public static final AtomicReference<String> lastPartyLeader = new AtomicReference<>(null);
}
