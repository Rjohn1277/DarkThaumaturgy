package Managers;

import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.PooledEngine;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.World;
import com.darkthaumaturgy.DarkThaumaturgy;

import Components.BodyComponent;
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

    public EntityManager(DarkThaumaturgy darkThaumaturgy, World world, SpriteBatch batch, PooledEngine engine) {
    this.darkThaumaturgy = darkThaumaturgy;
    this.world = world;
    this.batch = batch;
    this.engine = engine;
    generator = new BodyGenerator(world);
    tempPositionVector = Vector2.Zero;
    tempDimensionVector = Vector2.Zero;


    }

    public Entity spawnEntity(String entityName, int x, int y) {
        Entity entity = engine.createEntity();

        switch(entityName) {
            case "Player":
            addBodyComponent(entity, entityName, x, y);
            addTypeComponent(entity, entityName);
            break;

        }

        engine.addEntity(entity);
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
