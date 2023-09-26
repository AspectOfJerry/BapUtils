package net.jerrydev.baputils.utils;

public class MsDelay {

    Runnable fn;
    int ms;
    long endTime;

    /**
     * Delays the code by the given amount (in milliseconds).
     */
    public MsDelay(Runnable fn, int ms) {
        this.fn = fn;
        this.ms = ms;
        endTime = System.currentTimeMillis() + ms;
        run();
    }

    /**
     * Delays the code by the given tick amount converted to milliseconds.
     */
    public MsDelay(Runnable fn, int ticks, boolean inTicks) {
        this(fn, ticks * 50);
    }

    /**
     * Delays the code by a second.
     */
    public MsDelay(Runnable fn) {
        this(fn, 1000);
    }

    public void run() {
        Thread thread = new Thread(() -> {
            try {
                Thread.sleep(ms);
                fn.run();
            } catch (InterruptedException e) {
                // empty catch block
            }
        });
        thread.start();
    }
}