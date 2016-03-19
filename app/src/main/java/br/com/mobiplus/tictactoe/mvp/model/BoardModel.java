package br.com.mobiplus.tictactoe.mvp.model;

import br.com.mobiplus.tictactoe.GameStateEnum;
import br.com.mobiplus.tictactoe.mvp.repo.BoardRepo;
import br.com.mobiplus.tictactoe.mvp.repo.IBoardRepo;
import br.com.mobiplus.tictactoe.otto.BusProvider;
import br.com.mobiplus.tictactoe.otto.event.EventOnGameStateChange;
import br.com.mobiplus.tictactoe.pojo.Board;
import br.com.mobiplus.tictactoe.pojo.BoardLine;
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
    public void play(Player player, int playedIndex) {
        Board board = mRepo.getCurrentBoard();

        if (player.equals(Player.PLAYER_USER)) {
            board.updateBoard(playedIndex, "X");
        } else {
            board.updateBoard(playedIndex, "O");
        }

        mRepo.updateBoard(board);

        if (player.equals(Player.PLAYER_COMPUTER)) {

            if (hasAWinner() != null) {
                BusProvider.getInstance().post(new EventOnGameStateChange(mRepo.getCurrentBoard(), GameStateEnum.STATE_PLAYER_CPU_WINS));
            } else {
                BusProvider.getInstance().post(new EventOnGameStateChange(mRepo.getCurrentBoard(), GameStateEnum.STATE_PLAYER_HUMAN_PLAY));
            }

        } else {

            if (hasAWinner() != null) {
                BusProvider.getInstance().post(new EventOnGameStateChange(mRepo.getCurrentBoard(), GameStateEnum.STATE_PLAYER_HUMAN_WINS));
            } else {
                BusProvider.getInstance().post(new EventOnGameStateChange(mRepo.getCurrentBoard(), GameStateEnum.STATE_PLAYER_CPU_PLAY));
            }
        }
    }

    @Override
    public void verifyGameState() {

    }


    @Override
    public Player hasAWinner() {
        Board board = mRepo.getCurrentBoard();

        String [][] boardState = board.getBoard();
        String [] boardStatePositions = new String[9];

        int [][] winCombs = {
                {0, 1, 2}, {3, 4, 5}, {6, 7, 8}, {0, 3, 6}, {1, 4, 7}, {2, 5, 8}, {0, 4, 8}, {2, 4, 6}
        };

        for (int row = 0; row < boardState.length; row++) {
            for (int col = 0; col < boardState[row].length; col++) {
                int index = (row * 3) + col;

                boardStatePositions[index] = boardState[row][col];
            }
        }

        for (int i = 0; i < winCombs.length; i++) {
            String value1 = boardStatePositions[winCombs[i][0]];
            String value2 = boardStatePositions[winCombs[i][1]];
            String value3 = boardStatePositions[winCombs[i][2]];

            if (value1 != null && value1.equals(value2) && value2.equals(value3)) {
                return value1.equals("X") ? Player.PLAYER_USER : Player.PLAYER_COMPUTER;
            }

        }

        return null;
    }

    @Override
    public void restartGame() {
        mRepo.resetBoard();
        BusProvider.getInstance().post(new EventOnGameStateChange(mRepo.getCurrentBoard(), GameStateEnum.STATE_PLAYER_HUMAN_PLAY));
    }

    @Override
    public void iterateTest() {
        Board board = mRepo.getCurrentBoard();
        board.interateOverLines(new Board.IBoardIterator() {
            @Override
            public void onNextLine(BoardLine boardLine) {
                System.out.println(boardLine.toString());
                if (boardLine.isAWinnerLine()) {

                }
            }
        });
    }
}
