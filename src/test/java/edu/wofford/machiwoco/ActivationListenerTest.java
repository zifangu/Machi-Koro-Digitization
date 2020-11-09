package edu.wofford.machiwoco;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.*;
import org.junit.*;

import static org.hamcrest.CoreMatchers.*;

public class ActivationListenerTest {
    ActivationListener a;
    TwoPlayersPhase1 t;
    Establishment bakery;

    @Before
    public void before() {
        a = new ActivationListener();
        t = new TwoPlayersPhase1(false,2);
         bakery = new Establishment("Bakery", 1, Card.Color.GREEN, Card.Color_ab.G, Card.Icon.BREAD, Card.Icon_ab.B,
                "|  Get 1 coin from the  |\n" +
                        "|         bank.         |\n" +
                        "|   (your turn only)    |\n",
                "2-3", "receive", "bank", 1, "none", "none");
    }

    @Test
    public void testWheatAct() {
        a.diceActivation(1, t.getPlayers());
        assertThat(t.player1.getCoinCount(), is(4));
        assertThat(t.player2.getCoinCount(), is(4));
    }

    @Test
    public void testRanchAct() {
        t.player2.getEstOwned().put(t.ranch, 1);
        a.diceActivation(2, t.getPlayers());
        assertThat(t.player1.getCoinCount(), is(3));
        assertThat(t.player2.getCoinCount(), is(4));
    }

    @Test
    public void testBakeryRanchAct() {
        t.player1.setTurn(false);
        t.player2.getEstOwned().put(t.ranch, 1);
        t.player2.getEstOwned().put(bakery, 1);
        t.player1.getEstOwned().put(bakery, 1);
        t.player2.setTurn(true);
        a.diceActivation(2, t.getPlayers());
        assertThat(t.player1.getCoinCount(), is(3));
        assertThat(t.player2.getCoinCount(), is(5));
    }



}
