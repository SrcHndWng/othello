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
                Terminal.view(board);
                Terminal.requestInput(player.getName());

                String key = scanner.nextLine();
                Move move = new Move(key);
                if(move.isEnd()) {
                    break;
                }
                if(!move.isValidAddress()) {
                    Terminal.printInvalidMessage(move);
                    continue;
                }
                Terminal.printInput(move);
                board.input(player, move);
                player.change();
            }
        }

        Terminal.bye();
    }
}
