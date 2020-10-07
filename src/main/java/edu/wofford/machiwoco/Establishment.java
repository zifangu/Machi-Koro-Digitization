package edu.wofford.machiwoco;

//      "name": "Wheat Field",
//            "cost": 1,
//            "color": "blue", green purple red
//            "icon": "wheat", cow bread
//            "activation": "1",
//            "effect": {
//                "type": "receive", "exchange"
//                "target": "bank", "active" "others" "choice" "exchange"
//                "amount": 1,
//                "modifierType": "none", "icon"
//                "modifier": "none"  wheat gear cow
//
public class Establishment extends Card {
//
    public Establishment() {
       super();
        System.out.println("Team Beast");
    }
    protected boolean is_constructed;
    protected Color color;
    protected Icon icon;

    @Override
    public String toString() {
        return "Establishment{" +
                "is_constructed=" + is_constructed +
                ", color=" + color +
                ", icon=" + icon +
                '}';
    }
}