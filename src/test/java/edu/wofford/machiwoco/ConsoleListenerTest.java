package edu.wofford.machiwoco;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.*;
import org.junit.*;

import static org.hamcrest.CoreMatchers.*;

public class ConsoleListenerTest {
    ConsoleListener c;
    TwoPlayersPhase1 t;
    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    private final ByteArrayOutputStream errContent = new ByteArrayOutputStream();
    private final PrintStream originalOut = System.out;
    private final PrintStream originalErr = System.err;

    @Before
    public void before() {
        c = new ConsoleListener();
        t = new TwoPlayersPhase1(false);

        // capturing system outputs
        System.setOut(new PrintStream(outContent));
        System.setErr(new PrintStream(errContent));
    }

    @Test
    public void testOneDiceRoll() {
        c.diceRolled(2, 0, t.getPlayer1());
        assertThat(outContent.toString(), containsString("Player 1 rolled [2] = 2."));
    }

    @Test
    public void testTwoDiceRoll() {
        c.diceRolled(2, 3, t.getPlayer2());
        assertThat(outContent.toString(), containsString("Player 2 rolled [2][3] = 5."));
    }

    @Test
    public void testRanchActivation() {
        Map<Establishment, Integer> est = new HashMap<>();
        est.put(t.getRanch(), 1);
        t.player2.setEstOwned(est);
        c.diceActivation(2,  t.getPlayers());
        assertThat(outContent.toString(), containsString("Ranch activated for Player 2"));
    }

    @Test
    public void testRanchAndBakeryActivation() {
        Establishment bakery = new Establishment("Bakery", 1, Card.Color.GREEN, Card.Color_ab.G, Card.Icon.BREAD, Card.Icon_ab.B,
                "|  Get 1 coin from the  |\n" +
                        "|         bank.         |\n" +
                        "|   (your turn only)    |\n",
                "2-3", "receive", "bank", 1, "none", "none");

        Map<Establishment, Integer> est = new HashMap<>();
        est.put(t.getRanch(), 1);
        est.put(bakery, 1);
        t.player2.setEstOwned(est);
        t.player2.setTurn(true);
        c.diceActivation(2,  t.getPlayers());
        assertThat(outContent.toString(), containsString("Ranch activated for Player 2"));
        assertThat(outContent.toString(), containsString("Bakery activated for Player 2"));
    }



    @After
    public void restoreStreams() {
        System.setOut(originalOut);
        System.setErr(originalErr);
    }
}
