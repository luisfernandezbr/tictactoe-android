package br.com.mobiplus.tictactoe.ai.chain;

import br.com.mobiplus.tictactoe.pojo.Board;
import br.com.mobiplus.tictactoe.pojo.BoardCell;

/**
 * Created by luis.fernandez on 3/20/16.
 */
public class CenterPlayChooser extends AbstractBestPlayChooser {

    @Override
    public int chooseBestPlay(Board board) {

        BoardCell centerCell = board.getCenterCell();

        if (board.isNotEmpty() && centerCell.getValue() == null) {
            return centerCell.getIndex();
        }

        return mNext.chooseBestPlay(board);
    }
}
