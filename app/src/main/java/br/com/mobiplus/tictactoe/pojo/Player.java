package br.com.mobiplus.tictactoe.pojo;

/**
 * Created by luis.fernandez on 3/17/16.
 */
public enum Player {
    PLAYER_HUMAN("X", 0),
    PLAYER_CPU("O", 1);

    private final String symbol;
    private final int id;

    Player(String symbol, int id) {
        this.symbol = symbol;
        this.id = id;
    }

    public String getSymbol() {
        return symbol;
    }
    public int getId() {
        return id;
    }

    public static Player getPlayerBySymbol(String symbol) {
        if (symbol.equals(PLAYER_CPU.getSymbol())) {
            return PLAYER_CPU;
        } else {
            return PLAYER_HUMAN;
        }
    }
}
