package Managers;

import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.PooledEngine;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.maps.MapLayer;
import com.badlogic.gdx.maps.MapObject;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.World;
import com.darkthaumaturgy.DarkThaumaturgy;

import java.util.ArrayList;

import Components.AnimationComponent;
import Components.BodyComponent;
import Components.CollisionComponent;
import Components.PlayerComponent;
import Components.RenderableComponent;
import Components.StateComponent;
import Components.TextureComponent;
import Components.TransformComponent;
import Components.TypeComponent;
import Helpers.BodyGenerator;
import Helpers.Figures;

public class EntityManager {

    public String TAG = EntityManager.class.getSimpleName();
    private DarkThaumaturgy darkThaumaturgy;
    private World world;
    private SpriteBatch batch;
    private MyAssetManager myAssetManager;
    private TextureAtlas atlas;
    private PooledEngine engine;
    private BodyGenerator generator;
    private Vector2 tempPositionVector;
    private Vector2 tempDimensionVector;

    private ArrayList<Entity> entities;

    public EntityManager(DarkThaumaturgy darkThaumaturgy, World world, SpriteBatch batch, PooledEngine engine, MyAssetManager myAssetManager) {
    this.darkThaumaturgy = darkThaumaturgy;
    this.world = world;
    this.batch = batch;
    this.engine = engine;
    this.myAssetManager = myAssetManager;
    atlas = myAssetManager.getTextureAsset("Sprites/Output/DarkThaumaturgyAtlas.atlas");
    entities = new ArrayList<Entity>();
    generator = new BodyGenerator(world);
    tempPositionVector = new Vector2(Vector2.Zero);
    tempDimensionVector = new Vector2(Vector2.Zero);


    }
    public void spawnEntities (TiledMap map) {
        MapLayer layer = map.getLayers().get("SPAWN_LAYER");

        for(MapObject object: layer.getObjects()) {
            String entityName = object.getProperties().get("Spawn", String.class);
            int x = object.getProperties().get("x", Float.class).intValue();
            int y = object.getProperties().get("y", Float.class).intValue();

            entities.add(spawnEntity(entityName,x,y));
        }


    }

    public Entity spawnEntity(String entityName, int x, int y) {
        Entity entity = engine.createEntity();

        Gdx.app.log(TAG, "Created " + entityName);

        switch(entityName) {
            case "Player":
                //Gdx.app.log("PLAYER CREATION", "POSITION: (" +x+"," +y+")");
            addBodyComponent(entity, entityName, x, y);
            addTransformComponent(entity, x, y);
            addTypeComponent(entity, entityName);
            addCollisionComponent(entity);
            addPlayerComponent(entity);
            addStateComponent(entity, entityName);
            addAnimationComponent(entity, entityName);
            addtextureComponent(entity, entityName);
            addRenderableComponent(entity);
            break;

            case "Enemy":
                addBodyComponent(entity, entityName, x, y);
                addTransformComponent(entity, x, y);
                addTypeComponent(entity, entityName);
                addCollisionComponent(entity);
                addStateComponent(entity, entityName);

                break;
            case "Coin":
                Gdx.app.log("COIN CREATION", "POSITION: (" +x+"," +y+")");
                addBodyComponent(entity, entityName, x, y);
                addTransformComponent(entity, x, y);
                addTypeComponent(entity, entityName);
                addtextureComponent(entity, entityName);
                addRenderableComponent(entity);

                break;

        }

        engine.addEntity(entity);
        return entity;



    }

    private Entity addStateComponent(Entity entity, String entityName) {
        StateComponent stateComponent = engine.createComponent(StateComponent.class);

        switch(entityName) {
            case "Player":
                stateComponent.setDirection(StateComponent.DIRECTION.DOWN);
                stateComponent.setState(StateComponent.STATE.IDLE);
                break;

            case "Enemy":
                stateComponent.setDirection(StateComponent.DIRECTION.DOWN);
                stateComponent.setState(StateComponent.STATE.IDLE);
        }

        entity.add(stateComponent);
        return entity;









    }

    private Entity addPlayerComponent(Entity entity) {
        PlayerComponent playerComponent = engine.createComponent(PlayerComponent.class);
        entity.add(playerComponent);
        return entity;
    }

    private Entity addCollisionComponent(Entity entity) {
        CollisionComponent collisionComponent = engine.createComponent(CollisionComponent.class);
        entity.add(collisionComponent);
        return entity;
    }

