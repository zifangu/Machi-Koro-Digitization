package edu.wofford.machiwoco;

import java.util.ArrayList;
import java.util.*;
import java.util.Random;

/**
 * This is a class built to represent the Phase 6 version of Machi Koro.
 *
 * @author Eric Craft
 * @author Ivan Gu
 * @author Bennett Joyce
 */


public class Feature8 extends Feature7 {

    Establishment tvStation;
    Establishment businessComplex;
    Establishment stadium;
    Random rand;

    /**
     * MachiWoco constructor representing the Phase 6 version of the game.
     * @param numPlayers an integer representing the number of Players.
     */

    public Feature8(int numPlayers) {
        super(2);
        NUMBER_OF_PLAYERS = numPlayers;
        rand = new Random();

        setDeck(DeckA, 5);
        setDeck(DeckB, 5);
        setDeck(DeckC, 2);
        /*
        HashMap a = wheat, ranch, bakery, cafe , convenience, forest
        HashMap b = Cheese, Funiture, Mine, Family , Orchard, Farmer
        HashMap c = Stadium, TV, BusComplex

    Bennett
        Stack DeckA;
        while (a.size() != 0) {
            Get an random Establishment.
            Push it to DeckA
        }
        Same for DeckB and DeckC

    Eric
    *****Already randomlized*****
        DeckA
        DeckB
        DeckC
    *****************************
        market.put(   ) until market have 5 from A, 5 from B and 2 from C


        


    Ivan
    Test and Documentation


        Establishment = random(a)
        while (DeckA.size < 6) {
            DeckA.push(Establishment)
            a.put(Establishment, a.get(Establishment) -1)
        }


        market.put(     );


         */

        sc = new Scanner(System.in);

        // EST_ORDER
        EST_ORDER = new ArrayList<Establishment>();
        EST_ORDER.add(getWheat());
        EST_ORDER.add(getRanch());
        EST_ORDER.add(getBakery());
        EST_ORDER.add(getCafe());
        EST_ORDER.add(getConvenience());
        EST_ORDER.add(getForest());
        EST_ORDER.add(getStadium());
        EST_ORDER.add(getTvStation());
        EST_ORDER.add(getBusiness());
        EST_ORDER.add(getCheeseFactory());
        EST_ORDER.add(getFurnitureFactory());
        EST_ORDER.add(getMine());
        EST_ORDER.add(getFamilyRestaurant());
        EST_ORDER.add(getOrchard());
        EST_ORDER.add(getFarmersMarket());

        landmarkInit();
        playerInit(numPlayers);
    }

    /**
     * Establishes the market in 5-5-2 format.
     * @param deck a stack representing one of the 3 decks that will be used in the creation of the market itself.
     * @param sizeOfDeck an integer representing the number of cards in the deck.
     */

    private void setDeck(Stack deck, int sizeOfDeck) {
        int unique = 0;
        while (unique != sizeOfDeck) {
                Establishment e = deck.pop();
                if (!market.containsKey(e.getName())) unique++;
                market.put(e, 1);
        }
    }

    /**
     * Builds the Players to take part in the Phase3 version of Machi Koco.
     * @param player_num the number of players taking part in the current game
     */

    @Override
    protected void playerInit(int player_num) {
        NUMBER_OF_PLAYERS = player_num;
        player1 = new Player(startingEstablishments, startingLandmarks, 3,1, false);
        player2 = new Player(startingEstablishments2, startingLandmarks1, 3,2, true);
        if(player_num == 3) {
            player3 = new Player(P2startingEst3, startingLandmarks2, 3,3, true);
        } else if (player_num == 4) {
            player3 = new Player(P2startingEst3, startingLandmarks2, 3,3, true);
            player4 = new Player(P4startingEst4, startingLandmarks3, 3, 4, true);
        }
        players = new ArrayList<Player>();
        players.add(player1);
        players.add(player2);
        if(player_num == 3) {
            players.add(player3);
        } else if (player_num == 4) {
            players.add(player3);
            players.add(player4);
        }
    }

    /**
     * Creates the Landmarks to be used by Players in Phase 3
     */

