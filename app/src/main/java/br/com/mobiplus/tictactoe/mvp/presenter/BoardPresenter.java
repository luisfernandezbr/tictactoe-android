package br.com.mobiplus.tictactoe.mvp.presenter;

import android.app.Activity;
import android.widget.Toast;

import com.squareup.otto.Subscribe;

import br.com.mobiplus.tictactoe.android.application.AppApplication;
import br.com.mobiplus.tictactoe.mvp.model.BoardModel;
import br.com.mobiplus.tictactoe.mvp.model.IBoardModel;
import br.com.mobiplus.tictactoe.mvp.model.ia.ComputerIaModel;
import br.com.mobiplus.tictactoe.mvp.model.ia.IComputerIaModel;
import br.com.mobiplus.tictactoe.mvp.view.BoardView;
import br.com.mobiplus.tictactoe.mvp.view.IBoardView;
import br.com.mobiplus.tictactoe.otto.BusProvider;
import br.com.mobiplus.tictactoe.otto.EventBoardClick;
import br.com.mobiplus.tictactoe.otto.event.EventOnBoardLoad;
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
        Toast.makeText(AppApplication.getContext(), "Clicked " + eventBoardClick.getClickedPosition(), Toast.LENGTH_SHORT).show();
        mModel.updateBoard(eventBoardClick.getClickedPosition());
    }

    @Subscribe
    public void modelOnCurrentBoardLoad(EventOnBoardLoad eventOnBoardLoad) {
        Board board = eventOnBoardLoad.getBoard();

        Player currentPlayer = board.getCurrentPlayer();

        if (currentPlayer.equals(Player.PLAYER_2)) {
            mComputerIaModel.play();
        } else {
            mView.updateBoard(eventOnBoardLoad.getBoard());
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
