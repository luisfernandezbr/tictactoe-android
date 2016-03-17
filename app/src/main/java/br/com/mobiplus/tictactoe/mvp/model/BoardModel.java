package br.com.mobiplus.tictactoe.mvp.model;

import br.com.mobiplus.tictactoe.mvp.repo.BoardRepo;
import br.com.mobiplus.tictactoe.mvp.repo.IBoardRepo;
import br.com.mobiplus.tictactoe.otto.BusProvider;
import br.com.mobiplus.tictactoe.otto.event.EventOnBoardLoad;
import br.com.mobiplus.tictactoe.pojo.Board;
import br.com.mobiplus.tictactoe.pojo.Player;

/**
 * Created by luis.fernandez on 3/16/16.
 */
public class BoardModel implements IBoardModel {

    private IBoardRepo mRepo;

    public BoardModel() {
        this.mRepo = new BoardRepo();
    }

    public void requestCurrentBoard() {
        BusProvider.getInstance().post(new EventOnBoardLoad(mRepo.getCurrentBoard()));
    }

    @Override
    public void updateBoard(Player player, int clickedPosition) {
        Board board = mRepo.getCurrentBoard();

        if (player.equals(Player.PLAYER_1)) {
            board.changeBoard(clickedPosition, "X");
        } else {
            board.changeBoard(clickedPosition, "O");
        }

        mRepo.updateBoard(board);
        BusProvider.getInstance().post(new EventOnBoardLoad(mRepo.getCurrentBoard()));
    }
}
