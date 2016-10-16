package br.com.mobiplus.tictactoe.mvp.presenter;

import com.squareup.otto.Subscribe;

import br.com.mobiplus.tictactoe.GameStateEnum;
import br.com.mobiplus.tictactoe.ai.model.ComputerAiModel;
import br.com.mobiplus.tictactoe.ai.model.IComputerAiModel;
import br.com.mobiplus.tictactoe.and_engine.mvp.view.BoardView;
import br.com.mobiplus.tictactoe.mvp.model.BoardModel;
import br.com.mobiplus.tictactoe.mvp.model.IBoardModel;
import br.com.mobiplus.tictactoe.otto.BusProvider;
import br.com.mobiplus.tictactoe.otto.event.EventOnCpuPlay;
import br.com.mobiplus.tictactoe.otto.event.EventOnCpuStart;
import br.com.mobiplus.tictactoe.otto.event.EventOnGameStateChange;
import br.com.mobiplus.tictactoe.otto.event.EventOnHumanPlay;
import br.com.mobiplus.tictactoe.otto.event.EventOnRestartGame;
import br.com.mobiplus.tictactoe.pojo.Board;
import br.com.mobiplus.tictactoe.pojo.Player;

/**
 * Created by Gama on 15/10/2016.
 */
public class AndEngineBoardPresenter implements IBoardPresenter {

    private IBoardModel mModel;
    private IComputerAiModel mComputerAiModel;
    private BoardView mBoardView;

    /**
     * Creating dependencies instances here for simplicity
     */
    public AndEngineBoardPresenter() {
        this.mModel = new BoardModel();
        this.mComputerAiModel = new ComputerAiModel();
        this.mBoardView = new BoardView();
    }

    @Subscribe
    public void viewOnCpuStart(EventOnCpuStart eventOnCpuStart) {
        mModel.cpuStartingGame();
    }

    @Subscribe
    public void viewOnHumanPlay(EventOnHumanPlay eventOnHumanPlay) {
        mModel.play(Player.PLAYER_HUMAN, eventOnHumanPlay.getPlayedIndex());
    }

    @Subscribe
    public void viewOnRestartGame(EventOnRestartGame eventOnRestartGame) {
        mModel.restartGame();
    }

    @Subscribe
    public void modelOnGameStateChange(EventOnGameStateChange eventOnGameStateChange) {
        Board board = eventOnGameStateChange.getBoard();
        GameStateEnum currentState = eventOnGameStateChange.getGameState();

        if (GameStateEnum.STATE_PLAYER_HUMAN_PLAY.equals(currentState)) {
            mBoardView.updateBoard(board);

        } else if (GameStateEnum.STATE_PLAYER_CPU_PLAY.equals(currentState)) {
            mComputerAiModel.play(board);

        } else if (GameStateEnum.STATE_PLAYER_HUMAN_WINS.equals(currentState)) {
            mBoardView.updateBoard(board, Player.PLAYER_HUMAN);

        } else if (GameStateEnum.STATE_PLAYER_CPU_WINS.equals(currentState)) {
            mBoardView.updateBoard(board, Player.PLAYER_CPU);

        } else if (GameStateEnum.STATE_DRAW.equals(currentState)) {
            mBoardView.updateBoard(board, null);
        }
    }

    @Subscribe
    public void aiOnCpuPlay(EventOnCpuPlay eventOnCpuPlay) {
        mModel.play(Player.PLAYER_CPU, eventOnCpuPlay.getPlayedIndex());
    }

    @Override
    public void onStart() {
        BusProvider.getInstance().register(this);
    }

    @Override
    public void onStop() {
        BusProvider.getInstance().unregister(this);
    }

}
