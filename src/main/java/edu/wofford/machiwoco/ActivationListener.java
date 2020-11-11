package edu.wofford.machiwoco;

import java.util.ArrayList;
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


    public void takeMoney(Player playerToTake, Player playerToGive, int amount) {
            playerToGive.addCoins(playerToTake.takeCoin(amount));
    }

    @Override
    public void diceRolled(int dice1, int dice2, Player p) {

    }
}
