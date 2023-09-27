package net.jerrydev.baputils.utils;

import java.util.Timer;
import java.util.TimerTask;

public final class Delay {
    public static void setTimeout(Runnable function, long delayMs) {
        Debug.dout("Scheduled a new task to run after " + delayMs + "ms.");
        Timer timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                function.run();
                timer.cancel(); // Terminate the timer after execution.
            }
        }, delayMs);
    }
}
