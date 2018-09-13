package Screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.darkthaumaturgy.DarkThaumaturgy;

public class LoadingScreen implements Screen{

    private SpriteBatch batch;
    private Texture img;
    private DarkThaumaturgy game;

    public LoadingScreen(DarkThaumaturgy game, SpriteBatch batch) {
        this.game = game;
        this.batch = batch;
        img = new Texture("badlogic.jpg");
    }

    private static final String TAG = LoadingScreen.class.getSimpleName();


    @Override
    public void show() {
        Gdx.app.log(TAG, "in Loading Screen Render Method");
    }

    @Override
    public void render(float delta) {
        Gdx.app.log(TAG, "in Loading Screen Show Method");

        Gdx.gl.glClearColor(0, 1, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        batch.begin();
        batch.draw(img, 0, 0);
        batch.end();
    }

    @Override
    public void resize(int width, int height) {
        Gdx.app.log(TAG, "in Loading Screen Show Method");
    }

    @Override
    public void pause() {
        Gdx.app.log(TAG, "in Loading Screen Show Method");
    }

    @Override
    public void resume() {
        Gdx.app.log(TAG, "in Loading Screen Show Method");
    }

    @Override
    public void hide() {
        Gdx.app.log(TAG, "in  Show Method");
    }

    @Override
    public void dispose() {

    }
}
