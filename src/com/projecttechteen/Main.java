package com.projecttechteen;

public class Main {

    public static void main(String[] args) {

	    // initialize conditions needed to start game
        TicTacToe game = new TicTacToe();
        game.startGame();

        // game start game logic
        boolean gameCompleted = game.isCompleted();
        while (!gameCompleted) {
            game.play();
            gameCompleted = game.isCompleted();
        }
    }
}
