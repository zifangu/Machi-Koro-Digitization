package edu.wofford.machiwoco;

import io.cucumber.java.jv.Lan;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.io.PrintWriter;
import java.util.*;
import org.junit.*;
import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;

public class Feature2Test {
    MachiWoCo m;
    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    private final ByteArrayOutputStream errContent = new ByteArrayOutputStream();
    private final PrintStream originalOut = System.out;
    private final PrintStream originalErr = System.err;

    @Before
    public void before() {
        m = new MachiWoCo();
        System.setOut(new PrintStream(outContent));
        System.setErr(new PrintStream(errContent));
    }

    @Test
    public void testPlayerNum() {
        assertThat(m.getPlayers().length, is(2));
    }

    @Test
    public void testInitialMarket() {
        Establishment wheat = m.getWheat();
        assertThat(m.getMarket().get(wheat), is(6));

        Establishment ranch = m.getRanch();
        assertThat(m.getMarket().get(ranch), is(6));

        Establishment forest = m.getForest();
        assertThat(m.getMarket().get(forest), is(6));
    }

    @Test
    public void testPlayerStats() {
        Player[] players = m.getPlayers();
        int count = 0;
        for (Player p : players) {
            count += p.getCoinCount();
        }
        assertThat(count, is(4*players.length));
    }

    @Test
    public void testStaticMarketToString() {
        assertThat(m.generateStaticMarket(), is("******************************************\n" +
                "                  MARKET                  \n" +
                "------------------------------------------\n"));
    }

    @Test
    public void testMartket() {
        assertThat(m.generateMarket(), is("******************************************\n" +
                "                  MARKET                  \n" +
                "------------------------------------------\n" +
                "Wheat Field        BW (1)  [1]      #6\n" +
                "Ranch              BC (1)  [2]      #6\n" +
                "Forest             BG (3)  [5]      #6\n\n"));
    }

    @Test
    public void testActivePlayer1() {
        Player p = m.getPlayer1();
        assertThat(m.generatePlayerLine(p, 1, true), is("             Player 1* [YOU]              \n"));
    }

    @Test
    public void testNonActivePlayer1() {
        Player p = m.getPlayer1();
        assertThat(m.generatePlayerLine(p, 1, false), is("                 Player 1                 \n"));
    }


//    @Test
//    public void testActivePlayerEST() {
//        Player p = m.getPlayer1();
//        m.getPlayers()[0].setTurn(true);
//
//        assertThat(m.generatePlayer(p, m.getTurn(), true), is("             Player 1* [YOU]              \n" +
//                "------------------------------------------\n" +
//                "                (4 coins)                 \n" +
//                "Wheat Field        BW (1)  [1]      #1\n"));
//    }

    @Test
    public void testActivePlayerFull() {
        Player p = m.getPlayer1();
        m.getPlayers()[0].setTurn(true);

        assertThat(m.generatePlayer(p, m.getTurn(), true), is("             Player 1* [YOU]              \n" +
                "------------------------------------------\n" +
                "                (4 coins)                 \n" +
                "Wheat Field        BW (1)  [1]      #1\n"    +
                "..........................................\n" +
                "City Hall          NT (7)  [ ]\n\n"));
    }


    @Test
    public void testCurrentGameState() {
        Player p = m.getPlayer1();
        m.getPlayers()[0].setTurn(true);

        assertThat(m.getCurrentGameState(), is ("******************************************\n" +
                        "                  MARKET                  \n" +
                        "------------------------------------------\n" +
                        "Wheat Field        BW (1)  [1]      #6\n" +
                        "Ranch              BC (1)  [2]      #6\n" +
                        "Forest             BG (3)  [5]      #6\n\n" +
                        "             Player 1* [YOU]              \n" +
                        "------------------------------------------\n" +
                        "                (4 coins)                 \n" +
                        "Wheat Field        BW (1)  [1]      #1\n"    +
                        "..........................................\n" +
                        "City Hall          NT (7)  [ ]\n\n" +
                        "                 Player 2                 \n" +
                        "------------------------------------------\n" +
                        "                (4 coins)                 \n" +
                        "Wheat Field        BW (1)  [1]      #1\n"    +
                        "..........................................\n" +
                        "City Hall          NT (7)  [ ]\n\n" +
                        "******************************************"));
    }
    @Test
    public void testToStringIfCanConstructLandmark() {
        Player p = m.getPlayer1();
        m.getPlayers()[0].setTurn(true);
        p.setCoinCount(10);
        assertThat(m.getAvailLandmark(1), is("---------       CONSTRUCT        ---------\n" +
                " 1. City Hall          NT (7)  [ ]\n"));
    }

