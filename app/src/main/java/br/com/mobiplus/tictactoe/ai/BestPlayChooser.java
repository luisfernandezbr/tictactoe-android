package br.com.mobiplus.tictactoe.ai;

import br.com.mobiplus.tictactoe.pojo.Board;

/**
 * Created by luis.fernandez on 3/19/16.
 */
public abstract class BestPlayChooser {

    protected BestPlayChooser mNext;

    public abstract int chooseBestPlay(Board board);

    public void setNext(BestPlayChooser next) {
        this.mNext = next;
    }
}
