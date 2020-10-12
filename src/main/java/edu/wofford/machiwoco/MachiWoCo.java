package edu.wofford.machiwoco;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class MachiWoCo {


    public static void main(String[] args) {
        //List of Establishments
        Establishment wheat = new Establishment("Wheat Field", 1, Card.Color.BLUE, Card.Icon.WHEAT,
                "|  Get 1 coin from the  |\n" +
                        "|         bank.         |\n" +
                        "|    (anyone's turn)    |\n",
                "1", "receive", "bank", 1, "none", "none");

        //**********Establishment ranch creation************//
        Establishment ranch = new Establishment("Ranch", 1, Card.Color.BLUE, Card.Icon.COW,
                "|  Get 1 coin from the  |\n" +
                        "|         bank.         |\n" +
                        "|    (anyone's turn)    |\n",
                "2", "receive", "bank", 1, "none", "none");


        Establishment forest = new Establishment("Forest",
                3, Card.Color.BLUE, Card.Icon.GEAR,
                "|  Get 1 coin from the  |\n" +
                        "|         bank.         |\n" +
                        "|    (anyone's turn)    |\n",
                "5", "receive", "bank", 1, "none", "none");



        int dice1;
        //    int dice2;
        //    int sum= dice1 + dice2;

        //MARKET PLACE FOR ESTABLISHMENTS
       // Establishment[] market = new Establishment[18];
        Map<Establishment,Integer> market = new HashMap<>();
        market.put(wheat, 6);
        market.put(ranch,6);
        market.put(forest,6);

        Map<Establishment,Integer> est = new HashMap<>();
        est.put(wheat,1);

        Landmark city = new Landmark("City Hall", 7, Card.Color.NONE, Card.Icon.TOWER,
                "|  This is a city hall |\n");
        Landmark[] n = new Landmark[1];
        n[0] = city;

        Player player1 = new Player(est,n, 4);
        Player player2 = new Player(est,n, 4);

        //"The game has started. Player N will go
        //first." (where N is the starting player number).
     //   while(!gameIsOver()) {
            dice1 = (int) (Math.random() * 6 + 1);
            // startTurn(player1.getTurn()) {}
            //dice2 = (int)(Math.random()*6+1);
            //sum = dice1 + dice2;



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




//        for(int i = 0; i <6; i++) {
//            market[i] = wheat;
//            market[i+6] = ranch;
//            market[i+12] = forest;
//        }

        // OR

        // ADD PARAMETER to market "i.e. how many left"


        //CREATE STARTING ESTABLISHMENT


        //CREATE



     //   System.out.println(player1.getCoinCount());






     //   System.out.println("Let's play Machi WoCo!");
//        if (args[0].equals("landmark")) {
//        Landmark.main(args);
//        } else if (args[0].equals("establishment")) {
//        Establishment.main((args));
//        }
    }

