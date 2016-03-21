package br.com.mobiplus.tictactoe.ai.chain;

import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import br.com.mobiplus.tictactoe.pojo.Board;
import br.com.mobiplus.tictactoe.pojo.BoardCell;
import br.com.mobiplus.tictactoe.pojo.BoardLine;
import br.com.mobiplus.tictactoe.pojo.Player;

/**
 * Created by luis.fernandez on 3/20/16.
 */
public class PreventTwoWinnerConditionsChooser extends AbstractBestPlayChooser {

    @Override
    public int chooseBestPlay(Board board) {
        Log.i(TAG, "PreventTwoWinnerConditionsChooser");

        List<BoardLine> opponentOneOccupiedLineList = board.getOneOccupiedLineList(Player.PLAYER_HUMAN.getSymbol());
        List<BoardCell> candidates = new ArrayList<>(2);

        if (opponentOneOccupiedLineList.size() > 1) {
            for (int i = 0; i < opponentOneOccupiedLineList.size(); i++) {
                BoardLine boardLineOut = opponentOneOccupiedLineList.get(i);

                for (int j = 0; j < opponentOneOccupiedLineList.size(); j++) {
                    if (i == j) {
                        continue;
                    }

                    BoardLine boardLineIn = opponentOneOccupiedLineList.get(j);
                    BoardCell intersectionBoardCell = boardLineOut.getEmptyIntersection(boardLineIn);

                    if (intersectionBoardCell != null) {
                        candidates.add(intersectionBoardCell);
                    }
                }
            }
        }

        BoardCell selectedCell = null;

        for (int i = 0; i < candidates.size(); i++) {
            if (selectedCell == null) {
                selectedCell = candidates.get(i);

                if (selectedCell.isCornerCell()) {
                    break;
                }
                continue;
            }

            if (candidates.get(i).isCornerCell() && !selectedCell.isCornerCell()) {
                selectedCell = candidates.get(i);
            }
        }

        if (selectedCell != null) {
            return selectedCell.getIndex();
        }

        return super.handleNext(board);
    }
}
