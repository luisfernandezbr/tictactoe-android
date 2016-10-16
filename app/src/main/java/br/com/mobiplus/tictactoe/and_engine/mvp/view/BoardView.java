package br.com.mobiplus.tictactoe.and_engine.mvp.view;

import android.support.annotation.StringRes;

import br.com.mobiplus.tictactoe.R;
import br.com.mobiplus.tictactoe.pojo.Board;
import br.com.mobiplus.tictactoe.pojo.Player;

/**
 * Created by Gama on 15/10/2016.
 */
public class BoardView implements IBoardView {

    public BoardView() {
    }

    @Override
    public void updateBoard(Board board) {
        String[][] boardState = board.getBoard();

        for (int row = 0; row < boardState.length; row++) {
            for (int col = 0; col < boardState[row].length; col++) {
                String mark = boardState[row][col];

                if (mark != null) {
                    int index = (row * 3) + col;

                    if (mark.equals(Player.PLAYER_HUMAN)) {
                        // ANIMATE INDEX
                    } else {
                        // ANIMATE INDEX
                    }
                } else {

                }
            }
        }
    }

    @Override
    public void updateBoard(Board board, Player winner) {
        this.updateBoard(board);
        this.defineWinner(winner);
    }

    private void defineWinner(Player pPlayer) {
        if (pPlayer != null) {
            this.showResult(pPlayer.equals(Player.PLAYER_CPU) ? R.string.mp_ttt_matchresult_winner_cpu : R.string.mp_ttt_matchresult_winner_human);
        } else {
            this.showResult(R.string.mp_ttt_matchresult_draw);
        }
    }

    private void showResult(@StringRes int stringResId) {

    }
}