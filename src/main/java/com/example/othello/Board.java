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

    public void input(Player player, String key) {
        int inputRow = Integer.parseInt(key.substring(0, 1));
        int inputCol = getInputCol(key);

        swapUpper(player.getInputStone(), inputRow, inputCol); // 上方向にループして石を変える
        swapLower(player.getInputStone(), inputRow, inputCol); // 下方向にループして石を変える
        swapRight(player.getInputStone(), inputRow, inputCol); // 右方向にループして石を変える
        swapLeft(player.getInputStone(), inputRow, inputCol); // 左方向にループして石を変える
        swapRightUpper(player.getInputStone(), inputRow, inputCol); // 右上方向にループして石を変える
        swapRightLower(player.getInputStone(), inputRow, inputCol); // 右下方向にループして石を変える
        swapLeftUpper(player.getInputStone(), inputRow, inputCol); // 左上方向にループして石を変える
        swapLeftLower(player.getInputStone(), inputRow, inputCol); // 左下方向にループして石を変える
        // TODO: いづれもヒットしなければエラー
    }

    private void swapUpper(Stone inputStone, int inputRow, int inputCol) {
        int edgeRow = UNDEFINED_ROW_COL;
        for(int i = inputRow; i >= 0; i--) {
            if(stones.get(i).get(inputCol) == inputStone) {
                edgeRow = i;
                break;
            }
        }

        if(edgeRow == UNDEFINED_ROW_COL) {
            return;
        }

        for(int i = inputRow; i >= edgeRow; i--) {
            stones.get(i).set(inputCol, inputStone);
        }
    }

    private void swapLower(Stone inputStone, int inputRow, int inputCol) {
        int edgeRow = UNDEFINED_ROW_COL;
        for(int i = inputRow; i < stones.size(); i++) {
            if(stones.get(i).get(inputCol) == inputStone) {
                edgeRow = i;
                break;
            }
        }

        if(edgeRow == UNDEFINED_ROW_COL) {
            return;
        }

        for(int i = inputRow; i <= edgeRow; i++) {
            stones.get(i).set(inputCol, inputStone);
        }
    }

    private void swapRight(Stone inputStone, int inputRow, int inputCol) {
        int edgeCol = UNDEFINED_ROW_COL;
        for(int i = inputCol; i < stones.get(inputRow).size(); i++) {
            if(stones.get(inputRow).get(i) == inputStone) {
                edgeCol = i;
                break;
            }
        }

        if(edgeCol == UNDEFINED_ROW_COL) {
            return;
        }

        for(int i = inputCol; i <= edgeCol; i++) {
            stones.get(inputRow).set(i, inputStone);
        }
    }

    private void swapLeft(Stone inputStone, int inputRow, int inputCol) {
        int edgeCol = UNDEFINED_ROW_COL;
        for(int i = inputCol; i >= 0; i--) {
            if(stones.get(inputRow).get(i) == inputStone) {
                edgeCol = i;
                break;
            }
        }

        if(edgeCol == UNDEFINED_ROW_COL) {
            return;
        }

        for(int i = inputCol; i >= edgeCol; i--) {
            stones.get(inputRow).set(i, inputStone);
        }
    }

    private void swapRightUpper(Stone inputStone, int inputRow, int inputCol) {
        int j = inputCol;
        int edgeRow = UNDEFINED_ROW_COL;
        int edgeCol = UNDEFINED_ROW_COL;
        for(int i = inputRow; i >= 0; i--) {
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
            return;
        }

        int k = inputCol;
        for(int row = inputRow; row >= edgeRow; row--) {
            stones.get(row).set(k, inputStone);
            k++;
        }
    }

    private void swapRightLower(Stone inputStone, int inputRow, int inputCol) {
        int j = inputCol;
        int edgeRow = UNDEFINED_ROW_COL;
        int edgeCol = UNDEFINED_ROW_COL;
        for(int i = inputRow; i < stones.size(); i++) {
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
            return;
        }

        int k = inputCol;
        for(int row = inputRow; row <= edgeRow; row++) {
            stones.get(row).set(k, inputStone);
            k++;
        }
    }

    private void swapLeftUpper(Stone inputStone, int inputRow, int inputCol) {
        int j = inputCol;
        int edgeRow = UNDEFINED_ROW_COL;
        int edgeCol = UNDEFINED_ROW_COL;
        for(int i = inputRow; i >= 0; i--) {
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
            return;
        }

        int k = inputCol;
        for(int row = inputRow; row >= edgeRow; row--) {
            stones.get(row).set(k, inputStone);
            k--;
        }
    }

    private void swapLeftLower(Stone inputStone, int inputRow, int inputCol) {
        int j = inputCol;
        int edgeRow = UNDEFINED_ROW_COL;
        int edgeCol = UNDEFINED_ROW_COL;
        for(int i = inputRow; i < stones.get(i).size(); i++) {
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
            return;
        }

        int k = inputCol;
        for(int row = inputRow; row <= edgeRow; row++) {
            stones.get(row).set(k, inputStone);
            k--;
        }
    }

    private int getInputCol(String key) {
        return ColumnTitle.valueOf(key.substring(1)).id;
    }
}
