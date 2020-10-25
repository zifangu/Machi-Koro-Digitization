package edu.wofford.machiwoco;

import org.apache.commons.lang3.StringUtils;
import java.io.*;
import java.util.*;

public class gameStateObserver extends Observer{

    private gameStateSubject gameSubject;

    public gameStateObserver(gameStateSubject gameSubject){
        this.gameSubject = gameSubject;
        this.gameSubject.attach(this);
    }

    // Done
    protected String generate_pure_padding(String s) {
        return StringUtils.center("", 42, s) + "\n";
    }

    // Done
    protected String generatePlayerLine(Player p, int num, boolean active) {
        String player;
        if (active) {
            player = "Player " + num + "* [YOU]";
        } else {
            player = "Player " + num;
        }
        return StringUtils.center(player, 42, " ") + "\n";
    }

    // Done
    protected String generatePlayerCoin(Player p) {
        String account = "(" + p.getCoinCount() + " coins)";
        return StringUtils.center(account, 42, " ") + "\n";
    }

    // Done
    protected String generatePlayerEst(Player p) {
        Map<Establishment,Integer> estOwned = p.getEstOwned();
        StringBuilder s = new StringBuilder();
        for (Establishment e : gameSubject.getEstOrder()) {
            if (estOwned.containsKey(e)) {
                s.append(generateSingleMarketItem(e, estOwned.get(e)));
            }
        }
        return s + "";
    }

    /**
         * Generates a string displaying the given Landmark
         * @param l the Landmark instance to be displayed
         * @return a string displaying the given Landmark
         */

    protected String generateLandmark(Landmark l) {
        String construct = "[" + l.isConstructed(l.is_constructed) + "]";
        return StringUtils.rightPad(l.getName(), 18, " ") +
                " " + l.getColor_ab() + l.getIcon_ab() + " " +
                generateCost(l.getCost()) + " " +
                construct + "\n";

    }

    // Done
    protected StringBuilder generatePlayerLandMark(Player p) {
        StringBuilder s = new StringBuilder();
        for (Landmark l : p.getLandmarks()) {
            s.append(generateLandmark(l));
        }
        return s;
    }

    // Done
    protected String generatePlayer(Player p, int num, boolean active) {
        //        System.out.println("PLAYER: "+ active);
                    return generatePlayerLine(p, num, active) +
                            generate_pure_padding("-") +
                            generatePlayerCoin(p) +
                            generatePlayerEst(p) +
                            generate_pure_padding(".") +
                            generatePlayerLandMark(p) + "\n";
    }

    // Done
    protected String generateCost(int cost) {
        String act = "(" + cost + ")";
        return StringUtils.rightPad(act, 4, " ");
    }

    // Done
    protected String generateActivation(String s) {
        String act = "[" + s + "]";
        return StringUtils.rightPad(act, 7, " ");
    }

    // Done
    protected String generateSingleMarketItem(Establishment e, int count) {
        return StringUtils.rightPad(e.getName(), 18, " ") + " " +
                e.getColor_ab() + e.getIcon_ab() + " " +
                generateCost(e.getCost()) + " " +
                generateActivation(e.getActivation()) + " " +
                " #" + Integer.toString(count) + "\n";
    }

    // Done
    protected String generateStaticMarket() {
        return generate_pure_padding("*") +
                generate_title("MARKET") +
                generate_pure_padding("-");
    }

    // Done
    protected String generate_title(String s) {
        return StringUtils.center(s, 42, " ") + "\n";
    }

    // Done
    protected  String generateMarket() {
        StringBuilder s = new StringBuilder();
        for (Establishment e : gameSubject.getEstOrder()) {
            s.append(generateSingleMarketItem(e, gameSubject.getMarket().get(e)));
        }
//        System.out.print(s);
        return generateStaticMarket() + s + "\n";
    }

    // Sub out players
    public StringBuilder playerStringBuilder() {
        StringBuilder s = new StringBuilder();
        Player[] playersArr = gameSubject.getPlayers();
        for (int i = 0; i < playersArr.length; i++) {
            s.append(generatePlayer(playersArr[i], i + 1, playersArr[i].isTurn()));
        }
        return s;
    }

   @Override
    public void update() {
        System.out.println(generateMarket() + playerStringBuilder() + StringUtils.center("", 42, "*"));
    }
}