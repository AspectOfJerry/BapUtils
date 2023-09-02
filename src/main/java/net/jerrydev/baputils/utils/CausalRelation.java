package net.jerrydev.baputils.utils;

public class CausalRelation {
    public String actor;
    public String target;
    public boolean leftGreen;

    public CausalRelation(String _actor, String _target, boolean _leftGreen) {
        this.actor = _actor;
        this.target = _target;
        this.leftGreen = _leftGreen;
    }
}
