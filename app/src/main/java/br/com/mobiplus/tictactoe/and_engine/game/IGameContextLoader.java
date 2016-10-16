package br.com.mobiplus.tictactoe.and_engine.game;

/**
 * Created by Gama on 16/10/2016.
 */
public interface IGameContextLoader {
    GameElements getGameElements();
    GameScreen getGameScreen();
    GameFonts getGameFonts();
}
