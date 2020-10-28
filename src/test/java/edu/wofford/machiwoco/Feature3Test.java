package edu.wofford.machiwoco;

import io.cucumber.java.jv.Lan;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

import java.io.*;
import java.util.*;

import org.hamcrest.Matchers;
import org.junit.*;
import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;

public class Feature3Test  {
    Feature3 feature3;

    GameStateSubject gameSubject;
    DiceSubject diceSubject;
    InputSubject inputSubject;

    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    private final ByteArrayOutputStream errContent = new ByteArrayOutputStream();
    private final PrintStream originalOut = System.out;
    private final PrintStream originalErr = System.err;

    @Before
    public void before() {
        feature3 = new Feature3();
        feature3.gameInit();
        gameSubject = feature3.getGameSubject();
        diceSubject = feature3.getDiceSubject();
        inputSubject = feature3.getInputSubject();

        System.setOut(new PrintStream(outContent));
        System.setErr(new PrintStream(errContent));
    }


    @Test
    public void testPlayerNum() {
        assertThat(feature3.getPlayers().length, is(2));
    }

    @Test
    public void testDiceRollActivation() {
        // give player 2 a wheat field
        Map<Establishment,Integer> map = new HashMap<>();
        map.put(feature3.getWheat(), 1);
        feature3.getPlayer2().setEstOwned(map);

//        set the active player to be player 2
        diceSubject.setActivePlayer(feature3.getPlayer2());
        diceSubject.setPlayers(feature3.getPlayers());
        diceSubject.setDice(1);
        diceSubject.notifyObservers();
        assertThat(outContent.toString(), containsString("Wheat Field activated for Player 2"));
    }
    @Test
    public void testRandomAIChoice() {
//        set the active player to be player 2
        feature3.getPlayers()[1].setTurn(true);
        Player p = feature3.getPlayer2();
        assertThat(p.random_ai_choice(2), Matchers.anyOf(Matchers.is(1),Matchers.is(2),Matchers.is(99)));
    }


    @After
    public void restoreStreams() {
        System.setOut(originalOut);
        System.setErr(originalErr);
    }

}
