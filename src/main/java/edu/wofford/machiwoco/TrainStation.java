package edu.wofford.machiwoco;

public class TrainStation extends Landmark {
    public TrainStation() {
//        super();
//        train station landmark instantiation
        name = "Train Station";
        cost = 4;
        description = "You may roll 1 or 2 dice.";
        is_constructed = false;
        color_ab = Color_ab.N;
        icon_ab = Icon_ab.T;

    }
    public static void main(String[] args) {
        System.out.println("Hi");

        TrainStation t = new TrainStation();
        System.out.println(t.toString());
    }



}
