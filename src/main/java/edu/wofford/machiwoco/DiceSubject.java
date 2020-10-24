package edu.wofford.machiwoco;

import java.util.ArrayList;
import java.util.List;

public class DiceSubject {

    private List<Observer> observers = new ArrayList<Observer>();
    private int player;
    private int dice;

    public DiceSubject(int player, int dice) {
        this.player = player;
        this.dice = dice;
    }

    public int getPlayer() {
        return player;
    }

    public void setPlayer(int player) {
        this.player = player;
    }

    public int getDice() {
        return dice;
    }

    public void setDice(int dice) {
        this.dice = dice;
    }
//
//    public int getState() {
//        return dice;
//    }
//
//    public void setState(int state) {
//        this.dice = state;
//        notifyObservers();
//    }

    public void attach(Observer observer){
        observers.add(observer);
    }

    public void notifyObservers(){
        for (Observer observer : observers) {
            observer.update();
        }
    }
}
