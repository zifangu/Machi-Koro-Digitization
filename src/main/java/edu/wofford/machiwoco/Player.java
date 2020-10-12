package edu.wofford.machiwoco;

import java.util.ArrayList;
import java.util.Map;
import java.util.Set;

public class Player {
    private int coinCount;
    private Map<Establishment,Integer> estOwned;
    private Landmark[] landmarks;
    private boolean turn;
    private int playerNumber;


    public Player(Map<Establishment,Integer> estOwned, Landmark[] landmarks, int coinCount, int playerNumber) {
      this.estOwned = estOwned;
      this.landmarks = landmarks;
      this.coinCount = coinCount;
      this.playerNumber = playerNumber;
      turn = false;
    }

    public void getActivationNumbers(int diceRoll){
        Set<Establishment> keys = estOwned.keySet();
        for(Establishment est: keys){
            int activation = Integer.parseInt(est.getActivation());
            int numberOwned = estOwned.get(est);
            if(diceRoll == activation) {
                performAction(est,numberOwned);
            }
        }
    }
    private void performAction(Establishment e, int numberOwned) {
        String type = e.getType();
        int amount = e.getAmount();
        String target = e.getTarget();
        if(type.equals("receive") && target.equals("bank")) {
            printCardAfterActivation(e);
            addCoins(amount * numberOwned);
        }
    }

    private void printCardAfterActivation(Establishment e) {
        String name = e.getName();
        System.out.println(name + " activated for Player " + getPlayerNumber() + ".");
    }


    private void addCoins(int coinCount) {
        this.coinCount += coinCount;
    }
    protected void setTurn(boolean setter) {
        turn = setter;
    }

    public int getPlayerNumber() {
        return playerNumber;
    }

    public boolean isTurn() { return turn; }

    public int getCoinCount() {
        return coinCount;
    }

   // public Establishment[] getEstOwned() {
      //  return estOwned;
   // }

    public Landmark[] getLandmarks() {
        return landmarks;
    }



}