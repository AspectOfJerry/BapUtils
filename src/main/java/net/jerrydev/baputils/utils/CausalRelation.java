package net.jerrydev.baputils.utils;

public class CausalRelation {
    public final String actor;
    public final String target;
    public final boolean leftGreen;
    public final boolean positive;

    public CausalRelation(String _actor, String _target, boolean _leftGreen, boolean _positive) {
        this.actor = _actor;
        this.target = _target;
        this.leftGreen = _leftGreen;
        this.positive = _positive;
    }
}
