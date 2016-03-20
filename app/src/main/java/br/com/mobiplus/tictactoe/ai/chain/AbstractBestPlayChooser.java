package br.com.mobiplus.tictactoe.ai.chain;

import br.com.mobiplus.tictactoe.pojo.Board;

/**
 * Created by luis.fernandez on 3/19/16.
 */
public abstract class AbstractBestPlayChooser {

    protected static final String TAG = "AbstractBestPlayChooser";

    protected AbstractBestPlayChooser mNext;

    public abstract int chooseBestPlay(Board board);

    protected boolean hasNext() {
        return mNext != null;
    }

    public void setNextInChain(AbstractBestPlayChooser next) {
        this.mNext = next;
    }
}
