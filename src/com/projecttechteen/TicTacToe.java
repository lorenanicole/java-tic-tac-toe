package com.projecttechteen;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

/**
 * Created by lorenamesa on 3/27/15.
 */
public class TicTacToe {
    Player player;
    ComputerPlayer computerPlayer;
    Board board;
    boolean completed;
    List<Integer> numsCalled;
    String winner;

    public TicTacToe() {
        this.board = new Board();
        this.completed = false;
        this.numsCalled = new ArrayList<Integer>();
    }

    public Boolean isCompleted() {
        return this.completed;
    }

    public void checkBoard() {
        if (this.board.horizontalTicTacToe(this.player.getSymbol())
                || this.board.verticalTicTacToe(this.player.getSymbol())
                || this.board.diagonalTicTacToe(this.player.getSymbol())) {
            this.completed = true;
            this.winner = this.player.getName();
        }
        if (this.board.horizontalTicTacToe(this.computerPlayer.getSymbol())
                || this.board.verticalTicTacToe(this.computerPlayer.getSymbol())
                || this.board.diagonalTicTacToe(this.computerPlayer.getSymbol())) {
            this.completed = true;
            this.winner = this.computerPlayer.getName();
        }
    }

    public void startGame() {
        Scanner in = new Scanner(System.in);
        System.out.println("Greetings! So you want to play tic tac toe eh?\nPlease enter your name: ");
        String playerName = in.nextLine();
        System.out.println("Now are you playing as an 'X' or an 'O'? Please type one: ");
        String playerSymbol = in.nextLine().toUpperCase();
        List<String> options = Arrays.asList("X", "O");

        boolean validOption = options.contains(playerSymbol);
        while (!validOption) {
            System.out.println("Try again, options are 'X' or 'O': ");
            playerSymbol = in.nextLine().toUpperCase();
            validOption =  options.contains(playerSymbol);
        }

        this.player = new Player(playerName, playerSymbol);
        for (String option : options) {
            if (!option.equals(playerSymbol)) {
                this.computerPlayer = new ComputerPlayer(option);
            }
        }
        System.out.println(board.drawBoard());
    }

    public void play() {
        Scanner in = new Scanner(System.in);

        System.out.println("What slot do you want to put a value into?. Use number 0 to 8.");
        Integer numSlot = in.nextInt();
        board.placeSymbol(numSlot, player.getSymbol());
        System.out.println(board.drawBoard());

        numsCalled.add(numSlot);
        int computerSlot = computerPlayer.selectSlot(numsCalled);
        numsCalled.add(computerSlot);
        board.placeSymbol(computerSlot, computerPlayer.getSymbol());
        System.out.println("Computer's turn ... ");

        try{
            Thread.sleep(500);}
        catch(InterruptedException e){
            System.out.println(e);
        }
        System.out.println(board.drawBoard());

        try{
            Thread.sleep(500);}
        catch(InterruptedException e){
            System.out.println(e);
        }

        checkBoard();
        if (isCompleted()) {
            System.out.println("Congrats - " + this.winner + " won!");
        }
    }
}
