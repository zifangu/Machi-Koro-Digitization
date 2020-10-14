package edu.wofford.machiwoco;

import io.cucumber.java.jv.Lan;
import org.junit.Before;
import org.junit.Test;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import java.util.*;

public class MachiWoCoTest {

    /*@Test
    public void testGetAffordableLandmarksTEST() {
        MachiWoCo machiwoco = new MachiWoCo();

        Establishment wheat = new Establishment("Wheat Field", 1, Card.Color.BLUE, Card.Color_ab.B, Card.Icon.WHEAT, Card.Icon_ab.W,
                "|  Get 1 coin from the  |\n" +
                        "|         bank.         |\n" +
                        "|    (anyone's turn)    |\n",
                "1", "receive", "bank", 1, "none", "none");

        Map<Establishment,Integer> startingEstablishments = new HashMap<>();
        startingEstablishments.put(wheat,1);

        ArrayList<Landmark> landmarkArr = new ArrayList<Landmark>();
        Landmark city = new Landmark("City Hall", 7, Card.Color.NONE, Card.Color_ab.N, Card.Icon.TOWER, Card.Icon_ab.T,
                "|  This is a city hall  |\n");
        Landmark shop = new Landmark("Shop", 14, Card.Color.NONE, Card.Color_ab.N, Card.Icon.TOWER, Card.Icon_ab.T,
                "|    This is a shop     |\n");
        Landmark beach = new Landmark("Beach", 21, Card.Color.NONE, Card.Color_ab.N, Card.Icon.TOWER, Card.Icon_ab.T,
                "|    This is a beach    |\n");
        
        Landmark[] startingLandmarks = new Landmark[3];
        startingLandmarks[0] = city;
        startingLandmarks[1] = beach;
        startingLandmarks[2] = shop;
        landmarkArr.add(city);
        landmarkArr.add(shop);
        landmarkArr.add(beach);
        Player player1 = new Player(startingEstablishments, startingLandmarks, 25,1);
        assertThat(machiwoco.getAffordableLandmarks(player1), is(landmarkArr));
    }*/

    @Test
    public void testGetAffordableLandmarks() {
        MachiWoCo machiwoco = new MachiWoCo();

        Establishment wheat = new Establishment("Wheat Field", 1, Card.Color.BLUE, Card.Color_ab.B, Card.Icon.WHEAT, Card.Icon_ab.W,
                "|  Get 1 coin from the  |\n" +
                        "|         bank.         |\n" +
                        "|    (anyone's turn)    |\n",
                "1", "receive", "bank", 1, "none", "none");
            
        Map<Establishment,Integer> startingEstablishments = new HashMap<>();
        startingEstablishments.put(wheat,1);

        ArrayList<Landmark> landmarkArr = new ArrayList<Landmark>();
        Landmark city = new Landmark("City Hall", 7, Card.Color.NONE, Card.Color_ab.N, Card.Icon.TOWER, Card.Icon_ab.T,
                "|  This is a city hall  |\n");
        Landmark[] startingLandmarks = new Landmark[1];
        landmarkArr.add(city);
        startingLandmarks[0] = city;

        Player player1 = new Player(startingEstablishments, startingLandmarks, 7,1);
        assertThat(machiwoco.getAffordableLandmarks(player1), is(landmarkArr));

    }

    @Test
    public void testGetAffordableLandmarksFail() {
        MachiWoCo machiwoco = new MachiWoCo();

        Establishment wheat = new Establishment("Wheat Field", 1, Card.Color.BLUE, Card.Color_ab.B, Card.Icon.WHEAT, Card.Icon_ab.W,
                "|  Get 1 coin from the  |\n" +
                        "|         bank.         |\n" +
                        "|    (anyone's turn)    |\n",
                "1", "receive", "bank", 1, "none", "none");
            
        Map<Establishment,Integer> startingEstablishments = new HashMap<>();
        startingEstablishments.put(wheat,1);

        ArrayList<Landmark> landmarkArr = new ArrayList<Landmark>();
        Landmark city = new Landmark("City Hall", 7, Card.Color.NONE, Card.Color_ab.N, Card.Icon.TOWER, Card.Icon_ab.T,
                "|  This is a city hall  |\n");
        Landmark[] startingLandmarks = new Landmark[1];
        startingLandmarks[0] = city;

        Player player1 = new Player(startingEstablishments, startingLandmarks, 6,1);
        assertThat(machiwoco.getAffordableLandmarks(player1), is(landmarkArr));

    }


}