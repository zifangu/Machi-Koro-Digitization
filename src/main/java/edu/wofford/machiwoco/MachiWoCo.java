package edu.wofford.machiwoco;


import org.apache.commons.lang3.StringUtils;

import java.util.HashMap;
import java.util.Map;
import java.util.ArrayList;
import java.util.*;

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
//        ********Run this for old versions of the code***********
            // m.testPlayer();
            // m.playGame();

//        ********Refactored code*************
            if (args.length > 1 && args[1].equals("--ai"))  {
                Feature3.main(args);
            } else {
                Feature2.main(args);
            }
        }


//        m.getPlayers()[1].setTurn(true);
//        m.getPlayers()[0].setTurn(true);

//            m.getCurrentPlayer().setCoinCount(69);
//        p.setCoinCount(3);

    }
}

