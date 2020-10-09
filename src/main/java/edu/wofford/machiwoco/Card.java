package edu.wofford.machiwoco;
//"name": "Wheat Field",
////            "cost": 1,
////            "color": "blue",
////            "icon": "wheat",
public class Card {
     protected enum Color {BLUE, GREEN, PURPLE, RED, NONE};
     protected enum Color_ab {B, G, P, R, N};
     protected enum Icon {WHEAT, COW, BREAD, GEAR, FACTORY, FRUITO, CUPU, TOWER};
     protected enum Icon_ab{W, B, C, G, F, O, U, T};

     protected String description;
     protected String name;
     protected Color color;
     protected int cost;
     protected Icon icon;

     public Card(String name, Color color, int cost, Icon icon) {
          this.name = name;
          this.color = color;
          this.cost = cost;
          this.icon = icon;
     }

     public String getName() {
          return name;
     }

     public void setName(String name) {
          this.name = name;
     }

     public Color getColor() {
          return color;
     }

     public int getCost() {
          return cost;
     }

     public void setCost(int cost) {
          this.cost = cost;
     }

     public Icon getIcon() {
          return icon;
     }

     public String getDescription() {
          return description;
     }

     public void setDescription(String description) {
          this.description = description;
     }


}