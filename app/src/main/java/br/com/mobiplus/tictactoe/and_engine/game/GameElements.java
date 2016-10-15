package br.com.mobiplus.tictactoe.and_engine.game;

import android.content.res.Resources;

import org.anddev.andengine.entity.sprite.Sprite;
import org.anddev.andengine.opengl.texture.TextureOptions;
import org.anddev.andengine.opengl.texture.atlas.bitmap.BitmapTextureAtlas;
import org.anddev.andengine.opengl.texture.atlas.bitmap.BitmapTextureAtlasTextureRegionFactory;
import org.anddev.andengine.opengl.texture.region.TextureRegion;
import org.anddev.andengine.opengl.texture.region.TextureRegionFactory;

import br.com.mobiplus.tictactoe.R;
import br.com.mobiplus.tictactoe.android.ContextLoader;

/**
 * Created by Gama on 15/10/2016.
 */
public class GameElements {

    private ContextLoader contextLoader;
    private BitmapTextureAtlas gameAtlas;

    public GameElements(ContextLoader pContextLoader) {
        this.contextLoader = pContextLoader;
    }

    public BitmapTextureAtlas setupGameAtlas() {
        Resources resources = contextLoader.loadContext().getResources();

        gameAtlas = new BitmapTextureAtlas(resources.getInteger(R.integer.game_atlas_width), resources.getInteger(R.integer.game_atlas_height), TextureOptions.BILINEAR_PREMULTIPLYALPHA);
        BitmapTextureAtlasTextureRegionFactory.createFromAsset(gameAtlas, contextLoader.loadContext(), contextLoader.loadContext().getString(R.string.game_atlas), 0, 0);

        return gameAtlas;
    }

    public Sprite setupBoard() {
        Resources resources = contextLoader.loadContext().getResources();

        int boardWidth = resources.getInteger(R.integer.board_width);
        int boardHeight = resources.getInteger(R.integer.board_height);

        int boardTexturePosX = resources.getInteger(R.integer.board_texture_pos_x);
        int boardTexturePosY = resources.getInteger(R.integer.board_texture_pos_y);

        int boardPosX = resources.getInteger(R.integer.board_pos_x);
        int boardPosY = resources.getInteger(R.integer.board_pos_y);

        TextureRegion boardTexture = TextureRegionFactory.extractFromTexture(gameAtlas,
                boardTexturePosX, boardTexturePosY, boardWidth, boardHeight, false);

        return new Sprite(boardPosX, boardPosY, boardTexture);
    }

    public Sprite[] setupBoardTiles(int pLines, int pColumns) {
        Resources resources = contextLoader.loadContext().getResources();

        int boardWidth = resources.getInteger(R.integer.board_width);
        int boardHeight = resources.getInteger(R.integer.board_height);

        int boardTexturePosX = resources.getInteger(R.integer.board_texture_pos_x);
        int boardTexturePosY = resources.getInteger(R.integer.board_texture_pos_y);

        int boardPosX = resources.getInteger(R.integer.board_pos_x);
        int boardPosY = resources.getInteger(R.integer.board_pos_y);

        int tileWidth = boardWidth / pLines;
        int tileHeight = boardHeight / pColumns;

        Sprite[] boardTileSprites = new Sprite[pLines * pColumns];

        int currentBoardTileIndex = 0;
        for (int line = 0; line < pLines - 1; pLines ++) {
            for (int column = 0; column < pColumns - 1; pColumns ++) {
                TextureRegion boardTileTexture = TextureRegionFactory.extractFromTexture(gameAtlas,
                        boardTexturePosX + tileWidth * line,
                        boardTexturePosY + tileHeight * column,
                        boardWidth, boardHeight, false);

                boardTileSprites[currentBoardTileIndex] = new Sprite(boardPosX + tileWidth * line,
                        boardPosY + tileHeight * column, boardTileTexture);

                currentBoardTileIndex ++;
            }
        }

        return boardTileSprites;
    }

    public Sprite createXMark(int pPosX, int pPosY) {
        Resources resources = contextLoader.loadContext().getResources();

        TextureRegion xMarkTexture = TextureRegionFactory.extractFromTexture(gameAtlas,
                resources.getInteger(R.integer.x_mark_texture_pos_x), resources.getInteger(R.integer.x_mark_texture_pos_y),
                resources.getInteger(R.integer.mark_width), resources.getInteger(R.integer.mark_height), false);

        return new Sprite(pPosX, pPosY, xMarkTexture);
    }

    public Sprite createOMark(int pPosX, int pPosY) {
        Resources resources = contextLoader.loadContext().getResources();

        TextureRegion xMarkTexture = TextureRegionFactory.extractFromTexture(gameAtlas,
                resources.getInteger(R.integer.o_mark_texture_pos_x), resources.getInteger(R.integer.o_mark_texture_pos_y),
                resources.getInteger(R.integer.mark_width), resources.getInteger(R.integer.mark_height), false);

        return new Sprite(pPosX, pPosY, xMarkTexture);
    }
}
