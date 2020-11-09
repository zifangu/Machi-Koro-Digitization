package edu.wofford.machiwoco;


/**
 * This is a class built to represent the MachiWoco game.
 * 
 * @author Eric Craft
 * @author Ivan Gu
 * @author Bennett Joyce
 */

public class MachiWoCo {

    public static void main(String[] args) {

        if (args[0].equals("phase0")) {
            Establishment.main(args);
        } else if (args[0].equals("phase1")) {
            TwoPlayersPhase1.main(args,2);
        } else if (args[0].equals("phase2")) {
            Feature4.main(args);
        }

    }
}

