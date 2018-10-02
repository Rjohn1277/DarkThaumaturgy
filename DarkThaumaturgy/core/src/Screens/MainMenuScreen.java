package Screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.darkthaumaturgy.DarkThaumaturgy;

public class MainMenuScreen implements Screen{
    private static final String TAG = MainMenuScreen.class.getSimpleName();

    private DarkThaumaturgy game;
    private SpriteBatch batch;
    private Texture img;

    public MainMenuScreen(DarkThaumaturgy game, SpriteBatch batch) {
        this.game = game;
        this.batch = batch;
        Gdx.app.log(TAG, "MainMenu Constructor");
    }

    @Override
    public void show() {
        Gdx.app.log(TAG, "MainMenu SHOW");
    }

    @Override
    public void render(float delta) {
        Gdx.app.log(TAG, "MainMenu RENDER");
        Gdx.gl.glClearColor(0, 1, 1, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
    }

    @Override
    public void resize(int width, int height) {
        Gdx.app.log(TAG, "MainMenu RESIZE");

    }

    @Override
    public void pause() {
        Gdx.app.log(TAG, "MainMenu PAUSE");
    }

    @Override
    public void resume() {
        Gdx.app.log(TAG, "MainMenu RESUME");
    }

    @Override
    public void hide() {
        Gdx.app.log(TAG, "MainMenu HIDE");
    }

    @Override
    public void dispose() {
        Gdx.app.log(TAG, "MainMenu DISPOSE");
    }
}
