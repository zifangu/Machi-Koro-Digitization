package edu.wofford.machiwoco;

import java.util.ArrayList;
import java.util.Map;

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

//    public int getActivationNumbers (){
//        for(int i = 0; i < estOwned.length(); )
//    }

    public boolean isTurn() { return turn; }

    public int getCoinCount() {
        return coinCount;
    }

    public Establishment[] getEstOwned() {
        return estOwned;
    }

    public Landmark[] getLandmarks() {
        return landmarks;
    }



}