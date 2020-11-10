package edu.wofford.machiwoco;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.*;
import org.junit.*;

import static org.hamcrest.Matchers.greaterThanOrEqualTo;
import static org.hamcrest.Matchers.lessThanOrEqualTo;
import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;

import static org.hamcrest.Matchers.greaterThanOrEqualTo;
import static org.hamcrest.Matchers.lessThanOrEqualTo;

public class Feature5Test {
    Feature5 feature5;
    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    private final ByteArrayOutputStream errContent = new ByteArrayOutputStream();
    private final PrintStream originalOut = System.out;
    private final PrintStream originalErr = System.err;

    @Before
    public void before() {
        feature5 = new Feature5(3);
        System.setOut(new PrintStream(outContent));
        System.setErr(new PrintStream(errContent));
    }


    @Test
    public void testShoppingMall() {
        feature5.getPlayer1().getLandmarks()[2].setIs_constructed(true);
        feature5.getPlayer1().setTurn(true);
        feature5.getPlayer1().getEstOwned().put(feature5.getBakery(), 2);
        feature5.getPlayer1().getActivationNumbers(2, true);
        assertThat(feature5.getPlayer1().getCoinCount(), is(7));
    }


    @After
    public void restoreStreams() {
        System.setOut(originalOut);
        System.setErr(originalErr);
    }

}