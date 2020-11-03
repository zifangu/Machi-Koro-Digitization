package edu.wofford.machiwoco;

import java.util.List;
import java.util.Set;

public class ConsoleListener implements GameListener {
    public void diceRolled(int dice1, int dice2, Player p) {
        if (dice2 == 0) {
            System.out.println("Player " + p.getPlayerNumber() +
                    " rolled ["+dice1+"] = " + dice1 + ".");
        } else {
            System.out.println("Player " + p.getPlayerNumber() +
                    " rolled ["+dice1+"]" + "[" + dice2 + "] = " + (dice1 + dice2) + ".");
        }
    }

    @Override
    public void diceActivation(int dice, List<Player> players) {
//        int activation;
//        for (Player player : players) {
//            // this prints out the activations for user's information
//            Set<Establishment> keys = player.getEstOwned().keySet();
//            for(Establishment est: keys) {
//                if(!est.getName().equals("Bakery")) {
//                    activation = Integer.parseInt(est.getActivation());
//                } else if(est.getName().equals("Bakery")) {
//                    if(dice == 2) { activation = 2; }
//                    else { activation = 3; }
//                }
//
//                if (dice == activation && !est.getColor_ab().equals(Card.Color_ab.G)) {
//                    s.append(est.getName()).append(" activated for Player ").append(player.getPlayerNumber()).append("\n");
//                } else if (dice == activation && player.isTurn()) {
//                    s.append(est.getName()).append(" activated for Player ").append(player.getPlayerNumber()).append("\n");
//                }
//            }
//        }
    }

}
