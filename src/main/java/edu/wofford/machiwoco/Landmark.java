package edu.wofford.machiwoco;

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

    @Override
    public String toString() {
        // need to replace with string format method

        return ".-----------------------.\n"+
                "| <" + color_ab + ">   LANDMARK    {" +
                icon_ab + "} |" +
                ", name='" + name + '\'' +
                ", cost=" + cost +
                ", description='" + description + '\'' +
                '}';
    }

    public static void main(String[] args) {
        Landmark l = new Landmark();
        System.out.println(l.toString());
    }
}