package edu.wofford.machiwoco;

import java.util.ArrayList;
import java.util.List;

public class InputSubject {

    private List<Observer> observers = new ArrayList<Observer>();
    private Player activePlayer;
    private Player[] players;
    private String input;

    public InputSubject(Player activePlayer, Player[] players, String input) {
        this.activePlayer = activePlayer;
        this.players = players;
        this.input = input;
    }

    public Player getActivePlayer() {
        return activePlayer;
    }

    public void setActivePlayer(Player activePlayer) {
        this.activePlayer = activePlayer;
    }

    public Player[] getPlayers() {
        return players;
    }

    public void setPlayers(Player[] players) {
        this.players = players;
    }

    public String getInput() {
        return input;
    }

    public void setInput(String input) {
        this.input = input;
    }

    public void attach(Observer observer){
        observers.add(observer);
    }

    public void notifyObservers(){
        for (Observer observer : observers) {
            observer.update();
        }
    }
}
