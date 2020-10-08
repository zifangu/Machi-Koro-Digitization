package edu.wofford.machiwoco;

import org.apache.commons.lang3.StringUtils;

import java.lang.*;
import java.io.*;

public class Landmark extends Card{
    public Landmark() {
        super();
        System.out.println("Team Beast");
    }

//    used by landmark sub classes to determine if a landmark is constructed or not
    protected boolean is_constructed;
    protected  Color color;
    protected Color_ab color_ab;
    protected  Icon_ab icon_ab;

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

    //  return strings to be used in toString()
    public String generateLine(String s) {
        String str = StringUtils.center(s, 21, " ");

        // description construction
        String des_cons;
        int len = s.length();
        System.out.println("slen: " + len);


        // determines how many new lines to print out the description
        int new_lines = len / 23 + 1;
        for (int i = 0; i < new_lines; i++) {
            System.out.println("len:" + new_lines);

//            this will be a multiline string
            if (new_lines > 1) {
                for (int j = 20; j > 0; j--) {
// TODO
                    int k = 0;
                }
            } else {
                System.out.println("ppp");
                return "| " + str + " |\n";
            }
        }
        return "";

        /*
        int len = s.length();
        int left_space = s.length() / 2;
        String left_align = "|%-" + Integer.toString(left_space / 2) + "s";
        String right_align = "%";
        */
    }
    @Override
    public String toString() {
        // need to replace with string format method


        return ".-----------------------.\n"+
                "| <" + color_ab + ">   LANDMARK    {" +
                icon_ab + "} |\n" +
                generateLine(name) +
                generateLine(" ") + name + '\'' +
                ", cost=" + cost +
                ", description='" + description + '\'' +
                '}';
    }

    public static void main(String[] args) {
        Landmark l = new Landmark();
        System.out.println(l.toString());
    }
}