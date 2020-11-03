package edu.wofford.machiwoco;

import java.util.*;

public interface GameListener {
    void diceRolled (int dice1, int dice2, Player p);
    void diceActivation (int dice, List<Player> players);
}
