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

    private GameStateSubject gameSubject;
    private DiceSubject diceSubject;
    private InputSubject inputSubject;

    public Feature3() {
        super();
        playerInit();
    }

    /**
     * Second player is now an AI.
     */

    @Override
    protected void playerInit() {
        NUMBER_OF_PLAYERS = 2;
        player1 = new Player(startingEstablishments, startingLandmarks, 3,1, false);
        player2 = new Player(startingEstablishments2, startingLandmarks, 3,2, true);
        players = new Player[NUMBER_OF_PLAYERS];
        players[0] = player1;
        players[1] = player2;
    }



    protected void gameInit() {
        startGame();
        players[0].setTurn(true);

//        observer pattern
        gameSubject = new GameStateSubject(EST_ORDER, getPlayers(), getMarket());
        diceSubject = new DiceSubject(getCurrentPlayer(), getPlayers(), 0);
        inputSubject = new InputSubject(getCurrentPlayer(),getPlayers(), "x");

//      subscribe to subjects
        new DiceObserver(diceSubject);
        new ActivationObserver(diceSubject);
        new GameStateObserver(gameSubject);
        new InputObserver(inputSubject);
    }



    /**
     * The prompt is no longer applied to both players. AI choice are made randomly, and the prompt is redircted backed to player 1.
     */

    @Override
    public void playGame() {
        gameInit();

//        game driver
        while(!isGameOver()) {
            // (1) PRINT TURN
            printTurn(); //"Turn started for Player N."

            // (2) PRINT CURRENT GAME STATE

            gameSubject.notifyObservers();

            // (3) ROLL THE DICE AND THE CORRESPONDING ACTIVATIONS
            diceSubject.setActivePlayer(getCurrentPlayer());
            diceSubject.setDice(roll());
            diceSubject.notifyObservers();

            // (5) SHOW BUY MENU

            buyFinished = false;

//            Random AI Action
            if (getCurrentPlayer().isAi()) {
                int estSize = buyEstablishmentLogic().size();
                int lmkSize = getAffordableLandmarks(getCurrentPlayer()).size();

                // add last option of "99. Do Nothing" to AI
                int ai_choices = estSize + lmkSize + 1;
                int ai_input = getCurrentPlayer().random_ai_choice(ai_choices);

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

    public GameStateSubject getGameSubject() {
        return gameSubject;
    }


    public DiceSubject getDiceSubject() {
        return diceSubject;
    }


    public InputSubject getInputSubject() {
        return inputSubject;
    }

    public static void main(String[] args) {
        Feature3 feature3 = new Feature3();
        feature3.playGame();
    }
}
