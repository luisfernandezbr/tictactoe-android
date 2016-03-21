package br.com.mobiplus.tictactoe.ai.chain;

import android.util.Log;

import br.com.mobiplus.tictactoe.pojo.Board;

/**
 * Created by luis.fernandez on 3/19/16.
 */
public class PreventOpponentWinChooser extends WinPlayChooser {

    @Override
    public int chooseBestPlay(Board board) {
        Log.i(TAG, "BlockOpponentWinChooser");
        return super.chooseBestPlay(board);
    }

    @Override
    protected String getSymbolToSearch() {
        return "X";
    }
}
