package br.com.mobiplus.tictactoe.ai.chain;

import android.util.Log;

import java.util.List;

import br.com.mobiplus.tictactoe.pojo.Board;
import br.com.mobiplus.tictactoe.pojo.BoardCell;

/**
 * Created by luis.fernandez on 3/20/16.
 */
public class CornerPlayChooser extends AbstractBestPlayChooser {

    @Override
    public int chooseBestPlay(Board board) {
        Log.i(TAG, "CornerPlayChooser");

        List<BoardCell> freeCornerCells = board.getFreeCornerCells();

        for (int i = 0; i < freeCornerCells.size(); i++) {
            return freeCornerCells.get(i).getIndex();
        }

//        BoardCell[] cornerCellsArray = board.getCornerCells();
//
//        for (int i = 0; i < cornerCellsArray.length; i++) {
//            BoardCell boardCell = cornerCellsArray[i];
//
//            if (boardCell.isEmpty()) {
//                return boardCell.getIndex();
//            }
//        }


        return mNext.chooseBestPlay(board);
    }
}
