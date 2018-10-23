package Screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.darkthaumaturgy.DarkThaumaturgy;

public class MainGameScreen implements Screen{
    private static final String TAG = MainGameScreen.class.getSimpleName();

    private DarkThaumaturgy game;
    private SpriteBatch batch;
    private Texture img;


    public MainGameScreen(DarkThaumaturgy game, SpriteBatch batch) {
        this.game = game;
        this.batch = batch;
    }



    @Override
    public void show() {
        Gdx.app.log(TAG, "MainGame SHOW");

    }

    @Override
    public void render(float delta) {
        Gdx.app.log(TAG, "MainGame RENDER");
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
    }

    @Override
    public void resize(int width, int height) {
        Gdx.app.log(TAG, "MainGame RESIZE");
    }

    @Override
    public void pause() {
        Gdx.app.log(TAG, "MainGame PAUSE");
    }

    @Override
    public void resume() {
        Gdx.app.log(TAG, "MainGame RESUME");
    }

    @Override
    public void hide() {
        Gdx.app.log(TAG, "MainGame HIDE");
    }

    @Override
    public void dispose() {
        Gdx.app.log(TAG, "MainGame DISPOSE");
    }
}
