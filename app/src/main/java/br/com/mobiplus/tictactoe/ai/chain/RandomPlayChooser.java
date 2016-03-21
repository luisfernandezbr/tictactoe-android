package br.com.mobiplus.tictactoe.ai.chain;

import android.util.Log;

import java.util.List;
import java.util.Random;

import br.com.mobiplus.tictactoe.pojo.Board;
import br.com.mobiplus.tictactoe.pojo.BoardCell;

/**
 * Created by luis.fernandez on 3/19/16.
 */
public class RandomPlayChooser extends AbstractBestPlayChooser {
    @Override
    public int chooseBestPlay(Board board) {
        Log.i(TAG, "RandomPlayChooser");

        List<BoardCell> freeCells = board.getFreeCells();

        int size = freeCells.size();
        Random random = new Random();
        int index = random.nextInt(size);

        return freeCells.get(index).getIndex();
    }
}
