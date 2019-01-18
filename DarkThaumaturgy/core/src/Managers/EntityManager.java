package Managers;

import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.PooledEngine;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.maps.MapLayer;
import com.badlogic.gdx.maps.MapObject;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.World;
import com.darkthaumaturgy.DarkThaumaturgy;

import java.util.ArrayList;

import Components.BodyComponent;
import Components.CollisionComponent;
import Components.PlayerComponent;
import Components.StateComponent;
import Components.TypeComponent;
import Helpers.BodyGenerator;
import Helpers.Figures;

public class EntityManager {
    private DarkThaumaturgy darkThaumaturgy;
    private World world;
    private SpriteBatch batch;
    private PooledEngine engine;
    private BodyGenerator generator;
    private Vector2 tempPositionVector;
    private Vector2 tempDimensionVector;

    private ArrayList<Entity> entities;

    public EntityManager(DarkThaumaturgy darkThaumaturgy, World world, SpriteBatch batch, PooledEngine engine) {
    this.darkThaumaturgy = darkThaumaturgy;
    this.world = world;
    this.batch = batch;
    this.engine = engine;
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

        switch(entityName) {
            case "Player":
            addBodyComponent(entity, entityName, x, y);
            addTypeComponent(entity, entityName);
            addCollisionComponent(entity);
            addPlayerComponent(entity);
            addStateComponent(entity, entityName);
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
                fdef.filter.maskBits = Figures.ENEMY | Figures.LEVEL;
                tempDimensionVector.x = 1;
                tempDimensionVector.y = 1;

                bodyComponent.setBody(generator.createBody(entity, tempPositionVector,
                        tempDimensionVector, BodyDef.BodyType.DynamicBody,1, fdef));
                bodyComponent.setActive(true);
                bodyComponent.getBody().setLinearDamping(3f);
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
            default:
                type = Figures.OTHER;
                break;
        }
        typeComponent.setType(type);
        entity.add(typeComponent);
        return entity;
    }

}
