package br.com.mobiplus.tictactoe.android.application;

import android.app.Application;
import android.content.Context;

/**
 * Created by luis.fernandez on 3/16/16.
 */
public class AppApplication extends Application {

    private static AppApplication mInstance;

    @Override
    public void onCreate() {
        super.onCreate();

        mInstance = AppApplication.this;
    }

    public static AppApplication getInstance() {
        return mInstance;
    }

    public static Context getContext() {
        return mInstance.getBaseContext();
    }
}
