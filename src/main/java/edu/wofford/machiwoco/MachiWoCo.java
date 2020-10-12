package edu.wofford.machiwoco;


import com.sun.org.apache.bcel.internal.generic.LAND;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class MachiWoCo {
    Map<Establishment,Integer> market;
    Map<Establishment,Integer> est;

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
    final int NUMBER_OF_PLAYERS = 2;
    final int NUMBER_OF_LANDMARKS = 1;
    Establishment wheat;
    Establishment ranch;
    Establishment forest;

    public MachiWoCo() {
        //List of Establishments
        wheat = new Establishment("Wheat Field", 1, Card.Color.BLUE, Card.Icon.WHEAT,
                "|  Get 1 coin from the  |\n" +
                        "|         bank.         |\n" +
                        "|    (anyone's turn)    |\n",
                "1", "receive", "bank", 1, "none", "none");

        //**********Establishment ranch creation************//
        ranch = new Establishment("Ranch", 1, Card.Color.BLUE, Card.Icon.COW,
                "|  Get 1 coin from the  |\n" +
                        "|         bank.         |\n" +
                        "|    (anyone's turn)    |\n",
                "2", "receive", "bank", 1, "none", "none");


        forest = new Establishment("Forest", 3, Card.Color.BLUE, Card.Icon.GEAR,
                "|  Get 1 coin from the  |\n" +
                        "|         bank.         |\n" +
                        "|    (anyone's turn)    |\n",
                "5", "receive", "bank", 1, "none", "none");



        int dice1;
        //    int dice2;
        //    int sum= dice1 + dice2;

        //MARKET PLACE FOR ESTABLISHMENTS
        // Establishment[] market = new Establishment[18];

        market = new HashMap<>();
        market.put(wheat, 6);
        market.put(ranch,6);
        market.put(forest,6);
        market.get(wheat);

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
    }

    protected String generateStaticMarket() {
        return "******************************************\n" +
                "                  MARKET                  \n" +
                "------------------------------------------\n";
    }

    private void printTurn() {
        for(int i = 0; i < NUMBER_OF_PLAYERS; i++) {
            if(players[i].isTurn()) {
                System.out.println("Turn started for Player " + i + 1 + ".");
            }
        }
    }

    private Player getTurn() {
        for(int i = 0; i < NUMBER_OF_PLAYERS; i++) {
            if(players[i].isTurn()) {
                return players[i];
            }
        }
        return null;
    }

    private void isGameOver() {
        if(allLandmarksConstructed()) {
       //     System.out.println("The game is over. Player " + get + " is the winner.")
        }
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



    public static void main(String[] args) {

        //"The game has started. Player N will go
        //first." (where N is the starting player number).
     //   while(!gameIsOver()) {
            int dice1 = (int) (Math.random() * 6 + 1);
            // startTurn(player1.getTurn()) {}
            //dice2 = (int)(Math.random()*6+1);
            //sum = dice1 + dice2;


         //   getTurn();

           // print() Turn                    "Turn started for Player N."

            //print() Current Game State

            // print() Roll(dice1)             "Player N rolled [3] = 3."

            // print() Activation             "Forest activated for Player N."


            // MENU TO BUY
            //Establishment Purchase or Landmark Construction
                //EST: "Player N purchased the Furniture Factory."
                //LAND: "Player N constructed the Shopping Mall."
                //"Player N chose not to make improvements."


//            if(isActivated(dice1)) {
//                exchange/recieve money .action();
//            }

            //MARKET ToSTRING

            //Player 1 EST
            //Player 1 LAND

            //Player 2 EST
            //Player 2 LAND


            //changeTurn();
        }
    }

