package br.com.mobiplus.tictactoe.pojo;

/**
 * Created by luis.fernandez on 3/17/16.
 */
public enum Player {
    PLAYER_HUMAN("X"),
    PLAYER_CPU("O");

    private final String symbol;

    Player(String symbol) {
        this.symbol = symbol;
    }

    public String getSymbol() {
        return symbol;
    }
}
