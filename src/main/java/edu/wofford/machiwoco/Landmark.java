package edu.wofford.machiwoco;

public class Landmark extends Card{
    public Landmark() {
        super();
        System.out.println("Team Beast");
    }

//    used by landmark sub classes to determine if a landmark is constructed or not
    protected boolean is_constructed;
    protected Color color;
}