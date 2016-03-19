package br.com.mobiplus.tictactoe.mvp.presenter;

import android.app.Activity;

import com.squareup.otto.Subscribe;

import br.com.mobiplus.tictactoe.mvp.model.BoardModel;
import br.com.mobiplus.tictactoe.mvp.model.IBoardModel;
import br.com.mobiplus.tictactoe.ai.model.ComputerIaModel;
import br.com.mobiplus.tictactoe.ai.model.IComputerIaModel;
import br.com.mobiplus.tictactoe.mvp.view.BoardView;
import br.com.mobiplus.tictactoe.mvp.view.IBoardView;
import br.com.mobiplus.tictactoe.otto.BusProvider;
import br.com.mobiplus.tictactoe.otto.EventBoardClick;
import br.com.mobiplus.tictactoe.otto.event.EventRestartGame;
import br.com.mobiplus.tictactoe.otto.event.EventOnBoardStateChange;
import br.com.mobiplus.tictactoe.pojo.Board;
import br.com.mobiplus.tictactoe.pojo.Player;

/**
 * Created by luis.fernandez on 3/16/16.
 */
public class BoardPresenter implements IBoardPresenter {

    private IBoardView mView;
    private IBoardModel mModel;
    private IComputerIaModel mComputerIaModel;

    public BoardPresenter(Activity activity) {
        this.mView = new BoardView(activity);
        this.mModel = new BoardModel();
        this.mComputerIaModel = new ComputerIaModel();
    }

    @Subscribe
    public void viewOnBoardClick(EventBoardClick eventBoardClick) {
        //Toast.makeText(AppApplication.getContext(), "Clicked " + eventBoardClick.getClickedPosition(), Toast.LENGTH_SHORT).show();
        mModel.updateBoard(eventBoardClick.getClickedPosition());
        mModel.iterateTest();
    }

    @Subscribe
    public void viewOnRestartGame(EventRestartGame eventRestartGame) {
        mModel.restartGame();
    }

    @Subscribe
    public void modelOnBoardStateChange(EventOnBoardStateChange eventOnBoardStateChange) {
        Board board = eventOnBoardStateChange.getBoard();

        Player currentPlayer = board.getCurrentPlayer();

        if (currentPlayer.equals(Player.PLAYER_COMPUTER)) {

            Player player = mModel.hasAWinner();

            if (player != null) {
                mView.defineWinner(player);
                mView.updateBoard(eventOnBoardStateChange.getBoard());
            } else {
                mComputerIaModel.play();
            }

        } else {

            Player player = mModel.hasAWinner();

            if (player != null) {
                mView.defineWinner(player);
            }

            mView.updateBoard(eventOnBoardStateChange.getBoard());
        }
    }

    @Override
    public void onStart() {
        BusProvider.getInstance().register(this);
    }

    @Override
    public void onStop() {
        BusProvider.getInstance().unregister(this);
    }

}
