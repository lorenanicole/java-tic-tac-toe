package com.projecttechteen;

/**
 * Created by lorenamesa on 3/27/15.
 */
public class Player {
    String name;
    String symbol;
    int playerNumber;

    public Player(String name, String symbol, int playerNumber) {
        this.name = name;
        this.symbol = symbol;
        this.playerNumber = playerNumber;
    }

    public String getSymbol() {
        return this.symbol;
    }

    public String getName() {
        return this.name;
    }

    public int getPlayerNumber() {
        return this.playerNumber;
    }
}
