package edu.wofford.machiwoco;

public class DiceObserver extends Observer{

    public DiceObserver(DiceSubject diceSubject){
        this.diceSubject = diceSubject;
        this.diceSubject.attach(this);
    }

    @Override
    public void update() {
        if (diceSubject.getDiceNum() == 1) {
            System.out.println( "Player " + diceSubject.getActivePlayer().getPlayerNumber() +" rolled ["+diceSubject.getDice()+"] = " + diceSubject.getDice() + ".");
        } else {
            System.out.println( "Player " + diceSubject.getActivePlayer().getPlayerNumber() +" rolled ["+diceSubject.getDicePair()[0]+"]["+diceSubject.getDicePair()[1]+"] = " + diceSubject.getDice() + ".");
        }
    }
}
