package com.example.othello;

import static org.junit.Assert.*;

import org.junit.Test;

public class MoveTest {
    @Test
    public void getInputKeyValue() {
        Move move = new Move("5d");
        assertEquals(5, move.getRow());
        assertEquals(3, move.getCol());
    }

    @Test
    public void validate() {
        Move move;

        move = new Move("2cc");
        assertFalse(move.isValidAddress());

        move = new Move("cc");
        assertFalse(move.isValidAddress());

        move = new Move("c2");
        assertFalse(move.isValidAddress());

        move = new Move("32");
        assertFalse(move.isValidAddress());

        move = new Move("0i");
        assertFalse(move.isValidAddress());

        move = new Move("8a");
        assertFalse(move.isValidAddress());

        move = new Move("0h");
        assertTrue(move.isValidAddress());

        move = new Move("7a");
        assertTrue(move.isValidAddress());

        move = new Move("2c");
        assertTrue(move.isValidAddress());
    }

    public void exitKey() {
         Move move;

         move = new Move("2c");
         assertFalse(move.isEnd());

         move = new Move("end");
         assertTrue(move.isEnd());

         move = new Move("exit");
         assertTrue(move.isEnd());
    }
}
