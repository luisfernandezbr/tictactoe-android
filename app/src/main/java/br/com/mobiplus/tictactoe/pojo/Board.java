package br.com.mobiplus.tictactoe.pojo;

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


    public interface IBoardWinnerSearcher {
        void onWinnerFounded();

        void onFinishSearch();

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
     * This method iterates over the eight lines of board, calling <code>IBoardIterator.onFinishSearch<code/>
     * eight times every time when called.
     * The <code>BoardLine</code> param contains three <code>BoardCell</code>s of the line,
     * with your value and your position.
     *
     * @param iterator
     */
    public void searchGameState(final IBoardWinnerSearcher iterator) {

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
            iterator.onFinishSearch();
        }
    }

    public BoardCell getCenterCell() {
        return new BoardCell(1, 1, boardState[1][1]);
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
