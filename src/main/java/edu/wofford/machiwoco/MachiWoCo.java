package edu.wofford.machiwoco;




public class MachiWoCo {

    public static void main(String[] args) {

        System.out.println("Let's play Machi WoCo!");
        if (args[0].equals("landmark")) {
        Landmark.main(args);
        } else if (args[0].equals("establishment")) {
        Establishment.main((args));
        }
    }

}
