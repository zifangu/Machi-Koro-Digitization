package edu.wofford.machiwoco;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/**
 * A class that pulls from the current game state to perform activation actions based on a Player's dice roll.
 *
 * @author Eric Craft
 * @author Ivan Gu
 * @author Bennett Joyce
 */

public class ActivationListener implements GameListener {

    /**
     * Checks what activations will be activated based on a Player's given dice roll.
     * @param dice an integer representing the Player's dice roll.
     * @param players a List of the current game state's Players.
     */

    @Override
    public void diceActivation(int dice, List<Player> players) {
       ArrayList<Player> inactivePlayers = nonActivePlayers(players);
       Player active= players.get(0);
       for(Player p : players) {
           if(p.isTurn()) {
               active = p;
           }
       }
       int moneyOwed;
       for(Player p : inactivePlayers) {
           moneyOwed = 0;
           if (dice == 3) {
                 moneyOwed = p.numOfCafe(p.getEstOwned());
           } else if (dice == 9 || dice == 10) {
                 moneyOwed = p.numOfRestaurant(p.getEstOwned()) *2;
           }
           takeMoney(active,p,moneyOwed);
       }

        for (Player player : players) {
            //  changes the coin amounts in the player bank\
            player.getActivationNumbers(dice, player.isTurn());
        }
    }

    /**
     * Provides a list of non-active Players.
     * @param players a List of Players involved in the current game.
     * @return an ArrayList of Players that are not currently active.
     */

    public ArrayList<Player> nonActivePlayers(List<Player> players) {
        ArrayList<Player> nonActive = new ArrayList<>();
        int currentPlayerIndex = 0;
        for (Player p : players) {
            if (p.isTurn()) {currentPlayerIndex = players.indexOf(p);}
        }

        for (int i = 1; i < players.size(); i++) {
            nonActive.add(players.get((currentPlayerIndex + i)%players.size()));
        }
        return nonActive;
    }

    /**
     * Takes money from a given Player.
     * @param playerToTake the Player taking/receiving coins.
     * @param playerToGive the Player losing/giving coins.
     * @param amount an integer representing the number of coins that are being given/being taken away.
     */

    public void takeMoney(Player playerToTake, Player playerToGive, int amount) {
        playerToGive.addCoins(playerToTake.takeCoin(amount));
    }

    @Override
    public void diceRolled(int dice1, int dice2, Player p) {

    }
}