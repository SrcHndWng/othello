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
    }

    public void setFirstBoard() {
        player.setFirst();
        System.out.println("before");
        Terminal.view(board);
    }

    /**
     * 初回、上方向へ置き換え
     */
    @Test
    public void firstInputSwapUpper() {
        System.out.printf("----- %s -----%n", new Object(){}.getClass().getEnclosingMethod().getName());
        setFirstBoard();

        Boolean result = board.input(player, "5d");
        System.out.println("after");
        Terminal.view(board);

        // 上方向に置き換え
        assertTrue(result);
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
        setFirstBoard();

        Boolean result = board.input(player, "2e");
        System.out.println("after");
        Terminal.view(board);

        // 下方向に置き換え
        assertTrue(result);
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
        setFirstBoard();

        Boolean result = board.input(player, "4c");
        System.out.println("after");
        Terminal.view(board);

        // 右方向に置き換え
        assertTrue(result);
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
        setFirstBoard();

        Boolean result = board.input(player, "3f");
        System.out.println("after");
        Terminal.view(board);

        // 左方向に置き換え
        assertTrue(result);
        assertEquals(Stone.BLACK, board.getStones().get(3).get(3));
        assertEquals(Stone.BLACK, board.getStones().get(3).get(4));
        assertEquals(Stone.BLACK, board.getStones().get(3).get(5));
        // 隣の行は変更なし
        assertEquals(Stone.WHITE, board.getStones().get(4).get(3));
        assertEquals(Stone.BLACK, board.getStones().get(4).get(4));
    }

    /**
     * 右上方向に置き換え
     */
    @Test
    public void swapRightUpper() {
        System.out.printf("----- %s -----%n", new Object(){}.getClass().getEnclosingMethod().getName());

        player.setFirst();
        board.getStones().get(3).set(3, Stone.WHITE);
        board.getStones().get(3).set(4, Stone.BLACK);
        board.getStones().get(4).set(3, Stone.WHITE);
        board.getStones().get(4).set(4, Stone.WHITE);
        board.getStones().get(3).set(2, Stone.WHITE);
        board.getStones().get(4).set(2, Stone.WHITE);
        board.getStones().get(5).set(2, Stone.WHITE);
        System.out.println("before");
        Terminal.view(board);

        Boolean result = board.input(player, "6b");
        System.out.println("after");
        Terminal.view(board);

        // 右上方向に置き換え
        assertTrue(result);
        assertEquals(Stone.BLACK, board.getStones().get(6).get(1));
        assertEquals(Stone.BLACK, board.getStones().get(5).get(2));
        assertEquals(Stone.BLACK, board.getStones().get(4).get(3));
        assertEquals(Stone.BLACK, board.getStones().get(3).get(4));
        // 他は変更なし
        assertEquals(Stone.WHITE, board.getStones().get(3).get(2));
        assertEquals(Stone.WHITE, board.getStones().get(3).get(3));
        assertEquals(Stone.WHITE, board.getStones().get(4).get(2));
        assertEquals(Stone.WHITE, board.getStones().get(4).get(4));
    }

    /**
     * 右下方向に置き換え
     */
    @Test
    public void swapRightLower() {
        System.out.printf("----- %s -----%n", new Object(){}.getClass().getEnclosingMethod().getName());

        player.setFirst();
        board.getStones().get(3).set(3, Stone.WHITE);
        board.getStones().get(3).set(4, Stone.BLACK);
        board.getStones().get(4).set(3, Stone.WHITE);
        board.getStones().get(4).set(4, Stone.WHITE);
        board.getStones().get(5).set(5, Stone.BLACK);
        System.out.println("before");
        Terminal.view(board);

        Boolean result = board.input(player, "2c");
        System.out.println("after");
        Terminal.view(board);

        // 右下方向に置き換え
        assertTrue(result);
        assertEquals(Stone.BLACK, board.getStones().get(2).get(2));
        assertEquals(Stone.BLACK, board.getStones().get(3).get(3));
        assertEquals(Stone.BLACK, board.getStones().get(4).get(4));
        assertEquals(Stone.BLACK, board.getStones().get(5).get(5));
        // 他は変更なし
        assertEquals(Stone.WHITE, board.getStones().get(4).get(3));
        assertEquals(Stone.BLACK, board.getStones().get(3).get(4));
    }

    /**
     * 左上方向に置き換え
     */
    @Test
    public void swapLeftUpper() {
        System.out.printf("----- %s -----%n", new Object(){}.getClass().getEnclosingMethod().getName());

        player.setFirst();
        board.getStones().get(2).set(2, Stone.BLACK);
        board.getStones().get(3).set(3, Stone.WHITE);
        board.getStones().get(4).set(4, Stone.WHITE);
        board.getStones().get(3).set(4, Stone.BLACK);
        board.getStones().get(4).set(3, Stone.BLACK);
        System.out.println("before");
        Terminal.view(board);

        Boolean result = board.input(player, "5f");
        System.out.println("after");
        Terminal.view(board);

        // 左上方向に置き換え
        assertTrue(result);
        assertEquals(Stone.BLACK, board.getStones().get(5).get(5));
        assertEquals(Stone.BLACK, board.getStones().get(4).get(4));
        assertEquals(Stone.BLACK, board.getStones().get(3).get(3));
        assertEquals(Stone.BLACK, board.getStones().get(2).get(2));
        // 他は変更なし
        assertEquals(Stone.BLACK, board.getStones().get(3).get(4));
        assertEquals(Stone.BLACK, board.getStones().get(4).get(3));
    }

    /**
     * 左下方向に置き換え
     */
    @Test
    public void swapLeftLower() {
        System.out.printf("----- %s -----%n", new Object(){}.getClass().getEnclosingMethod().getName());

        player.setFirst();
        board.getStones().get(3).set(3, Stone.WHITE);
        board.getStones().get(5).set(2, Stone.BLACK);
        System.out.println("before");
        Terminal.view(board);

        Boolean result = board.input(player, "2f");
        System.out.println("after");
        Terminal.view(board);

        // 左下方向に置き換え
        assertTrue(result);
        assertEquals(Stone.BLACK, board.getStones().get(2).get(5));
        assertEquals(Stone.BLACK, board.getStones().get(3).get(4));
        assertEquals(Stone.BLACK, board.getStones().get(4).get(3));
        assertEquals(Stone.BLACK, board.getStones().get(5).get(2));
        // 他は変更なし
        assertEquals(Stone.WHITE, board.getStones().get(3).get(3));
        assertEquals(Stone.BLACK, board.getStones().get(4).get(4));
    }
}
