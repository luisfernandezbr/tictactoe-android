package br.com.mobiplus.tictactoe.and_engine.game;

import android.content.res.Resources;

import org.anddev.andengine.engine.camera.Camera;
import org.anddev.andengine.entity.IEntity;
import org.anddev.andengine.entity.scene.Scene;
import org.anddev.andengine.entity.scene.background.RepeatingSpriteBackground;
import org.anddev.andengine.entity.shape.IShape;
import org.anddev.andengine.entity.shape.Shape;
import org.anddev.andengine.entity.sprite.Sprite;
import org.anddev.andengine.opengl.texture.TextureManager;
import org.anddev.andengine.opengl.texture.atlas.bitmap.source.AssetBitmapTextureAtlasSource;

import br.com.mobiplus.tictactoe.R;
import br.com.mobiplus.tictactoe.android.IContextLoader;

/**
 * Created by Gama on 15/10/2016.
 */
public class GameScreen {
    private IContextLoader iContextLoader;
    private Scene currentScene;
    private RepeatingSpriteBackground sceneBackground;

    public GameScreen(IContextLoader pIContextLoader) {
        this.iContextLoader = pIContextLoader;
    }

    public Camera setupCamera() {
        Resources resources = iContextLoader.loadContext().getResources();

        int cameraWidth = resources.getInteger(R.integer.camera_width);
        int cameraHeight = resources.getInteger(R.integer.camera_height);

        return new Camera(0, 0, cameraWidth, cameraHeight);
    }

    public Scene setupScene() {
        currentScene = new Scene();
        currentScene.setTouchAreaBindingEnabled(true);
        currentScene.setBackground(sceneBackground);
        return currentScene;
    }

    public void setupSceneBackground(TextureManager pTextureManager) {
        Resources resources = iContextLoader.loadContext().getResources();

        int cameraWidth = resources.getInteger(R.integer.camera_width);
        int cameraHeight = resources.getInteger(R.integer.camera_height);

        sceneBackground = new RepeatingSpriteBackground(cameraWidth, cameraHeight,
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

    public void registerTouchArea(IShape... pShapes) {
        for (IShape shape : pShapes) {
            registerTouchArea(shape);
        }
    }

    public void registerTouchArea(IShape pShapes) {
        if (pShapes != null) {
            currentScene.registerTouchArea(pShapes);
        }
    }

    public void unregisterTouchArea(IShape... pShapes) {
        for (IShape shape : pShapes) {
            unregisterTouchArea(shape);
        }
    }

    public void unregisterTouchArea(IShape pShapes) {
        if (pShapes != null) {
            currentScene.unregisterTouchArea(pShapes);
        }
    }
}
