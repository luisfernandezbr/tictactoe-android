package br.com.mobiplus.tictactoe.and_engine.game;

import android.content.Context;
import android.os.Bundle;

import org.anddev.andengine.engine.Engine;
import org.anddev.andengine.engine.options.EngineOptions;
import org.anddev.andengine.engine.options.resolutionpolicy.FillResolutionPolicy;
import org.anddev.andengine.entity.scene.Scene;
import org.anddev.andengine.entity.sprite.Sprite;
import org.anddev.andengine.opengl.texture.atlas.bitmap.BitmapTextureAtlas;
import org.anddev.andengine.ui.activity.BaseGameActivity;

import br.com.mobiplus.tictactoe.android.IContextLoader;
import br.com.mobiplus.tictactoe.mvp.presenter.AndEngineBoardPresenter;
import br.com.mobiplus.tictactoe.mvp.presenter.IBoardPresenter;

/**
 * Created by Gama on 15/10/2016.
 */
public class GameActivity extends BaseGameActivity {
    private IGameContextLoader mIGameContextLoader;
    private IContextLoader mIContextLoader;
    private IBoardPresenter mPresenter;

    private GameScreen mGameScreen;
    private GameElements mGameElements;
    private GameFonts mGameFonts;

    @Override
    protected void onCreate(Bundle pSavedInstanceState) {
        super.onCreate(pSavedInstanceState);
        mPresenter = new AndEngineBoardPresenter(getGameContextLoader());
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
        mGameElements = new GameElements(getContextLoader(), getEngineLoader());
        mGameFonts = new GameFonts(getContextLoader());

        BitmapTextureAtlas phantomFingersFontTexture = mGameFonts.setupFontTexture();

        mEngine.getTextureManager().loadTextures(mGameElements.setupGameAtlas(), phantomFingersFontTexture);
        mEngine.getFontManager().loadFonts(mGameFonts.setupPhantomFingersFont(phantomFingersFontTexture));
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
        mEngine.runOnUpdateThread(new Runnable() {
            @Override
            public void run() {
                mGameScreen.addEntity(mGameElements.setupBoard());

                Sprite[] marks = mGameElements.setupMarks(3, 3);
                mGameScreen.addEntities(marks);
                mGameScreen.registerTouchArea(marks);
            }
        });
    }

    @Override
    protected void onStop() {
        super.onStop();
        mPresenter.onStop();
    }

    private IContextLoader getContextLoader() {
        if (mIContextLoader == null) {
            mIContextLoader = new IContextLoader() {
                @Override
                public Context loadContext() {
                    return getApplicationContext();
                }
            };
        }
        return mIContextLoader;
    }

    private IGameContextLoader getGameContextLoader() {
        if (mIGameContextLoader == null) {
            mIGameContextLoader = new IGameContextLoader() {
                @Override
                public GameElements getGameElements() {
                    return mGameElements;
                }

                @Override
                public GameScreen getGameScreen() {
                    return mGameScreen;
                }

                @Override
                public GameFonts getGameFonts() {
                    return mGameFonts;
                }
            };
        }
        return mIGameContextLoader;
    }

    private IEngineLoader getEngineLoader() {
        return new IEngineLoader() {
            @Override
            public Engine getEngine() {
                return mEngine;
            }
        };
    }

    public interface IEngineLoader {
        Engine getEngine();
    }
}