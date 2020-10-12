package edu.wofford.machiwoco;

import java.util.ArrayList;
import java.util.Map;
import java.util.Set;

public class Player {
    private int coinCount;
    private Map<Establishment,Integer> estOwned;
    private Landmark[] landmarks;
    private boolean turn;

    public Player(Map<Establishment,Integer> estOwned, Landmark[] landmarks, int coinCount) {
      this.estOwned = estOwned;
      this.landmarks = landmarks;
      this.coinCount = coinCount;
      turn = false;
    }



    public void getActivationNumbers (){
        Set<Establishment> keys = estOwned.keySet();
        for(Establishment est: keys){
            int activation = Integer.parseInt(est.getActivation());
            estOwned.get(est);
        }
    }

    protected void setTurn(boolean setter) {
        turn = setter;
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