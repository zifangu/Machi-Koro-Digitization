package edu.wofford.machiwoco;

import org.apache.commons.lang3.StringUtils;

import java.lang.*;
import java.io.*;

public class Landmark extends Card{
    public Landmark(String name, int cost, Color color, Icon icon, String description) {
        super(name,cost, color, icon, description);
        //System.out.println("Team Beast");
    }

//    used by landmark sub classes to determine if a landmark is constructed or not
    protected boolean is_constructed;
    protected  Color color;


    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    public Color_ab getColor_ab() {
        return color_ab;
    }

    public void setColor_ab(Color_ab color_ab) {
        this.color_ab = color_ab;
    }

    public Icon_ab getIcon_ab() {
        return icon_ab;
    }

    public void setIcon_ab(Icon_ab icon_ab) {
        this.icon_ab = icon_ab;
    }



    // ************ Saved for later. Do NOT delete. **************** //
    public String[] generate_blocks(String s) {
        int len = s.length();
        System.out.println("slen: " + len);
        // determines how many lines to print out
        // if s is longer than 23 char
        int new_lines = len / 23 + 1;
        String[] stringsToProcess = new String[new_lines];
        int start_index = 0;
        int end_index;

        for (int i = 0; i < new_lines; i++) {
//            this will be a multiline string
            for (int j = 20; j > 0; j--) {
                if (i == new_lines - 1) {j = len;}
                if (s.charAt(j) == ' ') {
                    end_index = j;
                    stringsToProcess[i] = s.substring(start_index, end_index);
                    s = s.substring(end_index + 1, len);
                }
            }
        }
        return stringsToProcess;
    }

    public String isConstructed(boolean is_constructed) {
        if (is_constructed) {return "X";}
        return " ";
    }

    @Override
    public String toString() {
        // need to replace with string format method

        if (cost < 10) {
            return ".-----------------------.\n"+
                    "| <" + color_ab + ">   LANDMARK    {" + icon_ab + "} |\n" +
                    generate_single_line(name) +
                    generate_single_line(" ") +
                    description +
                    generate_single_line(" ") +
                    "| (" + cost + ")               [" + isConstructed(is_constructed) + "] |\n" +
                    "|_______________________|";
        } else {
            return ".-----------------------.\n"+
                    "| <" + color_ab + ">   LANDMARK    {" + icon_ab + "} |\n" +
                    generate_single_line(name) +
                    generate_single_line(" ") +
                    description +
                    generate_single_line(" ") +
                    "| (" + cost + ")              [" + isConstructed(is_constructed) + "] |\n" +
                    "|_______________________|";
        }
    }

    public static void main(String[] args) {
//        Landmark l = new Landmark("Test", 1, Card.Color.BLUE, Card.Icon.FRUITO, "Test");
//        System.out.println(l.toString());

        //**********Landmark train station creation************//
        Landmark trainStation;
        trainStation = new Landmark("Train Station", 4, Card.Color.NONE, Card.Icon.TOWER,
                "|  You may roll 1 or 2  |\n" +
                        "|         dice.         |\n");
        trainStation.color_ab = Card.Color_ab.N;
        trainStation.icon_ab = Card.Icon_ab.T;
        System.out.println(trainStation.toString());

        //**********Landmark shopping mall creation************//
        Landmark shoppingMall;
        shoppingMall = new Landmark("Shopping Mall", 10, Card.Color.NONE, Card.Icon.TOWER,
                "|   Your {U} and {B}    |\n" +
                        "|  establishments earn  |\n" + 
                            "|     +1 coin when      |\n" +
                                "|      activated.       |\n");
        shoppingMall.color_ab = Card.Color_ab.N;
        shoppingMall.icon_ab = Card.Icon_ab.T;
        System.out.println(shoppingMall.toString());

        //**********Landmark amusement park creation************//
        Landmark amusementPark;
        amusementPark = new Landmark("Amusement Park", 16, Card.Color.NONE, Card.Icon.TOWER,
                "| If you roll doubles,  |\n" +
                        "|   take another turn   |\n" + 
                            "|    after this one.    |\n");
        amusementPark.color_ab = Card.Color_ab.N;
        amusementPark.icon_ab = Card.Icon_ab.T;
        System.out.println(amusementPark.toString());

        //**********Landmark radio tower creation************//
        Landmark radioTower;
        radioTower = new Landmark("Radio Tower", 22, Card.Color.NONE, Card.Icon.TOWER,
                "|  Once per turn, you   |\n" +
                        "| may reroll the dice.  |\n");
        radioTower.color_ab = Card.Color_ab.N;
        radioTower.icon_ab = Card.Icon_ab.T;
        System.out.println(radioTower.toString());

        //**********Landmark city hall creation************//
        Landmark cityHall;
        cityHall = new Landmark("City Hall", 7, Card.Color.NONE, Card.Icon.TOWER,
                "|      You have a       |\n" +
                        "|      city hall.       |\n");
        cityHall.color_ab = Card.Color_ab.N;
        cityHall.icon_ab = Card.Icon_ab.T;
        System.out.println(cityHall.toString());

    }
}