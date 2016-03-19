package br.com.mobiplus.tictactoe.ai;

import br.com.mobiplus.tictactoe.pojo.Board;

/**
 * Created by luis.fernandez on 3/19/16.
 */
public class CreateWinnerConditionChooser extends BestPlayChooser {

    @Override
    public int chooseBestPlay(Board board) {
        String[][] boardState = board.getBoard();

        if (boardState[1][1] != null) {
            return 0;
        } else {
            return mNext.chooseBestPlay(board);
        }
    }
}
