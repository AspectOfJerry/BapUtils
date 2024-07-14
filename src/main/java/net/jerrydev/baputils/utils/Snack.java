package net.jerrydev.baputils.utils;

/**
 * Inside joke
 */
public class Snack {
    private final String name;

    public Snack(String name) {
        this.name = name;
    }

    public void consume() {
        System.out.println("Consuming " + name);
    }
}
