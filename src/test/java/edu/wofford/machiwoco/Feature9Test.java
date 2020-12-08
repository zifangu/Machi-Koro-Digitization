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

    @Test
    public void testGetIndexColor() {
        feature9.player1.setTurn(false);
        feature9.player2.setTurn(true);
        ArrayList<Establishment> e = new ArrayList<>();
        e.add(feature9.ranch);
        e.add(feature9.mine);
        e.add(feature9.stadium);
        e.add(feature9.orchard);
        assertThat(feature9.getIndexColorAB(e, Card.Color_ab.P), is(3));
    }
    @Test
    public void testGetIndexColorLast() {
        feature9.player1.setTurn(false);
        feature9.player2.setTurn(true);
        ArrayList<Establishment> e = new ArrayList<>();
        e.add(feature9.ranch);
        e.add(feature9.mine);
        e.add(feature9.orchard);
        e.add(feature9.stadium);
        assertThat(feature9.getIndexColorAB(e, Card.Color_ab.P), is(4));
    }
    @Test
    public void testGetIndexColorFirst() {
        feature9.player1.setTurn(false);
        feature9.player2.setTurn(true);
        ArrayList<Establishment> e = new ArrayList<>();
        e.add(feature9.stadium);
        e.add(feature9.ranch);
        e.add(feature9.mine);
        e.add(feature9.orchard);
        assertThat(feature9.getIndexColorAB(e, Card.Color_ab.P), is(1));
    }
    @Test
    public void testGetIndexColorNonexistent() {
        feature9.player1.setTurn(false);
        feature9.player2.setTurn(true);
        ArrayList<Establishment> e = new ArrayList<>();
        e.add(feature9.ranch);
        e.add(feature9.mine);
        e.add(feature9.orchard);
        assertThat(feature9.getIndexColorAB(e, Card.Color_ab.P), is(0));
    }

    @Test
    public void testNumOfLandmarksCurrentPlayerNone() {
        feature9.player1.setTurn(false);
        feature9.player2.setTurn(true);
        assertThat(feature9.currPlayerNumLandmarkConstructed(), is(0));
    }

    @Test
    public void testNumOfLandmarksCurrentPlayer2() {
        feature9.player1.setTurn(false);
        feature9.player2.setTurn(true);
        feature9.player2.getLandmarks()[0].setIs_constructed(true);
        feature9.player2.getLandmarks()[2].setIs_constructed(true);
        assertThat(feature9.currPlayerNumLandmarkConstructed(), is(2));
    }

    @Test
    public void testNumOfLandmarksCurrentPlayer5() {
        feature9.player1.setTurn(false);
        feature9.player2.setTurn(true);
        feature9.player2.getLandmarks()[0].setIs_constructed(true);
        feature9.player2.getLandmarks()[1].setIs_constructed(true);
        feature9.player2.getLandmarks()[2].setIs_constructed(true);
        feature9.player2.getLandmarks()[3].setIs_constructed(true);
        assertThat(feature9.currPlayerNumLandmarkConstructed(), is(4));
    }


//    @Test
//    public void testPurple() {
//        feature9.player1.setTurn(false);
//        feature9.player2.setTurn(true);
//        feature9.player2.getLandmarks()[0].setIs_constructed(true);
//        feature9.player2.setCoinCount(9);
//        feature9.market.remove(feature9.getCheeseFactory());
//        feature9.market.remove(feature9.getMine());
//        feature9.market.remove(feature9.getForest());
//        if (!feature9.market.containsKey(feature9.businessComplex)) {feature9.market.put(feature9.businessComplex, 1);}
//        if (!feature9.market.containsKey(feature9.stadium)) {feature9.market.put(feature9.stadium, 1);}
//        if (!feature9.market.containsKey(feature9.tvStation)) {feature9.market.put(feature9.tvStation, 1);}
//        feature9.makeMove();
//        assertThat(feature9.player2.getEstOwned().get(feature9.businessComplex), is(1));
//    }

    @Test
    public void testBlue() {
        feature9.player1.setTurn(false);
        feature9.player2.setTurn(true);
        feature9.player2.getLandmarks()[0].setIs_constructed(true);
        feature9.player2.setCoinCount(9);

        feature9.market.remove(feature9.getCheeseFactory());
        feature9.market.remove(feature9.getMine());
        feature9.market.remove(feature9.getForest());
        feature9.market.remove(feature9.stadium);
        feature9.market.remove(feature9.tvStation);
        feature9.market.remove(feature9.businessComplex);
        if (!feature9.market.containsKey(feature9.orchard)) {feature9.market.put(feature9.orchard, 1);}
        feature9.makeMove();
        assertThat(feature9.player2.getEstOwned().get(feature9.orchard), is(1));
    }

    @Test
    public void testRed() {
        feature9.player1.setTurn(false);
        feature9.player2.setTurn(true);
        feature9.player2.getLandmarks()[0].setIs_constructed(true);
        feature9.player2.setCoinCount(9);
        feature9.market.remove(feature9.getCheeseFactory());
        feature9.market.remove(feature9.getMine());
        feature9.market.remove(feature9.getForest());
        feature9.market.remove(feature9.stadium);
        feature9.market.remove(feature9.tvStation);
        feature9.market.remove(feature9.businessComplex);
        feature9.market.remove(feature9.ranch);
        feature9.market.remove(feature9.wheat);
        feature9.market.remove(feature9.mine);
        feature9.market.remove(feature9.orchard);
        if (!feature9.market.containsKey(feature9.familyRestaurant)) {feature9.market.put(feature9.familyRestaurant, 1);}
        feature9.makeMove();
        assertThat(feature9.player2.getEstOwned().get(feature9.familyRestaurant), is(1));
    }

    @Test
    public void testGreen() {
        feature9.player1.setTurn(false);
        feature9.player2.setTurn(true);
        feature9.player2.getLandmarks()[0].setIs_constructed(true);
        feature9.player2.setCoinCount(9);
        feature9.market.remove(feature9.getCheeseFactory());
        feature9.market.remove(feature9.getMine());
        feature9.market.remove(feature9.getForest());
        feature9.market.remove(feature9.stadium);
        feature9.market.remove(feature9.tvStation);
        feature9.market.remove(feature9.businessComplex);
        feature9.market.remove(feature9.ranch);
        feature9.market.remove(feature9.wheat);
        feature9.market.remove(feature9.mine);
        feature9.market.remove(feature9.orchard);
        feature9.market.remove(feature9.cafe);
        feature9.market.remove(feature9.familyRestaurant);
        if (!feature9.market.containsKey(feature9.farmersMarket)) {feature9.market.put(feature9.farmersMarket, 1);}
        feature9.makeMove();
        assertThat(feature9.player2.getEstOwned().get(feature9.farmersMarket), is(1));
    }

//    @Test
//    public void testPurple() {
//        feature9.player1.setTurn(false);
//        feature9.player2.setTurn(true);
//        feature9.player2.getLandmarks()[0].setIs_constructed(true);
//        feature9.player2.setCoinCount(9);
//
//        feature9.market.remove(feature9.getCheeseFactory());
//        feature9.market.remove(feature9.getMine());
//        feature9.market.remove(feature9.getForest());
//        feature9.makeMove();
//        assertThat(feature9.player2.getEstOwned().get(feature9.tvStation), is(1));
//    }



}
