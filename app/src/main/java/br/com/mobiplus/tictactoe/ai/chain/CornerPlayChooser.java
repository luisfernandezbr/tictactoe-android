package br.com.mobiplus.tictactoe.ai.chain;

import br.com.mobiplus.tictactoe.pojo.Board;
import br.com.mobiplus.tictactoe.pojo.BoardCell;

/**
 * Created by luis.fernandez on 3/20/16.
 */
public class CornerPlayChooser extends AbstractBestPlayChooser {

    @Override
    public int chooseBestPlay(Board board) {
        BoardCell[] cornerCellsArray = board.getCornerCells();

        for (int i = 0; i < cornerCellsArray.length; i++) {
            BoardCell boardCell = cornerCellsArray[i];

            if (boardCell.isEmpty()) {
                return boardCell.getIndex();
            }
        }


        return mNext.chooseBestPlay(board);
    }
}
