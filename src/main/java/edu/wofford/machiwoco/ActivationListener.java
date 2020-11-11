package edu.wofford.machiwoco;

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

//        Player playtotake = activeplayer
//        List playertogive = nonactive;
//        for (Player pl : playertogive) {
//            if dice == 3
//                moneyowned = pl.cafeowned
//            else if dice == 9 or 10
//                moneyowned = pl.(restaurantowned) * 2
//            takemoney(playertotake, pl, moneyowned)
//        }
//
        for (Player player : players) {
            //  changes the coin amounts in the player bank\
            player.getActivationNumbers(dice, player.isTurn());
        }
    }

//    takemoney(plaertotake, plaertogive, money) {
//        result = playertotake.subtract(money)
//        playertogfive.add(result)
//    }

    /**
     * Provides the necessary information for a Player's dice roll.
     * @param dice1 an integer representing the first die's total.
     * @param dice2 an integer representing the second die's total.
     * @param p the Player instance who conducted the dice roll.
     */

    @Override
    public void diceRolled(int dice1, int dice2, Player p) {

    }
}
