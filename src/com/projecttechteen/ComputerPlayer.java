package com.projecttechteen;

import java.util.List;
import java.util.Random;

/**
 * Created by lorenamesa on 3/28/15.
 */
public class ComputerPlayer {
    String symbol;
    String name;

    public ComputerPlayer(String symbol) {
        this.symbol = symbol;
        this.name = "Hackinator 555555";
    }

    public String getSymbol() {
        return this.symbol;
    }

    public String getName() {
        return this.name;
    }

    public int selectSlot(List<Integer> numsCalled) {
        Random rand = new Random();
        int randomNum = rand.nextInt((9 - 0) + 1) + 0;
        boolean numCalled = numsCalled.contains(randomNum);

        while(numCalled) {
            randomNum = rand.nextInt((9 - 0) + 1) + 0;
            numCalled = numsCalled.contains(randomNum);
        }

        return randomNum;
    }
}
