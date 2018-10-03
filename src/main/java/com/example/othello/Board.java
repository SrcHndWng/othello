package com.example.othello;

import java.util.ArrayList;

public class Board {
    private ArrayList<ArrayList<Stone>> stones;
    private final int UNDEFINED_ROW_COL = -1;

    private abstract class SwapStones{
        Stone inputStone;
        int inputRow;
        int inputCol;

        protected class Edge{
            private int row;
            private int col;
            public Edge(int row, int col) {
                this.row = row;
                this.col = col;
            }
        }

        public SwapStones(Stone inputStone, int inputRow, int inputCol) {
            this.inputStone = inputStone;
            this.inputRow = inputRow;
            this.inputCol = inputCol;
        }
        public int exec(){
            if(!isEnableSwap()) {
                return 0;
            }
            Edge edge = getEdge();
            if(!isExistEdge(edge.row, edge.col)) {
                return 0;
            }
            return swap(edge.row, edge.col);
        }
        abstract protected Boolean isEnableSwap();
        abstract protected Edge getEdge();
        abstract protected int swap(int edgeRow, int edgeCol);
        protected Boolean isExistEdge(int edgeRow, int edgeCol) {
            return (edgeRow > UNDEFINED_ROW_COL) && (edgeCol > UNDEFINED_ROW_COL);
        }
    }

    private class SwapToUpper extends SwapStones{
        public SwapToUpper(Stone inputStone, int inputRow, int inputCol) {
            super(inputStone, inputRow, inputCol);
        }

        @Override
        protected int swap(int edgeRow, int edgeCol) {
            for(int i = inputRow; i >= edgeRow; i--) {
                if(i < 0) {
                    break;
                }
                stones.get(i).set(inputCol, inputStone);
            }
            return 1;
        }

        @Override
        protected Edge getEdge() {
            int edgeRow = UNDEFINED_ROW_COL;
            for(int i = inputRow - 1; i >= 0; i--) {
                if(i < 0) {
                    break;
                }
                if(stones.get(i).get(inputCol) == inputStone) {
                    edgeRow = i;
                    break;
                }
            }
            return new Edge(edgeRow, this.inputCol);
        }

        @Override
        protected Boolean isEnableSwap() {
            int checkRow = this.inputRow - 1;
            if(checkRow < 0) return false;
            return stones.get(checkRow).get(this.inputCol) != this.inputStone;
        }
    }

    private class SwapToLower extends SwapStones{
        public SwapToLower(Stone inputStone, int inputRow, int inputCol) {
            super(inputStone, inputRow, inputCol);
        }

        @Override
        protected int swap(int edgeRow, int edgeCol) {
            for(int i = inputRow; i <= edgeRow; i++) {
                if(i >= Const.MAX_ROW_COL_NUM) {
                    break;
                }
                stones.get(i).set(inputCol, inputStone);
            }
            return 1;
        }

        @Override
        protected Edge getEdge() {
            int edgeRow = UNDEFINED_ROW_COL;
            for(int i = inputRow + 1; i < Const.MAX_ROW_COL_NUM; i++) {
                if(i >= Const.MAX_ROW_COL_NUM) {
                    break;
                }
                if(stones.get(i).get(inputCol) == inputStone) {
                    edgeRow = i;
                    break;
                }
            }
            return new Edge(edgeRow, this.inputCol);
        }

        @Override
        protected Boolean isEnableSwap() {
            int checkRow = this.inputRow + 1;
            if(checkRow >= Const.MAX_ROW_COL_NUM) {
                return true;
            }
            return stones.get(checkRow).get(this.inputCol) != this.inputStone;
        }

    }

    private class SwapToRight extends SwapStones{
        public SwapToRight(Stone inputStone, int inputRow, int inputCol) {
            super(inputStone, inputRow, inputCol);
        }

        @Override
        protected Boolean isEnableSwap() {
            int checkCol = this.inputCol + 1;
            if(checkCol >= Const.MAX_ROW_COL_NUM) return true;
            return stones.get(this.inputRow).get(checkCol) != this.inputStone;
        }

