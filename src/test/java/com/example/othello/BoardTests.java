package com.example.othello;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

//@RunWith(SpringRunner.class)
//@SpringBootTest
public class BoardTests {
    Board board;
    Player player;

    @Before
    public void doBefore() {
        board = new Board();
        player = new Player();
        player.setFirst();
        System.out.println("----- before board -----");
        Terminal.view(board);
        System.out.println("----- after board -----");
    }

    @Test
    public void firstInput() {
        board.input(player, "5d");
        Terminal.view(board);

        // 上方向に置き換え
        assertEquals(Stone.BLACK, board.getStones().get(5).get(3));
        assertEquals(Stone.BLACK, board.getStones().get(4).get(3));
        assertEquals(Stone.BLACK, board.getStones().get(3).get(3));
        // 隣の列は変更なし
        assertEquals(Stone.BLACK, board.getStones().get(4).get(4));
        assertEquals(Stone.WHITE, board.getStones().get(3).get(4));
    }
}
