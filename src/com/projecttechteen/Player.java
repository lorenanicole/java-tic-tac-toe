package com.projecttechteen;

import java.util.Scanner;

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

    public int selectPosition() {
        Scanner in = new Scanner(System.in);
        System.out.println("What slot do you want to put a value into?. Use number 0 to 8.");
        return in.nextInt();
    }

}
