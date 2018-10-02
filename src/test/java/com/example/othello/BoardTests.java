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
    }

    /**
     * 初回、上方向へ置き換え
     */
    @Test
    public void firstInputSwapUpper() {
        System.out.printf("----- %s -----%n", new Object(){}.getClass().getEnclosingMethod().getName());
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

    /**
     * 初回、下方向に置き換え
     */
    @Test
    public void firstInputSwapLower() {
        System.out.printf("----- %s -----%n", new Object(){}.getClass().getEnclosingMethod().getName());
        board.input(player, "2e");
        Terminal.view(board);

        // 下方向に置き換え
        assertEquals(Stone.BLACK, board.getStones().get(2).get(4));
        assertEquals(Stone.BLACK, board.getStones().get(3).get(4));
        assertEquals(Stone.BLACK, board.getStones().get(4).get(4));
        // 隣の列は変更なし
        assertEquals(Stone.BLACK, board.getStones().get(3).get(3));
        assertEquals(Stone.WHITE, board.getStones().get(4).get(3));
    }

    /**
     * 初回、右方向に置き換え
     */
    @Test
    public void firstInputSwapRight() {
        System.out.printf("----- %s -----%n", new Object(){}.getClass().getEnclosingMethod().getName());
        board.input(player, "4c");
        Terminal.view(board);

        // 右方向に置き換え
        assertEquals(Stone.BLACK, board.getStones().get(4).get(2));
        assertEquals(Stone.BLACK, board.getStones().get(4).get(3));
        assertEquals(Stone.BLACK, board.getStones().get(4).get(4));
        // 隣の行は変更なし
        assertEquals(Stone.BLACK, board.getStones().get(3).get(3));
        assertEquals(Stone.WHITE, board.getStones().get(3).get(4));
    }

    /**
     * 初回、左方向に置き換え
     */
    @Test
    public void firstInputSwapLeft() {
        System.out.printf("----- %s -----%n", new Object(){}.getClass().getEnclosingMethod().getName());
        board.input(player, "3f");
        Terminal.view(board);

        // 左方向に置き換え
        assertEquals(Stone.BLACK, board.getStones().get(3).get(3));
        assertEquals(Stone.BLACK, board.getStones().get(3).get(4));
        assertEquals(Stone.BLACK, board.getStones().get(3).get(5));
        // 隣の行は変更なし
        assertEquals(Stone.WHITE, board.getStones().get(4).get(3));
        assertEquals(Stone.BLACK, board.getStones().get(4).get(4));
    }
}
