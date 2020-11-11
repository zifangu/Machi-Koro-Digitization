package edu.wofford.machiwoco;

import java.util.List;
import java.util.Set;

public class ActivationListener implements GameListener {
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


    @Override
    public void diceRolled(int dice1, int dice2, Player p) {

    }
}
