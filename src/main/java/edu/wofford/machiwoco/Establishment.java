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

    protected String activation;
    private  String type;
    private  String target;
    private  int amount;
    private  String modifierType;
    private  String modifier;

    //
    public Establishment(String name,
                         int cost,
                         Color color,
                         Icon icon,
                         String activation) {
        super(name, color, cost, icon);
        this.activation = activation;
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
    public Establishment(String name,
                         int cost,
                         Color color,
                         Icon icon,
                         String activation,
                         String type,
                         String target,
                         int amount,
                         String modifierType,
                         String modifier) {
        super(name,color,cost, icon);
//       this.effect = effect;
        this.activation = activation;
        this.type = type;
        this.target = target;
        this.amount = amount;
        this.modifierType = modifierType;
        this.modifier = modifier;
    }
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
        Establishment e = new Establishment("Wheat Field", 2, Color.BLUE, Icon.WHEAT,
                "1", "receive", "bank", 3, "Test", "Test");
        System.out.println(e.getName());
        System.out.println(e.getCost());
        System.out.println(e.getColor());
        System.out.println(e.getIcon());
        System.out.println(e.getActivation());
        System.out.println(e.getType());
        System.out.println(e.getTarget());
        System.out.println(e.getAmount());
        System.out.println(e.getModifierType());
        System.out.println(e.getModifier());
    }
}