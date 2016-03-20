package br.com.mobiplus.tictactoe.ai.chain;

import java.util.List;

import br.com.mobiplus.tictactoe.pojo.Board;
import br.com.mobiplus.tictactoe.pojo.BoardCell;

/**
 * Created by luis.fernandez on 3/19/16.
 */
public abstract class AbstractBestPlayChooser {

    protected AbstractBestPlayChooser mNext;

    public abstract int chooseBestPlay(Board board);

    protected boolean hasNext() {
        return mNext != null;
    }

    public void setNextInChain(AbstractBestPlayChooser next) {
        this.mNext = next;
    }

    protected boolean hasTwoCellsOccupied(List<BoardCell> boardCellList, String cellValue) {
        boolean result = false;

        int count = 0;

        for (int i = 0; i < boardCellList.size(); i++) {
            BoardCell boardCell = boardCellList.get(i);

            if (cellValue.equals(boardCell.getValue())) {
                count++;

                if (count == 2) {
                    result = true;
                    break;
                }
            }
        }

        return result;
    }
}
