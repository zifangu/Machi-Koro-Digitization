package edu.wofford.machiwoco;

public class DiceObserver extends Observer{

    public DiceObserver(Subject subject){
        this.subject = subject;
        this.subject.attach(this);
    }

    @Override
    public void update() {
        System.out.println( "Player N rolled " + subject.getState());
    }
}
