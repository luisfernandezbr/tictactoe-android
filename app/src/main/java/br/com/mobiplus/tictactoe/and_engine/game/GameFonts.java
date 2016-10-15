package br.com.mobiplus.tictactoe.and_engine.game;

import android.graphics.Typeface;

import org.anddev.andengine.opengl.font.Font;
import org.anddev.andengine.opengl.texture.TextureOptions;
import org.anddev.andengine.opengl.texture.atlas.bitmap.BitmapTextureAtlas;

import br.com.mobiplus.tictactoe.R;
import br.com.mobiplus.tictactoe.android.ContextLoader;

/**
 * Created by Gama on 15/10/2016.
 */
public class GameFonts {

    private ContextLoader contextLoader;

    private Typeface phantomFingers;

    public GameFonts(ContextLoader pContextLoader) {
        this.contextLoader = pContextLoader;
        this.phantomFingers = Typeface.createFromAsset(contextLoader.loadContext().getAssets(),
                contextLoader.loadContext().getString(R.string.phantom_fingers_font));
    }

    public BitmapTextureAtlas setupFontTexture() {
        return new BitmapTextureAtlas(256, 256, TextureOptions.BILINEAR_PREMULTIPLYALPHA); // Each Font has your own texture
    }

    public Font setupFont(Typeface pTypeFace, BitmapTextureAtlas pFontTexture, int pSize, int pColor) {
        return new Font(pFontTexture, pTypeFace, pSize, true, pColor);
    }

    public Typeface getPhantomFingersTypeFace() {
        return phantomFingers;
    }
}