        @Override
        protected Edge getEdge() {
            int edgeCol = UNDEFINED_ROW_COL;
            for(int i = inputCol + 1; i < Const.MAX_ROW_COL_NUM; i++) {
                if(i >= Const.MAX_ROW_COL_NUM) {
                    break;
                }
                if(stones.get(inputRow).get(i) == inputStone) {
                    edgeCol = i;
                    break;
                }
            }
            return new Edge(this.inputRow, edgeCol);
        }

        @Override
        protected int swap(int edgeRow, int edgeCol) {
            for(int i = inputCol; i <= edgeCol; i++) {
                if(i >= Const.MAX_ROW_COL_NUM) {
                    break;
                }
                stones.get(inputRow).set(i, inputStone);
            }
            return 1;
        }

    }

    private class SwapToLeft extends SwapStones{
        public SwapToLeft(Stone inputStone, int inputRow, int inputCol) {
            super(inputStone, inputRow, inputCol);
        }

        @Override
        protected Boolean isEnableSwap() {
            int checkCol = this.inputCol -1;
            if(checkCol < 0) {
                return true;
            }
            return stones.get(this.inputRow).get(checkCol) != this.inputStone;
        }

        @Override
        protected Edge getEdge() {
            int edgeCol = UNDEFINED_ROW_COL;
            for(int i = inputCol - 1; i >= 0; i--) {
                if(i < 0) {
                    break;
                }
                if(stones.get(inputRow).get(i) == inputStone) {
                    edgeCol = i;
                    break;
                }
            }
            return new Edge(this.inputRow, edgeCol);
        }

        @Override
        protected int swap(int edgeRow, int edgeCol) {
            for(int i = inputCol; i >= edgeCol; i--) {
                if(i < 0) {
                    break;
                }
                stones.get(inputRow).set(i, inputStone);
            }
            return 1;
        }

    }

    private class SwapToRightUpper extends SwapStones{
        public SwapToRightUpper(Stone inputStone, int inputRow, int inputCol) {
            super(inputStone, inputRow, inputCol);
        }

        @Override
        protected int swap(int edgeRow, int edgeCol) {
            int k = inputCol;
            for(int row = inputRow; row >= edgeRow; row--) {
                if(row < 0) {
                    break;
                }
                if(k >= Const.MAX_ROW_COL_NUM) {
                    break;
                }
                stones.get(row).set(k, inputStone);
                k++;
            }
            return 1;
        }

        @Override
        protected Edge getEdge() {
            int j = inputCol + 1;
            int edgeRow = UNDEFINED_ROW_COL;
            int edgeCol = UNDEFINED_ROW_COL;
            for(int i = inputRow - 1; i >= 0; i--) {
                if(i < 0) {
                    break;
                }
                if(j > Const.MAX_ROW_COL_NUM - 1) {
                    break;
                }
                if(stones.get(i).get(j) == inputStone) {
                    edgeRow = i;
                    edgeCol = j;
                    break;
                }
                j++;
            }
            return new Edge(edgeRow, edgeCol);
        }

        @Override
        protected Boolean isEnableSwap() {
            int checkRow = inputRow - 1;
            if(checkRow < 0) return true;
            int checkCol = inputCol + 1;
            if(checkCol >= Const.MAX_ROW_COL_NUM) return true;
            return stones.get(checkRow).get(checkCol) != this.inputStone;
        }

    }

    private class SwapToRightLower extends SwapStones{
        public SwapToRightLower(Stone inputStone, int inputRow, int inputCol) {
            super(inputStone, inputRow, inputCol);
        }

        @Override
        protected Boolean isEnableSwap() {
            int checkRow = this.inputRow + 1;
            if(checkRow >= Const.MAX_ROW_COL_NUM) {
                return true;
            }
            int checkCol = this.inputCol + 1;
            if(checkCol >= Const.MAX_ROW_COL_NUM) {
                return true;
            }
            return stones.get(checkRow).get(checkCol) != this.inputStone;
        }

