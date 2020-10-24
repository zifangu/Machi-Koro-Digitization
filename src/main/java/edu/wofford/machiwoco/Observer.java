package edu.wofford.machiwoco;

public abstract class Observer {
    protected Subject subject;
    public abstract void update();
}
