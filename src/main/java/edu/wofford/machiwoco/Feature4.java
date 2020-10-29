package edu.wofford.machiwoco;

import org.apache.commons.lang3.StringUtils;

import java.io.Console;
import java.util.HashMap;
import java.util.Map;
import java.util.ArrayList;
import java.util.*;

/**
 * phase 1 of the game with 1(one) random AI
 *
 * @author Eric Craft
 * @author Ivan Gu
 * @author Bennett Joyce
 */


public class Feature4 extends TwoPlayersPhase1 {

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
    int NUMBER_OF_LANDMARKS;


    Landmark[] startingLandmarks1;
    Landmark[] startingLandmarks2;
    Landmark[] startingLandmarks3;

    public Feature4(int numPlayers) {
        super(true);
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
        playerInitFeature4(numPlayers);
    }

    /**
     * Second player is now an AI.
     */

    protected void playerInitFeature4(int player_num) {
        NUMBER_OF_PLAYERS = player_num;
        player1 = new Player(P2startingEst, startingLandmarks1, 3,1, false);
        player2 = new Player(P2startingEst2, startingLandmarks2, 3,2, true);
        if(NUMBER_OF_PLAYERS == 3) {
            player3 = new Player(P2startingEst3, startingLandmarks3, 3,3, true);
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
        NUMBER_OF_LANDMARKS = 2;
        Landmark city = new Landmark("City Hall", 7, Card.Color.NONE, Card.Color_ab.N, Card.Icon.TOWER, Card.Icon_ab.T,
                "|  This is a city hall  |\n");
        Landmark trainStation = new Landmark("Train Station", 4, Card.Color.NONE, Card.Color_ab.N, Card.Icon.TOWER, Card.Icon_ab.T,
                "|  You may roll 1 or 2  |\n" +
                        "|         dice.         |\n");
        startingLandmarks = new Landmark[2];
        startingLandmarks[0] = city;
        startingLandmarks[1] = trainStation;

        startingLandmarks1 = new Landmark[2];
        startingLandmarks1[0] = city;
        startingLandmarks1[1] = trainStation;

        startingLandmarks2 = new Landmark[2];
        startingLandmarks2[0] = city;
        startingLandmarks2[1] = trainStation;
    }

    /**
     * AI Logic for making a move
     */
    protected void aiLogic() {
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
    }

    @Override
    public ArrayList<Establishment> getAffordableEstablishments(Player player, int owned) {
        Set<Establishment> setE = market.keySet();
        ArrayList<Establishment> eResult = new ArrayList<Establishment>();
        for(Establishment est: EST_ORDER){
            int cost = est.getCost();
            int numberLeft = market.get(est);
            if(owned >= cost && numberLeft!=0) {
                eResult.add(est);
            }
        }
        return eResult;
    }

    @Override
    protected ArrayList<Establishment> buyEstablishmentLogic() {
        int amountOwned = getCurrentPlayer().getCoinCount();
//        System.out.println("AMOUNT: " + amountOwned);
        ArrayList<Establishment> e = getAffordableEstablishments(getCurrentPlayer(),amountOwned);
        return e;
    }


    @Override
    protected String getAvailEst(int i) {

            ArrayList<Establishment> e = buyEstablishmentLogic();

//        ArrayList<Establishment> e = new ArrayList<Establishment>(Arrays.asList(EST_ORDER));
            StringBuilder s = new StringBuilder();
            int count = i;
            if (e.size() != 0) {
                for (Establishment est : e) {
                    String order = count + ".";
                    s.append(StringUtils.leftPad(order, 3, " ")).append(" ").append(generateSingleMarketItem(est, market.get(est)));
                    count ++;
                }
                return getMenuStatic("PURCHASE") + s;
            }

            return "";
        }

    @Override
    protected String getMenu() {
        int count = 1;
        String s = generate_pure_padding("=") + getAvailEst(count);
        count = buyEstablishmentLogic().size() + 1;
        return s + getAvailLandmark(count) +
                getMenuStatic("CANCEL") +
                "99. " + StringUtils.rightPad("Do nothing", (42-4), " ") + "\n" +
                StringUtils.center("", 42, "=") +"\n";
    }

    /**
     * Human Input makes moves for human
     */
    private void humanInput() {
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

    @Override
    protected boolean getBuyInput(int index) {
        ArrayList<Establishment> listOfEstablishments = getAffordableEstablishments(getCurrentPlayer(), getCurrentPlayer().getCoinCount());
        ArrayList<Landmark> listOfLandmarks = getAffordableLandmarks(getCurrentPlayer());
        int numberOfLandmarks = listOfLandmarks.size();
        int numberOfEstablishments = listOfEstablishments.size();
        if(index == 99) {
            System.out.println("Player "  + getTurn() + " chose not to make improvements.");
            return true;
        } else if(index <= numberOfEstablishments) {
            Establishment e = listOfEstablishments.get(index-1);
//            System.out.println("Player 1: round1" + player1.getEstOwned());
//            System.out.println("Player 2:" + player2.getEstOwned());
            getCurrentPlayer().buyCard(e);
            int numberLeft = market.get(e) - 1;
            market.put(e,numberLeft);
//            System.out.println("Player 1: jsdflkjskl" + player1.getEstOwned());
//            System.out.println("Player 2:" + player2.getEstOwned());

            System.out.println("Player "  + getTurn() + " purchased the " + e.getName() + ".");
            return true;
        } else if(index <= numberOfEstablishments + numberOfLandmarks) {
            Landmark l = listOfLandmarks.get(index-numberOfEstablishments-1);
            getCurrentPlayer().buyLandmark(l);
            System.out.println("Player "  + getTurn() + " constructed the " +l.getName() + ".");
            return true;
        } else {
            System.out.println("Not a valid input");
            return false;
        }
    }

    /**
     * Driver for making the move
     */
    public void makeMove() {
        buyFinished = false;
//            Random AI Action
        if (getCurrentPlayer().isAi()) {
            aiLogic();
        } else {
            humanInput();
        }
    }


    public Map<Establishment,Integer> getMarketP2() {
        return market;
    }

    /**
     * The prompt is no longer applied to both players. AI choice are made randomly, and the prompt is redircted backed to player 1.
     */
 

    /**
     * Gets the sum of a dice roll using 2 die
     * @return the sum of the 2-die dice roll
     */

    protected int roll2(String input) {
        int diceSum = 0;
        if (input == "2") {
            dice1 = (int) (Math.random() * 6 + 1);
            dice2 = (int) (Math.random() * 6 + 1);
            diceSum = dice1 +dice2;
            return diceSum;
        } else {
            diceSum = roll();
        }
        return diceSum;
    }


    /**
     * Checks to see if the Train Station landmark is constructed for a certain player
     * @param player the Player instance whose Train Station construction is being checked
     * @return a boolean holding true if the Player has constructed Train Station
     */

    protected boolean isTrainStationConstructed(Player player) {
        return player.getLandmarks()[1].getIsConstructed();
    }

    @Override
    public void playGame() {
        startGame();
        players[0].setTurn(true);
        int count = 0;
        boolean ai;
        DiceSubject diceSubject = new DiceSubject(getCurrentPlayer(), getPlayers(), 0, 1);
        GameStateSubject gameSubject = new GameStateSubject(EST_ORDER, getPlayers(), market);
        new DiceObserver(diceSubject);
        new ActivationObserver(diceSubject);
        new GameStateObserver(gameSubject);

        InputSubject inputSubject = new InputSubject(getCurrentPlayer(),getPlayers(), "x");
        new InputObserver(inputSubject);
//        gameSubject.setPlayers(players);

        while(!isGameOver()) {
            // (1) PRINT TURN
            //printTurn(); //"Turn started for Player N."

            // (2) PRINT CURRENT GAME STATE
            //System.out.println(getCurrentGameState());

            // (3) ROLL THE DICE
            //roll(); //"Player N rolled [3] = 3."

            // (4) ACTIVATE / ACTIONS

            gameSubject.setMarket(getMarketP2());
            gameSubject.notifyObservers();
            // (3) ROLL THE DICE AND THE CORRESPONDING ACTIVATIONS
            if (isTrainStationConstructed(getCurrentPlayer())) {
                System.out.println(StringUtils.center("Player " + getCurrentPlayer().getPlayerNumber() + ", would you like to roll 1 or 2 die?", 42, " "));
                String rollInput = sc.next();
                diceSubject.setDiceNum(Integer.parseInt(rollInput));
                //diceSubject.setDice(roll2(rollInput));
                //diceSubject.rollDice();

                //diceSubject.setDice()
            } else {
                diceSubject.setDice(roll());
            }
            diceSubject.setActivePlayer(getCurrentPlayer());
            diceSubject.notifyObservers();

            // (5) SHOW BUY MENU
            makeMove();
            inputSubject.notifyObservers();
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
