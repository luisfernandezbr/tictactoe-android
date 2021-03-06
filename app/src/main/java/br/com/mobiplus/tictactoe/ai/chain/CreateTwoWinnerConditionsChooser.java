package br.com.mobiplus.tictactoe.ai.chain;

import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import br.com.mobiplus.tictactoe.pojo.Board;
import br.com.mobiplus.tictactoe.pojo.BoardCell;
import br.com.mobiplus.tictactoe.pojo.BoardLine;
import br.com.mobiplus.tictactoe.pojo.Player;

/**
 * Created by luis.fernandez on 3/19/16.
 */
public class CreateTwoWinnerConditionsChooser extends AbstractBestPlayChooser {

    @Override
    public int chooseBestPlay(Board board) {
        Log.i(TAG, "CreateTwoWinnerConditionsChooser");

        if (board.getCenterCell().isEmpty()) {

            BoardCell[] cornerCells = board.getCornerCells();

            for (int i = 0; i < cornerCells.length; i++) {
                BoardCell cornerCell = cornerCells[i];

                if (cornerCell.hasValue(Player.PLAYER_CPU.getSymbol())) {
                    List<BoardCell> freeCornerCells = board.getFreeCornerCells();

                    for (int j = 0; j < freeCornerCells.size(); j++) {
                        BoardCell edgeBetween = board.getEdgeBetween(cornerCell, freeCornerCells.get(j));

                        if (edgeBetween.isEmpty()) {
                            return freeCornerCells.get(j).getIndex();
                        }
                    }
                }
            }
        }

        List<BoardLine> oneOccupiedCellList = new ArrayList<>();

        BoardLine[] boardLineArray = board.getBoardLineArray();

        for (int i = 0; i < boardLineArray.length; i++) {
            BoardLine boardLine = boardLineArray[i];

            if (boardLine.hasOneOccupiedCell(Player.PLAYER_CPU.getSymbol())) {
                oneOccupiedCellList.add(boardLine);
            }
        }

        for (int i = 0; i < oneOccupiedCellList.size(); i++) {
            BoardLine boardLineOut = oneOccupiedCellList.get(i);

            for (int j = 0; j < oneOccupiedCellList.size(); j++) {
                if (j == i) {
                    continue;
                }

                BoardLine boardLineIn = oneOccupiedCellList.get(j);

                List<BoardCell> boardCellListOut = boardLineOut.getBoardCellList();
                List<BoardCell> boardCellListIn = boardLineIn.getBoardCellList();

                for (int k = 0; k < boardCellListOut.size(); k++) {
                    BoardCell boardCellOut = boardCellListOut.get(k);

                    for (int z = 0; z < boardCellListIn.size(); z++) {
                        BoardCell boardCellIn = boardCellListIn.get(z);

                        if (boardCellOut.isEmpty() && boardCellIn.isEmpty()) {

                            if (boardCellOut.getIndex() == boardCellIn.getIndex()) {
                                return boardCellOut.getIndex();
                            }
                        }
                    }
                }
            }
        }

        return super.handleNext(board);
    }
}
