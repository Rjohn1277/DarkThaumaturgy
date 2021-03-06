package Systems;

import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.core.PooledEngine;
import com.badlogic.ashley.systems.IteratingSystem;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.physics.box2d.World;
import com.darkthaumaturgy.DarkThaumaturgy;

import Components.BodyComponent;
import Components.CollisionComponent;
import Components.PlayerComponent;
import Components.StateComponent;
import Components.TypeComponent;
import Helpers.Figures;
import Helpers.Mappers;

public class CollisionSystem extends IteratingSystem {


    private PooledEngine engine;
    private World world;
    private DarkThaumaturgy game;

    public CollisionSystem(PooledEngine engine, World world, DarkThaumaturgy game) {
        super(Family.all(CollisionComponent.class).get());
        this.engine = engine;
        this.world = world;
        this.game = game;
    }

    @Override
    protected void processEntity(Entity entity, float deltaTime) {
        CollisionComponent collision = Mappers.collisionComponent.get(entity);
        Entity collidedEntity = collision.getCollidedEntity();

        TypeComponent thisType = entity.getComponent(TypeComponent.class);
        PlayerComponent playerComponent = Mappers.playerComponent.get(entity);
        StateComponent state = Mappers.stateComponent.get(entity);
        BodyComponent body = Mappers.bodyComponent.get(entity);
        TypeComponent collidedType;


        String TAG;

        if (thisType.getType() == Figures.PLAYER) {
            TAG = "PLAYER COLLISION";
            if (collidedEntity == null) {
                return;
            }

            collidedType = collidedEntity.getComponent(TypeComponent.class);

            if (collidedType == null) {
                return;
            }

            switch (collidedType.getType()) {
                case Figures.ENEMY:
                    Gdx.app.log(TAG, "ENEMY");
                    break;
                case Figures.LEVEL:
                    Gdx.app.log(TAG, "LEVEL");
                    break;
                    case Figures.COIN:
                        Gdx.app.log(TAG, "COIN");
                case Figures.OTHER:
                    Gdx.app.log(TAG, "OTHER");
                    break;


            }
        } else if (thisType.getType() == Figures.ENEMY) {

            TAG = "ENEMY COLLISION";
            if (collidedEntity == null) {
                return;
            }

            collidedType = collidedEntity.getComponent(TypeComponent.class);

            if (collidedType == null) {
                return;
            }

            switch (collidedType.getType()) {
                case Figures.ENEMY:
                    Gdx.app.log(TAG, "ENEMY");
                    break;
                case Figures.LEVEL:
                    Gdx.app.log(TAG, "LEVEL");
                    break;
                case Figures.PLAYER:
                    Gdx.app.log(TAG, "PLAYER");
                    break;
                case Figures.OTHER:
                    Gdx.app.log(TAG, "OTHER");
                    break;
            }
        }
        collision.setCollidedEntity(null);

    }
}
