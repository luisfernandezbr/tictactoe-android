package br.com.mobiplus.tictactoe.ai.chain;

import java.util.ArrayList;
import java.util.List;

import br.com.mobiplus.tictactoe.pojo.Board;
import br.com.mobiplus.tictactoe.pojo.BoardCell;
import br.com.mobiplus.tictactoe.pojo.BoardLine;

/**
 * Created by luis.fernandez on 3/19/16.
 */
public class CreateTwoWinnerConditionsChooser extends AbstractBestPlayChooser {

    @Override
    public int chooseBestPlay(Board board) {
        List<BoardLine> twoOccupiedCellsList = new ArrayList<>();
        List<BoardLine> oneOccupiedCellList = new ArrayList<>();

        BoardLine[] boardLineArray = board.getBoardLineArray();

        for (int i = 0; i < boardLineArray.length; i++) {
            BoardLine boardLine = boardLineArray[i];

            if (boardLine.hasTwoOccupiedCells("O")) {
                twoOccupiedCellsList.add(boardLine);
                continue;
            }

            if (boardLine.hasOneOccupiedCell("O")) {
                oneOccupiedCellList.add(boardLine);
            }
        }

        for (int i = 0; i < oneOccupiedCellList.size(); i++) {
            BoardLine boardLineOne = oneOccupiedCellList.get(i);

            for (int j = 0; j < twoOccupiedCellsList.size(); j++) {
                BoardLine boardLineTwo = twoOccupiedCellsList.get(j);

                List<BoardCell> boardCellListOne = boardLineOne.getBoardCellList();
                List<BoardCell> boardCellListTwo = boardLineTwo.getBoardCellList();

                for (int k = 0; k < boardCellListOne.size(); k++) {
                    BoardCell boardCellOne = boardCellListOne.get(k);

                    for (int z = 0; z < boardCellListTwo.size(); z++) {
                        BoardCell boardCellTwo = boardCellListTwo.get(z);

                        if (boardCellOne.getValue() == null && boardCellTwo.getValue() == null) {
                            return boardCellOne.getIndex();
                        }
                    }
                }
            }
        }

        return mNext.chooseBestPlay(board);
    }
}
