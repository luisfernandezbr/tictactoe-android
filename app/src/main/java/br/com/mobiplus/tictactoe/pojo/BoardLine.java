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

    public List<BoardCell> getBoardCellList() {
        return boardCellList;
    }

    public boolean isAWinnerLine() {
        return boardCellList.get(0) != null &&
                boardCellList.get(0).equals(boardCellList.get(1)) &&
                boardCellList.get(1).equals(boardCellList.get(2));
    }

    public boolean hasOneOccupiedCell(String symbol) {
        boolean result = false;

        int occupiedCount = 0;
        int freeCount = 0;

        for (int i = 0; i < boardCellList.size(); i++) {
            BoardCell boardCell = boardCellList.get(i);

            if (boardCell.isEmpty()) {
                freeCount++;
            } else if (boardCell.hasValue(symbol)) {
                occupiedCount++;
            }
        }

        if (occupiedCount == 1 && freeCount == 2) {
            result = true;
        }

        return result;
    }

    public boolean hasTwoOccupiedCells(String symbol) {
        boolean result = false;

        int occupiedCount = 0;
        int freeCount = 0;

        for (int i = 0; i < boardCellList.size(); i++) {
            BoardCell boardCell = boardCellList.get(i);

            if (boardCell.isEmpty()) {
                freeCount++;
            } else if (boardCell.hasValue(symbol)) {
                occupiedCount++;
            }
        }

        if (occupiedCount == 2 && freeCount == 1) {
            result = true;
        }

        return result;
    }

    public BoardCell getEmptyIntersection(BoardLine boardLine) {
        List<BoardCell> otherBoardCellList = boardLine.getBoardCellList();

        for (int i = 0; i < boardCellList.size(); i++) {
            BoardCell boardCell = boardCellList.get(i);

            for (int j = 0; j < otherBoardCellList.size(); j++) {
                BoardCell otherBoardCell = otherBoardCellList.get(j);

                if (boardCell.hasSamePosition(otherBoardCell) && boardCell.isEmpty() && otherBoardCell.isEmpty()) {
                    return boardCell;
                }
            }
        }

        return null;
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
