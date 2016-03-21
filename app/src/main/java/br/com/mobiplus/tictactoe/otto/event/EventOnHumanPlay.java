package br.com.mobiplus.tictactoe.otto.event;

/**
 * Created by luis.fernandez on 3/16/16.
 */
public class EventOnHumanPlay {

    private int playedIndex;

    public EventOnHumanPlay(int playedIndex) {
        this.playedIndex = playedIndex;
    }

    public int getPlayedIndex() {
        return playedIndex;
    }
}
