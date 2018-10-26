package Screens;

import com.badlogic.ashley.core.PooledEngine;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.darkthaumaturgy.DarkThaumaturgy;

import Helpers.Figures;
import Helpers.GameInput;

public class MainGameScreen implements Screen{
    private static final String TAG = MainGameScreen.class.getSimpleName();

    private DarkThaumaturgy game;
    private SpriteBatch batch;
    private Texture img;

    //view
    private OrthographicCamera camera;
    private Viewport gameViewport;

    //Controls
    private GameInput gameInput;

    //Ashley
    private PooledEngine engine;

    public MainGameScreen(DarkThaumaturgy game, SpriteBatch batch) {
        this.game = game;
        this.batch = batch;

        camera = new OrthographicCamera();
        gameViewport = new FitViewport(Figures.VIRTUALWIDTH,Figures.VIRTUALHEIGHT, camera);
        camera.position.set(gameViewport.getWorldWidth()/2,gameViewport.getWorldHeight()/2,0);

        gameInput = new GameInput(gameViewport);

        engine = new PooledEngine(100, 500, 300,
                1000);

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

        gameViewport.update(width, height);

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
