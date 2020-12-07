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
    public void testLandMarkPurchase() {
        feature9.player1.setTurn(false);
        feature9.player2.setTurn(true);
        feature9.player2.getLandmarks()[0].setIs_constructed(true);
        feature9.player2.setCoinCount(69);
        feature9.aiLogic();
        assertThat(feature9.player2.getLandmarks()[3].is_constructed, is(true));
    }


}
