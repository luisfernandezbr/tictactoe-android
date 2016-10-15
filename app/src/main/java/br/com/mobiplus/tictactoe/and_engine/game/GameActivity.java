package br.com.mobiplus.tictactoe.and_engine.game;

import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;

import org.anddev.andengine.engine.Engine;
import org.anddev.andengine.engine.options.EngineOptions;
import org.anddev.andengine.engine.options.resolutionpolicy.FillResolutionPolicy;
import org.anddev.andengine.entity.scene.Scene;
import org.anddev.andengine.opengl.font.Font;
import org.anddev.andengine.opengl.texture.atlas.bitmap.BitmapTextureAtlas;
import org.anddev.andengine.ui.activity.BaseGameActivity;

import br.com.mobiplus.tictactoe.android.ContextLoader;
import br.com.mobiplus.tictactoe.mvp.presenter.AndEngineBoardPresenter;
import br.com.mobiplus.tictactoe.mvp.presenter.IBoardPresenter;

/**
 * Created by Gama on 15/10/2016.
 */
public class GameActivity extends BaseGameActivity {

    private ContextLoader contextLoader;
    private IBoardPresenter mPresenter;

    private GameScreen mGameScreen;
    private GameElements mGameElements;
    private GameFonts mGameFonts;

    @Override
    protected void onCreate(Bundle pSavedInstanceState) {
        super.onCreate(pSavedInstanceState);
        mPresenter = new AndEngineBoardPresenter();
    }

    @Override
    public Engine onLoadEngine() {
        mGameScreen = new GameScreen(getContextLoader());
        EngineOptions engineOptions = new EngineOptions(true, EngineOptions.ScreenOrientation.PORTRAIT, new FillResolutionPolicy(), mGameScreen.setupCamera());
        return new Engine(engineOptions);
    }

    @Override
    public void onLoadResources() {
        mGameScreen.setupSceneBackground(mEngine.getTextureManager());
        mGameElements = new GameElements(getContextLoader());
        mGameFonts = new GameFonts(getContextLoader());

        BitmapTextureAtlas phantomFingersFontTexture = mGameFonts.setupFontTexture();
        Font phantomFingersFont = mGameFonts.setupFont(mGameFonts.getPhantomFingersTypeFace(), phantomFingersFontTexture, 30, Color.parseColor("#000000"));

        mEngine.getTextureManager().loadTextures(mGameElements.setupGameAtlas(), phantomFingersFontTexture);
        mEngine.getFontManager().loadFonts(phantomFingersFont);
    }

    @Override
    public Scene onLoadScene() {
        return mGameScreen.setupScene();
    }

    @Override
    public void onLoadComplete() {
        createGame();
        mPresenter.onStart();
    }

    private void createGame() {
        mGameScreen.addEntity(mGameElements.setupBoard());
        mGameScreen.addEntities(mGameElements.setupBoardTiles(3, 3));
    }

    @Override
    protected void onStop() {
        super.onStop();
        mPresenter.onStop();
    }

    private ContextLoader getContextLoader() {
        if (contextLoader == null) {
            contextLoader = new ContextLoader() {
                @Override
                public Context loadContext() {
                    return getApplicationContext();
                }
            };
        }
        return contextLoader;
    }
}