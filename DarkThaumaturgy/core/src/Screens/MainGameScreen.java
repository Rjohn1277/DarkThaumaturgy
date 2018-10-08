package Screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.badlogic.gdx.physics.box2d.CircleShape;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.World;
import com.darkthaumaturgy.DarkThaumaturgy;

public class MainGameScreen implements Screen{
    private static final String TAG = MainGameScreen.class.getSimpleName();

    private DarkThaumaturgy game;
    private SpriteBatch batch;
    private Texture img;

    //box2d
    private World world;
    private Body body;
    private Body body2;
    private Vector2 gravitationalForces;


    //view
    private OrthographicCamera camera;
    private Box2DDebugRenderer b2dr;

    public MainGameScreen(DarkThaumaturgy game, SpriteBatch batch) {
        this.game = game;
        this.batch = batch;

        gravitationalForces = new Vector2(0, -1f);

        world = new World(gravitationalForces, false);
        b2dr = new Box2DDebugRenderer();
        camera = new OrthographicCamera();
        camera.setToOrtho(false,16,10);
        camera.position.set(camera.viewportWidth/2,camera.viewportHeight/2,0);

    }

    public Body createBody(Vector2 position, float size) {
        Body body;
        BodyDef bdef = new BodyDef();
        FixtureDef fdef = new FixtureDef();

        bdef.type = BodyDef.BodyType.DynamicBody;
        bdef.position.set(position.x,position.y);
        body = world.createBody(bdef);

        CircleShape shape = new CircleShape();
        shape.setRadius(size/2);

        fdef.shape = shape;
        fdef.density = 1f;
        fdef.restitution = .5f;
        fdef.isSensor = false;
        body.createFixture(fdef);

        shape.dispose();

        return body;
    }

    @Override
    public void show() {
        Gdx.app.log(TAG, "MainGame SHOW");

        body = createBody(new Vector2(camera.viewportWidth/2,camera.viewportHeight),1f);
        body2 = createBody(new Vector2(camera.viewportWidth/2+2,camera.viewportHeight),2f);
    }

    @Override
    public void render(float delta) {
        camera.update();

        world.step(delta,6,2);


        Gdx.app.log(TAG, "MainGame RENDER");
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        b2dr.render(world,camera.combined);

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
        b2dr.dispose();
        world.dispose();

    }
}
