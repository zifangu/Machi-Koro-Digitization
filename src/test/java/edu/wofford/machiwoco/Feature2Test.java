package edu.wofford.machiwoco;

import io.cucumber.java.jv.Lan;
import org.junit.Before;
import org.junit.Test;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

public class Feature2Test {
    MachiWoCo m;

    @Before
    public void before() {
        m = new MachiWoCo();
    }

    @Test
    public void testPlayerNum() {
        assertThat(m.getPlayers().length, is(2));
    }

    @Test
    public void testInitialMarket() {
        Establishment wheat = m.getWheat();
        assertThat(m.getMarket().get(wheat), is(6));

        Establishment ranch = m.getRanch();
        assertThat(m.getMarket().get(ranch), is(6));

        Establishment forest = m.getForest();
        assertThat(m.getMarket().get(forest), is(6));
    }

    @Test
    public void testPlayerStats() {
        Player[] players = m.getPlayers();
        int count = 0;
        for (Player p : players) {
            count += p.getCoinCount();
        }
        assertThat(count, is(4*players.length));
    }

    @Test
    public void testStaticMarketToString() {
        assertThat(m.generateStaticMarket(), is("******************************************\n" +
                "                  MARKET                  \n" +
                "------------------------------------------\n"));
    }



}