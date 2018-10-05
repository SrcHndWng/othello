package com.example.othello;

import java.util.ArrayList;
import java.util.stream.Stream;

public class Terminal {
    public static void dispBoard(Board board) {
        System.out.println(createHeader());
        for(int i = 0; i < board.getStones().size(); i++) {
            ArrayList<Stone> row = board.getStones().get(i);
            StringBuilder column = new StringBuilder();
            row.forEach(col -> {
                column.append(getDispStone(col));
            });
            System.out.println(i + " " + column.toString());
        }
    }
    public static void dispCompleteMessage(StonesCount count) {
        System.out.printf("Game complete! black = %d, white = %d%n", count.getBlack(), count.getWhite());
    }
    public static void dispRequestInputMessage(Player player) {
        System.out.printf("You're %s [%s]. Input address >%n", player.getName(), getDispStone(player.getStone()));
    }
    public static void dispInputKey(Move move) {
        System.out.println("your input = " + move.getKey());
    }
    public static void dispInvalidMessage(Move move) {
        System.out.printf("your input = %s. Please input [0-7][a-h] like '2d'.%n", move.getKey());
    }
    public static void dispInvalidAddress(Move move) {
        System.out.printf("your input = %s. You can't change stones.%n", move.getKey());
    }
    public static void bye() {
        System.out.println("Thanks for your playing. Bye!");
    }
    private static String createHeader() {
        return "- " + Stream.of(ColumnNames.values()).collect(StringBuilder::new, StringBuilder::append, StringBuilder::append).toString();
    }
    private static String getDispStone(Stone stone) {
        switch(stone) {
        case NONE:
            return "-";
        case BLACK:
            return "X";
        case WHITE:
            return "O";
        }
        throw new IllegalStateException("Illegal Stone.");
    }
}
