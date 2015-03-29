package com.projecttechteen;

import java.util.Random;

/**
 * Created by lorenamesa on 3/28/15.
 */
public class ComputerPlayer extends Player {
    String symbol;
    String name;
    int playerNumber;

    public ComputerPlayer(String symbol, int playerNumber) {
        super("Hackinator 555555", symbol, playerNumber);
    }

    public int selectPosition() {
        Random rand = new Random();
        int randomNum = rand.nextInt((8 - 0) + 1) + 0;
        return randomNum;
    }
}
