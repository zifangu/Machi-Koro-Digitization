package edu.wofford.machiwoco;

public class DiceObserver extends Observer{

    public DiceObserver(DiceSubject diceSubject){
        this.diceSubject = diceSubject;
        this.diceSubject.attach(this);
    }

    @Override
    public void update() {
        System.out.println( "Player " + diceSubject.getActivePlayer().getPlayerNumber() +" rolled ["+diceSubject.getDice()+"] = " + diceSubject.getDice() + ".");
    }
}