    private Entity addBodyComponent(Entity entity, String entityName, int x, int y) {
        tempPositionVector.x = x;
        tempPositionVector.y = y;
        BodyComponent bodyComponent = engine.createComponent(BodyComponent.class);
        FixtureDef fdef = new FixtureDef();

        //method used to build the body
        switch(entityName) {
            case "Player":
                fdef.filter.categoryBits = Figures.PLAYER;
                fdef.filter.maskBits = Figures.ENEMY | Figures.LEVEL | Figures.COIN;
                tempDimensionVector.x = 1;
                tempDimensionVector.y = 1;

                bodyComponent.setBody(generator.createBody(entity, tempPositionVector,
                        tempDimensionVector, BodyDef.BodyType.DynamicBody,1, fdef));
                bodyComponent.setActive(true);
                bodyComponent.getBody().setLinearDamping(3f);
                bodyComponent.getBody().setUserData(entity);
                break;

            case "Enemy":
                fdef.filter.categoryBits = Figures.ENEMY;
                fdef.filter.maskBits = Figures.ENEMY | Figures.LEVEL | Figures.PLAYER;
                tempDimensionVector.x = 1;
                tempDimensionVector.y = 1;

                bodyComponent.setBody(generator.createBody(entity, tempPositionVector,
                        tempDimensionVector, BodyDef.BodyType.DynamicBody,1, fdef));
                bodyComponent.setActive(true);
                bodyComponent.getBody().setLinearDamping(3f);
                bodyComponent.getBody().setUserData(entity);
                break;
            case "Coin":
                fdef.filter.categoryBits = Figures.COIN;
                fdef.filter.maskBits = Figures.LEVEL | Figures.PLAYER;
                tempDimensionVector.x = 1;
                tempDimensionVector.y = 1;

                bodyComponent.setBody(generator.createBody(entity, tempPositionVector,
                        tempDimensionVector, BodyDef.BodyType.DynamicBody,1, fdef));
                bodyComponent.setActive(true);
                //bodyComponent.getBody().setLinearDamping(3f);
                bodyComponent.getBody().setUserData(entity);
                break;

        }


        entity.add(bodyComponent);
        return entity;
    }

    private Entity addTypeComponent (Entity entity, String entityName) {
        TypeComponent typeComponent = engine.createComponent(TypeComponent.class);
        short type;
        switch(entityName) {
            case "Player":
                type = Figures.PLAYER;
                break;

            case "Enemy":
                type = Figures.ENEMY;
                break;

            case "Coin":
                type = Figures.COIN;
                break;
            default:
                type = Figures.OTHER;

        }
        typeComponent.setType(type);
        entity.add(typeComponent);
        return entity;
    }

    private Entity addAnimationComponent(Entity entity, String entiyName) {
        AnimationComponent animationComponent = engine.createComponent(AnimationComponent.class);

        switch(entiyName) {
            case "Player":
                animationComponent.addAnimation(AnimationComponent.ANIMATIONSTATE.UP,
                        new Animation(0.25f, atlas.findRegions("MainCharacterUp")))
                        .addAnimation(AnimationComponent.ANIMATIONSTATE.DOWN,
                                new Animation(0.25f, atlas.findRegions("MainCharacterDown")))
                        .addAnimation(AnimationComponent.ANIMATIONSTATE.LEFT,
                                new Animation(0.25f, atlas.findRegions("MainCharacterLeft")))
                        .addAnimation(AnimationComponent.ANIMATIONSTATE.RIGHT,
                                new Animation(0.25f, atlas.findRegions("MainCharacterRight")));
                break;

        }

        entity.add(animationComponent);
        return entity;
    }

    private Entity addtextureComponent(Entity entity, String entityName) {
        TextureComponent textureComponent = engine.createComponent(TextureComponent.class);

        switch(entityName) {
            case "Player":
                textureComponent.setRegion((TextureRegion) entity
                        .getComponent(AnimationComponent.class)
                        .getAnimation(AnimationComponent.ANIMATIONSTATE.DOWN)
                        .getKeyFrames()[0]);
                break;
            case "Coin":
                textureComponent.setRegion(new TextureRegion(atlas.findRegion("Coin")));
                break;
        }
        entity.add(textureComponent);
        return entity;
    }

    private Entity addRenderableComponent(Entity entity) {
        RenderableComponent renderableComponent = engine.createComponent(RenderableComponent.class);
        entity.add(renderableComponent);
        return entity;

    }

    public Entity addTransformComponent (Entity entity, int x, int y) {
        TransformComponent transformComponent = engine.createComponent(TransformComponent.class);
        tempPositionVector.set(x,y);
        transformComponent.setPosition(tempPositionVector);
        entity.add(transformComponent);

        return entity;
    }


}
