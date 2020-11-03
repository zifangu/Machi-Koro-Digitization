package edu.wofford.machiwoco;

import edu.wofford.machiwoco.Establishment;
import edu.wofford.machiwoco.Observer;
import edu.wofford.machiwoco.Player;

import java.util.*;

/**
 * A class that represents all necessary information surrounding the current game state.
 * 
 * @author Eric Craft
 * @author Ivan Gu
 * @author Bennett Joyce
 */

public class GameStateSubject {

    private List<edu.wofford.machiwoco.Observer> observers = new ArrayList<edu.wofford.machiwoco.Observer>();
    Establishment[] EST_ORDER;
    // Player[] players;
    ArrayList<Player> players;
    Map<Establishment,Integer> market;

    /**
     * The constructor used to initialize the GameStateSubject class and its necessary variables.
     * @param EST_ORDER an array of the Establishments in order of how they are to be displayed in the current game state.
     * @param players an array of all the Players within the current version of Machi Koro.
     * @param market a Map instance representing the the current game's market
     */

    // public GameStateSubject(Establishment[] EST_ORDER, Player[] players, Map<Establishment,Integer> market) {
    public GameStateSubject(Establishment[] EST_ORDER, ArrayList<Player> players, Map<Establishment,Integer> market) {
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
    // public Player[] getPlayers() {
    public ArrayList<Player> getPlayers() {
        return players;
    }

    /**
     * Sets correctly-ordered array of Players
     * @param players array of players in current game
     */
    //public void setPlayers(Player[] players) {
    public void setPlayers(ArrayList<Player> players) {
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