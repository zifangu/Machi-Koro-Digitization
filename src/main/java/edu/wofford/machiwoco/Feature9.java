package edu.wofford.machiwoco;

import java.util.Random;

/**
 * This is a class built to represent the Phase 6 version of Machi Koro with a Strategic AI.
 *
 * @author Eric Craft
 * @author Ivan Gu
 * @author Bennett Joyce
 */

public class Feature9 extends Feature8 {

    /**
     * MachiWoco constructor representing the Phase 6 version of the game with a Strategic AI.
     * @param numPlayers an integer representing the number of Players.
     */

    public Feature9(int numPlayers) {
        super(numPlayers);
    }

    /**
     *  If a player owns any card that has an activation value greater than 6, roll two die.
     * @return a boolean representing the decision to roll two die.
     */

    protected boolean aiRollTwoLogic() {
        for (Establishment e: getCurrentPlayer().getEstOwned().keySet()) {
            if (e.getName().equals("Bakery")) {
                continue;
            } else if (e.getName().equals("Family Restaurant") || e.getName().equals("Farmers Market")) {
                return true;
            } else if (Integer.parseInt(e.getActivation()) > 6 ) {
                return true;
            }
        }
        return false;
    }

    /**
     * Returns a boolean holding true if the Player rolls two die.
     * @return a boolean holding true if the Player rolls two die.
     */
    @Override
    protected boolean rollTwo() {
        if (getCurrentPlayer().getLandmarks()[0].is_constructed) {
            if (getCurrentPlayer().isAi()) {
                return aiRollTwoLogic();
            } else {
                return consoleListener.rollTwo(sc, getCurrentPlayer());
            }
        } else {
            return false;
        }
    }

    /**
     * If the AI's potential income is less than 2, reroll.
     * @return a boolean representing the decision to reroll the die.
     */
    protected boolean rerollAILogic() {
        int potential = getCurrentPlayer().peekActivation(dice1+dice2, true);
        return potential < 2;
    }

    /**
     * Conducts the logic associated with the Radio Tower.
     * @return a boolean holding true if the Player chooses to reroll.
     */

    @Override
    public boolean radioTowerLogic() {
        if (getCurrentPlayer().isAi()) {return rerollAILogic();}
        return consoleListener.playerChooseReroll(sc, getCurrentPlayer());
    }

    /**
     * Conducts a diceRoll and carries out any related activations.
     * @param rollTwo a boolean holding true if the roll includes two die.
     */

    @Override
    public void wayBetterRollDice(boolean rollTwo) {
        betterRollDice(rollTwo);
        if (getCurrentPlayer().isRadioTowerConstructed()) {
            consoleListener.diceRolled(dice1, dice2, getCurrentPlayer());
            boolean reroll = radioTowerLogic();
            if (reroll) {
                betterRollDice(rollTwo());
                System.out.println("Player " + getCurrentPlayer().getPlayerNumber() + " used a reroll.");
                actionsDiceRolled();
            } else {
                consoleListener.diceActivation(dice1+dice2, players);
                activationListener.diceActivation(dice1+dice2, players);
            }
        } else { actionsDiceRolled();}

        if ((dice1 + dice2) == 6) {
            if (getCurrentPlayer().isTVStationConstructed()) {TVStationLogic();}
            if (getCurrentPlayer().isBusinessComplexOwned()) {busComplexLogic();}
        }
    }

    /**
     * Contains all logic that the Strategic AI should follow when in position to purchase a card.
     * @return an integer representing the market selection for the AI.
     */

    protected int purchaseLogic() {
        if (!isTrainStationConstructed(getCurrentPlayer())) {
            int establishmentOwned = 0;
            for (Establishment est : buyEstablishmentLogic()) {
                establishmentOwned += getCurrentPlayer().getEstOwned().get(est);
            }

            if (establishmentOwned > 4) {
                // purchase train somehow
            } else if (buyEstablishmentLogic().contains(getRanch())) {
                // purchase ranch
            } else if (buyEstablishmentLogic().contains(getForest())) {
                // purchase ranch
            } else if (buyEstablishmentLogic().contains(getWheat())) {
                // purchase ranch
            } else if (buyEstablishmentLogic().contains(getConvenience())) {
                // purchase ranch
            } else if (buyEstablishmentLogic().contains(getCafe())) {
                // purchase ranch
            } else if (buyEstablishmentLogic().contains(getFamilyRestaurant())) {
                // purchase ranch

                // need to do any card logic.

            }
        }
        return 0;
    }

    /**
     * AI Logic for making a move
     */

    @Override
    protected void aiLogic() {
        if(canAffordCard(getCurrentPlayer())) {
//            System.out.println(getMenu());
            int estSize = buyEstablishmentLogic().size();
            int lmkSize = getAffordableLandmarks(getCurrentPlayer()).size();
            // add last option of "99. Do Nothing" to AI
            int ai_choices = estSize + lmkSize + 1;
            int ai_input = purchaseLogic();

//            System.out.println("AI CHOSE: " + ai_input);
            handleInput(Integer.toString(ai_input));
        } else {
            System.out.println("Player " + getTurn() + "did not have enough money to make \n" +
                    "improvements.");
        }
    }

    /**
     * Driver for making moves for either AI or Human
     */

    @Override
    public void makeMove() {
        buyFinished = false;
        if (getCurrentPlayer().isAi()) {
            aiLogic();
        } else {
            humanInput();
        }
    }

    /**
     * Play the MachiWoCo game in its entirety
     */

    @Override
    public void playGame() {
        gameInit();

        while (!isGameOver()) {
            // (1) print turn and (2) print current game state
            gameSubject.notifyObservers();

            // (3) ROLL THE DICE AND THE CORRESPONDING ACTIVATIONS
            wayBetterRollDice(rollTwo());

            // (4) either human or ai player make moves
            makeMove();

            // (5) check if Game has ended
            gameEnded();

            removeZeroesMarket();
            uniqueA = setDeck(DeckA, 5, uniqueA);
            uniqueB = setDeck(DeckB, 5, uniqueB);
            uniqueC = setDeck(DeckC, 2, uniqueC);

        }
    }


}
