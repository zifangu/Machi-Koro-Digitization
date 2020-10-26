package edu.wofford.machiwoco;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.io.PrintWriter;
import java.util.*;
import org.junit.*;
import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;

public class GameStateSubjectTest {
    GameStateSubject gameSubject;
    Feature2 feature2;
    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    private final ByteArrayOutputStream errContent = new ByteArrayOutputStream();
    private final PrintStream originalOut = System.out;
    private final PrintStream originalErr = System.err;

    @Before
    public void before() {
        feature2 = new Feature2();
        feature2.getPlayers()[0].setTurn(true);
        gameSubject = new GameStateSubject(feature2.EST_ORDER, feature2.getPlayers(), feature2.getMarket());
        new GameStateObserver(gameSubject);

        System.setOut(new PrintStream(outContent));
        System.setErr(new PrintStream(errContent));
    }

    // test set players
    @Test
    public void testSetPlayers() {
        Player[] players = feature2.getPlayers();
        gameSubject.setPlayers(players);
        assertThat(gameSubject.getPlayers(), is(players));
    }

    // test set market
    @Test
    public void testSetMarket() {
        Map<Establishment, Integer> market = feature2.getMarket();
        gameSubject.setMarket(market);
        assertThat(gameSubject.getMarket(), is(market));
    }

    // test set EST_ORDER
    @Test
    public void testSetEstOrder() {
        Establishment[] est = feature2.EST_ORDER;
        gameSubject.setEstOrder(est);
        assertThat(gameSubject.getEstOrder(), is(est));
    }

    @Test
    public void testNotifyObservers() {
        Player[] players = feature2.getPlayers();
        gameSubject.setPlayers(players);
        Map<Establishment, Integer> market = feature2.getMarket();
        gameSubject.setMarket(market);
        Establishment[] est = feature2.EST_ORDER;
        gameSubject.setEstOrder(est);
        gameSubject.notifyObservers();
        assertThat(outContent.toString(), containsString("******************************************\n" +
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


    @After
    public void restoreStreams() {
        System.setOut(originalOut);
        System.setErr(originalErr);
    }
}