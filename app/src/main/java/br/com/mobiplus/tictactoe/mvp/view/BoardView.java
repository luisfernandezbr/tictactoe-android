package br.com.mobiplus.tictactoe.mvp.view;

import android.app.Activity;
import android.view.View;

import br.com.mobiplus.tictactoe.R;
import br.com.mobiplus.tictactoe.otto.BusProvider;
import br.com.mobiplus.tictactoe.otto.EventBoardClick;

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
        int[] buttonArray = {
                R.id.button_1, R.id.button_2, R.id.button_3,
                R.id.button_4, R.id.button_5, R.id.button_6,
                R.id.button_7, R.id.button_8, R.id.button_9
        };

        for (int i = 0; i < buttonArray.length; i++) {
            findViewById(buttonArray[i]).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int clickedPosition = (int) v.getTag();
                    BusProvider.getInstance().post(new EventBoardClick(clickedPosition));
                }
            });
            findViewById(buttonArray[i]).setTag(i);
        }
    }

}
