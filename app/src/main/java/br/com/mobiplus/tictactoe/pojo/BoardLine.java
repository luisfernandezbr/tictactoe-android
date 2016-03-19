package br.com.mobiplus.tictactoe.pojo;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by luis.fernandez on 3/19/16.
 */
public class BoardLine {

    private List<BoardCell> boardCellList = new ArrayList<>(3);

    public BoardLine(BoardCell boardCell_1, BoardCell boardCell_2, BoardCell boardCell_3) {
        boardCellList.add(boardCell_1);
        boardCellList.add(boardCell_2);
        boardCellList.add(boardCell_3);
    }

    public void addBoardCell(BoardCell boardCell) {
        boardCellList.add(boardCell);
    }

    public List<BoardCell> getBoardCellList() {
        return boardCellList;
    }

    public boolean isAWinnerLine() {
        return boardCellList.get(0) != null &&
                boardCellList.get(0).equals(boardCellList.get(1)) &&
                boardCellList.get(1).equals(boardCellList.get(2));

    }

    public BoardCell getBoardCell_1() {
        return boardCellList.get(0);
    }

    public BoardCell getBoardCell_2() {
        return boardCellList.get(1);
    }

    public BoardCell getBoardCell_3() {
        return boardCellList.get(2);
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("\n\n### BoardLine ###\n");

        for (int i = 0; i < boardCellList.size(); i++) {
            sb.append(boardCellList.get(i).toString() + '\n');
        }
        return sb.toString();
    }
}
