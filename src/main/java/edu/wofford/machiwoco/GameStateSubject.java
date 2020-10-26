package edu.wofford.machiwoco;

import edu.wofford.machiwoco.Establishment;
import edu.wofford.machiwoco.Observer;
import edu.wofford.machiwoco.Player;

import java.util.*;

public class GameStateSubject {

    private List<edu.wofford.machiwoco.Observer> observers = new ArrayList<edu.wofford.machiwoco.Observer>();
    Establishment[] EST_ORDER;
    Player[] players;
    Map<Establishment,Integer> market;

    public GameStateSubject(Establishment[] EST_ORDER, Player[] players, Map<Establishment,Integer> market) {
        this.EST_ORDER = EST_ORDER;
        this.players = players;
        this.market = market;
    }
    /**
     * Gets Establishment Order
     * @return Array of correctly-ordered eEstablishments
     */
    public Establishment[] getEstOrder() {
        return EST_ORDER;
    }

    /**
     * Sets correctly-ordered array of Establishment Order
     * @param EST_ORDER Array of ordered Establishments
     */
    public void setEstOrder(Establishment[] EST_ORDER) {
        this.EST_ORDER = EST_ORDER;
    }

    /**
     * Gets array of all players
     * @return Array of players
     */
    public Player[] getPlayers() {
        return players;
    }

    /**
     * Sets correctly-ordered array of Players
     * @param players array of players in current game
     */
    public void setPlayers(Player[] players) {
        this.players = players;
    }

    /**
     * Gets Hashmap of Market
     * @return Hashmap of Market
     */
    public Map<Establishment,Integer> getMarket() {
        return market;
    }

    /**
     * Sets Hashmap of Market
     * @param  market Hashmap of market
     */
    public void setMarket(Map<Establishment,Integer> market) {
        this.market = market;
    }

    /**
     * Attaches observer to subject
     * @param observer observer to attach to subject
     */
    public void attach(edu.wofford.machiwoco.Observer observer){
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