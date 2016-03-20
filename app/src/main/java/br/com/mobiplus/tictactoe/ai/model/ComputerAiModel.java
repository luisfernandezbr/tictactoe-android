package br.com.mobiplus.tictactoe.ai.model;

import br.com.mobiplus.tictactoe.ai.chain.AbstractBestPlayChooser;
import br.com.mobiplus.tictactoe.ai.chain.BlockOpponentWinChooser;
import br.com.mobiplus.tictactoe.ai.chain.RandomPlayChooser;
import br.com.mobiplus.tictactoe.ai.chain.WinPlayChooser;
import br.com.mobiplus.tictactoe.otto.BusProvider;
import br.com.mobiplus.tictactoe.otto.event.EventOnCpuPlay;
import br.com.mobiplus.tictactoe.pojo.Board;

/**
 * Created by luis.fernandez on 3/17/16.
 */
public class ComputerAiModel implements IComputerAiModel {

    @Override
    public void play(final Board board) {
        BusProvider.getInstance().post(new EventOnCpuPlay(this.defineNextIndexPlay(board)));
    }

    private int defineNextIndexPlay(Board board) {
        AbstractBestPlayChooser winPlayChooser = new WinPlayChooser();
        AbstractBestPlayChooser blockOponentWinChooser = new BlockOpponentWinChooser();
        AbstractBestPlayChooser randomPlayChooser = new RandomPlayChooser();


        blockOponentWinChooser.setNextInChain(randomPlayChooser);
        winPlayChooser.setNextInChain(blockOponentWinChooser);

        return winPlayChooser.chooseBestPlay(board);
    }
}
