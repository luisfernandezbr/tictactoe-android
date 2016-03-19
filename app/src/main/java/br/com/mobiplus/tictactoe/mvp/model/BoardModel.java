package br.com.mobiplus.tictactoe.mvp.model;

import br.com.mobiplus.tictactoe.mvp.repo.BoardRepo;
import br.com.mobiplus.tictactoe.mvp.repo.IBoardRepo;
import br.com.mobiplus.tictactoe.otto.BusProvider;
import br.com.mobiplus.tictactoe.otto.event.EventOnBoardLoad;
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

    public void requestCurrentBoard() {
        BusProvider.getInstance().post(new EventOnBoardLoad(mRepo.getCurrentBoard()));
    }

    @Override
    public void updateBoard(int clickedPosition) {
        Board board = mRepo.getCurrentBoard();
        Player player = board.getCurrentPlayer();

        if (player.equals(Player.PLAYER_1)) {
            board.updateBoard(clickedPosition, "X");
        } else {
            board.updateBoard(clickedPosition, "O");
        }

        mRepo.updateBoard(board);
        BusProvider.getInstance().post(new EventOnBoardLoad(mRepo.getCurrentBoard()));
    }

    @Override
    public void verifyIfIsAFinishedMatch() {

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
                return value1.equals("X") ? Player.PLAYER_1 : Player.PLAYER_2;
            }

        }

        return null;
    }

    @Override
    public void restartGame() {
        mRepo.resetBoard();
        BusProvider.getInstance().post(new EventOnBoardLoad(mRepo.getCurrentBoard()));
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
