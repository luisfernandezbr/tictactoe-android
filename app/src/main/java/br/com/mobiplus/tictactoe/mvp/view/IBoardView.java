package br.com.mobiplus.tictactoe.mvp.view;

import br.com.mobiplus.tictactoe.pojo.Board;
import br.com.mobiplus.tictactoe.pojo.Player;

/**
 * Created by luis.fernandez on 3/16/16.
 */
public interface IBoardView {
    void updateBoard(Board board);

    void defineWinner(Player player);
}
