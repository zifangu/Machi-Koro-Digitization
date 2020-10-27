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

    /**
     * Gets Active Player
     * @return Active Player
     */
    public Player getActivePlayer() {
        return activePlayer;
    }

    /**
     * Sets Active Player
     * @param activePlayer Player object of active player
     */
    public void setActivePlayer(Player activePlayer) {
        this.activePlayer = activePlayer;
    }

    /**
     * Gets All Players
     * @return Array of All Players
     */
    public Player[] getPlayers() {
        return players;
    }

    /**
     * Sets All Players
     * @param players Array of All Players
     */
    public void setPlayers(Player[] players) {
        this.players = players;
    }

    /**
     * Gets input from Player
     * @return String of input
     */
    public String getInput() {
        return input;
    }

    /**
     * Sets input from players
     * @param input Input String
     */
    public void setInput(String input) {
        this.input = input;
    }

    /**
     * Attaches observer to subject
     * @param observer observer to attach to subject
     */
    public void attach(Observer observer){
        observers.add(observer);
    }

    /**
     * Notifies observers when action occurs
     */
    public void notifyObservers(){
        for (Observer observer : observers) {
            observer.update();
        }
    }
}
