package edu.wofford.machiwoco;

import org.apache.commons.lang3.StringUtils;

//"name": "Wheat Field",
////            "cost": 1,
////            "color": "blue",
////            "icon": "wheat",
public class Card {
     //Initial Enums For Color and Icons
     protected enum Color {BLUE, GREEN, PURPLE, RED, NONE};
     protected enum Color_ab {B, G, P, R, N};
     protected enum Icon {WHEAT, COW, BREAD, GEAR, FACTORY, FRUITO, CUPU, TOWER};
     protected enum Icon_ab{W, B, C, G, F, O, U, T};

     //Default Variables for Each Card
     protected String name;
     protected Color color;
     protected int cost;
     protected Icon icon;
     protected String description;
     protected Color_ab color_ab;
     protected  Icon_ab icon_ab;

     public Card(String name, int cost, Color color, Icon icon, String description) {
          this.name = name;
          this.color = color;
          this.cost = cost;
          this.icon = icon;
          this.description = description;
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

     //  return strings to be used in toString()
     public String generate_single_line(String s) {
          String str = StringUtils.center(s, 21, " ");
          return "| " + str + " |\n";
     }


}