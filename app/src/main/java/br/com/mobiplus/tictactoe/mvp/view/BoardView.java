package br.com.mobiplus.tictactoe.mvp.view;

import android.app.Activity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import br.com.mobiplus.tictactoe.R;
import br.com.mobiplus.tictactoe.otto.BusProvider;
import br.com.mobiplus.tictactoe.otto.EventBoardClick;
import br.com.mobiplus.tictactoe.pojo.Board;

/**
 * Created by luis.fernandez on 3/16/16.
 */
public class BoardView extends BaseView implements IBoardView {

    private static int[] mButtonArray = {
            R.id.button_1, R.id.button_2, R.id.button_3,
            R.id.button_4, R.id.button_5, R.id.button_6,
            R.id.button_7, R.id.button_8, R.id.button_9
    };

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
                    Button button = (Button) v;
                    //button.setText("X");
                    BusProvider.getInstance().post(new EventBoardClick(clickedPosition));
                }
            });
            findViewById(mButtonArray[i]).setTag(i);
        }
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
                }
            }
        }
    }
}
