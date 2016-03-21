package br.com.mobiplus.tictactoe.ai.chain;

import br.com.mobiplus.tictactoe.pojo.Board;

/**
 * Created by luis.fernandez on 3/19/16.
 */
public abstract class AbstractBestPlayChooser {

    protected static final String TAG = "AbstractBestPlayChooser";

    protected AbstractBestPlayChooser mNext;

    public abstract int chooseBestPlay(Board board);

    public void setNextInChain(AbstractBestPlayChooser next) {
        this.mNext = next;
    }

    protected int handleNext(Board board) {
        if (mNext != null) {
            return mNext.chooseBestPlay(board);
        } else {
            return new RandomPlayChooser().chooseBestPlay(board);
        }
    }
}
