package edu.wofford.machiwoco;

import org.hamcrest.Matchers;
import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.ArrayList;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

public class Feature4Test {
    Feature4 feature4;
    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    private final ByteArrayOutputStream errContent = new ByteArrayOutputStream();
    private final PrintStream originalOut = System.out;
    private final PrintStream originalErr = System.err;

    @Before
    public void before() {
        feature4 = new Feature4(3);
        System.setOut(new PrintStream(outContent));
        System.setErr(new PrintStream(errContent));
    }

    @Test
    public void testPlayerNum() {
        assertThat(feature4.getPlayers().length, is(3));
    }

    @Test
    public void testTrainStationConstructed() {
        Player player = feature4.getPlayer1();
        player.setCoinCount(10);
        player.buyLandmark(player.getLandmarks()[1]);
        assertThat(feature4.isTrainStationConstructed(player), is(true));

        Player player2 = feature4.getPlayer2();
        //assertFalse(feature4.isTrainStationConstructed(player2));
    }

//    /**************CITY HALL TEST ERRORS************/
//    @Test
//    public void testCityHallInit() {
//        Player[] players = feature4.getPlayers();
//        for (Player p : players) {
//            assertThat(p.getLandmarks()[0].is_constructed, is(false));
//        }
//    }
//        /************************************************/


//    /**************TRAIN STATION TEST ERRORS************/
//        @Test
//    public void testTrainStationInit() {
//        Player[] players = feature4.getPlayers();
//        for (Player p : players) {
//            assertThat(p.getLandmarks()[1].is_constructed, is(false));
//        }
//    }
//        /************************************************/



    @Test
    public void testInitialMarket() {
        Establishment wheat = feature4.getWheat();
        assertThat(feature4.getMarket().get(wheat), is(6));

        Establishment ranch = feature4.getRanch();
        assertThat(feature4.getMarket().get(ranch), is(6));

        Establishment forest = feature4.getForest();
        assertThat(feature4.getMarket().get(forest), is(6));

//        /***********NEWLY ADDED EST ERRORS*************/

//        Establishment bakery = feature4.getBakery();
//        assertThat(feature4.getMarket().get(bakery), is(6));
//
//        Establishment convenience = feature4.getConvenience();
//        assertThat(feature4.getMarket().get(convenience), is(6));
//
//        Establishment mine = feature4.getMine();
//        assertThat(feature4.getMarket().get(mine), is(6));
//
//        Establishment orchard = feature4.getOrchard();
//        assertThat(feature4.getMarket().get(orchard), is(6));
//        /************************************************/
    }


    @Test
    public void testInitPlayerCoinCount() {
        Player[] players = feature4.getPlayers();
        int count = 0;
        for (Player p : players) {
            count += p.getCoinCount();
        }
        assertThat(count, is(3*players.length));
    }


//        /***********PLAYER ESTABLISHMENT ERROR*************/
//    @Test
//    public void testInitPlayerEst() {
//        Player[] players = feature4.getPlayers();
//        for (Player p : players) {
//            assertThat(p.getEstOwned().get(feature4.getWheat()), is(1));
//            assertThat(p.getEstOwned().get(feature4.getRanch()), is(1));
//        }
//    }
//        /************************************************/





//    @Test
//    public void testOneLandmark() {
//        Player p = feature4.getCurrentPlayer();
//        p.setCoinCount(20);
//        ArrayList<Landmark> lmk = feature4.getAffordableLandmarks(p);
//        Landmark l = lmk.get(0);
//        Landmark l2 = lmk.get(1);
//        p.buyLandmark(l);
//       // p.buyLandmark();
//        assertThat(feature4.allLandmarksConstructed(),is(false));
//
//        // active player should not lose their turn since game ended
//       // assertThat(feature4.getCurrentPlayer().isTurn(), is(true));
//    }
//
//    @Test
//    public void testTwoLandmarks() {
//        Player p = feature4.getCurrentPlayer();
//        p.setCoinCount(20);
//        ArrayList<Landmark> lmk = feature4.getAffordableLandmarks(p);
//        Landmark l = lmk.get(0);
//        Landmark l2 = lmk.get(1);
//        p.buyLandmark(l);
//        p.buyLandmark(l2);
//        assertThat(feature4.allLandmarksConstructed(),is(true));
//    }

    //

}
