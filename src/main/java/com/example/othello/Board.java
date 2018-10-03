package com.example.othello;

import java.util.ArrayList;

public class Board {
    private ArrayList<ArrayList<Stone>> stones;
    private final int UNDEFINED_ROW_COL = -1;

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

    public Boolean input(Player player, String key) {
        int inputRow = Integer.parseInt(key.substring(0, 1));
        int inputCol = getInputCol(key);
        int swapCnt = 0;

        swapCnt += swapUpper(player.getInputStone(), inputRow, inputCol); // 上方向にループして石を変える
        swapCnt += swapLower(player.getInputStone(), inputRow, inputCol); // 下方向にループして石を変える
        swapCnt += swapRight(player.getInputStone(), inputRow, inputCol); // 右方向にループして石を変える
        swapCnt += swapLeft(player.getInputStone(), inputRow, inputCol); // 左方向にループして石を変える
        swapCnt += swapRightUpper(player.getInputStone(), inputRow, inputCol); // 右上方向にループして石を変える
        swapCnt += swapRightLower(player.getInputStone(), inputRow, inputCol); // 右下方向にループして石を変える
        swapCnt += swapLeftUpper(player.getInputStone(), inputRow, inputCol); // 左上方向にループして石を変える
        swapCnt += swapLeftLower(player.getInputStone(), inputRow, inputCol); // 左下方向にループして石を変える
        return (swapCnt > 0);
    }

    private int swapUpper(Stone inputStone, int inputRow, int inputCol) {
        int edgeRow = UNDEFINED_ROW_COL;

        for(int i = inputRow; i >= 0; i--) {
            if(i < 0) {
                break;
            }
            if(stones.get(i).get(inputCol) == inputStone) {
                edgeRow = i;
                break;
            }
        }

        if(edgeRow == UNDEFINED_ROW_COL) {
            return 0;
        }

        for(int i = inputRow; i >= edgeRow; i--) {
            if(i < 0) {
                break;
            }
            stones.get(i).set(inputCol, inputStone);
        }

        return 1;
    }

    private int swapLower(Stone inputStone, int inputRow, int inputCol) {
        int edgeRow = UNDEFINED_ROW_COL;
        for(int i = inputRow; i < stones.size(); i++) {
            if(i >= stones.size()) {
                break;
            }
            if(stones.get(i).get(inputCol) == inputStone) {
                edgeRow = i;
                break;
            }
        }

        if(edgeRow == UNDEFINED_ROW_COL) {
            return 0;
        }

        for(int i = inputRow; i <= edgeRow; i++) {
            if(i >= stones.size()) {
                break;
            }
            stones.get(i).set(inputCol, inputStone);
        }

        return 1;
    }

    private int swapRight(Stone inputStone, int inputRow, int inputCol) {
        int edgeCol = UNDEFINED_ROW_COL;
        for(int i = inputCol; i < stones.get(inputRow).size(); i++) {
            if(i >= stones.get(inputRow).size()) {
                break;
            }
            if(stones.get(inputRow).get(i) == inputStone) {
                edgeCol = i;
                break;
            }
        }

        if(edgeCol == UNDEFINED_ROW_COL) {
            return 0;
        }

        for(int i = inputCol; i <= edgeCol; i++) {
            if(i >= stones.get(inputRow).size()) {
                break;
            }
            stones.get(inputRow).set(i, inputStone);
        }

        return 1;
    }

    private int swapLeft(Stone inputStone, int inputRow, int inputCol) {
        int edgeCol = UNDEFINED_ROW_COL;
        for(int i = inputCol; i >= 0; i--) {
            if(i < 0) {
                break;
            }
            if(stones.get(inputRow).get(i) == inputStone) {
                edgeCol = i;
                break;
            }
        }

        if(edgeCol == UNDEFINED_ROW_COL) {
            return 0;
        }

        for(int i = inputCol; i >= edgeCol; i--) {
            if(i < 0) {
                break;
            }
            stones.get(inputRow).set(i, inputStone);
        }

        return 1;
    }

