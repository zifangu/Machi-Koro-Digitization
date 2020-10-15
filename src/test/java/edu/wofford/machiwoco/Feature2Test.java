package edu.wofford.machiwoco;

import io.cucumber.java.jv.Lan;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import java.util.*;
import org.junit.*;
import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;

public class Feature2Test {
    MachiWoCo m;

    @Before
    public void before() {
        m = new MachiWoCo();
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
        Landmark[] startingLandmarks = new Landmark[1];
        startingLandmarks[0] = city;

        Player player = new Player(startingEstablishments, startingLandmarks, 4,1);

        Map<Establishment, Integer> estOwnedTest = player.getEstOwned();
        player.buyCard(ranch);
        assertTrue(estOwnedTest.containsKey(ranch));
        assertTrue(estOwnedTest.containsValue(1));
        assertThat(player.getCoinCount(), is(3));

        player.buyCard(ranch);
        assertTrue(estOwnedTest.containsKey(wheat));
        assertTrue(estOwnedTest.containsValue(2));
        assertThat(player.getCoinCount(), is(2));
    }

}
