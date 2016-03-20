package br.com.mobiplus.tictactoe.ai.chain;

import java.util.List;

import br.com.mobiplus.tictactoe.pojo.Board;
import br.com.mobiplus.tictactoe.pojo.BoardCell;
import br.com.mobiplus.tictactoe.pojo.BoardLine;

/**
 * Created by luis.fernandez on 3/19/16.
 */
public class WinPlayChooser extends AbstractBestPlayChooser {

    @Override
    public int chooseBestPlay(Board board) {
        BoardLine[] boardLineArray = board.getBoardLineArray();

        //noinspection ForLoopReplaceableByForEach
        for (int i = 0; i < boardLineArray.length; i++) {
            List<BoardCell> boardCellList = boardLineArray[i].getBoardCellList();

            if (this.hasTwoCellsOccupied(boardCellList)) {

                for (int j = 0; j < boardCellList.size(); j++) {
                    BoardCell boardCell = boardCellList.get(j);

                    if (boardCell.getValue() == null) {
                        return boardCell.getIndex();
                    }
                }
            }
        }

        return mNext.chooseBestPlay(board);

    }

    private boolean hasTwoCellsOccupied(List<BoardCell> boardCellList) {
        boolean result = false;

        int count = 0;

        for (int i = 0; i < boardCellList.size(); i++) {
            BoardCell boardCell = boardCellList.get(i);

            if ("O".equals(boardCell.getValue())) {
                count++;

                if (count == 2) {
                    result = true;
                    break;
                }
            }
        }

        return result;
    }
}
