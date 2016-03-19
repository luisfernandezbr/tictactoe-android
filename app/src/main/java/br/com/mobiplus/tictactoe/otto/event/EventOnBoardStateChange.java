package br.com.mobiplus.tictactoe.otto.event;

import br.com.mobiplus.tictactoe.pojo.Board;

/**
 * Created by luis.fernandez on 3/16/16.
 */
public class EventOnBoardStateChange {

    private Board board;

    public EventOnBoardStateChange(Board board) {
        this.board = board;
    }

    public Board getBoard() {
        return board;
    }
}
