package edu.wofford.machiwoco;


import org.apache.commons.lang3.StringUtils;

import java.util.HashMap;
import java.util.Map;
import java.util.ArrayList;
import java.util.*;

public class MachiWoCo {

    //**********DECLARATION************//
    private Player player1;
    private Player player2;
    private Player[] players;
    Map<Establishment,Integer> market;
    Map<Establishment,Integer> startingEstablishments;
    final int NUMBER_OF_PLAYERS = 2;
    final int NUMBER_OF_LANDMARKS = 1;

    private int dice1;
    private int dice2;
    private int diceSum;

    private boolean is_GameOver;

    Establishment wheat;
    Establishment ranch;
    Establishment forest;
    Establishment[] EST_ORDER;


    //**********GETTERS AND SETTERS************//
    public Player getPlayer1() {
        return player1;
    }

    public void setPlayer1(Player player1) {
        this.player1 = player1;
    }

    public Player getPlayer2() {
        return player2;
    }

    public void setPlayer2(Player player2) {
        this.player2 = player2;
    }

    public Player[] getPlayers() {
        return players;
    }

    public void setPlayers(Player[] players) {
        this.players = players;
    }

    public Establishment getWheat() {
        return wheat;
    }

    public void setWheat(Establishment wheat) {
        this.wheat = wheat;
    }

    public Establishment getRanch() {
        return ranch;
    }

    public Map<Establishment, Integer> getMarket() {
        return market;
    }

    public void setMarket(Map<Establishment, Integer> market) {
        this.market = market;
    }

    public void setRanch(Establishment ranch) {
        this.ranch = ranch;
    }

    public Establishment getForest() {
        return forest;
    }

    public void setForest(Establishment forest) {
        this.forest = forest;
    }


    //**********CONSTRUCTOR************//
    public MachiWoCo() {
        //List of Establishments
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


        forest = new Establishment("Forest", 3, Card.Color.BLUE, Card.Color_ab.B, Card.Icon.GEAR, Card.Icon_ab.G,
                "|  Get 1 coin from the  |\n" +
                        "|         bank.         |\n" +
                        "|    (anyone's turn)    |\n",
                "5", "receive", "bank", 1, "none", "none");

        //MARKET PLACE FOR ESTABLISHMENTS
        // Establishment[] market = new Establishment[18];

        market = new HashMap<>();
        market.put(wheat, 6);
        market.put(ranch,6);
        market.put(forest,6);

        startingEstablishments = new HashMap<>();
        startingEstablishments.put(wheat,1);

        Landmark city = new Landmark("City Hall", 7, Card.Color.NONE, Card.Color_ab.N, Card.Icon.TOWER, Card.Icon_ab.T,
                "|  This is a city hall  |\n");
        Landmark[] startingLandmarks = new Landmark[1];
        startingLandmarks[0] = city;

        player1 = new Player(startingEstablishments, startingLandmarks, 4,1);
        player2 = new Player(startingEstablishments, startingLandmarks, 4,2);
        players = new Player[NUMBER_OF_PLAYERS];
        players[0] = player1;
        players[1] = player2;
        EST_ORDER = new Establishment[] {wheat, ranch, forest};
    }

    /**
     * Returns an array of Landmarks that the Player can afford
     * @return a Landmark array containing available and affordable Landmark cards
     */
    public ArrayList<Landmark> getAffordableLandmarks(Player player) {
        // cycle through the Landmarks... if player's coin count is >= cost of Landmark, add Landmark to array
        Landmark[] landmarkArr = player.getLandmarks();
        ArrayList<Landmark> resultArr = new ArrayList<Landmark>();
        for (int i = 0; i < landmarkArr.length; i++) {
            if (player.getCoinCount() >= landmarkArr[i].getCost()) {
                resultArr.add(landmarkArr[i]);
            }
        }

        // sort the arrayList
        Collections.sort(resultArr, new SortByCost());

        return resultArr;
    }

    //**********FUNCTIONS FOR ToSTRING()************//
    protected String generate_pure_padding(String s) {
        return StringUtils.center("", 42, s) + "\n";
    }

    protected String generate_title(String s) {
        return StringUtils.center(s, 42, " ") + "\n";
    }

    protected String generateStaticMarket() {
        return generate_pure_padding("*") +
                generate_title("MARKET") +
                generate_pure_padding("-");
    }
    protected String generateCost(int cost) {
        String act = "(" + cost + ")";
        return StringUtils.rightPad(act, 4, " ");
    }
    public String generateActivation(String s) {
        String act = "[" + s + "]";
        return StringUtils.rightPad(act, 7, " ");
    }
    protected String generateSingleMarketItem(Establishment e, int count) {
        return StringUtils.rightPad(e.getName(), 18, " ") + " " +
                e.getColor_ab() + e.getIcon_ab() + " " +
                generateCost(e.getCost()) + " " +
                generateActivation(e.getActivation()) + " " +
                " #" + Integer.toString(count) + "\n";
    }
    protected  String generateMarket() {
        StringBuilder s = new StringBuilder();
        for (Establishment e : EST_ORDER) {
            s.append(generateSingleMarketItem(e, market.get(e)));
        }
//        System.out.print(s);
        return generateStaticMarket() + s;
    }

    protected String generatePlayerCoin(Player p) {
        String account = "(" + p.getCoinCount() + " coins)";
        return StringUtils.center(account, 42, " ") + "\n";
    }

    protected String generatePlayerLine(Player p, int num, boolean active) {
        String player;
        if (active) {
            player = "Player " + num + "* [YOU]";
        } else {
            player = "Player " + num;
        }
        return StringUtils.center(player, 42, " ") + "\n";
    }


