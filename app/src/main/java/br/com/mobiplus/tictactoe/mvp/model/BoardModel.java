package br.com.mobiplus.tictactoe.mvp.model;

import br.com.mobiplus.tictactoe.GameStateEnum;
import br.com.mobiplus.tictactoe.mvp.repo.BoardRepo;
import br.com.mobiplus.tictactoe.mvp.repo.IBoardRepo;
import br.com.mobiplus.tictactoe.otto.BusProvider;
import br.com.mobiplus.tictactoe.otto.event.EventOnGameStateChange;
import br.com.mobiplus.tictactoe.pojo.Board;
import br.com.mobiplus.tictactoe.pojo.Player;

/**
 * Created by luis.fernandez on 3/16/16.
 */
public class BoardModel implements IBoardModel {

    private IBoardRepo mRepo;

    public BoardModel() {
        this.mRepo = new BoardRepo();
    }


    @Override
    public void play(final Player player, int playedIndex) {
        final Board board = mRepo.getCurrentBoard();

        if (player.equals(Player.PLAYER_HUMAN)) {
            board.updateBoard(playedIndex, "X");
        } else {
            board.updateBoard(playedIndex, "O");
        }

        mRepo.updateBoard(board);

        board.searchGameState(new BoardGameStateSearcher(board, player));
    }

    private class BoardGameStateSearcher implements Board.IBoardGameStateSearcher {

        private Board board;
        private Player player;

        public BoardGameStateSearcher(final Board board, final Player player) {
            this.board = board;
            this.player = player;
        }

        @Override
        public void onNextPlayRequired() {
            GameStateEnum gameState;
            gameState = Player.PLAYER_HUMAN.equals(player) ? GameStateEnum.STATE_PLAYER_CPU_PLAY : GameStateEnum.STATE_PLAYER_HUMAN_PLAY;
            BusProvider.getInstance().post(new EventOnGameStateChange(board, gameState));
        }

        @Override
        public void onWinnerFounded() {
            GameStateEnum gameState;
            gameState = Player.PLAYER_CPU.equals(player) ? GameStateEnum.STATE_PLAYER_CPU_WINS : GameStateEnum.STATE_PLAYER_HUMAN_WINS;
            BusProvider.getInstance().post(new EventOnGameStateChange(board, gameState));
        }


        @Override
        public void onDraw() {
            BusProvider.getInstance().post(new EventOnGameStateChange(board, GameStateEnum.STATE_DRAW));
        }
    }

    @Override
    public void restartGame() {
        mRepo.resetBoard();
        BusProvider.getInstance().post(new EventOnGameStateChange(mRepo.getCurrentBoard(), GameStateEnum.STATE_PLAYER_HUMAN_PLAY));
    }

    @Override
    public void cpuStartingGame() {
        mRepo.resetBoard();
        BusProvider.getInstance().post(new EventOnGameStateChange(mRepo.getCurrentBoard(), GameStateEnum.STATE_PLAYER_CPU_PLAY));
    }
}
