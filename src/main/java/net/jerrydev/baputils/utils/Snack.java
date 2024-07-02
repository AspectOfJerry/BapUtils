package net.jerrydev.baputils.utils;

public class Snack {
    String name;

    public Snack(String name) {
        this.name = name;
    }

    public void consume() {
        System.out.println("Consuming " + name);
    }
}
