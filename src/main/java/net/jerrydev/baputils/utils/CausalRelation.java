package net.jerrydev.baputils.utils;

public class CausalRelation {
    public String actor;
    public String target;
    public boolean leftGreen;
    public boolean positive;

    public CausalRelation(String _actor, String _target, boolean _leftGreen, boolean _positive) {
        this.actor = _actor;
        this.target = _target;
        this.leftGreen = _leftGreen;
        this.positive = _positive;
    }
}