    @Override
    protected void landmarkInit() {
        NUMBER_OF_LANDMARKS = 4;

        startingLandmarks = new Landmark[4];
        startingLandmarks[0] = new Landmark("Train Station", 4, Card.Color.NONE, Card.Color_ab.N, Card.Icon.TOWER, Card.Icon_ab.T,
                "|  You may roll 1 or 2  |\n" +
                        "|         dice.         |\n");
        startingLandmarks[1] = new Landmark("Shopping Mall", 10, Card.Color.NONE, Card.Color_ab.N, Card.Icon.TOWER, Card.Icon_ab.T,
                "|   Your {U} and {B}    |\n" +
                        "|  establishments earn  |\n" +
                                "|     +1 coin when      |\n" +
                                        "|      activated.       |\n");
        startingLandmarks[2] = new Landmark("Amusement Park", 16, Card.Color.NONE, Card.Color_ab.N, Card.Icon.TOWER, Card.Icon_ab.T,
                "| If you roll doubles,  |\n" +
                        "|   take another turn   |\n" +
                            "|    after this one.    |\n");
        startingLandmarks[3] = new Landmark("Radio Tower", 22, Card.Color.NONE, Card.Color_ab.N, Card.Icon.TOWER, Card.Icon_ab.T,
                        "|  Once per turn, you   |\n" +
                                "| may reroll the dice.  |\n");

        startingLandmarks1 = new Landmark[4];
        startingLandmarks1[0] = new Landmark("Train Station", 4, Card.Color.NONE, Card.Color_ab.N, Card.Icon.TOWER, Card.Icon_ab.T,
                "|  You may roll 1 or 2  |\n" +
                        "|         dice.         |\n");
        startingLandmarks1[1] = new Landmark("Shopping Mall", 10, Card.Color.NONE, Card.Color_ab.N, Card.Icon.TOWER, Card.Icon_ab.T,
                "|   Your {U} and {B}    |\n" +
                        "|  establishments earn  |\n" +
                                "|     +1 coin when      |\n" +
                                        "|      activated.       |\n");
        startingLandmarks1[2] = new Landmark("Amusement Park", 16, Card.Color.NONE, Card.Color_ab.N, Card.Icon.TOWER, Card.Icon_ab.T,
                "| If you roll doubles,  |\n" +
                        "|   take another turn   |\n" +
                            "|    after this one.    |\n");      
        startingLandmarks1[3] = new Landmark("Radio Tower", 22, Card.Color.NONE, Card.Color_ab.N, Card.Icon.TOWER, Card.Icon_ab.T,
                "|  Once per turn, you   |\n" +
                        "| may reroll the dice.  |\n");                        

        startingLandmarks2 = new Landmark[4];
        startingLandmarks2[0] = new Landmark("Train Station", 4, Card.Color.NONE, Card.Color_ab.N, Card.Icon.TOWER, Card.Icon_ab.T,
                "|  You may roll 1 or 2  |\n" +
                        "|         dice.         |\n");
        startingLandmarks2[1] = new Landmark("Shopping Mall", 10, Card.Color.NONE, Card.Color_ab.N, Card.Icon.TOWER, Card.Icon_ab.T,
                "|   Your {U} and {B}    |\n" +
                        "|  establishments earn  |\n" +
                                "|     +1 coin when      |\n" +
                                        "|      activated.       |\n");
        startingLandmarks2[2] = new Landmark("Amusement Park", 16, Card.Color.NONE, Card.Color_ab.N, Card.Icon.TOWER, Card.Icon_ab.T,
                "| If you roll doubles,  |\n" +
                        "|   take another turn   |\n" +
                            "|    after this one.    |\n");
        startingLandmarks2[3] = new Landmark("Radio Tower", 22, Card.Color.NONE, Card.Color_ab.N, Card.Icon.TOWER, Card.Icon_ab.T,
                "|  Once per turn, you   |\n" +
                        "| may reroll the dice.  |\n");  

        startingLandmarks3 = new Landmark[4];
        startingLandmarks3[0] = new Landmark("Train Station", 4, Card.Color.NONE, Card.Color_ab.N, Card.Icon.TOWER, Card.Icon_ab.T,
                "|  You may roll 1 or 2  |\n" +
                        "|         dice.         |\n");
        startingLandmarks3[1] = new Landmark("Shopping Mall", 10, Card.Color.NONE, Card.Color_ab.N, Card.Icon.TOWER, Card.Icon_ab.T,
                "|   Your {U} and {B}    |\n" +
                        "|  establishments earn  |\n" +
                                "|     +1 coin when      |\n" +
                                        "|      activated.       |\n");
        startingLandmarks3[2] = new Landmark("Amusement Park", 16, Card.Color.NONE, Card.Color_ab.N, Card.Icon.TOWER, Card.Icon_ab.T,
                "| If you roll doubles,  |\n" +
                        "|   take another turn   |\n" +
                            "|    after this one.    |\n");
        startingLandmarks3[3] = new Landmark("Radio Tower", 22, Card.Color.NONE, Card.Color_ab.N, Card.Icon.TOWER, Card.Icon_ab.T,
                "|  Once per turn, you   |\n" +
                        "| may reroll the dice.  |\n");  
    }

     /**
      * Initialize game to be played
      */

    @Override
    protected void gameInit() {
        startGame();
        players.get(0).setTurn(true);

//        observer pattern
        gameSubject = new GameStateSubject(EST_ORDER, getPlayers(), getMarket());
        diceSubject = new DiceSubject(getCurrentPlayer(), getPlayers(), 0, 1);
        inputSubject = new InputSubject(getCurrentPlayer(),getPlayers(), "x");

//      subscribe to subjects
        new DiceObserver(diceSubject);
        new ActivationObserver(diceSubject);
        new GameStateObserver(gameSubject);
        new InputObserver(inputSubject);
    }

    /**
     * Play the MachiWoCo game in its entirety
     */

    @Override
    public void playGame() {
        gameInit();

        while (!isGameOver()) {

            // (1) print turn and (2) print current game state
            gameSubject.notifyObservers();

            // (3) ROLL THE DICE AND THE CORRESPONDING ACTIVATIONS
            wayBetterRollDice(rollTwo());

            // (4) either human or ai player make moves
            makeMove();

            // (5) check if Game has ended
            gameEnded();
        }
    }


    /**
     * Starts the Phase 4 version of Machi Koro.
     * @param args a String array representing the user's console arguments.
     */

    public static void main(String[] args) {
        Feature8 feature8 = new Feature8(Integer.parseInt(args[1]));
//        feature7.getPlayer1().getEstOwned().put(feature7.businessComplex, 1);
//        feature7.getPlayer1().getEstOwned().put(feature7.tvStation, 1);
//        feature7.getPlayer1().getEstOwned().put(feature7.stadium, 1);
//        feature7.getPlayer1().getEstOwned().put(feature7.orchard, 2);
//        feature7.getPlayer1().getLandmarks()[3].is_constructed = true;
        feature8.playGame();
    }
}