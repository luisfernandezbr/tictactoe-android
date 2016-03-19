package br.com.mobiplus.tictactoe.ai.chain;

import br.com.mobiplus.tictactoe.pojo.Board;

/**
 * Created by luis.fernandez on 3/19/16.
 */
public abstract class AbstractBestPlayChooser {

    protected AbstractBestPlayChooser mNext;

    public abstract int chooseBestPlay(Board board);

    public void setNext(AbstractBestPlayChooser next) {
        this.mNext = next;
    }
}