    protected String generatePlayerEst(Player p) {
        Map<Establishment,Integer> estOwned = p.getEstOwned();
        StringBuilder s = new StringBuilder();
        for (Establishment e : EST_ORDER) {
            if (estOwned.containsKey(e)) {
                s.append(generateSingleMarketItem(e, estOwned.get(e)));
            }
        }
        return s + "";
    }

    protected String generateLandmark(Landmark l) {
        String construct = "[" + l.isConstructed(l.is_constructed) + "]";
        return StringUtils.rightPad(l.getName(), 18, " ") +
                " " + l.getColor_ab() + l.getIcon_ab() + " " +
                generateCost(l.getCost()) + " " +
                construct + "\n";

    }

    protected StringBuilder generatePlayerLandMark(Player p) {
        StringBuilder s = new StringBuilder();
        for (Landmark l : p.getLandmarks()) {
            s.append(generateLandmark(l));
        }
        return s;
    }


    protected String generatePlayer(Player p, int num, boolean active) {
        return generatePlayerLine(p, num, active) +
                generate_pure_padding("-") +
                generatePlayerCoin(p) +
                generatePlayerEst(p) +
                generate_pure_padding(".") +
                generatePlayerLandMark(p) + "\n";
    }




    //**********GAME STEP 1: START GAME************//
    private void startGame() { System.out.println( "The game has started. Player 1 will go first."); }

    //**********GAME STEP 2: PRINT TURN************//
    private void printTurn() {
        for(int i = 0; i < NUMBER_OF_PLAYERS; i++) {
            if(players[i].isTurn()) {
                System.out.println("Turn started for Player " + (i + 1) + ".");
            }
        }
    }

    //**********GAME STEP 3: CURRENT GAME STATE************//
    protected String getCurrentGameState() {
        StringBuilder s = new StringBuilder();
        for (int i = 0; i < players.length; i++) {
            s.append(generatePlayer(players[i], i + 1, players[i].isTurn()));
        }
        return generateMarket() + "\n" + s + generate_pure_padding("*");
    }

    //**********GAME STEP 4: ROLL THE DICE************//
    private void roll() {
        dice1 = (int) (Math.random() * 6 + 1);
        dice2 = 0; //(int) (Math.random() * 6 + 1);
        diceSum = dice1 +dice2;
        System.out.println("Player "  + getTurn() + " rolled ["+dice1+"] =" + diceSum + ".");
        //System.out.println("Player "  + getTurn() + " rolled ["+dice1+"]["+dice2+"] =" + diceSum + ".");
    }


    protected int getTurn() {
        for(int i = 0; i < NUMBER_OF_PLAYERS; i++) {
            if(players[i].isTurn()) {
                return i + 1;
            }
        }
        return 0;
    }
    //**********GAME STEP 5: Activation************//
    //Pass in dice to Player function
    private void activationTest() {
        for(int i = 0; i < NUMBER_OF_PLAYERS; i++) {
            players[i].getActivationNumbers(dice1);
        }
    }

    //Loop through player hands
    //


    //**********GAME STEP 6: END TURN************//
    private void endTurn() {
        int curPlayerIndex = getTurn() - 1;
        System.out.println("Turn ended for Player " + getTurn() +".");
        if(curPlayerIndex == NUMBER_OF_PLAYERS-1) {
            players[0].setTurn(true);
        } else {
            players[curPlayerIndex].setTurn(false);
            players[curPlayerIndex +1].setTurn(true);
        }
        isGameOver();
    }
    private void isGameOver() {
        if(allLandmarksConstructed()) {
           System.out.println("The game is over. Player " + getTurn() + " is the winner.");
           is_GameOver = true;
        }
        is_GameOver = false;
    }
    private boolean allLandmarksConstructed() {
        Landmark[] l;
        int count = 0;
        for (int i = 0; i < NUMBER_OF_PLAYERS; i++) {
            l = players[i].getLandmarks();
            for(int j = 0; j < NUMBER_OF_LANDMARKS; j++) {
                if(l[j].is_constructed) {
                    count++;
                }
            }
            if(count==NUMBER_OF_LANDMARKS) {
                return true;
            }
        }
        return false;
    }




//    private void printActivations() {
//        for(int i = 0; i < NUMBER_OF_PLAYERS; i++) {
//            players[i].
//        }
//    }

    public void playGame() {
        startGame();
        players[0].setTurn(true);
        while(!is_GameOver) {

           // (1) PRINT TURN
            printTurn(); //"Turn started for Player N."

            // (2) PRINT CURRENT GAME STATE
            //CURRENT GAME STATE (THANKS IVAN!)
                    //MARKET ToSTRING

                    //Player 1 EST
                    //Player 1 LAND

                    //Player 2 EST
                    //Player 2 LAND


            // (3) ROLL THE DICE
            roll(); //"Player N rolled [3] = 3."

            // (4) ACTIVATE / ACTIONS
            activationTest();
            //ACTIVATE  "Forest activated for Player N."


            // (5) SHOW BUY MENU
            //if(somethingToShow/Buy) {
                //BUY MENU()
            //}
                    // MENU TO BUY
                    //Establishment Purchase or Landmark Construction
                    //EST: "Player N purchased the Furniture Factory."
                    //LAND: "Player N constructed the Shopping Mall."
                    //"Player N chose not to make improvements."

            //(6) End Game
            endTurn();
        }
    }


    public static void main(String[] args) {

        MachiWoCo m = new MachiWoCo();
//        m.playGame();

        m.getPlayers()[0].setTurn(true);

        System.out.print(m.getCurrentGameState());
    }
}

