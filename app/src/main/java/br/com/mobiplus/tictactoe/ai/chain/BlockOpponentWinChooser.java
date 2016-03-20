package br.com.mobiplus.tictactoe.ai.chain;

import br.com.mobiplus.tictactoe.pojo.Board;

/**
 * Created by luis.fernandez on 3/19/16.
 */
public class BlockOpponentWinChooser extends WinPlayChooser {

    @Override
    public int chooseBestPlay(Board board) {
        return super.chooseBestPlay(board);
    }

    @Override
    protected String getSymbolToSearch() {
        return "X";
    }
}
