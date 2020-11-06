package edu.wofford.machiwoco;

import java.util.List;
import java.util.Set;

public class ActivationListener implements GameListener {
    @Override
    public void diceActivation(int dice, List<Player> players) {
        for (Player player : players) {
            //  changes the coin amounts in the player bank
            player.getActivationNumbers(dice, player.isTurn());
        }
    }

    @Override
    public void diceRolled(int dice1, int dice2, Player p) {

    }
}
