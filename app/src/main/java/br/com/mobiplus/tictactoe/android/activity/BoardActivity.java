package br.com.mobiplus.tictactoe.android.activity;

import android.nfc.Tag;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.TextView;

import com.gamesparks.sdk.GSEventConsumer;
import com.gamesparks.sdk.android.GSAndroidPlatform;
import com.gamesparks.sdk.api.GSData;
import com.gamesparks.sdk.api.autogen.GSRequestBuilder;
import com.gamesparks.sdk.api.autogen.GSResponseBuilder;

import br.com.mobiplus.tictactoe.mvp.presenter.BoardPresenter;
import br.com.mobiplus.tictactoe.mvp.presenter.IBoardPresenter;

public class BoardActivity extends AppCompatActivity {

    private static final String TAG = "BoardActivity";
    private IBoardPresenter mPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mPresenter = new BoardPresenter(BoardActivity.this);

        GSAndroidPlatform.initialise(this, "j299605S7tLl", "a5pytuquYjSSd4Alssdc4CzwJGo7Et7G", false, true);

        GSAndroidPlatform.gs().setOnAvailable(new GSEventConsumer<Boolean>() {
            @Override
            public void onEvent(Boolean available) {
//                TextView helloWorld = (TextView) findViewById(R.id.hello_world);
//                helloWorld.setText(Thread.currentThread().getId() + " AVAILABLE: " + available);

                if (available) {
                    sendAuth();
                }
            }
        });


    }

    private void sendAuth() {
                GSAndroidPlatform.gs().getRequestBuilder().createFacebookConnectRequest()
        .setAccessToken("EAAWPSy1lJEcBACwQY1VfzoUMRwkfSGR0B4rbCPrLLLTQZBGpGfX694T4S2LTEJZA2Iyzql0cdpq7y885OxH6gWfD3Fp7NrsV7yyFWBCbwZBqZBW5t0REBrp7bmeeoex0jv6WcaoP4O9FUylbBEywG6UowO3KhZAEqc6WCP3QZAsYK7ZCSsFw4Cd")
        //.setCode(code)
        .setDoNotLinkToCurrentPlayer(true)
        .setErrorOnSwitch(true)
        //facebookConnectRequest.setSegments(segments);
        .setSwitchIfPossible(true)
        .setSyncDisplayName(true)
        .send(new GSEventConsumer<GSResponseBuilder.AuthenticationResponse>() {
            @Override
            public void onEvent(GSResponseBuilder.AuthenticationResponse authenticationResponse) {
                Log.i(TAG, authenticationResponse.getDisplayName());
            }
        });


//        GSAndroidPlatform.gs().getRequestBuilder().createRegistrationRequest()
//                .setDisplayName("peppe")
//                .setPassword("password")
//                .setUserName("userName")
//                .send(new GSEventConsumer<GSResponseBuilder.RegistrationResponse>() {
//                    @Override
//                    public void onEvent(GSResponseBuilder.RegistrationResponse response) {
//                        String authToken = response.getAuthToken();
//                        String displayName = response.getDisplayName();
//                        Boolean newPlayer = response.getNewPlayer();
//                        GSData scriptData = response.getScriptData();
//                        String userId = response.getUserId();
//
//                        System.out.println("registrationRequest authToken: " + authToken + "  displayName: " + displayName + "  userId: " + userId);
//                    }
//                });
    }

    @Override
    protected void onStart() {
        super.onStart();

        //GSAndroidPlatform.gs().stop();
        GSAndroidPlatform.gs().start();
        mPresenter.onStart();
    }

    @Override
    protected void onStop() {
        super.onStop();

        GSAndroidPlatform.gs().stop();
        mPresenter.onStop();
    }
}
