package net.jerrydev.baputils;

import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicReference;

public final class AtomicMemCache {
    // temporary client settings from the disabled gui
    public static final AtomicBoolean clientDebug = new AtomicBoolean(false);
    public static final AtomicBoolean clientVerbose = new AtomicBoolean(true);

    // server
    public static final AtomicBoolean isInParty = new AtomicBoolean(false);
    public static final AtomicReference<String> lastPartyLeader = new AtomicReference<>(null);
}
