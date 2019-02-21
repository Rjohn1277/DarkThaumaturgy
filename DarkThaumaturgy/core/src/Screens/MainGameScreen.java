package Screens;

import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.core.PooledEngine;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.darkthaumaturgy.DarkThaumaturgy;
import com.badlogic.gdx.physics.box2d.World;

import Components.BodyComponent;
import Components.PlayerComponent;
import Helpers.Figures;
import Helpers.GameInput;
import Helpers.LevelCollisionGenerator;
import Managers.CollisionManager;
import Managers.EntityManager;
import Systems.CollisionSystem;
import Systems.PhysicsDebugSystem;
import Systems.PhysicsSystem;
import Systems.PlayerControlSystem;

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
    private CollisionManager collisionManager;

    private float random;

    //view
    private OrthographicCamera camera;
    private Viewport gameViewport;

    //Controls
    private GameInput gameInput;

    //Ashley
    private PooledEngine engine;
    private PhysicsSystem physicsSystem;
    private PhysicsDebugSystem physicsDebugSystem;
    private PlayerControlSystem playerControlSystem;
    private CollisionSystem collisionSystem;

    //Entity Manager
    private EntityManager entityManager;
    private Entity player;

    //Level Generator
    private LevelCollisionGenerator levelCollisionGenerator;
    private Entity ground;
    private OrthogonalTiledMapRenderer mapRenderer;
    private TiledMap map;

    //temp variables for optimization
    private Vector2 tempPosition;
    private Vector2 tempDimensions;

    public MainGameScreen(DarkThaumaturgy game, SpriteBatch batch) {
        this.game = game;
        this.batch = batch;

        tempDimensions = new Vector2(Vector2.Zero);
        tempPosition = new Vector2(Vector2.Zero);

        camera = new OrthographicCamera();
        gameViewport = new FitViewport(Figures.VIRTUALWIDTH,Figures.VIRTUALHEIGHT, camera);
        camera.position.set(gameViewport.getWorldWidth()/2,gameViewport.getWorldHeight()/2,0);

        gameInput = new GameInput(gameViewport);

        engine = new PooledEngine(100, 500, 300,
                1000);
        world = new World(Figures.GRAVITATIONAL_FORCES, true);
        collisionManager = new CollisionManager();
        world.setContactListener(collisionManager);

        initAshleySystems();
        entityManager = new EntityManager(game, world, this.batch, engine);
        levelCollisionGenerator = new LevelCollisionGenerator(world, engine);

        //todo need to change how map is loaded when implementing asset management
        map = new TmxMapLoader().load("TestMap.tmx");
        mapRenderer = new OrthogonalTiledMapRenderer(map, 1/Figures.PPM,this.batch);

        levelCollisionGenerator.createCollisionLevel(map);

    }

    public void initAshleySystems () {
        physicsSystem = new PhysicsSystem(world);
        physicsDebugSystem = new PhysicsDebugSystem(world, camera);
        playerControlSystem = new PlayerControlSystem(gameInput);
        collisionSystem = new CollisionSystem(engine, world, game);

        engine.addSystem(physicsSystem);
        engine.addSystem(physicsDebugSystem);
        engine.addSystem(playerControlSystem);
        engine.addSystem(collisionSystem);





    }

    private void updateCamera() {
        for(Entity player: engine.getEntitiesFor(Family.all(PlayerComponent.class).get())) {
            BodyComponent bodyComponent = player.getComponent(BodyComponent.class);
            camera.position.set(bodyComponent.getBody().getPosition(),0);
        }

        camera.update();

    }

    @Override
    public void show() {
        Gdx.app.log(TAG, "MainGame SHOW");
        Gdx.input.setInputProcessor(gameInput);

        //player = entityManager.spawnEntity("Player", 8,5);
        entityManager.spawnEntities(map);
        //temp test of level generation
        tempPosition.x = 0;
        tempPosition.y = 1;
        tempDimensions.x = gameViewport.getWorldWidth();
        tempDimensions.y = 1;





    }

    @Override
    public void render(float delta) {
        //camera.position.set(player.getComponent(BodyComponent.class).getBody().getPosition(),0);
        camera.update();

        //updateCamera();





        Gdx.app.log(TAG, "MainGame RENDER");
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        mapRenderer.setView((OrthographicCamera)gameViewport.getCamera());
        mapRenderer.render();
        engine.update(delta);


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
        world.dispose();
    }
}
