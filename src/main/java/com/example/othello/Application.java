package com.example.othello;

import java.util.Scanner;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
public class Application {

    public static void main(String[] args) {
        try (ConfigurableApplicationContext ctx = SpringApplication.run(Application.class, args)) {
            Application app = ctx.getBean(Application.class);
            app.run();
        } catch (Exception e) {
            System.out.println("Application Error!");
            e.printStackTrace();
        }
    }

    private void run() throws Exception {
        Board board = Board.initialize();

        Player player = new Player();
        player.setFirst();

        try(Scanner scanner = new Scanner(System.in)){
            while(true) {
                Terminal.dispBoard(board);
                StonesCount count = board.getStonesCount();
                if(count.isComplete()) {
                    Terminal.dispCompleteMessage(count);
                    break;
                }
                Terminal.dispRequestInputMessage(player);

                String key = scanner.nextLine();
                Move move = new Move(key);
                if(move.isEnd()) {
                    break;
                }
                if(move.isSkip()) {
                    player.change();
                    continue;
                }
                if(!move.isValidAddress()) {
                    Terminal.dispInvalidMessage(move);
                    continue;
                }
                Terminal.dispInputKey(move);
                Boolean isSwapped = board.input(player, move);
                if(!isSwapped) {
                    Terminal.dispInvalidAddress(move);
                    continue;
                }
                player.change();
            }
        }

        Terminal.bye();
    }
}
