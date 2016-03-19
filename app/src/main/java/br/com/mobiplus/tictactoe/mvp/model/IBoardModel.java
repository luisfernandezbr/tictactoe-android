package br.com.mobiplus.tictactoe.mvp.model;

import br.com.mobiplus.tictactoe.pojo.Player;

/**
 * Created by luis.fernandez on 3/16/16.
 */
public interface IBoardModel {

    void testPlay(Player player, int playedIndex);

    void restartGame();
}
