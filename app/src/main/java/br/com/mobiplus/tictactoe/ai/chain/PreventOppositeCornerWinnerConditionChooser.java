package br.com.mobiplus.tictactoe.ai.chain;

import android.util.Log;

import br.com.mobiplus.tictactoe.pojo.Board;
import br.com.mobiplus.tictactoe.pojo.BoardCell;
import br.com.mobiplus.tictactoe.pojo.Player;

/**
 * Created by luis.fernandez on 3/20/16.
 */
public class PreventOppositeCornerWinnerConditionChooser extends AbstractBestPlayChooser {

    @Override
    public int chooseBestPlay(Board board) {
        Log.i(TAG, "PreventOppositeCornerWinnerConditionChooser");

        BoardCell[] cornerCells = board.getCornerCells();
        BoardCell centerCell = board.getCenterCell();

        if (centerCell.hasValue(Player.PLAYER_CPU.getSymbol())) {
            BoardCell boardCellTopLeft = cornerCells[0];
            BoardCell boardCellTopRight = cornerCells[1];
            BoardCell boardCellBottomLeft = cornerCells[2];
            BoardCell boardCellBottomRight = cornerCells[3];

            if (boardCellTopLeft.hasSameValue(boardCellBottomRight) || boardCellTopRight.hasSameValue(boardCellBottomLeft)) {
                return board.getFreeEdgeCells().get(0).getIndex();
            }
        }

        return super.handleNext(board);
    }
}
