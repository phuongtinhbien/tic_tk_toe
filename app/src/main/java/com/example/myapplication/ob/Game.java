package com.example.myapplication.ob;

import android.arch.lifecycle.MutableLiveData;
import android.util.Log;

public class Game {
    private static final String TAG = Game.class.getSimpleName();

    private static final int BOARD_SIZE = 3;

    private Player player1, player2;

    private Player currPlayer = player1;

    protected Cell[][] cells;


    public MutableLiveData<Player> winner = new MutableLiveData<>();

    public Game(){
        cells = new Cell[BOARD_SIZE][BOARD_SIZE];
        player1 = new Player("Player 1", "x");
        player2 = new Player("Player 2", "o");

        currPlayer = player1;
    }

    public Game(String name1, String name2){
        if (!(name1 != null && name1.length()>0 && name2 != null && name2.length()>0)){
            cells = new Cell[BOARD_SIZE][BOARD_SIZE];
            player1 = new Player("Player 1", "x");
            player2 = new Player("Player 2", "o");

            currPlayer = player1;
        }
        else{
            cells = new Cell[BOARD_SIZE][BOARD_SIZE];
            player1 = new Player(name1, "x");
            player2 = new Player(name2, "o");

            currPlayer = player1;
        }


    }

    public void switchPlayer (){
        currPlayer = currPlayer==player1? player2: player1;
    }

    public boolean hasGameEnded() {
        if (hasThreeSameHorizontalCells() || hasThreeSameVerticalCells() || hasThreeSameDiagonalCells()) {
            winner.setValue(currPlayer);
            return true;
        }

        if (isBoardFull()) {
            winner.setValue(null);
            return true;
        }

        return false;
    }


    public boolean hasThreeSameHorizontalCells() {
        try {
            for (int i = 0; i < BOARD_SIZE; i++)
                if (areEqual(cells[i][0], cells[i][1], cells[i][2]))
                    return true;

            return false;
        } catch (NullPointerException e) {
            Log.e(TAG, e.getMessage());
            return false;
        }
    }


    public boolean hasThreeSameVerticalCells() {
        try {
            for (int i = 0; i < BOARD_SIZE; i++)
                if (areEqual(cells[0][i], cells[1][i], cells[2][i]))
                    return true;
            return false;
        } catch (NullPointerException e) {
            Log.e(TAG, e.getMessage());
            return false;
        }
    }


    public boolean hasThreeSameDiagonalCells() {
        try {
            return areEqual(cells[0][0], cells[1][1], cells[2][2]) ||
                    areEqual(cells[0][2], cells[1][1], cells[2][0]);
        } catch (NullPointerException e) {
            Log.e(TAG, e.getMessage());
            return false;
        }
    }


    public boolean isBoardFull() {
        for (Cell[] row : cells)
            for (Cell ce : row)
                if (ce == null || ce.isEmpty())
                    return false;
        return true;
    }


    private boolean areEqual(Cell... cells) {
        if (cells == null || cells.length == 0)
            return false;

        for (Cell cell : cells)
            if (cell == null || cell.getPlayer().getValue() == null || cell.getPlayer().getValue().length() == 0)
                return false;

        Cell comparisonBase = cells[0];
        for (int i = 1; i < cells.length; i++)
            if (!comparisonBase.getPlayer().getValue().equals(cells[i].getPlayer().getValue()))
                return false;

        return true;
    }


    public void reset(){
        player1 = null;
        player2 = null;
        currPlayer = null;
        cells = null;
    }

    public Player getPlayer1() {
        return player1;
    }

    public void setPlayer1(Player player1) {
        this.player1 = player1;
    }

    public Player getPlayer2() {
        return player2;
    }

    public void setPlayer2(Player player2) {
        this.player2 = player2;
    }

    public Player getCurrPlayer() {
        return currPlayer;
    }

    public void setCurrPlayer(Player currPlayer) {
        this.currPlayer = currPlayer;
    }

    public Cell[][] getCells() {
        return cells;
    }

    public void setCells(Cell[][] cells) {
        this.cells = cells;
    }

    public MutableLiveData<Player> getWinner() {
        return winner;
    }

    public void setWinner(MutableLiveData<Player> winner) {
        this.winner = winner;
    }
}
