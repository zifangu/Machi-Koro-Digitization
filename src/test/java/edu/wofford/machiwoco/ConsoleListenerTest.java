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

    @After
    public void restoreStreams() {
        System.setOut(originalOut);
        System.setErr(originalErr);
    }
}
