package br.com.mobiplus.tictactoe.android.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import br.com.mobiplus.tictactoe.mvp.presenter.BoardPresenter;
import br.com.mobiplus.tictactoe.mvp.presenter.IBoardPresenter;

public class BoardActivity extends AppCompatActivity {

    private IBoardPresenter mPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mPresenter = new BoardPresenter(BoardActivity.this);
    }

    @Override
    protected void onStart() {
        super.onStart();
        mPresenter.onStart();
    }

    @Override
    protected void onStop() {
        super.onStop();
        mPresenter.onStop();
    }
}
