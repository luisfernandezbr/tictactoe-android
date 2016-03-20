package br.com.mobiplus.tictactoe.ai.chain;

import java.util.ArrayList;
import java.util.List;

import br.com.mobiplus.tictactoe.pojo.Board;
import br.com.mobiplus.tictactoe.pojo.BoardCell;
import br.com.mobiplus.tictactoe.pojo.BoardLine;

/**
 * Created by luis.fernandez on 3/20/16.
 */
public class BlockTwoWinnerConditionsChooser extends AbstractBestPlayChooser {

    @Override
    public int chooseBestPlay(Board board) {
        BoardLine[] boardLineArray = board.getBoardLineArray();

        List<BoardLine> opponentOneOccupiedCellList = new ArrayList<>();

        for (int i = 0; i < boardLineArray.length; i++) {
            BoardLine boardLine = boardLineArray[i];

            if (boardLine.hasOneOccupiedCell("X")) {
                opponentOneOccupiedCellList.add(boardLine);
            }
        }

        for (int i = 0; i < opponentOneOccupiedCellList.size(); i++) {
            BoardLine boardLine = opponentOneOccupiedCellList.get(i);
            List<BoardCell> boardCellList = boardLine.getBoardCellList();

            for (int j = 0; j < boardCellList.size(); j++) {
                BoardCell boardCell = boardCellList.get(j);

//                if (boardCell.) {
//
//                }
            }
        }


        return mNext.chooseBestPlay(board);
    }
}
