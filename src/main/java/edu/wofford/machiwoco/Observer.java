package edu.wofford.machiwoco;

public abstract class Observer {
    protected DiceSubject diceSubject;
    protected InputSubject inputSubject;
    public abstract void update();
}
