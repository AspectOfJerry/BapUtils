package net.jerrydev.baputils.commands;

import java.util.List;

public interface BapHandleable {
    List<String> getPatterns();

    void handle(List<String> args);
}
