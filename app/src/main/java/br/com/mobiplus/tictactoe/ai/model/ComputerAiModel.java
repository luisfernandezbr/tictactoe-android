package br.com.mobiplus.tictactoe.ai.model;

import java.util.ArrayList;
import java.util.List;

import br.com.mobiplus.tictactoe.ai.chain.AbstractBestPlayChooser;
import br.com.mobiplus.tictactoe.ai.chain.PreventOpponentWinChooser;
import br.com.mobiplus.tictactoe.ai.chain.PreventOppositeCornerWinnerConditionChooser;
import br.com.mobiplus.tictactoe.ai.chain.PreventTwoWinnerConditionsChooser;
import br.com.mobiplus.tictactoe.ai.chain.CenterPlayChooser;
import br.com.mobiplus.tictactoe.ai.chain.CornerPlayChooser;
import br.com.mobiplus.tictactoe.ai.chain.CreateTwoWinnerConditionsChooser;
import br.com.mobiplus.tictactoe.ai.chain.RandomPlayChooser;
import br.com.mobiplus.tictactoe.ai.chain.WinPlayChooser;
import br.com.mobiplus.tictactoe.otto.BusProvider;
import br.com.mobiplus.tictactoe.otto.event.EventOnCpuPlay;
import br.com.mobiplus.tictactoe.pojo.Board;

/**
 * Created by luis.fernandez on 3/17/16.
 */
public class ComputerAiModel implements IComputerAiModel {

    AbstractBestPlayChooser winPlayChooser = new WinPlayChooser();
    AbstractBestPlayChooser preventOpponentWinChooser = new PreventOpponentWinChooser();
    AbstractBestPlayChooser createTwoWinnerConditionsChooser = new CreateTwoWinnerConditionsChooser();
    AbstractBestPlayChooser preventTwoWinnerConditionsChooser = new PreventTwoWinnerConditionsChooser();
    AbstractBestPlayChooser preventOppositeCornerWinnerConditionChooser = new PreventOppositeCornerWinnerConditionChooser();
    AbstractBestPlayChooser centerPlayChooser = new CenterPlayChooser();
    AbstractBestPlayChooser cornerPlayChooser = new CornerPlayChooser();
    AbstractBestPlayChooser randomPlayChooser = new RandomPlayChooser();

    @Override
    public void play(final Board board) {
        BusProvider.getInstance().post(new EventOnCpuPlay(this.defineNextIndexPlay(board)));
    }

    private int defineNextIndexPlay(Board board) {
        return this.getExpertMode().chooseBestPlay(board);
    }

    private AbstractBestPlayChooser getExpertMode() {
        List<AbstractBestPlayChooser> chainList = new ArrayList<>();
        chainList.add(randomPlayChooser);
        chainList.add(cornerPlayChooser);
        chainList.add(centerPlayChooser);

        chainList.add(preventTwoWinnerConditionsChooser);
        chainList.add(preventOppositeCornerWinnerConditionChooser);

        chainList.add(createTwoWinnerConditionsChooser);

        chainList.add(preventOpponentWinChooser);

        chainList.add(winPlayChooser);

        AbstractBestPlayChooser lastChooser = null;
        AbstractBestPlayChooser currentChooser = null;

        for (int i = 0; i < chainList.size(); i++) {
            currentChooser = chainList.get(i);

            if (lastChooser != null) {
                currentChooser.setNextInChain(lastChooser);
            }

            lastChooser = currentChooser;
        }

        return currentChooser;
    }

    private AbstractBestPlayChooser getMedium() {
        cornerPlayChooser.setNextInChain(randomPlayChooser);
        centerPlayChooser.setNextInChain(cornerPlayChooser);
        preventOppositeCornerWinnerConditionChooser.setNextInChain(centerPlayChooser);
        preventTwoWinnerConditionsChooser.setNextInChain(preventOppositeCornerWinnerConditionChooser);
        createTwoWinnerConditionsChooser.setNextInChain(preventTwoWinnerConditionsChooser);
        preventOpponentWinChooser.setNextInChain(createTwoWinnerConditionsChooser);
        winPlayChooser.setNextInChain(preventOpponentWinChooser);

        return winPlayChooser;
    }
}
