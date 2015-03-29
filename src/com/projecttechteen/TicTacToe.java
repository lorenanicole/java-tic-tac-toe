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

        System.out.println("Would you like to go first or second? Please type 1 or 2.");
        Integer playerNumber = in.nextInt();
        this.player = new Player(playerName, playerSymbol, playerNumber);
        System.out.println("I am: " + player.getPlayerNumber());
        String computerSymbol = (playerSymbol.equals(options.get(0))) ? options.get(1) : options.get(0);
        int computerNumber = (playerNumber == 1) ? 2 : 1;

        computerPlayer = new ComputerPlayer(computerSymbol, computerNumber);

        setCurrentPlayer();
        System.out.println(board.drawBoard());
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
        Scanner in = new Scanner(System.in);
        Integer position;
        if (currentPlayer instanceof ComputerPlayer) {
            position = computerPlayer.selectPosition();
            boolean positionFilled = positionsMarked.contains(position);

            while(positionFilled) {
                position = computerPlayer.selectPosition();
                positionFilled = positionsMarked.contains(position);
            }
        } else {
            System.out.println("What slot do you want to put a value into?. Use number 0 to 8.");
            position = in.nextInt();
        }
        board.placeSymbol(position, currentPlayer.getSymbol());
        System.out.println(board.drawBoard());
        positionsMarked.add(position);
        setCurrentPlayer();
        checkBoard();
        determineBoardFilled();
        processGameState();

        if (gameState != GameState.CONTINUE) {
            System.out.println(displayFinalMessage());
        }

        try{
            System.out.println(board.drawBoard());
            Thread.sleep(1000);}
        catch(InterruptedException e){
            System.out.println(e);
        }
    }

    private void processGameState() {
        if (completed && winner == null) {
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

    private void determineBoardFilled() {
        List<Integer> allOptions = Arrays.asList(0,1,2,3,4,5,6,7,8);
        if (positionsMarked.containsAll(allOptions)) {
            this.completed = true;
        }
    }

    public GameState getGameState() {
        return gameState;
    }

}
