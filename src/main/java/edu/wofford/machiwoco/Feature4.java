package edu.wofford.machiwoco;

import org.apache.commons.lang3.StringUtils;

import java.io.Console;
import java.util.HashMap;
import java.util.Map;
import java.util.ArrayList;
import java.util.*;
import java.util.stream.Stream;

/**
 * phase 1 of the game with 1(one) random AI
 *
 * @author Eric Craft
 * @author Ivan Gu
 * @author Bennett Joyce
 */


public class Feature4 extends Feature3 {

    Map<Establishment,Integer> market;
    Map<Establishment,Integer> P2startingEst;
    Map<Establishment,Integer> P2startingEst2;
    Map<Establishment,Integer> P2startingEst3;

    protected Player player1;
    protected Player player2;
    protected Player player3;

    Establishment wheat;
    Establishment bakery;
    Establishment ranch;
    Establishment forest;
    Establishment convenience;
    Establishment mine;
    Establishment orchard;
    Establishment[] EST_ORDER;

    int NUMBER_OF_PLAYERS;
    int NUMBER_OF_LANDMARKS = 2;


    Landmark[] startingLandmarks;

    public Feature4(int numPlayers) {
        NUMBER_OF_PLAYERS = numPlayers;
        //**********Establishment wheat field creation************//
        wheat = new Establishment("Wheat Field", 1, Card.Color.BLUE, Card.Color_ab.B, Card.Icon.WHEAT, Card.Icon_ab.W,
                "|  Get 1 coin from the  |\n" +
                        "|         bank.         |\n" +
                        "|    (anyone's turn)    |\n",
                "1", "receive", "bank", 1, "none", "none");

        //**********Establishment ranch creation************//
        ranch = new Establishment("Ranch", 1, Card.Color.BLUE, Card.Color_ab.B, Card.Icon.COW, Card.Icon_ab.C,
                "|  Get 1 coin from the  |\n" +
                        "|         bank.         |\n" +
                        "|    (anyone's turn)    |\n",
                "2", "receive", "bank", 1, "none", "none");

        //**********Establishment bakery creation************//
        bakery = new Establishment("Bakery", 1, Card.Color.GREEN, Card.Color_ab.G, Card.Icon.BREAD, Card.Icon_ab.B,
                "|  Get 1 coin from the  |\n" +
                        "|         bank.         |\n" +
                        "|   (your turn only)    |\n",
                "2-3", "receive", "bank", 1, "none", "none");


        //**********Establishment convenience store creation************//
        convenience = new Establishment("Convenience Store", 2, Card.Color.GREEN, Card.Color_ab.G, Card.Icon.BREAD, Card.Icon_ab.B,
                "| Get 3 coins from the  |\n" +
                        "|         bank.         |\n" +
                        "|   (your turn only)    |\n",
                "4", "receive", "bank", 3, "none", "none");

        //**********Establishment forest creation************//
        forest = new Establishment("Forest",
                3, Card.Color.BLUE, Card.Color_ab.B, Card.Icon.GEAR, Card.Icon_ab.G,
                "|  Get 1 coin from the  |\n" +
                        "|         bank.         |\n" +
                        "|    (anyone's turn)    |\n",
                "5", "receive", "bank", 1, "none", "none");

        //**********Establishment mine creation************//
        mine = new Establishment("Mine", 6, Card.Color.BLUE, Card.Color_ab.B, Card.Icon.GEAR, Card.Icon_ab.G,
                "| Get 5 coins from the  |\n" +
                        "|         bank.         |\n" +
                        "|    (anyone's turn)    |\n",
                "9", "receive", "bank", 5, "none", "none");


        //**********Establishment orchard creation************//
        orchard = new Establishment("Apple Orchard", 3, Card.Color.BLUE, Card.Color_ab.B, Card.Icon.WHEAT, Card.Icon_ab.W,
                "| Get 3 coins from the  |\n" +
                        "|         bank.         |\n" +
                        "|    (anyone's turn)    |\n",
                "10", "receive", "bank", 3, "none", "none");

        market = new HashMap<>();
        market.put(wheat, 6);
        market.put(ranch,6);
        market.put(forest,6);
        market.put(bakery, 6);
        market.put(convenience,6);
        market.put(mine,6);
        market.put(orchard,6);

        P2startingEst = new HashMap<>();
        P2startingEst2 = new HashMap<>();
        P2startingEst3 = new HashMap<>();

        P2startingEst.put(wheat,1);
        P2startingEst.put(bakery,1);
        P2startingEst2.put(wheat,1);
        P2startingEst2.put(bakery,1);
        P2startingEst3.put(wheat,1);
        P2startingEst3.put(bakery,1);
        sc = new Scanner(System.in);

        EST_ORDER = new Establishment[] {wheat, ranch, bakery,convenience, forest, mine, orchard};

        landmarkInit();
        playerInit();
    }