    @Test
    public void testToStringCannotConstructLandmark() {
        Player p = m.getPlayer1();
        m.getPlayers()[0].setTurn(true);
        p.setCoinCount(3);
        assertThat(m.getAvailLandmark(1), is(""));
    }

//    @Test
//    public void testToStringIfCanBuy() {
//        m.getPlayers()[0].setTurn(true);
//
//        m.getCurrentPlayer().setCoinCount(69);
//
//        int count = 1;
//        assertThat(m.getAvailEst(count), is("---------        PURCHASE        ---------\n" +
//                " 1. Wheat Field        BW (1)  [1]      #6\n" +
//                " 2. Ranch              BC (1)  [2]      #6\n" +
//                " 3. Forest             BG (3)  [5]      #6\n"));
//    }

        @Test
    public void testToStringCannotBuy() {
        Player p = m.getPlayer1();
        m.getPlayers()[0].setTurn(true);
        p.setCoinCount(0);
        int count = 1;
        assertThat(m.getAvailEst(count), is(""));
    }





    // SortByCost Test
    @Test
    public void testCompare() {
        SortByCost sort = new SortByCost();
        Landmark city = new Landmark("City Hall", 14, Card.Color.NONE, Card.Color_ab.N, Card.Icon.TOWER, Card.Icon_ab.T,
                "|  This is a city hall  |\n");
        Landmark beach = new Landmark("Beach", 7, Card.Color.NONE, Card.Color_ab.N, Card.Icon.TOWER, Card.Icon_ab.T,
                "|  This is a city hall  |\n");

        assertThat(sort.compare(city, beach), is(7));
    }


    // Card tests
    @Test
    public void testCardGettersSetters() {
        Card card = new Card("Test Card", 4, Card.Color.BLUE, Card.Icon.WHEAT, "Description");

        card.setName("Home");
        assertThat(card.getName(), is("Home"));

        card.setCost(8);
        assertThat(card.getCost(), is(8));

        assertThat(card.getColor(), is(Card.Color.BLUE));
        assertThat(card.getIcon(), is(Card.Icon.WHEAT));

        card.setDescription("New description");
        assertThat(card.getDescription(), is("New description"));

    }


