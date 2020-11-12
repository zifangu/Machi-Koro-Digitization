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
    Feature5 f5;

    @Before
    public void before() {
        a = new ActivationListener();
        t = new TwoPlayersPhase1(false,2);
         bakery = new Establishment("Bakery", 1, Card.Color.GREEN, Card.Color_ab.G, Card.Icon.BREAD, Card.Icon_ab.B,
                "|  Get 1 coin from the  |\n" +
                        "|         bank.         |\n" +
                        "|   (your turn only)    |\n",
                "2-3", "receive", "bank", 1, "none", "none");
        f5 = new Feature5(3);
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

    @Test
    public void testTakeMoneySufficient() {
        t.player1.setCoinCount(10);
        t.player2.setCoinCount(1);
        a.takeMoney(t.player1, t.player2, 4);
        assertThat(t.player1.getCoinCount(), is(6));
        assertThat(t.player2.getCoinCount(), is(5));
    }

    @Test
    public void testTakeMoneyNotSufficient() {
        t.player1.setCoinCount(10);
        t.player2.setCoinCount(1);
        a.takeMoney(t.player2, t.player1, 4);
        assertThat(t.player1.getCoinCount(), is(11));
        assertThat(t.player2.getCoinCount(), is(0));
    }

    @Test
    public void testNonActivePlayers() {
        t.player1.setTurn(false);
        t.player2.setTurn(true);
        assertThat(a.nonActivePlayers(t.players).size(), is(1));
        assertThat(a.nonActivePlayers(t.players).get(0), is(t.player1));
    }

    @Test
    public void testTwoNonActivePlayers() {
        f5.player1.setTurn(false);
        f5.player2.setTurn(true);
        assertThat(a.nonActivePlayers(f5.players).size(), is(2));
        assertThat(a.nonActivePlayers(f5.players).get(1), is(f5.player3));
        assertThat(a.nonActivePlayers(f5.players).get(0), is(f5.player1));
    }


    @Test
    public void testTwoNonActivePlayers3() {
        f5.player1.setTurn(false);
        f5.player3.setTurn(true);
        assertThat(a.nonActivePlayers(f5.players).size(), is(2));
        assertThat(a.nonActivePlayers(f5.players).get(1), is(f5.player1));
        assertThat(a.nonActivePlayers(f5.players).get(0), is(f5.player2));
    }




}
