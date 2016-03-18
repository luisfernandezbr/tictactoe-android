package br.com.mobiplus.tictactoe.mvp.repo;

import br.com.mobiplus.tictactoe.pojo.Board;

/**
 * Created by luis.fernandez on 3/16/16.
 */
public interface IBoardRepo {
    Board getCurrentBoard();

    void updateBoard(Board board);

    void resetBoard();
}
