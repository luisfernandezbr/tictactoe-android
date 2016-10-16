package br.com.mobiplus.tictactoe.and_engine.game;

import android.content.res.Resources;
import android.graphics.Color;
import android.graphics.Typeface;
import android.support.annotation.StringRes;

import org.anddev.andengine.entity.text.ChangeableText;
import org.anddev.andengine.opengl.font.Font;
import org.anddev.andengine.opengl.texture.TextureOptions;
import org.anddev.andengine.opengl.texture.atlas.bitmap.BitmapTextureAtlas;

import br.com.mobiplus.tictactoe.R;
import br.com.mobiplus.tictactoe.android.IContextLoader;

/**
 * Created by Gama on 15/10/2016.
 */
public class GameFonts {

    private IContextLoader iContextLoader;

    private Font phantomFingersFont;
    private Typeface phantomFingersTypeFace;

    public GameFonts(IContextLoader pIContextLoader) {
        this.iContextLoader = pIContextLoader;
        this.phantomFingersTypeFace = Typeface.createFromAsset(iContextLoader.loadContext().getAssets(),
                iContextLoader.loadContext().getString(R.string.phantom_fingers_font));
    }

    public BitmapTextureAtlas setupFontTexture() {
        return new BitmapTextureAtlas(256, 256, TextureOptions.BILINEAR_PREMULTIPLYALPHA); // Each Font has your own texture
    }

    public ChangeableText createText(String pText, Font pFont) {
        return new ChangeableText(0, 0, pFont, pText);
    }

    public ChangeableText createEndRoundText(@StringRes int pStringResId) {
        Resources resources = iContextLoader.loadContext().getResources();

        ChangeableText boardLabel = createText(resources.getString(pStringResId), phantomFingersFont);

        float posX = (resources.getInteger(R.integer.camera_width) - boardLabel.getWidth()) / 2;
        float posY = resources.getInteger(R.integer.end_round_text_pos_y);

        boardLabel.setPosition(posX, posY);

        return boardLabel;
    }

    public Font setupPhantomFingersFont(BitmapTextureAtlas phantomFingersFontTexture) {
        this.phantomFingersFont = setupFont(phantomFingersTypeFace, phantomFingersFontTexture, 30, Color.parseColor("#ffffff"));
        return this.phantomFingersFont;
    }
    private Font setupFont(Typeface pTypeFace, BitmapTextureAtlas pFontTexture, int pSize, int pColor) {
        return new Font(pFontTexture, pTypeFace, pSize, true, pColor);
    }
}