    private int swapRightUpper(Stone inputStone, int inputRow, int inputCol) {
        int j = inputCol;
        int edgeRow = UNDEFINED_ROW_COL;
        int edgeCol = UNDEFINED_ROW_COL;
        for(int i = inputRow; i >= 0; i--) {
            if(i < 0) {
                break;
            }
            if(j > stones.get(i).size() - 1) {
                break;
            }
            if(stones.get(i).get(j) == inputStone) {
                edgeRow = i;
                edgeCol = j;
                break;
            }
            j++;
        }

        if((edgeRow == UNDEFINED_ROW_COL) || (edgeCol == UNDEFINED_ROW_COL)) {
            return 0;
        }

        int k = inputCol;
        for(int row = inputRow; row >= edgeRow; row--) {
            if(row < 0) {
                break;
            }
            if(k >= stones.get(inputRow).size()) {
                break;
            }
            stones.get(row).set(k, inputStone);
            k++;
        }

        return 1;
    }

    private int swapRightLower(Stone inputStone, int inputRow, int inputCol) {
        int j = inputCol;
        int edgeRow = UNDEFINED_ROW_COL;
        int edgeCol = UNDEFINED_ROW_COL;
        for(int i = inputRow; i < stones.size(); i++) {
            if(i >= stones.size()) {
                break;
            }
            if(j > stones.get(i).size() - 1) {
                break;
            }
            if(stones.get(i).get(j) == inputStone) {
                edgeRow = i;
                edgeCol = j;
                break;
            }
            j++;
        }

        if((edgeRow == UNDEFINED_ROW_COL) || (edgeCol == UNDEFINED_ROW_COL)) {
            return 0;
        }

        int k = inputCol;
        for(int row = inputRow; row <= edgeRow; row++) {
            if(row >= stones.size()) {
                break;
            }
            if(k >= stones.get(inputRow).size()) {
                break;
            }
            stones.get(row).set(k, inputStone);
            k++;
        }

        return 1;
    }

    private int swapLeftUpper(Stone inputStone, int inputRow, int inputCol) {
        int j = inputCol;
        int edgeRow = UNDEFINED_ROW_COL;
        int edgeCol = UNDEFINED_ROW_COL;
        for(int i = inputRow; i >= 0; i--) {
            if(i < 0) {
                break;
            }
            if(j < 0) {
                break;
            }
            if(stones.get(i).get(j) == inputStone) {
                edgeRow = i;
                edgeCol = j;
                break;
            }
            j--;
        }

        if((edgeRow == UNDEFINED_ROW_COL) || (edgeCol == UNDEFINED_ROW_COL)) {
            return 0;
        }

        int k = inputCol;
        for(int row = inputRow; row >= edgeRow; row--) {
            if(row < 0) {
                break;
            }
            if(k < 0) {
                break;
            }
            stones.get(row).set(k, inputStone);
            k--;
        }

        return 1;
    }

    private int swapLeftLower(Stone inputStone, int inputRow, int inputCol) {
        int j = inputCol;
        int edgeRow = UNDEFINED_ROW_COL;
        int edgeCol = UNDEFINED_ROW_COL;
        for(int i = inputRow; i < stones.get(i).size(); i++) {
            if(i >= stones.size()) {
                break;
            }
            if(j < 0) {
                break;
            }
            if(stones.get(i).get(j) == inputStone) {
                edgeRow = i;
                edgeCol = j;
                break;
            }
            j--;
        }

        if((edgeRow == UNDEFINED_ROW_COL) || (edgeCol == UNDEFINED_ROW_COL)) {
            return 0;
        }

        int k = inputCol;
        for(int row = inputRow; row <= edgeRow; row++) {
            if(row >= stones.size()) {
                break;
            }
            if(k < 0) {
                break;
            }
            stones.get(row).set(k, inputStone);
            k--;
        }

        return 1;
    }

    private int getInputCol(String key) {
        return ColumnTitle.valueOf(key.substring(1)).id;
    }
}
