package br.com.mobiplus.tictactoe.otto.event;

import br.com.mobiplus.tictactoe.GameStateEnum;
import br.com.mobiplus.tictactoe.pojo.Board;

/**
 * Created by luis.fernandez on 3/19/16.
 */
public class EventOnGameStateChange {

    private Board board;
    private GameStateEnum gameState;

    public EventOnGameStateChange(Board board, GameStateEnum gameState) {
        this.board = board;
        this.gameState = gameState;
    }

    public Board getBoard() {
        return board;
    }

    public GameStateEnum getGameState() {
        return gameState;
    }
}
