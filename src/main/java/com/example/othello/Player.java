package com.example.othello;

public class Player {
    private Stone inputStone;

    public void setFirst() {
        inputStone = Stone.BLACK;
    }

    public String getName() {
        return inputStone.toString();
    }

    public void change() {
        switch(inputStone) {
        case BLACK:
            inputStone = Stone.WHITE;
            break;
        case WHITE:
            inputStone = Stone.BLACK;
            break;
        default:
            throw new IllegalStateException("Stone uninitialized.");
        }
    }
}
