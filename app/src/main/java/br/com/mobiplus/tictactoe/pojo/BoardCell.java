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

    public void setRow(int row) {
        this.row = row;
    }

    public int getCol() {
        return col;
    }

    public void setCol(int col) {
        this.col = col;
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

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("    BoardCell { ");
        sb.append("[row=").append(row);
        sb.append("], col=").append(col);
        sb.append("], value=").append(value);
        sb.append("]}");
        return sb.toString();
    }
}
