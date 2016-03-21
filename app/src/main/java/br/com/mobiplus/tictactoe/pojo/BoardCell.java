package br.com.mobiplus.tictactoe.pojo;

/**
 * Created by luis.fernandez on 3/19/16.
 */
public class BoardCell {

    private int row;
    private int col;
    private String value;

    public BoardCell(int row, int col) {
        this.row = row;
        this.col = col;
    }

    public BoardCell(int row, int col, String value) {
        this.row = row;
        this.col = col;
        this.value = value;
    }

    public int getRow() {
        return row;
    }

    public int getCol() {
        return col;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public int getIndex() {
        return (row * 3) + col;
    }

    public boolean isEmpty() {
        return value == null;
    }

    public boolean isNotEmpty() {
        return !this.isEmpty();
    }

    public boolean hasValue(String value) {
        return !isEmpty() && this.value.equals(value);
    }

    public boolean hasSameValue(BoardCell boardCell) {
        return boardCell != null && this.isNotEmpty() && this.value.equals(boardCell.getValue());
    }

    public boolean hasSamePosition(BoardCell boardCell) {
        return boardCell != null && boardCell.getRow() == this.getRow() && boardCell.getCol() == this.getCol();
    }

    public boolean isCenterCell() {
        return this.getIndex() == 4;
    }

    public boolean isCornerCell() {
        return this.getIndex() == 0 || this.getIndex() == 2 || this.getIndex() == 6 || this.getIndex() == 8;
    }

    public boolean isEdgeCell() {
        return this.getIndex() == 1 || this.getIndex() == 3 || this.getIndex() == 5 || this.getIndex() == 7;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        BoardCell boardCell = (BoardCell) o;

        if (value == null || boardCell.getValue() == null) {
            return false;
        }

        return value.equals(boardCell.value);

    }

    @Override
    public int hashCode() {
        return value.hashCode();
    }

//    @Override
//    public String toString() {
//        final StringBuilder sb = new StringBuilder("    BoardCell ");
//        sb.append("[row=").append(row);
//        sb.append("], col=").append(col);
//        sb.append("], value=").append(value);
//        sb.append("]");
//        return sb.toString();
//    }
}
