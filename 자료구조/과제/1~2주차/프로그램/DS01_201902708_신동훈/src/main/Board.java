package main;

/**
 * Created by ShinD on 2022-03-03.
 */
public class Board {


    // Constant
    private static int EMPTY_CELL = -1 ;
    // Private instance variables
    private int _order ;
    private int[][]_cells ;



    // Getter/Setter
    public int order() { // 마방진차수를얻는다.
        return this._order ;
    }
    private void setOrder(int newOrder) { // 마방진차수를주어진값으로설정한다.
        this._order = newOrder ;
    }
    public int[][] cells() {
        return this._cells;
    }

    // 기본생성자
    public Board (int givenOrder) {
        this.setOrder(givenOrder) ;
        this.setCells (new int[givenOrder][givenOrder]) ;
        for ( int row = 0 ; row < givenOrder ; row++) {
            for ( int col = 0 ; col < givenOrder ; col++) {
                this.setCellValue (row, col, Board.EMPTY_CELL) ;
            }
        }
    }





    private void setCells(int[][] newCells) {
        this._cells = newCells ;
    }
    public int cellValue(CellLocation location) {
        // 주어진 location의 cell 값을얻는다.
        return this._cells [location.row()][location.col()] ;
    }
    public void setCellValue(CellLocation location, int value) {
        // 주어진 location의 cell 에 주어진value 를넣는다.
        this.cells() [location.row()][location.col()] = value ;
    }
    private void setCellValue(int row, int col, int value) {
        // 이 method는 class 내부에서만사용한다.
        // 주어진위치(row, col) 의cell 에주어진값value를넣는다.
        this._cells [row][col] = value ;
    }

    public boolean cellIsEmpty(CellLocation location) {
        // 주어진 위치의 cell이 비어있는지 여부를 알려준다
        // 비어있으면 true, 아니면 false를 얻는다.
        return (this.cellValue(location) == EMPTY_CELL) ;
    }
}
