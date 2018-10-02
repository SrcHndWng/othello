package com.example.othello;

public class Terminal {
    public static void view() {
        System.out.println("- abcdefgh");
        System.out.println("1 --------");
        System.out.println("2 --------");
        System.out.println("3 --------");
        System.out.println("4 ---XO---");
        System.out.println("5 ---OX---");
        System.out.println("6 --------");
        System.out.println("7 --------");
        System.out.println("8 --------");
    }

    public static void requestInput(String player) {
        System.out.printf("You're %s. Input address >%n", player);
    }

    public static void bye() {
        System.out.println("Thanks for your playing. Bye!");
    }
}
