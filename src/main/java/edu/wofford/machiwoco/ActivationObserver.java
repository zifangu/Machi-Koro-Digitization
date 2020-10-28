package edu.wofford.machiwoco;

import java.util.Set;

public class ActivationObserver extends Observer {
    public ActivationObserver(DiceSubject diceSubject) {
        this.diceSubject = diceSubject;
        this.diceSubject.attach(this);
    }

    @Override
    public void update() {
        System.out.print(checkActivation());
    }

    private StringBuilder checkActivation() {
        int dice = diceSubject.getDice();
        int activation;
        Player[] players = diceSubject.getPlayers();
        StringBuilder s = new StringBuilder();
        for (Player player : players) {
            // this changes the actual coin amounts in the player bank
            player.getActivationNumbers(dice,player.isTurn());

            // this prints out the activations for user's information
            Set<Establishment> keys = player.getEstOwned().keySet();
            for(Establishment est: keys) {
                if(!est.getName().equals("Bakery")) {
                    activation = Integer.parseInt(est.getActivation());
                } else if(dice==2) {
                    activation = 2;
                } else {
                    activation = 3;
                }

                if (dice == activation) {
                    s.append(est.getName()).append(" activated for Player ").append(player.getPlayerNumber()).append("\n");
                }
            }
        }
//        if (dice == 1) {
//            return "Wheat Field Activated";
//        } else {
//            return "NOPE";
//        }
        return s;
    }
}
