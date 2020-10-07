package edu.wofford.machiwoco;

public class TrainStation extends Landmark {
    public TrainStation() {
//        super();
//        train station landmark instantiation
        name = "Train Station";
        cost = 4;
        description = "You may roll 1 or 2 dice.";
        is_constructed = false;
        color = Color.NONE;

    }
    public static void main(String[] args) {
        TrainStation t = new TrainStation();
        System.out.println(t.toString());
    }



}
