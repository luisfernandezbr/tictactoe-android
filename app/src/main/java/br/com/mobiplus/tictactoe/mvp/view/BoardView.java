package br.com.mobiplus.tictactoe.mvp.view;

import android.app.Activity;
import android.support.annotation.StringRes;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import br.com.mobiplus.tictactoe.R;
import br.com.mobiplus.tictactoe.otto.BusProvider;
import br.com.mobiplus.tictactoe.otto.event.EventOnHumanPlay;
import br.com.mobiplus.tictactoe.otto.event.EventOnCpuStart;
import br.com.mobiplus.tictactoe.otto.event.EventOnRestartGame;
import br.com.mobiplus.tictactoe.pojo.Board;
import br.com.mobiplus.tictactoe.pojo.Player;

/**
 * Created by luis.fernandez on 3/16/16.
 */
public class BoardView extends BaseView implements IBoardView {

    private static int[] mButtonArray = {
            R.id.button_1, R.id.button_2, R.id.button_3,
            R.id.button_4, R.id.button_5, R.id.button_6,
            R.id.button_7, R.id.button_8, R.id.button_9
    };

    private Button buttonCpuStart;

    public BoardView(Activity activity) {
        super(activity);
    }

    @Override
    protected int getLayoutResId() {
        return R.layout.activity_board;
    }

    @Override
    protected void initViews() {
        for (int i = 0; i < mButtonArray.length; i++) {
            findViewById(mButtonArray[i]).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int clickedPosition = (int) v.getTag();
                    BoardView.this.enableCpuStartButton(false);
                    BusProvider.getInstance().post(new EventOnHumanPlay(clickedPosition));
                }
            });
            findViewById(mButtonArray[i]).setTag(i);
        }

        buttonCpuStart = (Button) findViewById(R.id.buttonCpuStart);
        buttonCpuStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                BoardView.this.enableBoardCellsClick(true);
                BoardView.this.resetResultView();
                BoardView.this.enableCpuStartButton(false);
                BusProvider.getInstance().post(new EventOnCpuStart());
            }
        });

        final Button buttonRestart = (Button) findViewById(R.id.buttonRestart);
        buttonRestart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                BoardView.this.enableBoardCellsClick(true);
                BoardView.this.resetResultView();
                BoardView.this.enableCpuStartButton(true);
                BusProvider.getInstance().post(new EventOnRestartGame());
            }
        });
    }

    @Override
    public void updateBoard(Board board) {
        String[][] boardState = board.getBoard();

        for (int row = 0; row < boardState.length; row++) {
            for (int col = 0; col < boardState[row].length; col++) {
                int index = (row * 3) + col;

                TextView textView = (TextView) findViewById(mButtonArray[index]);
                textView.setText(boardState[row][col]);

                if (boardState[row][col] != null) {
                    textView.setClickable(false);
                } else {
                    textView.setClickable(true);
                }
            }
        }
    }

    @Override
    public void updateBoard(Board board, Player winner) {
        this.enableBoardCellsClick(false);
        this.updateBoard(board);
        this.defineWinner(winner);

    }

    @Override
    public void finishOnDraw(Board board) {
        this.enableBoardCellsClick(false);
        this.updateBoard(board);
        this.defineResultDraw();
    }

    private void defineResultDraw() {
        this.enableCpuStartButton(true);
        this.showResult(R.string.mp_ttt_matchresult_draw);
    }

    private void defineWinner(Player player) {
        this.enableCpuStartButton(true);
        this.showResult(player.equals(Player.PLAYER_CPU) ? R.string.mp_ttt_matchresult_winner_cpu : R.string.mp_ttt_matchresult_winner_human);
    }

    private void enableCpuStartButton(boolean enable) {
        buttonCpuStart.setClickable(enable);
        buttonCpuStart.setEnabled(enable);
    }

    private void resetResultView() {
        ViewGroup viewGroup = (ViewGroup) findViewById(R.id.viewGroupMatchResult);
        viewGroup.setVisibility(View.GONE);

        TextView textResult = (TextView) findViewById(R.id.textMatchResult);
        textResult.setVisibility(View.GONE);
        textResult.setText("");
    }

    private void showResult(@StringRes int stringResId) {
        ViewGroup viewGroup = (ViewGroup) findViewById(R.id.viewGroupMatchResult);
        viewGroup.setVisibility(View.VISIBLE);

        TextView textResult = (TextView) findViewById(R.id.textMatchResult);
        textResult.setVisibility(View.VISIBLE);
        textResult.setText(stringResId);
        textResult.setTextColor(mActivity.getResources().getColor(this.getResultColor(stringResId)));
    }

    private void enableBoardCellsClick(boolean enable) {
        for (int i = 0; i < mButtonArray.length; i++) {
            findViewById(mButtonArray[i]).setClickable(enable);
            findViewById(mButtonArray[i]).setEnabled(enable);
        }
    }

    private int getResultColor(@StringRes int stringResId) {
        switch (stringResId) {
            case R.string.mp_ttt_matchresult_winner_cpu : {
                return android.R.color.holo_red_dark;
            }
            case R.string.mp_ttt_matchresult_winner_human : {
                return android.R.color.holo_green_dark;
            }
            default: {
                return android.R.color.background_dark;
            }
        }
    }
}
