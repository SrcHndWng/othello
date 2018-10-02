package com.example.othello;

public class Player {
    private Stone inputStone;

    private enum Stone{
        BLACK(1),
        WHITE(2);

        private final int id;

        private Stone(final int id) {
            this.id = id;
        }
    };

    public void setFirst() {
        inputStone = Stone.BLACK;
    }

    public String getName() {
        return inputStone.toString();
    }

    public void change() {
        if(inputStone == Stone.BLACK) {
            inputStone = Stone.WHITE;
        }else {
            inputStone = Stone.BLACK;
        }
    }
}
