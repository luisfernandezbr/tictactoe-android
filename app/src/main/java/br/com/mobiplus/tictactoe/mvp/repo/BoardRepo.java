package br.com.mobiplus.tictactoe.mvp.repo;

import br.com.mobiplus.tictactoe.pojo.Board;

/**
 * Created by luis.fernandez on 3/16/16.
 */
public class BoardRepo implements IBoardRepo {

    private static Board mBoard = new Board();

    @Override
    public Board getCurrentBoard() {
        return mBoard;
    }

    @Override
    public void updateBoard(Board board) {
        BoardRepo.mBoard = board;
    }
}
