package com.example.othello;

import java.util.ArrayList;

public class Terminal {
    public static void view(Board board) {
        System.out.println("- abcdefgh");
        for(int i = 0; i < board.getStones().size(); i++) {
            ArrayList<Stone> row = board.getStones().get(i);
            StringBuilder column = new StringBuilder();
            row.forEach(col -> {
                switch(col) {
                case NONE:
                    column.append("-");
                    break;
                case BLACK:
                    column.append("X");
                    break;
                case WHITE:
                    column.append("O");
                    break;
                default:
                    throw new IllegalStateException("Illegal Stone.");
                }
            });
            System.out.println(i + " " + column.toString());
        }
    }

    public static void requestInput(String player) {
        System.out.printf("You're %s. Input address >%n", player);
    }

    public static void bye() {
        System.out.println("Thanks for your playing. Bye!");
    }
}