    // Player Tests
    @Test
    public void testBuy() {
        //List of Establishments
        Establishment wheat = new Establishment("Wheat Field", 1, Card.Color.BLUE, Card.Color_ab.B, Card.Icon.WHEAT, Card.Icon_ab.W,
                "|  Get 1 coin from the  |\n" +
                        "|         bank.         |\n" +
                        "|    (anyone's turn)    |\n",
                "1", "receive", "bank", 1, "none", "none");

        //**********Establishment ranch creation************//
        Establishment ranch = new Establishment("Ranch", 1, Card.Color.BLUE, Card.Color_ab.B, Card.Icon.COW, Card.Icon_ab.C,
                "|  Get 1 coin from the  |\n" +
                        "|         bank.         |\n" +
                        "|    (anyone's turn)    |\n",
                "2", "receive", "bank", 1, "none", "none");


        Establishment forest = new Establishment("Forest", 3, Card.Color.BLUE, Card.Color_ab.B, Card.Icon.GEAR, Card.Icon_ab.G,
                "|  Get 1 coin from the  |\n" +
                        "|         bank.         |\n" +
                        "|    (anyone's turn)    |\n",
                "5", "receive", "bank", 1, "none", "none");

        Establishment testEst = new Establishment("X", 3, Card.Color.RED, Card.Color_ab.R, Card.Icon.GEAR, Card.Icon_ab.G,
                "X", "8", "give", "nobody", 1, "none", "none");

        //MARKET PLACE FOR ESTABLISHMENTS
        // Establishment[] market = new Establishment[18];

        Map<Establishment,Integer> market = new HashMap<>();
        market.put(wheat, 6);
        market.put(ranch,6);
        market.put(forest,6);

        Map<Establishment,Integer> startingEstablishments = new HashMap<>();
        startingEstablishments.put(wheat,1);

        Landmark city = new Landmark("City Hall", 7, Card.Color.NONE, Card.Color_ab.N, Card.Icon.TOWER, Card.Icon_ab.T,
                "|  This is a city hall  |\n");
        Landmark testL = new Landmark("X", 7, Card.Color.NONE, Card.Color_ab.N, Card.Icon.TOWER, Card.Icon_ab.T,
                "X");
        Landmark[] startingLandmarks = new Landmark[1];
        startingLandmarks[0] = city;

        Player player = new Player(startingEstablishments, startingLandmarks, 4,1);

        Map<Establishment, Integer> estOwnedTest = player.getEstOwned();

        player.buyCard(wheat);
        //assertTrue(estOwnedTest.containsKey(wheat));
        assertTrue(estOwnedTest.containsValue(2));
        assertThat(player.getCoinCount(), is(3));

        player.buyCard(ranch);
        assertTrue(estOwnedTest.containsKey(ranch));
        //assertTrue(estOwnedTest.containsValue(1));
        assertThat(player.getCoinCount(), is(2));

        // Test activation
        player.getActivationNumbers(1);
        assertThat(player.getCoinCount(), is(4));

         // Test buy landmark fail because of nonexisting landmark
         player.buyLandmark(testL);
         assertThat(player.getCoinCount(), is(4));

         // Test buy landmark fail because of cost
         player.buyLandmark(city);
         assertThat(player.getCoinCount(), is(4));
 
         //Test buy landmark
         Player player2 = new Player(startingEstablishments, startingLandmarks, 7,2);
         player2.buyLandmark(city);
         assertThat(player2.getCoinCount(), is(0));
        
        // test Player number
        assertThat(player.getPlayerNumber(), is(1));

        //test setEstOwned
        Map<Establishment,Integer> startingEstablishments2 = new HashMap<>();
        startingEstablishments2.put(testEst,1);
        player.setEstOwned(startingEstablishments2);
        assertThat(player.getEstOwned(), is(startingEstablishments2));
        
        // Test setLandmarks
        Landmark[] startingLandmarks2 = new Landmark[1];
        startingLandmarks2[0] = testL;
        player.setLandmarks(startingLandmarks2);
        assertThat(player.getLandmarks(), is(startingLandmarks2));


        Establishment testEst2 = new Establishment("X", 3, Card.Color.RED, Card.Color_ab.R, Card.Icon.GEAR, Card.Icon_ab.G,
                "X", "11", "receive", "nobody", 1, "none", "none");
        Establishment testEst3 = new Establishment("X", 3, Card.Color.RED, Card.Color_ab.R, Card.Icon.GEAR, Card.Icon_ab.G,
                "X", "12", "give", "bank", 1, "none", "none");
        startingEstablishments2.put(testEst2,1);
        startingEstablishments2.put(testEst3,1);
        Player player3 = new Player(startingEstablishments2, startingLandmarks, 7,3);

        // Test activation fail - no receive
        player3.getActivationNumbers(12);
        assertThat(player3.getCoinCount(), is(7));

        // Test activation fail - target != bank
        player3.getActivationNumbers(11);
        assertThat(player3.getCoinCount(), is(7));

    }

    // More Machi Tests
    @Test
    public void testSetters() {
        MachiWoCo machi = new MachiWoCo();
        Player player1 = machi.getPlayer1();
        Player player2 = machi.getPlayer2();

        machi.setPlayer1(machi.getPlayer2());
        assertThat(machi.getPlayer1(), is(machi.getPlayer2()));

        machi.setPlayer1(player1);
        machi.setPlayer2(machi.getPlayer1());
        assertThat(machi.getPlayer2(), is(machi.getPlayer1()));

        machi.setPlayer1(player1);
        machi.setPlayer2(player2);
        Player[] playerArr = new Player[] {player2, player1};
        machi.setPlayers(playerArr);
        Player[] testPArr = machi.getPlayers();
        assertThat(testPArr[0], is(player2));
    }

