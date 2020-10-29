package edu.wofford.machiwoco;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.*;
import org.junit.*;
import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;

public class TwoPlayersPhase1Test {
    TwoPlayersPhase1 twoPlayersPhase1;
    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    private final ByteArrayOutputStream errContent = new ByteArrayOutputStream();
    private final PrintStream originalOut = System.out;
    private final PrintStream originalErr = System.err;

    @Before
    public void before() {
        twoPlayersPhase1 = new TwoPlayersPhase1(false);
        System.setOut(new PrintStream(outContent));
        System.setErr(new PrintStream(errContent));
    }

    @Test
    public void testPlayerNum() {
        assertThat(twoPlayersPhase1.getPlayers().length, is(2));
    }


//    test separately
//    test market after buy

    @Test
    public void testInitialMarket() {
        Establishment wheat = twoPlayersPhase1.getWheat();
        assertThat(twoPlayersPhase1.getMarket().get(wheat), is(6));

        Establishment ranch = twoPlayersPhase1.getRanch();
        assertThat(twoPlayersPhase1.getMarket().get(ranch), is(6));

        Establishment forest = twoPlayersPhase1.getForest();
        assertThat(twoPlayersPhase1.getMarket().get(forest), is(6));
    }

    @Test
    public void testPlayerStats() {
        Player[] players = twoPlayersPhase1.getPlayers();
        int count = 0;
        for (Player p : players) {
            count += p.getCoinCount();
        }
        assertThat(count, is(3*players.length));
    }

    @Test
    public void testStaticMarketToString() {
        assertThat(twoPlayersPhase1.generateStaticMarket(), is("******************************************\n" +
                "                  MARKET                  \n" +
                "------------------------------------------\n"));
    }

    @Test
    public void testMartket() {
        assertThat(twoPlayersPhase1.generateMarket(), is("******************************************\n" +
                "                  MARKET                  \n" +
                "------------------------------------------\n" +
                "Wheat Field        BW (1)  [1]      #6\n" +
                "Ranch              BC (1)  [2]      #6\n" +
                "Forest             BG (3)  [5]      #6\n\n"));
    }

    @Test
    public void testActivePlayer1() {
        Player p = twoPlayersPhase1.getPlayer1();
        assertThat(twoPlayersPhase1.generatePlayerLine(p, 1, true), is("             Player 1* [YOU]              \n"));
    }

    @Test
    public void testNonActivePlayer1() {
        Player p = twoPlayersPhase1.getPlayer1();
        assertThat(twoPlayersPhase1.generatePlayerLine(p, 1, false), is("                 Player 1                 \n"));
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
        Player p = twoPlayersPhase1.getPlayer1();
        twoPlayersPhase1.getPlayers()[0].setTurn(true);

        assertThat(twoPlayersPhase1.generatePlayer(p, twoPlayersPhase1.getTurn(), true), is("             Player 1* [YOU]              \n" +
                "------------------------------------------\n" +
                "                (3 coins)                 \n" +
                "Wheat Field        BW (1)  [1]      #1\n"    +
                "..........................................\n" +
                "City Hall          NT (7)  [ ]\n\n"));
    }

    @Test
    public void testNoOneTurn() {
        assertThat(twoPlayersPhase1.getTurn(), is(0));
    }


    @Test
    public void testCurrentGameState() {
        Player p = twoPlayersPhase1.getPlayer1();
        twoPlayersPhase1.getPlayers()[0].setTurn(true);

        assertThat(twoPlayersPhase1.getCurrentGameState(), is ("******************************************\n" +
                        "                  MARKET                  \n" +
                        "------------------------------------------\n" +
                        "Wheat Field        BW (1)  [1]      #6\n" +
                        "Ranch              BC (1)  [2]      #6\n" +
                        "Forest             BG (3)  [5]      #6\n\n" +
                        "             Player 1* [YOU]              \n" +
                        "------------------------------------------\n" +
                        "                (3 coins)                 \n" +
                        "Wheat Field        BW (1)  [1]      #1\n"    +
                        "..........................................\n" +
                        "City Hall          NT (7)  [ ]\n\n" +
                        "                 Player 2                 \n" +
                        "------------------------------------------\n" +
                        "                (3 coins)                 \n" +
                        "Wheat Field        BW (1)  [1]      #1\n"    +
                        "..........................................\n" +
                        "City Hall          NT (7)  [ ]\n\n" +
                        "******************************************"));
    }
    @Test
    public void testToStringIfCanConstructLandmark() {
        Player p = twoPlayersPhase1.getPlayer1();
        twoPlayersPhase1.getPlayers()[0].setTurn(true);
        p.setCoinCount(10);
        assertThat(twoPlayersPhase1.getAvailLandmark(1), is("---------       CONSTRUCT        ---------\n" +
                " 1. City Hall          NT (7)  [ ]\n"));
    }

