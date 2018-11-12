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
    private float timeToWait = 2f;

    public LoadingScreen(DarkThaumaturgy game, SpriteBatch batch) {
        this.game = game;
        this.batch = batch;
        img = new Texture("badlogic.jpg");
    }

    private static final String TAG = LoadingScreen.class.getSimpleName();


    @Override
    public void show() {
        Gdx.app.log(TAG, "in Loading Screen Show Method");
    }

    @Override
    public void render(float delta) {
        Gdx.app.log(TAG, "in Loading Screen Render Method");

        Gdx.gl.glClearColor(1, 0, 1, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        batch.begin();
        batch.draw(img, 0, 0);
        batch.end();

        timeToWait-=delta;
            Gdx.app.log(TAG, "time To Wait: " + timeToWait);
            if(timeToWait<=0) {

            game.setScreen(DarkThaumaturgy.SCREENTYPE.MENU);
            timeToWait = 2f;
        }
    }

    @Override
    public void resize(int width, int height) {
        Gdx.app.log(TAG, "in Loading Screen Resize Method");
    }

    @Override
    public void pause() {
        Gdx.app.log(TAG, "in Loading Screen Pause Method");
    }

    @Override
    public void resume() {
        Gdx.app.log(TAG, "in Loading Screen Resume Method");
    }

    @Override
    public void hide() {
        Gdx.app.log(TAG, "in Loading Screen Hide Method");
    }

    @Override
    public void dispose() {
        Gdx.app.log(TAG, "in Loading Screen Dispose Method");
        img.dispose();
    }
}
