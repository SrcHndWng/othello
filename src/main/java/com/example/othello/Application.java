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
        Board board = new Board();

        Player player = new Player();
        player.setFirst();

        try(Scanner scanner = new Scanner(System.in)){
            while(true) {
                Terminal.view(board);
                Terminal.requestInput(player.getName());

                String key = scanner.nextLine();
                if(key.equals(Const.KEY_END)) {
                    break;
                }

                System.out.println("your input = " + key);
                board.input(player, key);
                player.change();
            }
        }

        Terminal.bye();
    }
}
