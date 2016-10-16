package br.com.mobiplus.tictactoe.and_engine.game;

import android.content.res.Resources;
import android.util.Log;

import org.anddev.andengine.entity.sprite.Sprite;
import org.anddev.andengine.input.touch.TouchEvent;
import org.anddev.andengine.opengl.texture.TextureOptions;
import org.anddev.andengine.opengl.texture.atlas.bitmap.BitmapTextureAtlas;
import org.anddev.andengine.opengl.texture.atlas.bitmap.BitmapTextureAtlasTextureRegionFactory;
import org.anddev.andengine.opengl.texture.region.TextureRegion;
import org.anddev.andengine.opengl.texture.region.TextureRegionFactory;

import br.com.mobiplus.tictactoe.R;
import br.com.mobiplus.tictactoe.android.IContextLoader;
import br.com.mobiplus.tictactoe.otto.BusProvider;
import br.com.mobiplus.tictactoe.otto.event.EventOnHumanPlay;
import br.com.mobiplus.tictactoe.pojo.Player;

/**
 * Created by Gama on 15/10/2016.
 */
public class GameElements {

    private IContextLoader iContextLoader;
    private GameActivity.IEngineLoader iEngineLoader;
    private BitmapTextureAtlas gameAtlas;

    private Sprite[] marks;

    public GameElements(IContextLoader pIContextLoader, GameActivity.IEngineLoader pIEngineLoader) {
        this.iContextLoader = pIContextLoader;
        this.iEngineLoader = pIEngineLoader;
    }

    public BitmapTextureAtlas setupGameAtlas() {
        Resources resources = iContextLoader.loadContext().getResources();

        gameAtlas = new BitmapTextureAtlas(resources.getInteger(R.integer.game_atlas_width), resources.getInteger(R.integer.game_atlas_height), TextureOptions.BILINEAR_PREMULTIPLYALPHA);
        BitmapTextureAtlasTextureRegionFactory.createFromAsset(gameAtlas, iContextLoader.loadContext(), iContextLoader.loadContext().getString(R.string.game_atlas), 0, 0);

        return gameAtlas;
    }

    public Sprite setupBoard() {
        Resources resources = iContextLoader.loadContext().getResources();

        int boardSize = resources.getInteger(R.integer.board_size);

        int boardTexturePosX = resources.getInteger(R.integer.board_texture_pos_x);
        int boardTexturePosY = resources.getInteger(R.integer.board_texture_pos_y);

        int boardPosX = resources.getInteger(R.integer.board_pos_x);
        int boardPosY = resources.getInteger(R.integer.board_pos_y);

        TextureRegion boardTexture = TextureRegionFactory.extractFromTexture(gameAtlas,
                boardTexturePosX, boardTexturePosY, boardSize, boardSize, false);

        return new Sprite(boardPosX, boardPosY, boardTexture);
    }

    public Sprite[] setupMarks(int pLines, int pColumns) {
        Resources resources = iContextLoader.loadContext().getResources();

        int boardPosX = resources.getInteger(R.integer.board_pos_x);
        int boardPosY = resources.getInteger(R.integer.board_pos_y);

        int markSize = resources.getInteger(R.integer.mark_size);
        int xMarkTexturePosX = resources.getInteger(R.integer.x_mark_texture_pos_x);
        int xMarkTexturePosY = resources.getInteger(R.integer.x_mark_texture_pos_y);

        int boardBorderSize = resources.getInteger(R.integer.board_border_size);
        int gradeBarSize = resources.getInteger(R.integer.grade_bar_size);
        int cellSize = resources.getInteger(R.integer.cell_size);

        marks = new Sprite[pLines * pColumns];

        int currentBoardTileIndex = 0;
        for (int line = 0; line < pLines; line ++) {
            for (int column = 0; column < pColumns; column ++) {

                int spritePosX = boardPosX + boardBorderSize + (cellSize + gradeBarSize) * line +((cellSize - markSize) / 2);
                int spritePosY = boardPosY + boardBorderSize + (cellSize + gradeBarSize) * column + ((cellSize - markSize) / 2);

                final int spriteIndex = currentBoardTileIndex;

                marks[currentBoardTileIndex] = new Sprite(spritePosX, spritePosY, TextureRegionFactory.extractFromTexture(gameAtlas,
                        xMarkTexturePosX, xMarkTexturePosY, markSize, markSize, false)) {
                    @Override
                    public boolean onAreaTouched(TouchEvent pSceneTouchEvent, float pTouchAreaLocalX, float pTouchAreaLocalY) {
                        if (pSceneTouchEvent.isActionDown()) {
                            BusProvider.getInstance().post(new EventOnHumanPlay(spriteIndex));
                        }
                        return super.onAreaTouched(pSceneTouchEvent, pTouchAreaLocalX, pTouchAreaLocalY);
                    }
                };
                marks[currentBoardTileIndex].setVisible(false);

                currentBoardTileIndex ++;
            }
        }
        return marks;
    }

    public void updateMarkByIndex(final Player pPlayer, final int pMarkIndex, final boolean pIsVisible) {
        iEngineLoader.getEngine().runOnUpdateThread(new Runnable() {
            @Override
            public void run() {
                Resources resources = iContextLoader.loadContext().getResources();

                int xMarkTexturePosX = resources.getInteger(R.integer.x_mark_texture_pos_x);
                int xMarkTexturePosY = resources.getInteger(R.integer.x_mark_texture_pos_y);
                int oMarkTexturePosX = resources.getInteger(R.integer.o_mark_texture_pos_x);
                int oMarkTexturePosY = resources.getInteger(R.integer.o_mark_texture_pos_y);

                if (pPlayer == Player.PLAYER_HUMAN) {
                    marks[pMarkIndex].getTextureRegion().setTexturePosition(xMarkTexturePosX, xMarkTexturePosY);
                } else if (pPlayer == Player.PLAYER_CPU) {
                    marks[pMarkIndex].getTextureRegion().setTexturePosition(oMarkTexturePosX, oMarkTexturePosY);
                }

                marks[pMarkIndex].setVisible(pIsVisible);
            }
        });
    }
}
