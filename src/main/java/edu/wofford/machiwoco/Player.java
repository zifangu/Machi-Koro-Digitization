package edu.wofford.machiwoco;

import java.util.ArrayList;
import java.util.Map;
import java.util.Set;

/** 
 * This is a class built to represent a Player.
 * 
 * @author Eric Craft
 * @author Ivan Gu
 * @author Bennett Joyce
 */

public class Player {
    private int coinCount;
    private Map<Establishment,Integer> estOwned;
    private Landmark[] landmarks;
    private boolean turn;

    /**
     * Sets the player's coin total
     * @param coinCount the amount of coins the current Player has in total
     */

    public void setCoinCount(int coinCount) {
        this.coinCount = coinCount;
    }

    /**
     * Gets the Player's owned establishments
     * @return a Map object containing the Player's owned Establishments
     */

    public Map<Establishment, Integer> getEstOwned() {
        return estOwned;
    }

    /**
     * Sets the Player's owned establishments
     * @param estOwned a Map object containing the Player's owned Establishments
     */

    public void setEstOwned(Map<Establishment, Integer> estOwned) {
        this.estOwned = estOwned;
    }

    /**
     * Sets the Player's owned Landmarks
     * @param landmarks an array containing the game's Landmarks
     */

    public void setLandmarks(Landmark[] landmarks) {
        this.landmarks = landmarks;
    }

    private int playerNumber;

    /**
     * Player constructor that contains all necessary info about a given player within the game
     * @param estOwned a Map object containing the Player's owned Establishments
     * @param landmarks an array containing the game's Landmarks
     * @param coinCount an integer representing the Player's total coins
     * @param playerNumber an integer representing the Player's number
     */

    public Player(Map<Establishment,Integer> estOwned, Landmark[] landmarks, int coinCount, int playerNumber) {
      this.estOwned = estOwned;
      this.landmarks = landmarks;
      this.coinCount = coinCount;
      this.playerNumber = playerNumber;
      turn = false;
    }

    /**
     * Calls the action to be performed based on the Player's dice roll
     * @param diceRoll an integer representing the result of the Player's dice roll
     */

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

    /**
     * Performs the action associated with the given Establishment
     * @param e the Establishment upon which an action is being performed
     * @param numberOwned an integer representing the amount of coins owned by the Player
     */

    private void performAction(Establishment e, int numberOwned) {
        String type = e.getType();
        int amount = e.getAmount();
        String target = e.getTarget();
        if(type.equals("receive") && target.equals("bank")) {
            printCardAfterActivation(e);
            addCoins(amount * numberOwned);
        }
    }

    /**
     * Prints a message to the console stating that an activation has occurred.
     */

    private void printCardAfterActivation(Establishment e) {
        String name = e.getName();
        System.out.println(name + " activated for Player " + getPlayerNumber() + ".");
    }

    /**
     * Adds the given coin total to the Player's coin count
     * @param coinCount an integer representing the amount of coins to be added to the Player's coin count
     */

    private void addCoins(int coinCount) {
        this.coinCount += coinCount;
    }

    /**
     * Sets the Player's turn to be active (true) or not (false)
     * @param setter a boolean holding 'true' if it is the Player's turn
     */

    protected void setTurn(boolean setter) {
        turn = setter;
    }

    /**
     * Gets the Player's number
     * @return an integer representing the Player's number
     */

    public int getPlayerNumber() {
        return playerNumber;
    }

    /**
     * States whether or not it is the given Player's turn
     * @return a boolean representing whether it is the Player's turn
     */

    public boolean isTurn() { return turn; }

    /**
     * Gets the Player's coin count
     * @return an integer representing the Player's coin count
     */

    public int getCoinCount() {
        return coinCount;
    }

   // public Establishment[] getEstOwned() {
      //  return estOwned;
   // }

   /**
    * Gets the Player's landmarks
    * @return an array of current Landmarks
    */

    public Landmark[] getLandmarks() {
        return landmarks;
    }



}