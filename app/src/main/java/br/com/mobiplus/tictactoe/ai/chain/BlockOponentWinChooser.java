package br.com.mobiplus.tictactoe.ai.chain;

import java.util.List;

import br.com.mobiplus.tictactoe.pojo.Board;
import br.com.mobiplus.tictactoe.pojo.BoardCell;
import br.com.mobiplus.tictactoe.pojo.BoardLine;

/**
 * Created by luis.fernandez on 3/19/16.
 */
public class BlockOponentWinChooser extends AbstractBestPlayChooser {

    @Override
    public int chooseBestPlay(Board board) {
        BoardLine[] boardLineArray = board.getBoardLineArray();

        //noinspection ForLoopReplaceableByForEach
        for (int i = 0; i < boardLineArray.length; i++) {
            List<BoardCell> boardCellList = boardLineArray[i].getBoardCellList();

            if (this.hasTwoCellsOccupied(boardCellList, "X")) {

                for (int j = 0; j < boardCellList.size(); j++) {
                    BoardCell boardCell = boardCellList.get(j);

                    if (boardCell.getValue() == null) {
                        return boardCell.getIndex();
                    }
                }
            }
        }

        return mNext.chooseBestPlay(board);
    }
}