    /**
     * Second player is now an AI.
     */

    @Override
    protected void playerInit() {
        player1 = new Player(P2startingEst, startingLandmarks, 3,1, false);
        player2 = new Player(P2startingEst2, startingLandmarks, 3,2, true);
        if(NUMBER_OF_PLAYERS == 3) {
            player3 = new Player(P2startingEst3, startingLandmarks, 3,3, true);
        }
        players = new Player[NUMBER_OF_PLAYERS];
        players[0] = player1;
        players[1] = player2;
        if(NUMBER_OF_PLAYERS == 3) {
            players[2] = player3;
        }
    }

    @Override
    protected void landmarkInit() {
        Landmark city = new Landmark("City Hall", 7, Card.Color.NONE, Card.Color_ab.N, Card.Icon.TOWER, Card.Icon_ab.T,
                "|  This is a city hall  |\n");
        Landmark trainStation = new Landmark("Train Station", 4, Card.Color.NONE, Card.Color_ab.N, Card.Icon.TOWER, Card.Icon_ab.T,
 "|  You may roll 1 or 2  |\n" +
       "|         dice.         |\n");
        startingLandmarks = new Landmark[NUMBER_OF_LANDMARKS];
        startingLandmarks[0] = city;
        startingLandmarks[1] = trainStation;
    }



    /**
     * The prompt is no longer applied to both players. AI choice are made randomly, and the prompt is redircted backed to player 1.
     */

    @Override
    public void playGame() {
        startGame();
        players[0].setTurn(true);
        int count = 0;
        boolean ai;

        while(!isGameOver()) {
            // (1) PRINT TURN
            printTurn(); //"Turn started for Player N."

            // (2) PRINT CURRENT GAME STATE

            System.out.println(getCurrentGameState());

            // (3) ROLL THE DICE
            roll(); //"Player N rolled [3] = 3."

            // (4) ACTIVATE / ACTIONS
            activationTest();

            // (5) SHOW BUY MENU

            buyFinished = false;

//            Random AI Action
            if (getCurrentPlayer().isAi()) {
                System.out.println(getMenu());

                int estSize = buyEstablishmentLogic().size();
                int lmkSize = getAffordableLandmarks(getCurrentPlayer()).size();

                // add last option of "99. Do Nothing" to AI
                int ai_choices = estSize + lmkSize + 1;
                int ai_input = (int) (Math.random() * ai_choices + 1);
                if (ai_input == ai_choices) {
                    ai_input = 99;
                }
                System.out.println("AI CHOSE: " + ai_input);

                handleInput(Integer.toString(ai_input));

            } else {

                // human player input
                if(canAffordCard(getCurrentPlayer())) {
                    String s = "Player " + getTurn() + " would you like to purchase an \n" + "establishment or construct a landmark?" + " (" + getCurrentPlayer().getCoinCount() +
                            "\n" + "coins) \n" + "(To view details of an item, type 'view'  \n" +
                            "followed by the item number. For example, \n" +
                            "to view item 6, type 'view 6'.)           \n";

                    System.out.print(s);
                    System.out.print(getMenu()); //Ivan

                }
                while(!buyFinished && canAffordCard(getCurrentPlayer())) {
                    Console cnsl = System.console();
                    String input = cnsl.readLine(StringUtils.center("Choose a number to purchase or construct: ", 42, " "));
                    cnsl.flush();
                    buyFinished = handleInput(input);
                }
            }

            //(6) End Game
            if(!allLandmarksConstructed()) {
                endTurn();
            }
        }
    }



    public static void main(String[] args) {
        Feature4 feature4 = new Feature4(Integer.parseInt(args[1]));
        feature4.playGame();
    }
}
