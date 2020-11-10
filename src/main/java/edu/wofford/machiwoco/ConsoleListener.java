package edu.wofford.machiwoco;

import org.apache.commons.lang3.StringUtils;

import java.util.List;
import java.util.Scanner;
import java.util.Set;

public class ConsoleListener implements GameListener {

    @Override
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
        int activation = 0;
        StringBuilder s = new StringBuilder();
        for (Player player : players) {
            Set<Establishment> keys = player.getEstOwned().keySet();
            for(Establishment est: keys) {
                String name = est.getName();
                switch (name) {
                    case "Bakery": if(dice == 2) { activation = 2; }
                                    else { activation = 3; }
                                    break;

                    case "Farmers Market": if(dice == 11) { activation = 11; }
                                            else { activation = 12; }
                                            break;

                    default: activation = Integer.parseInt(est.getActivation());
                }
                if (dice == activation && !est.getColor_ab().equals(Card.Color_ab.G)) {
                    s.append(est.getName()).append(" activated for Player ").append(player.getPlayerNumber()).append("\n");
                } else if (dice == activation && player.isTurn()) {
                    s.append(est.getName()).append(" activated for Player ").append(player.getPlayerNumber()).append("\n");
                }
            }
        }
        System.out.print(s);
    }

    public boolean rollTwo(Scanner sc, Player p) {
        int input = 0;
        while (!((input == 1) || (input == 2))) {
            System.out.print(StringUtils.center("Player " + p.getPlayerNumber() + ", would you like to roll 1 or 2 die?", 42, " "));
            input = Integer.parseInt(sc.nextLine());
        }
        return input != 1;
    }








}
