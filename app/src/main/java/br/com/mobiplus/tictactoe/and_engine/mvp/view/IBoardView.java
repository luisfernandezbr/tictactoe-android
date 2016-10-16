package br.com.mobiplus.tictactoe.and_engine.mvp.view;

import br.com.mobiplus.tictactoe.pojo.Board;
import br.com.mobiplus.tictactoe.pojo.Player;

/**
 * Created by Gama on 15/10/2016.
 */
public interface IBoardView {
    void updateBoard(Board pBoard);
    void updateBoard(Board pBoard, Player pWinner);
}
