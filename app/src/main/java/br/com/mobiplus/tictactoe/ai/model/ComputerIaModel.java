package br.com.mobiplus.tictactoe.ai.model;

import br.com.mobiplus.tictactoe.otto.BusProvider;
import br.com.mobiplus.tictactoe.otto.event.EventOnCpuPlay;
import br.com.mobiplus.tictactoe.pojo.Board;

/**
 * Created by luis.fernandez on 3/17/16.
 */
public class ComputerIaModel implements IComputerIaModel {

    @Override
    public void play(final Board board) {
        boolean search = true;

        SEARCH_BEST_PLAY : while (search) {

            String[][] boardState = board.getBoard();

            for (int row = 0; row < boardState.length; row++) {
                for (int col = 0; col < boardState[row].length; col++) {
                    int index = (row * 3) + col;

                    String boardPosition = boardState[row][col];

                    if (boardPosition == null) {
                        BusProvider.getInstance().post(new EventOnCpuPlay(index));
                        break SEARCH_BEST_PLAY;
                    }
                }
            }
        }
    }
}
