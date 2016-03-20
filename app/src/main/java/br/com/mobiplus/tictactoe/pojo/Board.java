package br.com.mobiplus.tictactoe.pojo;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by luis.fernandez on 3/16/16.
 */
public class Board {

    private String[][] boardState = new String[3][3];
    private Player currentPlayer = Player.PLAYER_HUMAN;

    private BoardLine[] boardLineArray = {
            new BoardLine(new BoardCell(0, 0), new BoardCell(0, 1), new BoardCell(0, 2)),
            new BoardLine(new BoardCell(1, 0), new BoardCell(1, 1), new BoardCell(1, 2)),
            new BoardLine(new BoardCell(2, 0), new BoardCell(2, 1), new BoardCell(2, 2)),
            new BoardLine(new BoardCell(0, 0), new BoardCell(1, 0), new BoardCell(2, 0)),
            new BoardLine(new BoardCell(0, 1), new BoardCell(1, 1), new BoardCell(2, 1)),
            new BoardLine(new BoardCell(0, 2), new BoardCell(1, 2), new BoardCell(2, 2)),
            new BoardLine(new BoardCell(0, 0), new BoardCell(1, 1), new BoardCell(2, 2)),
            new BoardLine(new BoardCell(0, 2), new BoardCell(1, 1), new BoardCell(2, 0)),
    };


    public interface IBoardGameStateSearcher {
        void onNextPlayRequired();

        void onWinnerFounded();

        void onDraw();
    }

    private void changeCurrentPlayer() {
        if (currentPlayer.equals(Player.PLAYER_HUMAN)) {
            currentPlayer = Player.PLAYER_CPU;
        } else {
            currentPlayer = Player.PLAYER_HUMAN;
        }
    }

    /**
     * This method iterates over the eight lines of board, calling <code>IBoardIterator.onNextPlayRequired<code/>
     * eight times every time when called.
     * The <code>BoardLine</code> param contains three <code>BoardCell</code>s of the line,
     * with your value and your position.
     *
     * @param iterator
     */
    public void searchGameState(final IBoardGameStateSearcher iterator) {

        System.out.println("\n\n ============ \n\n");

        if (iterator == null) {
            throw new IllegalArgumentException("Param iterator cannot be null!");
        }

        for (int i = 0; i < boardLineArray.length; i++) {
            BoardLine boardLine = boardLineArray[i];

            for (int j = 0; j < boardLine.getBoardCellList().size(); j++) {
                BoardCell boardCell = boardLine.getBoardCellList().get(j);
                boardCell.setValue(boardState[boardCell.getRow()][boardCell.getCol()]);
            }

            //System.out.println(boardLine.toString());

            if (boardLine.isAWinnerLine()) {
                iterator.onWinnerFounded();
                return;
            }
        }

        if (this.isFull()) {
            iterator.onDraw();
        } else {
            iterator.onNextPlayRequired();
        }
    }

    public BoardCell getCenterCell() {
        return new BoardCell(1, 1, boardState[1][1]);
    }

    public BoardCell [] getCornerCells() {
        BoardCell [] boardCellsArray = new BoardCell[4];
        boardCellsArray[0] = new BoardCell(0, 0, boardState[0][0]);
        boardCellsArray[1] = new BoardCell(0, 2, boardState[0][2]);
        boardCellsArray[2] = new BoardCell(2, 0, boardState[2][0]);
        boardCellsArray[3] = new BoardCell(2, 2, boardState[2][2]);

        return boardCellsArray;
    }

    public List<BoardCell> getFreeCornerCells() {
        List<BoardCell> boardCellList = new ArrayList<>(4);

        for (int i = 0; i < this.getCornerCells().length; i++) {
            BoardCell boardCell = this.getCornerCells()[i];

            if (boardCell.isEmpty()) {
                boardCellList.add(boardCell);
            }
        }

        return boardCellList;
    }

    public BoardCell [] getEdgeCells() {
        BoardCell [] boardCellsArray = new BoardCell[4];
        boardCellsArray[0] = new BoardCell(0, 1, boardState[0][1]);
        boardCellsArray[1] = new BoardCell(1, 0, boardState[1][0]);
        boardCellsArray[2] = new BoardCell(1, 2, boardState[1][2]);
        boardCellsArray[3] = new BoardCell(2, 1, boardState[2][1]);

        return boardCellsArray;
    }

    public List<BoardCell> getFreeEdgeCells() {
        List<BoardCell> boardCellList = new ArrayList<>(4);

        for (int i = 0; i < this.getEdgeCells().length; i++) {
            BoardCell boardCell = this.getEdgeCells()[i];

            if (boardCell.isEmpty()) {
                boardCellList.add(boardCell);
            }
        }

        return boardCellList;
    }

    public List<BoardLine> getOneOccupiedLineList(String symbol) {
        List<BoardLine> list = new ArrayList<>(2);

        BoardLine[] boardLineArray = getBoardLineArray();
        for (int i = 0; i < boardLineArray.length; i++) {
            BoardLine boardLine = boardLineArray[i];

            if (boardLine.hasOneOccupiedCell(symbol)) {
                list.add(boardLine);
            }
        }

        return list;
    }

    public boolean isEmpty() {
        for (int row = 0; row < boardState.length; row++) {
            for (int col = 0; col < boardState[row].length; col++) {
                if (boardState[row][col] != null) {
                    return false;
                }
            }
        }

        return true;
    }

    public boolean isNotEmpty() {
        return !this.isEmpty();
    }

    public boolean isFull() {
        for (int row = 0; row < boardState.length; row++) {
            for (int col = 0; col < boardState[row].length; col++) {
                if (boardState[row][col] == null) {
                    return false;
                }
            }
        }

        return true;
    }

    public BoardLine[] getBoardLineArray() {

        for (int i = 0; i < boardLineArray.length; i++) {
            BoardLine boardLine = boardLineArray[i];

            for (int j = 0; j < boardLine.getBoardCellList().size(); j++) {
                BoardCell boardCell = boardLine.getBoardCellList().get(j);
                boardCell.setValue(boardState[boardCell.getRow()][boardCell.getCol()]);
            }
        }

        return boardLineArray;
    }

    public String[][] getBoard() {
        return boardState;
    }

    public void updateBoard(int position, String value) {
        changeCurrentPlayer();

        switch (position) {
            case 0: {
                boardState[0][0] = value;
                break;
            }
            case 1: {
                boardState[0][1] = value;
                break;
            }
            case 2: {
                boardState[0][2] = value;
                break;
            }
            case 3: {
                boardState[1][0] = value;
                break;
            }
            case 4: {
                boardState[1][1] = value;
                break;
            }
            case 5: {
                boardState[1][2] = value;
                break;
            }
            case 6: {
                boardState[2][0] = value;
                break;
            }
            case 7: {
                boardState[2][1] = value;
                break;
            }
            case 8: {
                boardState[2][2] = value;
                break;
            }
        }
    }
}
