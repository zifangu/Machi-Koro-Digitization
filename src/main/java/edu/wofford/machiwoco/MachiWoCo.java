package edu.wofford.machiwoco;


import com.sun.org.apache.bcel.internal.generic.LAND;
import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class MachiWoCo {


    private Player player1;

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

    private Player player2;
    private Player[] players;
    Map<Establishment,Integer> market;
    Map<Establishment,Integer> est;
    final int NUMBER_OF_PLAYERS = 2;
    final int NUMBER_OF_LANDMARKS = 1;

    private int dice1;
    private int dice2;
    private int diceSum;

    private boolean isGameOver;

    Establishment wheat;
    Establishment ranch;
    Establishment forest;
    Establishment[] EST_ORDER;


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

        est = new HashMap<>();
        est.put(wheat,1);

        Landmark city = new Landmark("City Hall", 7, Card.Color.NONE, Card.Icon.TOWER,
                "|  This is a city hall |\n");
        Landmark[] n = new Landmark[1];
        n[0] = city;

        player1 = new Player(est,n, 4);
        player2 = new Player(est,n, 4);
        players = new Player[NUMBER_OF_PLAYERS];
        players[0] = player1;
        players[1] = player2;

        EST_ORDER = new Establishment[] {wheat, ranch, forest};
    }

    //  return strings to be used in toString()
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

    protected String generateSingleMarketItem(Establishment e, int count) {
        return StringUtils.rightPad(e.getName(), 18, " ") + " ";

    }

    private void startGame() {
        System.out.println( "The game has started. Player 1 will go first.");
    }


    private void printTurn() {
        for(int i = 0; i < NUMBER_OF_PLAYERS; i++) {
            if(players[i].isTurn()) {
                System.out.println("Turn started for Player " + i + 1 + ".");
            }
        }
    }

    private void roll() {
        dice1 = (int) (Math.random() * 6 + 1);
        dice2 = 0; //(int) (Math.random() * 6 + 1);
        diceSum = dice1 +dice2;
        System.out.println("Player "  + getTurn() + " rolled ["+dice1+"] =" + diceSum + ".");
        //System.out.println("Player "  + getTurn() + " rolled ["+dice1+"]["+dice2+"] =" + diceSum + ".");
    }


    private int getTurn() {
        for(int i = 0; i < NUMBER_OF_PLAYERS; i++) {
            if(players[i].isTurn()) {
                return i + 1;
            }
        }
        return 0;
    }

    private void isGameOver() {
        if(allLandmarksConstructed()) {
           System.out.println("The game is over. Player " + getTurn() + " is the winner.");
           isGameOver = true;
        }
        isGameOver = false;
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
        while(!isGameOver) {

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


            // (6) endTurn()
            //  "Turn ended for Player N."
        }
    }


    public static void main(String[] args) {

        MachiWoCo m = new MachiWoCo();
        m.playGame();
    }
}

