package br.com.mobiplus.tictactoe.mvp.repo;

import android.util.Log;

import java.util.List;

import br.com.mobiplus.tictactoe.pojo.Board;
import br.com.mobiplus.tictactoe.pojo.BoardCell;
import br.com.mobiplus.tictactoe.pojo.BoardLine;

/**
 * Created by luis.fernandez on 3/16/16.
 */
public class BoardRepo implements IBoardRepo {

    private static final String TAG = "BoardRepo";
    private static Board mBoard = new Board();

    @Override
    public Board getCurrentBoard() {
        return mBoard;
    }

    @Override
    public void updateBoard(Board board) {
        BoardRepo.mBoard = board;

        BoardLine[] boardLineArray = board.getBoardLineArray();
        for (int i = 0; i < 3; i++) {
            BoardLine boardLine = boardLineArray[i];
            List<BoardCell> boardCellList = boardLine.getBoardCellList();

            Log.d(TAG, "------------");
            Log.d(TAG, String.format(" %s | %s | %s ",
                    boardCellList.get(0).getValue() != null ? boardCellList.get(0).getValue() : " ",
                    boardCellList.get(1).getValue() != null ? boardCellList.get(1).getValue() : " ",
                    boardCellList.get(2).getValue() != null ? boardCellList.get(2).getValue() : " "));

            System.out.println();
        }
    }

    @Override
    public void resetBoard() {
        mBoard = new Board();
    }
}
