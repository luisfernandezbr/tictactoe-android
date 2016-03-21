package br.com.mobiplus.tictactoe.ai.chain;

import android.util.Log;

import br.com.mobiplus.tictactoe.pojo.Board;
import br.com.mobiplus.tictactoe.pojo.BoardCell;

/**
 * Created by luis.fernandez on 3/20/16.
 */
public class CenterPlayChooser extends AbstractBestPlayChooser {

    @Override
    public int chooseBestPlay(Board board) {
        Log.i(TAG, "CenterPlayChooser");

        BoardCell centerCell = board.getCenterCell();

        if (board.isNotEmpty() && centerCell.isEmpty()) {
            return centerCell.getIndex();
        }

        return super.handleNext(board);
    }
}
