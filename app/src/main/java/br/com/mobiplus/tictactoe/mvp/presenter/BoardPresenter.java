package br.com.mobiplus.tictactoe.mvp.presenter;

import android.app.Activity;

import br.com.mobiplus.tictactoe.mvp.model.BoardModel;
import br.com.mobiplus.tictactoe.mvp.model.IBoardModel;
import br.com.mobiplus.tictactoe.mvp.view.BoardView;
import br.com.mobiplus.tictactoe.mvp.view.IBoardView;
import br.com.mobiplus.tictactoe.otto.BusProvider;

/**
 * Created by luis.fernandez on 3/16/16.
 */
public class BoardPresenter implements IBoardPresenter {

    private IBoardView mView;
    private IBoardModel mModel;

    public BoardPresenter(Activity activity) {
        this.mView = new BoardView(activity);
        this.mModel = new BoardModel();
    }

    public BoardPresenter(IBoardView view, IBoardModel model) {
        this.mView = view;
        this.mModel = model;
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
