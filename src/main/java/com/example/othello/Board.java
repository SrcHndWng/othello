package com.example.othello;

import java.util.ArrayList;

public class Board {
    private ArrayList<ArrayList<Stone>> stones;

    public enum ColumnTitle{
        a(0),
        b(1),
        c(2),
        d(3),
        e(4),
        f(5),
        g(6),
        h(7);

        private final int id;

        private ColumnTitle(final int id) {
            this.id = id;
        }
    };

    public Board() {
        stones = new ArrayList<ArrayList<Stone>>();
        for(int i = 0; i < Const.MAX_ROW_COL_NUM; i++) {
            ArrayList<Stone> column = new ArrayList<Stone>();
            for(int j = 0; j < Const.MAX_ROW_COL_NUM; j++) {
                if(i == 3 && j == 3) {
                    column.add(Stone.BLACK);
                }else if(i == 3 && j == 4){
                    column.add(Stone.WHITE);
                }else if(i == 4 && j == 3){
                    column.add(Stone.WHITE);
                }else if(i == 4 && j == 4){
                    column.add(Stone.BLACK);
                }else {
                    column.add(Stone.NONE);
                }
            }
            stones.add(column);
        }
    }

    public ArrayList<ArrayList<Stone>> getStones(){
        return stones;
    }

    public void input(Player player, String key) {
        int inputRow = Integer.parseInt(key.substring(0, 1));
        int inputCol = getInputCol(key);

        swapUpper(player.getInputStone(), inputRow, inputCol); // TODO: 上方向にループして石を変える
        // TODO: 下方向にループして石を変える
        // TODO: 右方向にループして石を変える
        // TODO: 左方向にループして石を変える
        // TODO: 右上方向にループして石を変える
        // TODO: 右下方向にループして石を変える
        // TODO: 左上方向にループして石を変える
        // TODO: 左下方向にループして石を変える
        // TODO: いづれもヒットしなければエラー
    }

    private void swapUpper(Stone inputStone, int inputRow, int inputCol) {
        int edgeRow = 0;
        for(int i = inputRow; i >= 0; i--) {
            if(stones.get(i).get(inputCol) == inputStone) {
                edgeRow = i;
                break;
            }
        }

        for(int i = inputRow; i >= edgeRow; i--) {
            stones.get(i).set(inputCol, inputStone);
        }
    }

    private int getInputCol(String key) {
        return ColumnTitle.valueOf(key.substring(1)).id;
    }
}
