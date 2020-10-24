package edu.wofford.machiwoco;

public abstract class Observer {
    protected DiceSubject diceSubject;
    public abstract void update();
}
