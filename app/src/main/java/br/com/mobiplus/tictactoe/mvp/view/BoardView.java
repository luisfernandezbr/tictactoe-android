package br.com.mobiplus.tictactoe.mvp.view;

import android.app.Activity;

import br.com.mobiplus.tictactoe.R;

/**
 * Created by luis.fernandez on 3/16/16.
 */
public class BoardView extends BaseView implements IBoardView {

    public BoardView(Activity activity) {
        super(activity);
    }

    @Override
    protected int getLayoutResId() {
        return R.layout.activity_board;
    }

    @Override
    protected void initViews() {

    }
}
