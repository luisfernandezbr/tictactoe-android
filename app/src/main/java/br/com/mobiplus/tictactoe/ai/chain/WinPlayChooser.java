package br.com.mobiplus.tictactoe.ai.chain;

import android.util.Log;

import java.util.List;

import br.com.mobiplus.tictactoe.pojo.Board;
import br.com.mobiplus.tictactoe.pojo.BoardCell;
import br.com.mobiplus.tictactoe.pojo.BoardLine;
import br.com.mobiplus.tictactoe.pojo.Player;

/**
 * Created by luis.fernandez on 3/19/16.
 */
public class WinPlayChooser extends AbstractBestPlayChooser {

    @Override
    public int chooseBestPlay(Board board) {
        Log.i(TAG, "WinPlayChooser");

        BoardLine[] boardLineArray = board.getBoardLineArray();

        for (int i = 0; i < boardLineArray.length; i++) {
            BoardLine boardLine = boardLineArray[i];

            if (boardLine.hasTwoOccupiedCells(this.getSymbolToSearch())) {
                List<BoardCell> boardCellList = boardLine.getBoardCellList();

                for (int j = 0; j < boardCellList.size(); j++) {
                    BoardCell boardCell = boardCellList.get(j);

                    if (boardCell.isEmpty()) {
                        return boardCell.getIndex();
                    }
                }
            }
        }

        return super.handleNext(board);
    }

    protected String getSymbolToSearch() {
        return Player.PLAYER_CPU.getSymbol();
    }
}
