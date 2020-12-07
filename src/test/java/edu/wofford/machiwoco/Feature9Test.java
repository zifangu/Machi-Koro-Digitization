package edu.wofford.machiwoco;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.*;

import org.hamcrest.Matchers;
import org.junit.*;


import static org.hamcrest.Matchers.greaterThanOrEqualTo;
import static org.hamcrest.Matchers.lessThanOrEqualTo;
import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;

import static org.hamcrest.Matchers.greaterThanOrEqualTo;
import static org.hamcrest.Matchers.lessThanOrEqualTo;

public class Feature9Test {
    Feature9 feature9;
    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    private final ByteArrayOutputStream errContent = new ByteArrayOutputStream();
    private final PrintStream originalOut = System.out;
    private final PrintStream originalErr = System.err;

    @Before
    public void before() {
        feature9 = new Feature9(0);
        feature9.player1.setTurn(true);
        System.setOut(new PrintStream(outContent));
        System.setErr(new PrintStream(errContent));
    }

    @Test
    public void testTrainLogic() {
        feature9.player1.setTurn(false);
        feature9.player2.setTurn(true);
        feature9.player2.setCoinCount(4);
        feature9.player2.getEstOwned().put(feature9.orchard, 3);
        feature9.aiLogic();
        assertThat(feature9.player2.getLandmarks()[0].is_constructed, is(true));
    }

    @Test
    public void testTrainLogic2() {
        feature9.player1.setTurn(false);
        feature9.player2.setTurn(true);
        feature9.player2.setCoinCount(1);
        feature9.player2.getEstOwned().put(feature9.orchard, 3);
        feature9.makeMove();
        assertThat(feature9.player2.getLandmarks()[0].is_constructed, is(false));
    }

    @Test
    public void testRanch() {
        feature9.player1.setTurn(false);
        feature9.player2.setTurn(true);
        if (!feature9.market.containsKey(feature9.getRanch())) {feature9.market.put(feature9.ranch, 1);}
        feature9.aiLogic();
        assertThat(feature9.player2.getEstOwned().get(feature9.getRanch()), is(1));
    }

    @Test
    public void testForest() {
        feature9.player1.setTurn(false);
        feature9.player2.setTurn(true);
        feature9.market.remove(feature9.getRanch());
        if (!feature9.market.containsKey(feature9.getForest())) {feature9.market.put(feature9.forest, 1);}
        feature9.aiLogic();
        assertThat(feature9.player2.getEstOwned().get(feature9.forest), is(1));
    }

    @Test
    public void testWheat() {
        feature9.player1.setTurn(false);
        feature9.player2.setTurn(true);
        feature9.market.remove(feature9.getRanch());
        feature9.market.remove(feature9.getForest());

        if (!feature9.market.containsKey(feature9.getWheat())) {feature9.market.put(feature9.wheat, 1);}
        feature9.makeMove();
        assertThat(feature9.player2.getEstOwned().get(feature9.wheat), is(2));
    }

    @Test
    public void testConvenience() {
        feature9.player1.setTurn(false);
        feature9.player2.setTurn(true);
        feature9.market.remove(feature9.getRanch());
        feature9.market.remove(feature9.getForest());
        feature9.market.remove(feature9.getWheat());
        if (!feature9.market.containsKey(feature9.getConvenience())) {feature9.market.put(feature9.convenience, 1);}
        feature9.makeMove();
        assertThat(feature9.player2.getEstOwned().get(feature9.convenience), is(1));
    }

    @Test
    public void testCafe() {
        feature9.player1.setTurn(false);
        feature9.player2.setTurn(true);
        feature9.market.remove(feature9.getRanch());
        feature9.market.remove(feature9.getForest());
        feature9.market.remove(feature9.getWheat());
        feature9.market.remove(feature9.getConvenience());
        if (!feature9.market.containsKey(feature9.getCafe())) {feature9.market.put(feature9.cafe, 1);}
        feature9.makeMove();
        assertThat(feature9.player2.getEstOwned().get(feature9.cafe), is(1));
    }

    @Test
    public void testBuy() {
        feature9.player1.setTurn(false);
        feature9.player2.setTurn(true);
        feature9.market.remove(feature9.getRanch());
        feature9.market.remove(feature9.getForest());
        feature9.market.remove(feature9.getWheat());
        feature9.market.remove(feature9.getConvenience());
        feature9.market.remove(feature9.getCafe());

        feature9.makeMove();
        assertThat(feature9.player2.getEstOwned().get(feature9.bakery), Matchers.either(Matchers.is(1)).or(Matchers.is(2)));
    }


    @Test
    public void testLandMarkPurchase() {
        feature9.player1.setTurn(false);
        feature9.player2.setTurn(true);
        feature9.player2.getLandmarks()[0].setIs_constructed(true);
        feature9.player2.setCoinCount(69);
        feature9.aiLogic();
        assertThat(feature9.player2.getLandmarks()[3].is_constructed, is(true));
    }

    @Test
    public void testFurniture() {
        feature9.player1.setTurn(false);
        feature9.player2.setTurn(true);
        feature9.player2.getLandmarks()[0].setIs_constructed(true);
        feature9.player2.setCoinCount(9);
        feature9.player2.getEstOwned().put(feature9.forest, 3);
        if (!feature9.market.containsKey(feature9.furnitureFactory)) {feature9.market.put(feature9.furnitureFactory, 1);}
        feature9.makeMove();
        assertThat(feature9.player2.getEstOwned().get(feature9.furnitureFactory), is(1));
    }




}
