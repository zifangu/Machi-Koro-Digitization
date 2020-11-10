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
    private boolean ai;



    private int playerNumber;

    /**
     * Player constructor that contains all necessary info about a given player within the game
     * @param estOwned a Map object containing the Player's owned Establishments
     * @param landmarks an array containing the game's Landmarks
     * @param coinCount an integer representing the Player's total coins
     * @param playerNumber an integer representing the Player's number
     * @param ai a boolean holding true if the Player is an AI
     */

    public Player(Map<Establishment,Integer> estOwned, Landmark[] landmarks, int coinCount, int playerNumber, boolean ai) {
      this.estOwned = estOwned;
      this.landmarks = landmarks;
      this.coinCount = coinCount;
      this.playerNumber = playerNumber;
      turn = false;
      this.ai = ai;
    }

    protected int random_ai_choice(int range) {
        int ai_input = (int) (Math.random() * range + 1);
        if (ai_input == range) {
            ai_input = 99;
        }
        return ai_input;
    }

    /**
     * Calls the action to be performed based on the Player's dice roll
     * @param diceRoll an integer representing the result of the Player's dice roll
     * @param isTurn  boolean representing whether the player's turn is active
     */

    public void getActivationNumbers(int diceRoll, boolean isTurn){
        Set<Establishment> keys = estOwned.keySet();
        int numTaken = 0;
        int numberOwnedCow = 0;//getNumberOwnedIcon(Card.Icon.COW);
        int numberOwnedGear = 0;//getNumberOwnedIcon(Card.Icon.GEAR);
        int numberOwnedWheat = 0;//getNumberOwnedIcon(Card.Icon.WHEAT);
        for(Establishment est: keys){
            int activation = 0;
            int activation2 = 0;
            if(est.getName().equals("Bakery")) {
                activation = 2;
                activation2 = 3;
            } else if (est.getName().equals("Farmers Market")) {
                activation = 11;
                activation2 = 12;
            } else {
                activation = Integer.parseInt(est.getActivation());
            }
            int numberOwned = estOwned.get(est);
            if(diceRoll == activation || diceRoll == activation2) {
                if(est.getColor_ab().equals(Card.Color_ab.B)) {
                    performAction(est,numberOwned,0);
                } else if(est.getColor_ab().equals(Card.Color_ab.G)) {
                    if (isTurn && est.getModifierType() == "icon") {
                        performActionIcon(est, numberOwned);
                    } else if (isTurn) {
                        performAction(est, numberOwned, 0);
                    }
                } else if(est.getColor_ab().equals(Card.Color_ab.R)) {
                    //FUNCTION TO SELECT TARGET
                    if(activation == 3) {
                        if(!isTurn) {
                            performAction(est, numberOwned,0);
                        } else if(isTurn) {
                            performAction(est, numberOwned,countNumberOfReds()*1);
                        }
                    }

                }
            }
        }
    }

   protected int countNumberOfReds() {
       Set<Establishment> keys = estOwned.keySet();
       int count=0;
       for (Establishment e : keys) {
           if (e.getColor_ab().equals("R")) {
               count++;
           }
       }
       return count;
   }

    protected void takeCoin(int coinCount) {
        if(this.coinCount - coinCount > 0) {
            this.coinCount -= coinCount;
        } else {
            this.coinCount = 0;
        }
    }


    /**
     * Constructs/Buys Landmark
     * @param l The landmark needed to match another landmark in the player's landmarks array
     */

    public void buyLandmark(Landmark l) {
        int cost = l.getCost();
        int counter = 0;
        for(Landmark loop: landmarks) {
            if(loop.equals(l) && coinCount >= cost) {
                landmarks[counter].setIs_constructed(true);
                coinCount = coinCount - cost;
            }
            counter++;
        }
        
    }


    /**
     * Buying Card Action
     * @param e the Establishment upon which an action is being performed
     */

    public void buyCard(Establishment e) {
        int cost = e.getCost();
        if(estOwned.containsKey(e)) {
            int numberOwned = estOwned.get(e);
            estOwned.put(e,numberOwned+1);
            setCoinCount(getCoinCount() - cost);
        } else {
            estOwned.put(e,1);
            setCoinCount(getCoinCount() - cost);
        }
    }

    /**
     * Performs the action associated with the given Establishment
     * @param e the Establishment upon which an action is being performed
     * @param numberOwned an integer representing the amount of coins owned by the Player
     * @param amountToTake an integer representing the amount to be taken from another Player
     */

    private void performAction(Establishment e, int numberOwned, int amountToTake) {
        String type = e.getType();
        int amount = e.getAmount();
        String target = e.getTarget();
        if(type.equals("receive") && target.equals("bank")) {
            addCoins(amount * numberOwned);
        } else if (type.equals("receive") && target.equals("active")) {
            addCoins(amount*numberOwned);
            takeCoin(amountToTake);
        }
        if(landmarks.length > 2 && landmarks[2].getIsConstructed() && (e.getIcon_ab().name() == "U" || e.getIcon_ab().name() == "B")) {
            addCoins(1);
        }
    }

    /**
     * Performs the action associated with the given Establishment if said Establishment relies on icons.
     * @param e the Establishment upon which an action is being performed
     * @param numberOwned an integer representing the amount of coins owned by the Player
     */

    private void performActionIcon(Establishment e, int numberOwned) {
        String type = e.getType();
        int amount = e.getAmount();
        String target = e.getTarget();
        int numberOwnedIcon = 0;
        // it's getting icon ab of cheese factory
        String modifier = e.getModifier();
        String modifierAb = "";
        if (modifier == "cow") {
            numberOwnedIcon = getNumberOwnedIcon("C");
        } else if (modifier == "gear") {
            numberOwnedIcon = getNumberOwnedIcon("G");
        } else if (modifier == "wheat") {
            numberOwnedIcon = getNumberOwnedIcon("W");
        }
        addCoins(amount * numberOwned * numberOwnedIcon);

    }

    /**
     * Gets the number of Establishments owned corresponding to a specific icon.
     * @param icon_ab the icon of the card(s) being observed.
     * @return an integer representing the number of Establishments owned corresponding to a specific icon.
     */

    public int getNumberOwnedIcon(String icon_ab) {
        int count = 0;

        for (Map.Entry<Establishment, Integer> est : estOwned.entrySet()) {
            if (est.getKey().getIcon_ab().name() == icon_ab) {
                count = count + estOwned.get(est.getKey());
            }
        }

        return count;
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

   /**
    * Gets the Player's landmarks
    * @return an array of current Landmarks
    */

    public Landmark[] getLandmarks() {
        return landmarks;
    }

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
     * Checks if the player is an AI
     * @return a boolean holding true if the player is an AI
     */

    public boolean isAi() {
        return ai;
    }

    /**
     * Sets the player's AI status
     * @param ai a boolean holding true if the player is to be an AI
     */

    public void setAi(boolean ai) {
        this.ai = ai;
    }

    /**
     * Sets the Player's owned Landmarks
     * @param landmarks an array containing the game's Landmarks
     */

    public void setLandmarks(Landmark[] landmarks) {
        this.landmarks = landmarks;
    }

}