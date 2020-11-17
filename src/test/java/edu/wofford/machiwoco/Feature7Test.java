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

public class Feature7Test {
    Feature7 feature7;
    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    private final ByteArrayOutputStream errContent = new ByteArrayOutputStream();
    private final PrintStream originalOut = System.out;
    private final PrintStream originalErr = System.err;

    @Before
    public void before() {
        feature7 = new Feature7(3);
        System.setOut(new PrintStream(outContent));
        System.setErr(new PrintStream(errContent));
    }

    @Test
    public void testGetStadium() {
        assertThat(feature7.getStadium(), is(feature7.stadium));
    }

    @Test
    public void testNumOfPurples() {
        assertThat(feature7.market.get(feature7.stadium), is(3));
        Feature7 newF7 = new Feature7(4);
        assertThat(newF7.market.get(newF7.stadium), is(4));
    }

    @Test
    public void testIsTSConstructedFalse() {
        feature7.getPlayer1().getLandmarks()[1].setIs_constructed(false);
        assertThat(feature7.getPlayer1().isShoppingMallConstructed(), is(false));
    }
}
