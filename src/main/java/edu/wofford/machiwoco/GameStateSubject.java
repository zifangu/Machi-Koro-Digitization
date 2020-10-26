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

    public Establishment[] getEstOrder() {
        return EST_ORDER;
    }

    public void setEstOrder(Establishment[] EST_ORDER) {
        this.EST_ORDER = EST_ORDER;
    }

    public Player[] getPlayers() {
        return players;
    }

    public void setPlayers(Player[] players) {
        this.players = players;
    }

    public Map<Establishment,Integer> getMarket() {
        return market;
    }

    public void setMarket(Map<Establishment,Integer> market) {
        this.market = market;
    }

    public void attach(edu.wofford.machiwoco.Observer observer){
        observers.add(observer);
    }

    public void notifyObservers(){
        for (Observer observer : observers) {
            observer.update();
        }
    }

}