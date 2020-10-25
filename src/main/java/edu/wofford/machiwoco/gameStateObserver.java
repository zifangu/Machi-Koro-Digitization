package edu.wofford.machiwoco;

public class gameStateObserver extends Observer{

    public gameStateObserver(gameStateSubject gameSubject){
        this.gameSubject = gameSubject;
        this.gameSubject.attach(this);
    }

   /* @Override
    public void update() {
        System.out.println( "Player " + diceSubject.getPlayer() +" rolled ["+diceSubject.getDice()+"] = " + diceSubject.getDice() + ".");
    }*/
}