    // Machi setters
    @Test
    public void testSetEstablishments() {
        MachiWoCo machi = new MachiWoCo();

        Establishment wheat = new Establishment("Wheat Field", 1, Card.Color.BLUE, Card.Color_ab.B, Card.Icon.WHEAT, Card.Icon_ab.W,
                "|  Get 1 coin from the  |\n" +
                        "|         bank.         |\n" +
                        "|    (anyone's turn)    |\n",
                "4", "test", "test", 1, "none", "none");
        
        machi.setWheat(wheat);
        assertThat(machi.getWheat(), is(wheat));

        Establishment ranch = new Establishment("Ranch", 1, Card.Color.BLUE, Card.Color_ab.B, Card.Icon.COW, Card.Icon_ab.C,
                "|  Get 1 coin from the  |\n" +
                        "|         bank.         |\n" +
                        "|    (anyone's turn)    |\n",
                "2", "receive", "bank", 1, "none", "none");


        Establishment forest = new Establishment("Forest", 3, Card.Color.BLUE, Card.Color_ab.B, Card.Icon.GEAR, Card.Icon_ab.G,
                "|  Get 1 coin from the  |\n" +
                        "|         bank.         |\n" +
                        "|    (anyone's turn)    |\n",
                "5", "receive", "bank", 1, "none", "none");

        machi.setRanch(ranch);
        assertThat(machi.getRanch(), is(ranch));

        machi.setForest(forest);
        assertThat(machi.getForest(), is(forest));

        Map<Establishment,Integer> market = new HashMap<>();
        market.put(wheat, 6);
        market.put(ranch,6);
        market.put(forest,6);

        machi.setMarket(market);
        assertThat(machi.getMarket(), is(market));
        
    }

   /* @Test
    public void testPlayGame() {
        MachiWoCo machi = new MachiWoCo();
        Player player1 = machi.getPlayer1();
        Player player2 = machi.getPlayer2();

        Landmark city = new Landmark("City Hall", 7, Card.Color.NONE, Card.Color_ab.N, Card.Icon.TOWER, Card.Icon_ab.T,
                "|  This is a city hall  |\n");

        Landmark[] p1L = player1.getLandmarks();
        Landmark[] p2L = player2.getLandmarks();

        machi.playGame();

        player2.buyLandmark(city);

        assertEquals("The game is over. Player 2 is the winner.", outContent.toString());

    } */



    // game test
    @Test
    public void testNoMoveInput() {
        m.getPlayers()[0].setTurn(true);
        m.getBuyInput(99);
        assertThat(outContent.toString(), is("Player 1 chose not to make improvements.\n"));
        m.getBuyInput(98);
        assertThat(outContent.toString(), containsString("Not a valid input\n"));


    }

    @Test
    public void testEstInput() {
        m.getPlayers()[0].setTurn(true);
        assertThat(m.getBuyInput(1), is(true));
    }

    @Test
    public void testBuyInput() {
        m.getPlayers()[0].setTurn(true);
        assertThat(m.handleInput("1"), is(true));
    }

    @Test
    public void testViewInput() {
        m.getPlayers()[0].setTurn(true);
        m.handleInput("view 1");
        assertThat(outContent.toString(), containsString("Wheat Field"));
        assertThat(m.handleInput("view 1"), is(false));
    }

    @Test
    public void testInvalidInput() {
        m.getPlayers()[0].setTurn(true);
        m.handleInput("I love MachiWoco");
        assertThat(outContent.toString(), containsString("Not a valid input"));
    }


    @Test
    public void testGetMenu() {
        m.getPlayers()[0].setTurn(true);
        assertThat(m.getMenu(), containsString("PURCHASE"));
        assertThat(m.getMenu(), containsString("CANCEL"));

    }


    @Test
    public void testViewLandmarkInput() {
        m.getPlayers()[0].setTurn(true);
        m.getCurrentPlayer().setCoinCount(10);

        m.handleInput("view 4");
        assertThat(outContent.toString(), is(".-----------------------.\n" +
                "| <N>   LANDMARK    {T} |\n" +
                "|       City Hall       |\n" +
                "|                       |\n" +
                "|  This is a city hall  |\n" +
                "|                       |\n" +
                "| (7)               [ ] |\n" +
                "|_______________________|\n"));
    }

    @Test
    public void testLandInput() {
        m.getPlayers()[0].setTurn(true);
        m.getCurrentPlayer().setCoinCount(10);
        assertThat(m.getBuyInput(4), is(true));
    }



    @After
    public void restoreStreams() {
        System.setOut(originalOut);
        System.setErr(originalErr);
    }

}