    @Test
    public void testToStringCannotConstructLandmark() {
        Player p = twoPlayersPhase1.getPlayer1();
        twoPlayersPhase1.getPlayers()[0].setTurn(true);
        p.setCoinCount(3);
        assertThat(twoPlayersPhase1.getAvailLandmark(1), is(""));
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
        Player p = twoPlayersPhase1.getPlayer2();
        twoPlayersPhase1.getPlayers()[1].setTurn(true);
        p.setCoinCount(0);
        int count = 1;
        assertThat(twoPlayersPhase1.getAvailEst(count), is(""));
    }

    @Test
    public void testToStringCannotBuy2() {
        Player p = twoPlayersPhase1.getPlayer1();
        twoPlayersPhase1.getPlayers()[0].setTurn(true);
        p.setCoinCount(0);
        int count = 1;
        assertThat(twoPlayersPhase1.getAvailEst(count), is(""));
    }

    @Test
    public void testNoPlayer() {
        assertThat(twoPlayersPhase1.getCurrentPlayer(), is(nullValue()));
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


    // tests buying an establishment
    @Test
    public void testBuyCard() {
        Player player1 = twoPlayersPhase1.getPlayer1();
        Player player2 = twoPlayersPhase1.getPlayer2();
        Map<Establishment, Integer> market = twoPlayersPhase1.getMarket();

        player1.buyCard(twoPlayersPhase1.getRanch());
        assertTrue(player1.getEstOwned().containsKey(twoPlayersPhase1.getRanch()));
        assertThat(player1.getCoinCount(), is(2));
        assertTrue(player1.getEstOwned().containsKey(twoPlayersPhase1.getRanch()));
    }

    // tests actions performed if a dice roll lands within an activation range
    @Test
    public void testGetActivationNumbers() {
        Player player1 = twoPlayersPhase1.getPlayer1();
        Map<Establishment, Integer> estOwned = player1.getEstOwned();
        Establishment test = new Establishment("X", 3, Card.Color.RED, Card.Color_ab.R, Card.Icon.GEAR, Card.Icon_ab.G,
        "X", "8", "give", "nobody", 1, "none", "none");
        estOwned.put(test, 1);

        player1.setEstOwned(estOwned);
        player1.getActivationNumbers(2, true);
        player1.getActivationNumbers(1, true);
        assertThat(player1.getCoinCount(), is(4));
        player1.getActivationNumbers(8, true);
        assertThat(player1.getCoinCount(), is(4));
    }

    @Test
    public void testActivation() {
        Player p = twoPlayersPhase1.getPlayer1();
        twoPlayersPhase1.diceSum = 1;
        twoPlayersPhase1.activationTest();
        assertThat(p.getCoinCount(), is(4));
    }

    // Test get/set of ai
    @Test
    public void testAi() {
        Player player1 = twoPlayersPhase1.getPlayer1();
        player1.setAi(true);
        assertTrue(player1.isAi());
    }

    // Get the player's number
    @Test
    public void testGetPlayerNumber() {
        Player player1 = twoPlayersPhase1.getPlayer1();
        assertThat(player1.getPlayerNumber(), is(1));
    }

    // Set the player's landmarks
    @Test
    public void testSetLandmarks() {
        Player player1 = twoPlayersPhase1.getPlayer1();
        Landmark[] landmarks = new Landmark[2];
        Landmark city = new Landmark("City Hall", 7, Card.Color.NONE, Card.Color_ab.N, Card.Icon.TOWER, Card.Icon_ab.T,
                "|  This is a city hall  |\n");
        Landmark testL = new Landmark("X", 7, Card.Color.NONE, Card.Color_ab.N, Card.Icon.TOWER, Card.Icon_ab.T,
                "X");
        landmarks[0] = city;
        landmarks[1] = testL;
        player1.setLandmarks(landmarks);
        assertThat(player1.getLandmarks(), is(landmarks));
    }


    // More Machi Tests
    @Test
    public void testSetters() {
        TwoPlayersPhase1 machi = new TwoPlayersPhase1(false);
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
        TwoPlayersPhase1 machi = new TwoPlayersPhase1(false);

        Establishment wheat = machi.getWheat();
        Establishment ranch = machi.getRanch();
        Establishment forest = machi.getForest();
        
        machi.setWheat(wheat);
        assertThat(machi.getWheat(), is(wheat));

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


    @Test
    public void testNoMoveInput() {
        twoPlayersPhase1.getPlayers()[0].setTurn(true);
        twoPlayersPhase1.getBuyInput(99);
        assertThat(outContent.toString(), is("Player 1 chose not to make improvements.\n"));
//        twoPlayersPhase1.getBuyInput(98);
//        assertThat(outContent.toString(), containsString("Not a valid input\n"));


    }

    @Test
    public void testEstInput() {
        twoPlayersPhase1.getPlayers()[0].setTurn(true);
        assertThat(twoPlayersPhase1.getBuyInput(1), is(true));
    }

    @Test
    public void testBuyInput() {
        twoPlayersPhase1.getPlayers()[0].setTurn(true);
        assertThat(twoPlayersPhase1.handleInput("1"), is(true));
    }

    @Test
    public void testViewInput() {
        twoPlayersPhase1.getPlayers()[0].setTurn(true);
        twoPlayersPhase1.handleInput("view 1");
//        assertThat(outContent.toString(), containsString("Wheat Field"));
        assertThat(twoPlayersPhase1.handleInput("view 1"), is(false));
        assertThat(twoPlayersPhase1.handleInput("view 90"), is(false));

    }

//    @Test
//    public void testInvalidInput() {
//        twoPlayersPhase1.getPlayers()[0].setTurn(true);
//        twoPlayersPhase1.handleInput("I love MachiWoco");
//        assertThat(outContent.toString(), containsString("Not a valid input"));
//    }


    @Test
    public void testGetMenu() {
        twoPlayersPhase1.getPlayers()[0].setTurn(true);
        assertThat(twoPlayersPhase1.getMenu(), containsString("PURCHASE"));
        assertThat(twoPlayersPhase1.getMenu(), containsString("CANCEL"));

    }


    @Test
    public void testViewLandmarkInput() {
        twoPlayersPhase1.getPlayers()[0].setTurn(true);
        twoPlayersPhase1.getCurrentPlayer().setCoinCount(10);

        twoPlayersPhase1.handleInput("view 4");
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
        twoPlayersPhase1.getPlayers()[0].setTurn(true);
        twoPlayersPhase1.getCurrentPlayer().setCoinCount(10);
        assertThat(twoPlayersPhase1.getBuyInput(4), is(true));
    }

    @Test
    public void testAllLandmark() {
        Landmark[] l;
        twoPlayersPhase1.getPlayers()[0].setTurn(true);
        twoPlayersPhase1.getCurrentPlayer().setCoinCount(10);
        int count = 0;
        l = twoPlayersPhase1.getCurrentPlayer().getLandmarks();
        l[0].is_constructed = true;
        for (int j = 0; j < twoPlayersPhase1.NUMBER_OF_LANDMARKS; j++) {
            if (l[j].is_constructed) {
                count++;
            }
            assertThat(twoPlayersPhase1.allLandmarksConstructed(), is(true));
        }
    }

    @Test
    public void testNoLandmark() {
        Landmark[] l;
        twoPlayersPhase1.getPlayers()[0].setTurn(true);
        twoPlayersPhase1.getCurrentPlayer().setCoinCount(10);
        int count = 0;
        l = twoPlayersPhase1.getCurrentPlayer().getLandmarks();
        l[0].is_constructed = false;
        for (int j = 0; j < twoPlayersPhase1.NUMBER_OF_LANDMARKS; j++) {
            if (l[j].is_constructed) {
                count++;
            }
            assertThat(twoPlayersPhase1.allLandmarksConstructed(), is(false));
        }
    }

    @Test
    public void testIsNumeric() {
        assertThat(twoPlayersPhase1.isNumeric(""), is (false));

    }


    @Test
    public void testPrintTurn() {
        twoPlayersPhase1.getPlayers()[0].setTurn(true);
        twoPlayersPhase1.printTurn();
        assertThat(outContent.toString(), containsString("Turn started for Player 1"));
    }

    @Test
    public void testStartGame() {
        twoPlayersPhase1.startGame();
        assertThat(outContent.toString(), containsString("The game has started. Player 1 will go first."));
    }

    @Test
    public void testCanAfford() {
        twoPlayersPhase1.getPlayers()[0].setTurn(true);
        twoPlayersPhase1.getCurrentPlayer().setCoinCount(10);
        Player p = twoPlayersPhase1.getCurrentPlayer();
        assertThat(twoPlayersPhase1.canAffordCard(p), is(true));
        twoPlayersPhase1.getCurrentPlayer().setCoinCount(0);
        assertThat(twoPlayersPhase1.canAffordCard(p), is(false));
    }


    @After
    public void restoreStreams() {
        System.setOut(originalOut);
        System.setErr(originalErr);
    }

}