package br.com.mobiplus.tictactoe.otto.event;

/**
 * Created by luis.fernandez on 3/19/16.
 */
public class EventOnCpuPlay {

    private int playedIndex;

    public EventOnCpuPlay(int playedIndex) {
        this.playedIndex = playedIndex;
    }

    public int getPlayedIndex() {
        return playedIndex;
    }
}
