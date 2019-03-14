package Screens;

import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.darkthaumaturgy.DarkThaumaturgy;

import Managers.MyAssetManager;

public class CreditScreen implements Screen{

    private DarkThaumaturgy game;
    private SpriteBatch batch;
    private MyAssetManager myAssetManager;
    private Texture img;

    public CreditScreen(DarkThaumaturgy game, SpriteBatch batch, MyAssetManager myAssetManager) {
        this.game = game;
        this.batch = batch;
        this.myAssetManager = myAssetManager;
    }

    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {

    }

    @Override
    public void resize(int width, int height) {

    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {

    }
}
