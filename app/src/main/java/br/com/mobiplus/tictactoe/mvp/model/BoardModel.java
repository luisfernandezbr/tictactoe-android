package br.com.mobiplus.tictactoe.mvp.model;

import br.com.mobiplus.tictactoe.mvp.repo.BoardRepo;
import br.com.mobiplus.tictactoe.mvp.repo.IBoardRepo;

/**
 * Created by luis.fernandez on 3/16/16.
 */
public class BoardModel implements IBoardModel {

    private IBoardRepo mRepo;

    public BoardModel() {
        this.mRepo = new BoardRepo();
    }

    public BoardModel(IBoardRepo repo) {
        this.mRepo = repo;
    }
}
