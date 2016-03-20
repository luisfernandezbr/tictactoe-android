package br.com.mobiplus.tictactoe.ai.chain;

import android.util.Log;

import br.com.mobiplus.tictactoe.pojo.Board;

/**
 * Created by luis.fernandez on 3/19/16.
 */
public class RandomPlayChooser extends AbstractBestPlayChooser {
    @Override
    public int chooseBestPlay(Board board) {
        Log.i(TAG, "RandomPlayChooser");

        int result = 0;

        boolean search = true;

        SEARCH_BEST_PLAY:
        while (search) {

            String[][] boardState = board.getBoard();

            for (int row = 0; row < boardState.length; row++) {
                for (int col = 0; col < boardState[row].length; col++) {
                    int index = (row * 3) + col;

                    String boardPosition = boardState[row][col];

                    if (boardPosition == null) {
                        result = index;
                        break SEARCH_BEST_PLAY;
                    }
                }
            }
        }
        return result;
    }
}
