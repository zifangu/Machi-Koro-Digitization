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

    private String activation;
    private String type;
    private String target;
    private int amount;
    private String modifierType;
    private String modifier;

    //Default Constructor
    public Establishment(String name,
                         int cost,
                         Color color,
                         Icon icon,
                         String description,
                         String activation) {
        super(name, cost, color, icon, description);
        this.activation = activation;
    }

    //Robust Constructor (all Variable must be passed in)
    public Establishment(String name,
                         int cost,
                         Color color,
                         Icon icon,
                         String description,
                         String activation,
                         String type,
                         String target,
                         int amount,
                         String modifierType,
                         String modifier) {
        super(name,cost,color,icon,description);
//       this.effect = effect;
        this.activation = activation;
        this.type = type;
        this.target = target;
        this.amount = amount;
        this.modifierType = modifierType;
        this.modifier = modifier;
    }

    public String getActivation() {
        return activation;
    }

    public void setActivation(String activation) {
        this.activation = activation;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getTarget() {
        return target;
    }

    public void setTarget(String target) {
        this.target = target;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public String getModifierType() {
        return modifierType;
    }

    public void setModifierType(String modifierType) {
        this.modifierType = modifierType;
    }

    public String getModifier() {
        return modifier;
    }

    public void setModifier(String modifier) {
        this.modifier = modifier;
    }

    //

    protected boolean is_constructed;

    @Override
    public String toString() {
        return ".-----------------------.\n"+
                "| <" + color + ">   LANDMARK    {" +
                icon + "} |" +
                ", name='" + name + '\'' +
                ", cost=" + cost +
                ", description='" + description + '\'' +
                '}';
    }



    public static void main(String[] args) {
        Establishment wheat = new Establishment("Wheat Field", 1, Color.BLUE, Icon.WHEAT,
                "|  Get 1 coin from the  |\n" +
                          "|         bank.         |\n" +
                          "|    (anyone's turn)    |",
                "1", "receive", "bank", 1, "none", "none");


        Establishment ranch = new Establishment("Ranch", 1, Color.BLUE, Icon.COW,
                "|  Get 1 coin from the  |\n" +
                          "|         bank.         |\n" +
                          "|    (anyone's turn)    |",
                "2", "receive", "bank", 1, "none", "none");


        Establishment bakery = new Establishment("Bakery", 1, Color.GREEN, Icon.BREAD,
                "|  Get 1 coin from the  |\n" +
                          "|         bank.         |\n" +
                          "|   (your turn only)    |",
                "2-3", "receive", "bank", 1, "none", "none");


        Establishment cafe = new Establishment("Cafe", 2, Color.RED, Icon.CUPU,
                "| Take 1 coin from the  |\n" +
                          "|    active player.     |\n" +
                          "|   (opponent's turn)   |",
                "3", "receive", "active", 1, "none", "none");



        Establishment convenience = new Establishment("Convenience Store", 2, Color.GREEN, Icon.BREAD,
                "| Get 3 coins from the  |\n" +
                          "|         bank.         |\n" +
                          "|   (your turn only)    |",
                "4", "receive", "bank", 3, "none", "none");




        Establishment forest = new Establishment("Forest",
                3, Color.BLUE, Icon.GEAR,
                "|  Get 1 coin from the  |\n" +
                          "|         bank.         |\n" +
                          "|    (anyone's turn)    |",
                "5", "receive", "bank", 1, "none", "none");

        Establishment stadium = new Establishment("Stadium",
                6, Color.PURPLE, Icon.TOWER,
                "|   Take 2 coins from   |\n" +
                          "|    each opponent.     |\n" +
                          "|   (your turn only)    |",
                "6", "receive", "others", 2, "none", "none");

        Establishment tv = new Establishment("TV Station",
                7, Color.PURPLE, Icon.TOWER,
                "| Take 5 coins from an  |\n" +
                          "|       opponent.       |\n" +
                          "|   (your turn only)    |",
                "6", "receive", "choice", 5, "none", "none");

        Establishment business = new Establishment("Business Complex", 8, Color.PURPLE, Icon.TOWER,
                "| Exchange one of your  |\n" +
                          "|       non-tower       |\n" +
                          "| establishments for 1  |\n" +
                          "|   an opponent owns.   |\n" +
                          "|   (your turn only)    |",
                "6", "exchange", "choice", 0, "none", "none");

        Establishment cheese = new Establishment("Cheese Factory", 5, Color.GREEN, Icon.FACTORY,
                "| Get 3 coins from the  |\n" +
                          "|   bank for each {C}   |\n" +
                          "|   establishment you   |\n" +
                          "|         own.          |\n" +
                          "|   (your turn only)    |",
                "7", "receive", "bank", 3, "icon", "cow");

        Establishment furniture = new Establishment("Furniture Factory", 3, Color.GREEN, Icon.FACTORY,
                "| Get 3 coins from the  |\n" +
                          "|   bank for each {G}   |\n" +
                          "|   establishment you   |\n" +
                          "|         own.          |\n" +
                          "|   (your turn only)    |",
                "8", "receive", "bank", 3, "icon", "gear");

        Establishment mine = new Establishment("Mine", 6, Color.BLUE, Icon.GEAR,
                "| Get 5 coins from the  |\n" +
                          "|         bank.         |\n" +
                          "|    (anyone's turn)    |",
                "9", "receive", "bank", 5, "none", "none");

        Establishment restaurant = new Establishment("Family Restaurant", 3, Color.RED, Icon.CUPU,
                "| Take 2 coins from the |\n" +
                          "|    active player.     |\n" +
                          "|   (opponent's turn)   |",
                "9-10", "receive", "active", 2, "none", "none");

        Establishment orchard = new Establishment("Apple Orchard", 3, Color.BLUE, Icon.WHEAT,
                "| Get 3 coins from the  |\n" +
                          "|         bank.         |\n" +
                          "|    (anyone's turn)    |",
                "10", "receive", "bank", 3, "none", "none");

        Establishment market = new Establishment("Farmers Market", 2, Color.GREEN, Icon.FRUITO,
                "| Get 2 coins from the  |\n" +
                          "|   bank for each {W}   |\n" +
                          "|   establishment you   |\n" +
                          "|         own.          |\n" +
                          "|   (your turn only)    |",
                "11-12", "receive", "bank", 2, "icon", "wheat");

        System.out.println(wheat.getDescription() + "\n");
        System.out.println(ranch.getDescription()+ "\n");
        System.out.println(bakery.getDescription()+ "\n");
        System.out.println(cafe.getDescription()+ "\n");
        System.out.println(convenience.getDescription()+ "\n");
        System.out.println(forest.getDescription()+ "\n");
        System.out.println(stadium.getDescription()+ "\n");
        System.out.println(tv.getDescription()+ "\n");
        System.out.println(business.getDescription()+ "\n");
        System.out.println(cheese.getDescription()+ "\n");
        System.out.println(furniture.getDescription()+ "\n");
        System.out.println(mine.getDescription()+ "\n");
        System.out.println(restaurant.getDescription()+ "\n");
        System.out.println(orchard.getDescription()+ "\n");
        System.out.println(market.getDescription()+ "\n");


    }
}