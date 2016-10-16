package br.com.mobiplus.tictactoe.and_engine.game;

import org.anddev.andengine.engine.camera.Camera;
import org.anddev.andengine.entity.IEntity;
import org.anddev.andengine.entity.scene.Scene;
import org.anddev.andengine.entity.scene.background.RepeatingSpriteBackground;
import org.anddev.andengine.entity.sprite.Sprite;
import org.anddev.andengine.opengl.texture.TextureManager;
import org.anddev.andengine.opengl.texture.atlas.bitmap.source.AssetBitmapTextureAtlasSource;

import br.com.mobiplus.tictactoe.R;
import br.com.mobiplus.tictactoe.android.IContextLoader;

/**
 * Created by Gama on 15/10/2016.
 */
public class GameScreen {
    private final int CAMERA_WIDTH  = 768;
    private final int CAMERA_HEIGHT = 1280;

    private IContextLoader iContextLoader;
    private Scene currentScene;
    private RepeatingSpriteBackground sceneBackground;

    public GameScreen(IContextLoader pIContextLoader) {
        iContextLoader = pIContextLoader;
    }

    public Camera setupCamera() {
        return new Camera(0, 0, CAMERA_WIDTH, CAMERA_HEIGHT);
    }

    public Scene setupScene() {
        currentScene = new Scene();
        currentScene.setTouchAreaBindingEnabled(true);
        currentScene.setBackground(sceneBackground);
        return currentScene;
    }

    public void setupSceneBackground(TextureManager pTextureManager) {
        sceneBackground = new RepeatingSpriteBackground(CAMERA_WIDTH, CAMERA_HEIGHT,
                pTextureManager, new AssetBitmapTextureAtlasSource(iContextLoader.loadContext(), iContextLoader.loadContext().getString(R.string.main_scene_background)));
    }

    public void sortEntities() {
        currentScene.sortChildren();
    }

    private void putEntityOnTop(IEntity pEntity) {
        if (pEntity != null) {
            pEntity.setZIndex(currentScene.getChildCount() + 1);
        }
    }

    public void addEntity(IEntity pEntity) {
        if (pEntity != null) {
            currentScene.attachChild(pEntity);
            pEntity.setZIndex(currentScene.getChildCount() + 1);
        }
    }

    public void addEntities(IEntity ... pEntities) {
        for (IEntity entity : pEntities) {
            addEntity(entity);
        }
    }

    public void registerTouchArea(Sprite ... pSprities) {
        for (Sprite sprite : pSprities) {
            registerTouchArea(sprite);
        }
    }

    public void registerTouchArea(Sprite pSprite) {
        if (pSprite != null) {
            currentScene.registerTouchArea(pSprite);
        }
    }

    public void unregisterTouchArea(Sprite ... pSprities) {
        for (Sprite sprite : pSprities) {
            unregisterTouchArea(sprite);
        }
    }

    public void unregisterTouchArea(Sprite pSprite) {
        if (pSprite != null) {
            currentScene.unregisterTouchArea(pSprite);
        }
    }
}
