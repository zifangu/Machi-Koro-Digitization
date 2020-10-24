package edu.wofford.machiwoco;

public class ObserverPatternDemo {
    public static void main(String[] args) {
        Subject subject = new Subject();

        new DiceObserver(subject);

        // roll1
        int dice1 = (int) (Math.random() * 6 + 1);
        System.out.println("First Dice roll is " + dice1);
        subject.setState(dice1);
        System.out.println(" ");

        // roll2
        int dice2 = (int) (Math.random() * 6 + 1);
        System.out.println("First Dice roll is " + dice2);
        subject.setState(dice2);
    }
}
