package br.com.mobiplus.tictactoe.otto;

/**
 * Created by luis.fernandez on 3/16/16.
 */
public class EventBoardClick {

    private int clickedPosition;

    public EventBoardClick(int clickedPosition) {
        this.clickedPosition = clickedPosition;
    }

    public int getClickedPosition() {
        return clickedPosition;
    }
}
