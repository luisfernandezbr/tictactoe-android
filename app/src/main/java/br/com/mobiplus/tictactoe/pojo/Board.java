package br.com.mobiplus.tictactoe.pojo;

/**
 * Created by luis.fernandez on 3/16/16.
 */
public class Board {

    String[][] board = new String[3][3];

    public String[][] getBoard() {
        return board;
    }

    public void changeBoard(int position, String value) {
        switch (position) {
            case 0 : {
                board[0][0] = value;
                break;
            }
            case 1 : {
                board[0][1] = value;
                break;
            }
            case 2 : {
                board[0][2] = value;
                break;
            }
            case 3 : {
                board[1][0] = value;
                break;
            }
            case 4 : {
                board[1][1] = value;
                break;
            }
            case 5 : {
                board[1][2] = value;
                break;
            }
            case 6 : {
                board[2][0] = value;
                break;
            }
            case 7 : {
                board[2][1] = value;
                break;
            }
            case 8 : {
                board[2][2] = value;
                break;
            }
        }
    }
}
