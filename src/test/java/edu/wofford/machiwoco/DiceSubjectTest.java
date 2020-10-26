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

public class DiceSubjectTest {
    DiceSubject diceSubject;
    Feature2 feature2;
    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    private final ByteArrayOutputStream errContent = new ByteArrayOutputStream();
    private final PrintStream originalOut = System.out;
    private final PrintStream originalErr = System.err;

    @Before
    public void before() {
        feature2 = new Feature2();
        feature2.getPlayers()[0].setTurn(true);
        diceSubject = new DiceSubject(feature2.getCurrentPlayer(), feature2.getPlayers(), 0);
        new DiceObserver(diceSubject);
        new ActivationObserver(diceSubject);

        System.setOut(new PrintStream(outContent));
        System.setErr(new PrintStream(errContent));
    }

    @Test
    public void testDiceRoll2() {
        diceSubject.setDice(2);
        diceSubject.notifyObservers();
        assertThat(outContent.toString(), containsString("Player 1 rolled [2] = 2."));
    }

    @Test
    public void testDiceRollActivation() {
        // give player 2 a ranch
        Map<Establishment,Integer> map = new HashMap<>();
        map.put(feature2.getRanch(), 1);
        feature2.getPlayer2().setEstOwned(map);

//        set the active player to be player 2
        diceSubject.setActivePlayer(feature2.getPlayer2());
        diceSubject.setPlayers(feature2.getPlayers());
        diceSubject.setDice(2);
        diceSubject.notifyObservers();
        assertThat(outContent.toString(), containsString("Ranch activated for Player 2"));
    }

    @After
    public void restoreStreams() {
        System.setOut(originalOut);
        System.setErr(originalErr);
    }
}
