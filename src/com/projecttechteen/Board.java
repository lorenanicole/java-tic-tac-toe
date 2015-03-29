package com.projecttechteen;

import com.sun.deploy.util.StringUtils;

import java.util.ArrayList;
import java.util.List;


public class Board {
    String[] board;

    public Board() {
        this.board = new String[9];
        for (int i=0; i < board.length; i++) {
            this.board[i] = null;
        }
    }

    public String drawBoard() {
        String output = "  * Board *\n";
        for (int i=0; i < board.length; i++) {
            if (board[i] == null) {
                output += " - |";
            } else {
                output += " " + board[i] + " |";
            }
            if (i % 3 == 2) {
                output += "\n";
            }
        }
        return output;
    }

    public void placeSymbol(int slotNum, String symbol) {
        System.out.println("here is slot num: " + slotNum + symbol);
        board[slotNum] = symbol;
    }

    public boolean horizontalTicTacToe(String symbol) {
        List<String> rowOne = new ArrayList<String>();
        List<String> rowTwo = new ArrayList<String>();
        List<String> rowThree = new ArrayList<String>();
        for (int i = 0; i < (board.length / 3); i++) {
            rowOne.add(board[i]);
            rowTwo.add(board[i + 3]);
            rowThree.add(board[i + 6]);
        }

        return StringUtils.join(rowOne, "").equals(symbol + symbol + symbol)
        || StringUtils.join(rowTwo, "").equals(symbol + symbol + symbol)
        || StringUtils.join(rowThree, "").equals(symbol + symbol + symbol);
    }

    public boolean verticalTicTacToe(String symbol) {
        List<String> colOne = new ArrayList<String>();
        List<String> colTwo = new ArrayList<String>();
        List<String> colThree = new ArrayList<String>();
        int oneCounter = 0;
        int twoCounter = 1;
        int threeCounter = 2;

        for (int i = 0; i < (board.length/ 3); i++) {
            colOne.add(board[oneCounter]);
            colTwo.add(board[twoCounter]);
            colThree.add(board[threeCounter]);
            oneCounter += 3;
            twoCounter += 3;
            threeCounter += 3;
        }

        return StringUtils.join(colOne, "").equals(symbol + symbol + symbol)
                || StringUtils.join(colTwo, "").equals(symbol + symbol + symbol)
                || StringUtils.join(colThree, "").equals(symbol + symbol + symbol);
    }

    public boolean diagonalTicTacToe(String symbol) {
        List<String> diagOne = new ArrayList<String>();
        List<String> diagTwo = new ArrayList<String>();

        int oneCounter = 0;
        int twoCounter = 2;

        for (int i = 0; i < (board.length/ 3); i++) {
            diagOne.add(board[oneCounter]);
            diagTwo.add(board[twoCounter]);

            oneCounter += 4;
            twoCounter += 2;
        }

        return StringUtils.join(diagOne, "").equals(symbol + symbol + symbol)
                || StringUtils.join(diagTwo, "").equals(symbol + symbol + symbol);
    }
}
