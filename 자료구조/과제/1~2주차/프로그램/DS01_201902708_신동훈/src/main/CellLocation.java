package main;

/**
 * Created by ShinD on 2022-03-03.
 */
public class CellLocation {
    // Constant
    private static final int UndefinedIndex = -1;

    // Private instance variables
    private int _row;
    private int _col;

    public CellLocation(int givenRow, int givenCol) {
        this.setRow (givenRow) ;
        this.setCol (givenCol) ;
    }

    public CellLocation() {
        this.setRow (UndefinedIndex);
        this.setCol (UndefinedIndex);
    }

    // Getter / Setter
    public void setRow(int newRow) {
        this._row = newRow ;
    }
    public int row() {
        return this._row ;
    }
    public void setCol(int newCol){
        this._col = newCol ;
    }
    public int col() {
        return this._col ;
    }
}
