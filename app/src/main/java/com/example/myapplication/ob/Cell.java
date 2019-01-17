package com.example.myapplication.ob;

public class Cell {
    private Player player;

    public Player getPlayer() {
        return player;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }

    public Cell() {
    }

    public Cell(Player player) {
        this.player = player;
    }

    public boolean isEmpty (){
        return player == null || player.getValue().length() ==0 || player.getValue().length() == 0 ? true: false;
    }
}
