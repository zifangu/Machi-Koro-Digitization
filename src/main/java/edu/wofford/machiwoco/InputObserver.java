package edu.wofford.machiwoco;

public class InputObserver extends Observer{

    public InputObserver(InputSubject inputSubject){
        this.inputSubject = inputSubject;
        this.inputSubject.attach(this);
    }

    @Override
    public void update() {
       // System.out.println( "Player " + inputSubject.getActivePlayer().getPlayerNumber());
    }
}
