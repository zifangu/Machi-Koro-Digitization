package edu.wofford.machiwoco;

import org.apache.commons.lang3.StringUtils;

import java.io.Console;
import java.util.HashMap;
import java.util.Map;
import java.util.ArrayList;
import java.util.*;
import java.util.stream.Stream;

/**
 * phase 1 of the game with 1(one) random AI
 *
 * @author Eric Craft
 * @author Ivan Gu
 * @author Bennett Joyce
 */


public class Feature3 extends Feature2 {

    public Feature3() {
        super();
        playerInit();
    }

    /**
     * Second player is now an AI.
     */

    @Override
    protected void playerInit() {
        player1 = new Player(startingEstablishments, startingLandmarks, 3,1, false);
        player2 = new Player(startingEstablishments2, startingLandmarks, 3,2, true);
        players = new Player[NUMBER_OF_PLAYERS];
        players[0] = player1;
        players[1] = player2;
    }



    /**
     * The prompt is no longer applied to both players. AI choice are made randomly, and the prompt is redircted backed to player 1.
     */

    @Override
    public void playGame() {
        startGame();
        players[0].setTurn(true);
        int count = 0;
        boolean ai;

        while(!isGameOver()) {
            // (1) PRINT TURN
            printTurn(); //"Turn started for Player N."

            // (2) PRINT CURRENT GAME STATE

            System.out.println(getCurrentGameState());

            // (3) ROLL THE DICE
            roll(); //"Player N rolled [3] = 3."

            // (4) ACTIVATE / ACTIONS
            activationTest();

            // (5) SHOW BUY MENU

            buyFinished = false;

//            Random AI Action
            if (getCurrentPlayer().isAi()) {
                System.out.println(getMenu());

                int estSize = buyEstablishmentLogic().size();
                int lmkSize = getAffordableLandmarks(getCurrentPlayer()).size();

                // add last option of "99. Do Nothing" to AI
                int ai_choices = estSize + lmkSize + 1;
                int ai_input = (int) (Math.random() * ai_choices + 1);
                if (ai_input == ai_choices) {
                    ai_input = 99;
                }
                System.out.println("AI CHOSE: " + ai_input);

                handleInput(Integer.toString(ai_input));

            } else {

                // human player input
                if(canAffordCard(getCurrentPlayer())) {
                    String s = "Player " + getTurn() + " would you like to purchase an \n" + "establishment or construct a landmark?" + " (" + getCurrentPlayer().getCoinCount() +
                            "\n" + "coins) \n" + "(To view details of an item, type 'view'  \n" +
                            "followed by the item number. For example, \n" +
                            "to view item 6, type 'view 6'.)           \n";

                    System.out.print(s);
                    System.out.print(getMenu()); //Ivan

                }
                while(!buyFinished && canAffordCard(getCurrentPlayer())) {
                    Console cnsl = System.console();
                    String input = cnsl.readLine(StringUtils.center("Choose a number to purchase or construct: ", 42, " "));
                    cnsl.flush();
                    buyFinished = handleInput(input);
                }
            }

            //(6) End Game
            if(!allLandmarksConstructed()) {
                endTurn();
            }
        }
    }



    public static void main(String[] args) {
        Feature3 feature3 = new Feature3();
        feature3.playGame();
    }
}
