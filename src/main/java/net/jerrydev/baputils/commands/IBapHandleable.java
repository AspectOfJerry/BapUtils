package net.jerrydev.baputils.commands;

import java.util.List;

public interface IBapHandleable {
    List<String> getPatterns();

    void handle(List<String> args);
}
