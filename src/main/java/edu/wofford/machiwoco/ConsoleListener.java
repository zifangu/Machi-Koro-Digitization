package edu.wofford.machiwoco;

import org.apache.commons.lang3.StringUtils;

import java.util.List;
import java.util.Scanner;
import java.util.Set;
import java.util.*;

/**
 * A class used to output messages to the Player through the console regarding dice rolls and activated Establishments.
 * 
 * @author Eric Craft
 * @author Ivan Gu
 * @author Bennett Joyce
 */

public class ConsoleListener implements GameListener {

    /**
     * Outputs the Player's dice roll to the console.
     * @param dice1 an integer representing the first die's total.
     * @param dice2 an integer representing the second die's total.
     * @param p the Player instance that conducted the dice roll.
     */

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

    /**
     * Outputs Establishments activated by the Player's dice roll to the console.
     * @param dice an integer representing the completed dice roll.
     * @param players a List of the current game state's Players.
     */

    @Override
    public void diceActivation(int dice, List<Player> players) {
        int activation;
        StringBuilder s = new StringBuilder();
        for (Player player : players) {
            Set<Establishment> keys = player.getEstOwned().keySet();
            for(Establishment est: keys) {
                String name = est.getName();
                switch (name) {
                    case "Bakery": if(dice == 2) { activation = 2; }
                                    else { activation = 3; }
                                    break;
                    case "Family Restaurant": if(dice == 9) { activation = 9; }
                                              else { activation = 10; }
                                              break;

                    case "Farmers Market": if(dice == 11) { activation = 11; }
                                            else { activation = 12; }
                                            break;

                    default: activation = Integer.parseInt(est.getActivation());
                }
                if(dice == activation){
                    if(player.isTurn()) {
                        if(!est.getColor_ab().equals(Card.Color_ab.R)){
                            s.append(est.getName()).append(" activated for Player ").append(player.getPlayerNumber()).append("\n");
                        }
                    } else {
                        if(!est.getColor_ab().equals(Card.Color_ab.G) && !est.getColor_ab().equals(Card.Color_ab.P)) {
                            s.append(est.getName()).append(" activated for Player ").append(player.getPlayerNumber()).append("\n");
                        }
                    }

                }
//               if(dice == activation && est.getColor_ab().equals(Card.Color_ab.R) && !player.isTurn()) {
//                   s.append(est.getName()).append(" activated for Player ").append(player.getPlayerNumber()).append("\n");
//               } else if (dice == activation && !est.getColor_ab().equals(Card.Color_ab.G)) {
//                    s.append(est.getName()).append(" activated for Player ").append(player.getPlayerNumber()).append("\n");
//                } else if (dice == activation && player.isTurn() && !est.getColor_ab().equals(Card.Color_ab.R)) {
//                    s.append(est.getName()).append(" activated for Player ").append(player.getPlayerNumber()).append("\n");
//                }
            }
        }
        System.out.print(s);
    }

//    /**
//     * Prompts the user to choose how many die to roll.
//     * @param sc a Scanner item used to take in the user's input.
//     * @param p the Player choosing the number of die to roll.
//     * @return a boolean holding true if the Player chooses to roll two die.
//     */
//
//    @Override
//    public void targetAndTake(Player p1, Player p2, int amountToTake) {
//
//    }

    /**
     * Returns a boolean representing whether or not the Player has decided to roll 1 or 2 die.
     * @param sc a Scanner object used to gather the Player's input.
     * @param p the Player rolling the die.
     * @return a boolean holding true if the Player chooses to roll two die.
     */

    public boolean rollTwo(Scanner sc, Player p) {
        int input = 0;
        while (input != 1 && input != 2) {
            System.out.println(StringUtils.center("Player " + p.getPlayerNumber() + ", would you like to roll 1 or 2 die?", 42, " "));
            input = Integer.parseInt(sc.nextLine());
        }
        return input != 1;
    }

    public Player playerChooseTarget(Scanner sc, Player p, ArrayList<Player> playerArr) {
        ArrayList<Player> validPlayers = new ArrayList<Player>();
        System.out.println(StringUtils.center("-------     AVAILABLE PLAYERS      -------", 42, " "));
        for (int i = 0; i < playerArr.size(); i++) {
            if (!playerArr.get(i).isTurn() && playerArr.get(i).getCoinCount() > 0) {
                validPlayers.add(playerArr.get(i));
            }
        }
        
        int count = 1;
        for (Player pl : validPlayers) {
            String s = "";
            s += count + ". ";
            s += StringUtils.rightPad("Player " + pl.getPlayerNumber(), 20, " ");
            s += " (" + pl.getCoinCount() + " coins)";
            System.out.println(s);
            count++;
        }

        //System.out.println(StringUtils.center("" + i + 1 + ". Player " + playerArr.get(i).getPlayerNumber()))

        int input = 0;
        //System.out.println("size: " + validPlayers.size());
        while (input != 1 && input < validPlayers.size()) {
            System.out.println(StringUtils.center("Player " + p.getPlayerNumber() + ", who would you like to target?", 42, " "));
            input = Integer.parseInt(sc.nextLine());
        }
        return validPlayers.get(input - 1);
    }

    // public Establishment playerChooseEstablishment(Scanner sc, Player p, Player target) {
    //     int input = 0;
    //     while (input > 0 && input <= target.getEstOwned().size()) {
    //         System.out.println(StringUtils.center("Player " + p.getPlayerNumber() + ", select an establishment:", 42, " "));
    //         input = Integer.parseInt(sc.nextLine());
    //     }
    //     // return target.getEstOwned().get(input - 1);
    //     return target.getEstOwned().key(input - 1);
    // }








}
