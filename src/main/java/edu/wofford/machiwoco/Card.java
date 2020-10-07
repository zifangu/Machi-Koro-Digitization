package edu.wofford.machiwoco;
//"name": "Wheat Field",
////            "cost": 1,
////            "color": "blue",
////            "icon": "wheat",
public class Card {
     String name;
     int cost;
     public enum Color {BLUE, GREEN, PURPLE, RED, NONE};
     public enum Color_ab {B, G, P, R, N};
     enum Icon {WHEAT, COW, BREAD};
     enum Icon_ab{W, B, C, G, F, O, U, T};
     String description;

}