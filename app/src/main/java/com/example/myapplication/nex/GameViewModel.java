package com.example.myapplication.nex;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.ViewModel;
import android.databinding.ObservableArrayMap;

import com.example.myapplication.ob.Cell;
import com.example.myapplication.ob.Game;
import com.example.myapplication.ob.Player;

public class GameViewModel extends ViewModel {

    public ObservableArrayMap<String, String> cells;
    private Game game;

    public void init(String player1, String player2) {
        game = new Game();
        cells = new ObservableArrayMap<>();
    }

    public void onClickedCellAt(int row, int column) {
        if (game.getCells()[row][column] == null) {
            game.getCells()[row][column] = new Cell(game.getCurrPlayer());
            cells.put(stringFromNumbers(row, column), game.getCurrPlayer().getValue());
            if (game.hasGameEnded())
                game.reset();
            else
                game.switchPlayer();
        }
    }

    public LiveData<Player> getWinner() {
        return game.getWinner();
    }


    public static String stringFromNumbers(int... numbers) {
        StringBuilder sNumbers = new StringBuilder();
        for (int number : numbers)
            sNumbers.append(number);
        return sNumbers.toString();
    }
}