        @Override
        protected Edge getEdge() {
            int j = inputCol + 1;
            int edgeRow = UNDEFINED_ROW_COL;
            int edgeCol = UNDEFINED_ROW_COL;
            for(int i = inputRow + 1; i < Const.MAX_ROW_COL_NUM; i++) {
                if(i >= Const.MAX_ROW_COL_NUM) {
                    break;
                }
                if(j > Const.MAX_ROW_COL_NUM - 1) {
                    break;
                }
                if(stones.get(i).get(j) == inputStone) {
                    edgeRow = i;
                    edgeCol = j;
                    break;
                }
                j++;
            }
            return new Edge(edgeRow, edgeCol);
        }

        @Override
        protected int swap(int edgeRow, int edgeCol) {
            int k = inputCol;
            for(int row = inputRow; row <= edgeRow; row++) {
                if(row >= Const.MAX_ROW_COL_NUM) {
                    break;
                }
                if(k >= Const.MAX_ROW_COL_NUM) {
                    break;
                }
                stones.get(row).set(k, inputStone);
                k++;
            }
            return 1;
        }

    }

    private class SwapToLeftUpper extends SwapStones{
        public SwapToLeftUpper(Stone inputStone, int inputRow, int inputCol) {
            super(inputStone, inputRow, inputCol);
        }

        @Override
        protected Boolean isEnableSwap() {
            int checkRow = this.inputRow - 1;
            if(checkRow < 0) {
                return true;
            }
            int checkCol = this.inputCol - 1;
            if(checkCol < 0) {
                return true;
            }
            return stones.get(checkRow).get(checkCol) != this.inputStone;
        }

        @Override
        protected Edge getEdge() {
            int j = inputCol - 1;
            int edgeRow = UNDEFINED_ROW_COL;
            int edgeCol = UNDEFINED_ROW_COL;
            for(int i = inputRow - 1; i >= 0; i--) {
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
            return new Edge(edgeRow, edgeCol);
        }

        @Override
        protected int swap(int edgeRow, int edgeCol) {
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

    }

    private class SwapToLeftLower extends SwapStones{
        public SwapToLeftLower(Stone inputStone, int inputRow, int inputCol) {
            super(inputStone, inputRow, inputCol);
        }

        @Override
        protected Boolean isEnableSwap() {
            int checkRow = this.inputRow + 1;
            if(checkRow >= Const.MAX_ROW_COL_NUM) {
                return true;
            }
            int checkCol = this.inputCol - 1;
            if(checkCol < 0) {
                return true;
            }
            return stones.get(checkRow).get(checkCol) != this.inputStone;
        }

        @Override
        protected Edge getEdge() {
            int j = inputCol - 1;
            int edgeRow = UNDEFINED_ROW_COL;
            int edgeCol = UNDEFINED_ROW_COL;
            for(int i = inputRow + 1; i < Const.MAX_ROW_COL_NUM; i++) {
                if(i >= Const.MAX_ROW_COL_NUM) {
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
            return new Edge(edgeRow, edgeCol);
        }

        @Override
        protected int swap(int edgeRow, int edgeCol) {
            int k = inputCol;
            for(int row = inputRow; row <= edgeRow; row++) {
                if(row >= Const.MAX_ROW_COL_NUM) {
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

    }

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

        swapCnt += new SwapToUpper(player.getInputStone(), inputRow, inputCol).exec(); // 上方向にループして石を変える
        swapCnt += new SwapToLower(player.getInputStone(), inputRow, inputCol).exec(); // 下方向にループして石を変える
        swapCnt += new SwapToRight(player.getInputStone(), inputRow, inputCol).exec(); // 右方向にループして石を変える
        swapCnt += new SwapToLeft(player.getInputStone(), inputRow, inputCol).exec(); // 左方向にループして石を変える
        swapCnt += new SwapToRightUpper(player.getInputStone(), inputRow, inputCol).exec(); // 右上方向にループして石を変える
        swapCnt += new SwapToRightLower(player.getInputStone(), inputRow, inputCol).exec(); // 右下方向にループして石を変える
        swapCnt += new SwapToLeftUpper(player.getInputStone(), inputRow, inputCol).exec(); // 左上方向にループして石を変える
        swapCnt += new SwapToLeftLower(player.getInputStone(), inputRow, inputCol).exec(); // 左下方向にループして石を変える

        return (swapCnt > 0);
    }

    private int getInputCol(String key) {
        return ColumnTitle.valueOf(key.substring(1)).id;
    }
}
