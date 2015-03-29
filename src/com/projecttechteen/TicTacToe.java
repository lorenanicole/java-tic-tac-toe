package com.projecttechteen;

import java.util.*;

/**
 * Created by lorenamesa on 3/27/15.
 */
public class TicTacToe {
    Player player;
    ComputerPlayer computerPlayer;
    Board board;
    boolean completed;
    List<Integer> positionsMarked;
    String winner;
    GameState gameState;
    Player currentPlayer;

    public enum GameState {
        COMPUTER_WIN, PLAYER_WIN, DRAW, CONTINUE
    }

    public TicTacToe() {
        this.board = new Board();
        this.completed = false;
        this.positionsMarked = new ArrayList<Integer>();
        this.gameState = GameState.CONTINUE;
        this.winner = "";
        this.currentPlayer = null;
    }

    public void checkBoardForTicTacToe() {
        Player[] players = {computerPlayer, player};
        for (Player player : players) {
            if (board.horizontalTicTacToe(player.getSymbol())
                    || board.verticalTicTacToe(player.getSymbol())
                    || board.diagonalTicTacToe(player.getSymbol())) {
                completed = true;
                winner = player.getName();
            }
        }
    }

    public void startGame() {
        Scanner in = new Scanner(System.in);
        System.out.println("Greetings! So you want to play tic tac toe eh?\nPlease enter your name:");
        String playerName = in.nextLine();
        System.out.println("\nNow are you playing as an 'X' or an 'O'? Please type one:");
        String playerSymbol = in.nextLine().toUpperCase();
        List<String> options = Arrays.asList("X", "O");

        boolean validOption = options.contains(playerSymbol);
        while (!validOption) {
            System.out.println("\nTry again, options are 'X' or 'O':");
            playerSymbol = in.nextLine().toUpperCase();
            validOption =  options.contains(playerSymbol);
        }

        System.out.println("\nWould you like to go first or second? Please type 1 or 2.");
        Integer playerNumber = in.nextInt();
        player = new Player(playerName, playerSymbol, playerNumber);

        String computerSymbol = (playerSymbol.equals(options.get(0))) ? options.get(1) : options.get(0);
        int computerNumber = (playerNumber == 1) ? 2 : 1;

        computerPlayer = new ComputerPlayer(computerSymbol, computerNumber);

        setCurrentPlayer();
        try{
            System.out.println("\nGame starting ...\n");
            System.out.println(board.drawBoard());
            Thread.sleep(1000);}
        catch(InterruptedException e){
            System.out.println(e);
        }
        while (gameState == GameState.CONTINUE) {
            play();
            gameState = getGameState();
        }
    }

    private void setCurrentPlayer() {
        if (currentPlayer == null) {
            currentPlayer = (player.getPlayerNumber() == 1) ? player : computerPlayer;
        } else {
            currentPlayer = (currentPlayer == player) ? computerPlayer : player;
        }
    }

    public void play() {
        Integer position;
        position = currentPlayer.selectPosition();
        boolean positionFilled = positionsMarked.contains(position);

        while(positionFilled) {
            position = currentPlayer.selectPosition();
            positionFilled = positionsMarked.contains(position);
        }
        System.out.println("\n"+ currentPlayer.getName() + " selected " + position + ".");
        board.placeSymbol(position, currentPlayer.getSymbol());
        positionsMarked.add(position);
        setCurrentPlayer();
        checkBoardForTicTacToe();
        determineIfDraw();
        processGameState();

        try{
            System.out.println(board.drawBoard());
            Thread.sleep(1000);}
        catch(InterruptedException e){
            System.out.println(e);
        }

        if (gameState != GameState.CONTINUE) {
            System.out.println(displayFinalMessage());
        }
    }

    private void processGameState() {
        if (completed && winner.equals("")) {
            gameState = GameState.DRAW;
        } else if (winner.equals(computerPlayer.getName())) {
            gameState = GameState.COMPUTER_WIN;
        } else if (winner.equals(player.getName())) {
            gameState = GameState.PLAYER_WIN;
        } else {
            gameState = GameState.CONTINUE;
        }
    }

    private String displayFinalMessage() {
        String message = "";
        switch (gameState) {
            case COMPUTER_WIN:
                message = "Congrats " + computerPlayer.getName() + " won!";
                break;
            case PLAYER_WIN:
                message = "Congrats " + player.getName() + " won!";
                break;
            case DRAW:
                message = "Uhhhh oh no one won! Try again another time!";
                break;
        }
        return message;
    }

    private void determineIfDraw() {
        List<Integer> allOptions = Arrays.asList(0,1,2,3,4,5,6,7,8);
        if (positionsMarked.containsAll(allOptions)) {
            completed = true;
        }
    }

    public GameState getGameState() {
        return gameState;
    }

}
