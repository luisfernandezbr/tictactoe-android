package br.com.mobiplus.tictactoe.mvp.model.ia;

import br.com.mobiplus.tictactoe.mvp.repo.BoardRepo;
import br.com.mobiplus.tictactoe.mvp.repo.IBoardRepo;
import br.com.mobiplus.tictactoe.otto.BusProvider;
import br.com.mobiplus.tictactoe.otto.event.EventOnBoardLoad;
import br.com.mobiplus.tictactoe.pojo.Board;

/**
 * Created by luis.fernandez on 3/17/16.
 */
public class ComputerIaModel implements IComputerIaModel {

    private IBoardRepo mRepo;

    public ComputerIaModel() {
        this.mRepo = new BoardRepo();
    }


    @Override
    public void play() {
        Board board = mRepo.getCurrentBoard();

        boolean search = true;

        SEARCH_BEST_PLAY : while (search) {

            String[][] boardState = board.getBoard();

            for (int row = 0; row < boardState.length; row++) {
                for (int col = 0; col < boardState[row].length; col++) {
                    int index = (row * 3) + col;

                    String boardPosition = boardState[row][col];

                    if (boardPosition == null) {
                        board.updateBoard(index, "O");
                        break SEARCH_BEST_PLAY;
                    }
                }
            }
        }

        mRepo.updateBoard(board);
        BusProvider.getInstance().post(new EventOnBoardLoad(mRepo.getCurrentBoard()));
    }
}
