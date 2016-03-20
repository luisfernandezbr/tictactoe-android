package br.com.mobiplus.tictactoe.ai.chain;

import android.util.Log;

import br.com.mobiplus.tictactoe.pojo.Board;
import br.com.mobiplus.tictactoe.pojo.BoardCell;

/**
 * Created by luis.fernandez on 3/20/16.
 */
public class BlockOppositeCornerWinnerConditionChooser extends AbstractBestPlayChooser {

    @Override
    public int chooseBestPlay(Board board) {
        Log.i(TAG, "BlockOppositeCornerWinnerConditionChooser");

        BoardCell[] cornerCells = board.getCornerCells();
        BoardCell centerCell = board.getCenterCell();

        if (centerCell.hasValue("O")) {
            BoardCell boardCellTopLeft = cornerCells[0];
            BoardCell boardCellTopRight = cornerCells[1];
            BoardCell boardCellBottomLeft = cornerCells[2];
            BoardCell boardCellBottomRight = cornerCells[3];

            if (boardCellTopLeft.hasSameValue(boardCellBottomRight) || boardCellTopRight.hasSameValue(boardCellBottomLeft)) {
                return board.getFreeEdgeCells().get(0).getIndex();
            }
        }

        return mNext.chooseBestPlay(board);
    }
}
