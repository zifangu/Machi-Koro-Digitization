package edu.wofford.machiwoco;


import org.apache.commons.lang3.StringUtils;

import java.util.HashMap;
import java.util.Map;
import java.util.ArrayList;
import java.util.*;

/** 
 * This is a class built to represent the MachiWoco game.
 * 
 * @author Eric Craft
 * @author Ivan Gu
 * @author Bennett Joyce
 */

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

    Establishment wheat;
    Establishment ranch;
    Establishment forest;
    Establishment[] EST_ORDER;


    //**********GETTERS AND SETTERS************//

    /**
     * Gets player 1
     * @return player1's instance
     */

    public Player getPlayer1() {
        return player1;
    }

    /**
     * Sets the given player to be player1
     * @param player1 the given player to be set to player1
     */

    public void setPlayer1(Player player1) {
        this.player1 = player1;
    }
    
    /**
     * Gets player 2
     * @return player2's instance
     */

    public Player getPlayer2() {
        return player2;
    }

    /**
     * Sets the given player to be player2
     * @param player2 the given player to be set to player2
     */

    public void setPlayer2(Player player2) {
        this.player2 = player2;
    }

    /**
     * Gets the full list of available players
     * @return an array of Players to take part in the game
     */

    public Player[] getPlayers() {
        return players;
    }

    /**
     * Sets the players to partake in the game
     * @param players an array of Players to take part in the game
     */

    public void setPlayers(Player[] players) {
        this.players = players;
    }

    /**
     * Gets the wheat establishment
     * @return the wheat establishment
     */

    public Establishment getWheat() {
        return wheat;
    }

    /**
     * Sets the given Establishment to represent the wheat establishment
     * @param wheat the Establishment to represent the wheat establishment
     */

    public void setWheat(Establishment wheat) {
        this.wheat = wheat;
    }

    /**
     * Gets the ranch establishment
     * @return the ranch establishment
     */

    public Establishment getRanch() {
        return ranch;
    }

    /**
     * Gets the current market
     * @return the current market as a Map object
     */

    public Map<Establishment, Integer> getMarket() {
        return market;
    }

    /**
     * Sets the market
     * @param market a Map object to represent the current market
     */

    public void setMarket(Map<Establishment, Integer> market) {
        this.market = market;
    }

    /**
     * Sets the given Establishment to represent the ranch establishment
     * @param ranch the Establishment to represent the ranch establishment
     */

    public void setRanch(Establishment ranch) {
        this.ranch = ranch;
    }

    /**
     * Gets the forest establishment
     * @return the forest establishment
     */

    public Establishment getForest() {
        return forest;
    }

    /**
     * Sets the given Establishment to represent the forest establishment
     * @param forest the Establishment to represent the forest establishment
     */

    public void setForest(Establishment forest) {
        this.forest = forest;
    }


    //**********CONSTRUCTOR************//

    /**
     * MachiWoco constructor serving as the infrastructure of the game
     */

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
     * Returns an ArrayList of Landmarks that the Player can afford
     * @param player the player who's coin count is being checked
     * @return a Landmark ArrayList containing available and affordable Landmark cards
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

    /**
     * Generates padding for the toString() of the class
     * @param s the string to which padding is being added
     * @return the final padded string
     */
    protected String generate_pure_padding(String s) {
        return StringUtils.center("", 42, s) + "\n";
    }

    /**
     * Generates the title for the toString() of the class
     * @param s the string to which the title is being added
     * @return the resulting title string
     */

    protected String generate_title(String s) {
        return StringUtils.center(s, 42, " ") + "\n";
    }

    /**
     * Generates the static market in string form
     * @return a string representing the static market
     */

    protected String generateStaticMarket() {
        return generate_pure_padding("*") +
                generate_title("MARKET") +
                generate_pure_padding("-");
    }

    /**
     * Provides the given cost in string form
     * @param cost the cost of the card
     * @return the string representing the cost of the card to be used in toString()
     */

    protected String generateCost(int cost) {
        String act = "(" + cost + ")";
        return StringUtils.rightPad(act, 4, " ");
    }

    /**
     * Provides the given activation range in string form
     * @param s the activation range in string format
     * @return the string representing the activation range of the card
     */

    public String generateActivation(String s) {
        String act = "[" + s + "]";
        return StringUtils.rightPad(act, 7, " ");
    }

    /**
     * Generates a given market item to be displayed to the player
     * @param e an Establishment instance to be displayed within the market
     * @param count the available number of a given Establishment instance
     * @return a string representing the market item of an Establishment card
     */

    protected String generateSingleMarketItem(Establishment e, int count) {
        return StringUtils.rightPad(e.getName(), 18, " ") + " " +
                e.getColor_ab() + e.getIcon_ab() + " " +
                generateCost(e.getCost()) + " " +
                generateActivation(e.getActivation()) + " " +
                " #" + Integer.toString(count) + "\n";
    }

    /**
     * Generates the market to be displayed to the player
     * @return a string representing the current market
     */

    protected  String generateMarket() {
        StringBuilder s = new StringBuilder();
        for (Establishment e : EST_ORDER) {
            s.append(generateSingleMarketItem(e, market.get(e)));
        }
//        System.out.print(s);
        return generateStaticMarket() + s + "\n";
    }

    /**
     * Generates the player's coin count to be displayed
     * @param p the player who's coin count is being displayed
     * @return a string displaying the given player's coin count
     */

    protected String generatePlayerCoin(Player p) {
        String account = "(" + p.getCoinCount() + " coins)";
        return StringUtils.center(account, 42, " ") + "\n";
    }

    /**
     * Generates a string dispaying the current player
     * @param p the player being observed
     * @param num the player's number
     * @param active a boolean that holds true if the given player is active
     * @return a string displaying the current player
     */

    protected String generatePlayerLine(Player p, int num, boolean active) {
        String player;
        if (active) {
            player = "Player " + num + "* [YOU]";
        } else {
            player = "Player " + num;
        }
        return StringUtils.center(player, 42, " ") + "\n";
    }

    /**
     * Generates a string displaying the given player's current Establishments
     * @param p the player who's establishments are being displayed
     * @return a string displaying the player's current Establishments
     */

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

    /**
     * Generates a string displaying the given Landmark
     * @param l the Landmark instance to be displayed
     * @return a string displaying the given Landmark
     */

    protected String generateLandmark(Landmark l) {
        String construct = "[" + l.isConstructed(l.is_constructed) + "]";
        return StringUtils.rightPad(l.getName(), 18, " ") +
                " " + l.getColor_ab() + l.getIcon_ab() + " " +
                generateCost(l.getCost()) + " " +
                construct + "\n";

    }

    /**
     * Generates a string displaying the given player's current Landmark(s)
     * @param p the player who's landmark(s) are being displayed
     * @return a StringBuilder object displaying the player's current Landmark(s)
     */

    protected StringBuilder generatePlayerLandMark(Player p) {
        StringBuilder s = new StringBuilder();
        for (Landmark l : p.getLandmarks()) {
            s.append(generateLandmark(l));
        }
        return s;
    }

    /**
     * Generates a string displaying information about a given player
     * @param p the player to be displayed
     * @param num the given player's number
     * @param active a boolean that holds true if the given player is active
     * @return a string displaying the given player's coins, establishments, and landmarks
     */

    protected String generatePlayer(Player p, int num, boolean active) {
        return generatePlayerLine(p, num, active) +
                generate_pure_padding("-") +
                generatePlayerCoin(p) +
                generatePlayerEst(p) +
                generate_pure_padding(".") +
                generatePlayerLandMark(p) + "\n";
    }




    //**********GAME STEP 1: START GAME************//

    /**
     * Prints a message to the console indicating the start of the game
     */

    private void startGame() { System.out.println( "The game has started. Player 1 will go first."); }

    //**********GAME STEP 2: PRINT TURN************//

    /**
     * Prints a message to the console indicating the current player's turn
     */

    private void printTurn() {
        for(int i = 0; i < NUMBER_OF_PLAYERS; i++) {
            if(players[i].isTurn()) {
                System.out.println("Turn started for Player " + (i + 1) + ".");
            }
        }
    }

    //**********GAME STEP 3: CURRENT GAME STATE************//

    /**
     * Generates a string representing the current game state
     * @return a string displaying the current game state
     */

    protected String getCurrentGameState() {
        StringBuilder s = new StringBuilder();
        for (int i = 0; i < players.length; i++) {
            s.append(generatePlayer(players[i], i + 1, players[i].isTurn()));
        }
        return generateMarket() + s + StringUtils.center("", 42, "*");
    }

    //**********GAME STEP 4: ROLL THE DICE************//

    /**
     * Prints a message to the console displaying the current player's dice roll
     */

    private void roll() {
        dice1 = (int) (Math.random() * 6 + 1);
        dice2 = 0; //(int) (Math.random() * 6 + 1);
        diceSum = dice1 +dice2;
        System.out.println("Player "  + getTurn() + " rolled ["+dice1+"] =" + diceSum + ".");
        //System.out.println("Player "  + getTurn() + " rolled ["+dice1+"]["+dice2+"] =" + diceSum + ".");
    }

    /**
     * Finds which player's turn it is and provides their player number
     * @return the current player's number
     */

    protected int getTurn() {
        for(int i = 0; i < NUMBER_OF_PLAYERS; i++) {
            if(players[i].isTurn()) {
                return i + 1;
            }
        }
        return 0;
    }
    //**********GAME STEP 5: Activation************//

    /**
     * Passes in the dice to the Player function
     */

    private void activationTest() {
        for(int i = 0; i < NUMBER_OF_PLAYERS; i++) {
            players[i].getActivationNumbers(dice1);
        }
    }

    /**
     * The logic involved with buying Establishment cards
     * @return an ArrayList of Establishments that are available to purchase
     */
    
    private ArrayList<Establishment> buyEstablishmentLogic() {
        int amountOwned = getCurrentPlayer().getCoinCount();
        System.out.println("AMOUNT: " + amountOwned);
        ArrayList<Establishment> e = getAffordableEstablishments(getCurrentPlayer(),amountOwned);
        return e;
    }

    /**
     * Provides a list of affordable establishments available to a given player
     * @param player the player who's affordable establishments are being presented
     * @param owned the amount of coins owned by the given player
     * @return an ArrayList of Establishments that are available to purhcase by a given player
     */

    public ArrayList<Establishment> getAffordableEstablishments(Player player, int owned) {
        Set<Establishment> setE = market.keySet();
        ArrayList<Establishment> eResult = new ArrayList<Establishment>();
        for(Establishment est: setE){
            int cost = est.getCost();
            if(owned >= cost) {
                eResult.add(est);
            }
        }
        return eResult;
    }

    //**********GAME STEP 5.5: PURCHASE AND CONSTRUCT************//

    /**
     * Constructs the static portion of the menu to be displayed
     * @param s the string representing the static portion of the menu
     * @return the static portion of the menu to be displayed
     */

    protected String getMenuStatic(String s) {
        String name = StringUtils.center("", 9, "-") +
                StringUtils.center(s, 24, " ") + StringUtils.center("", 9, "-");
        return name + "\n";
    }

    /**
     * Constructs the available landmarks in string format
     * @param i the index to where the landmark is going to be placed
     * @return the string that displays the available landmarks
     */

    protected String getAvailLandmark(int i) {
        ArrayList<Landmark> l = getAffordableLandmarks(getCurrentPlayer());
        StringBuilder s = new StringBuilder();
        int count = i;
        if (l.size() != 0) {
            for (Landmark land : l) {
                String order = count + ".";
                s.append(StringUtils.leftPad(order, 3, " ")).append(" ").append(generateLandmark(land));
                count ++;
            }
            return getMenuStatic("CONSTRUCT") + s;
        }
        return "";
    }

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

    /**
     * Gets the current player
     * @return the current Player's instance
     */

    public Player getCurrentPlayer() {
        for(int i = 0; i < NUMBER_OF_PLAYERS; i++) {
            if(players[i].isTurn()) {
                return players[i];
            }
        }
        return null;
    }



    //**********GAME STEP 6: END TURN************//

    /**
     * Ends the current player's turn and checks to see if the game has ended
     */

    private void endTurn() {
        int curPlayerIndex = getTurn() - 1;
        System.out.println("Turn ended for Player " + getTurn() +".");
        if(curPlayerIndex == NUMBER_OF_PLAYERS-1) {
            players[0].setTurn(true);
        } else {
            players[curPlayerIndex].setTurn(false);
            players[curPlayerIndex +1].setTurn(true);
        }

    }

    /**
     * Checks to see if the game is over
     */

    private boolean isGameOver() {
        if(allLandmarksConstructed()) {
           System.out.println("The game is over. Player " + getTurn() + " is the winner.");
           return true;
        }
        return false;
    }

    /**
     * Checks to see if all Landmarks have been constructed
     * @return a boolean holding true if all landmarks have been constructed
     */

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

    /**
     * Play the MachiWoCo game in its entirety
     */

    public void playGame() {
        startGame();
        players[0].setTurn(true);
        while(!isGameOver()) {

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
            // buyLogic();
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

//        m.getPlayers()[1].setTurn(true);
        m.getPlayers()[0].setTurn(true);

//        System.out.print(m.getCurrentGameState());
            m.getCurrentPlayer().setCoinCount(10);
//        p.setCoinCount(3);
//        System.out.println("\n" + m.getAvailLandmark(10));

        int count = 1;
        System.out.println("\n" + m.getAvailEst(count));

    }
}

