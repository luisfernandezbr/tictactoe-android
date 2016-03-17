package br.com.mobiplus.tictactoe.otto;

import com.squareup.otto.Bus;

/**
 * Created by luis.fernandez on 3/16/16.
 */
public class BusProvider {

    private static Bus mInstance;

    public static Bus getInstance() {

        if (mInstance == null) {
            mInstance = new Bus();
        }

        return mInstance;
    }

    private BusProvider() {}
}
