package Systems;

import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.systems.IteratingSystem;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.utils.Array;

import Components.BodyComponent;
import Components.TransformComponent;
import Helpers.Mappers;


public class PhysicsSystem extends IteratingSystem {


    private final float MAX_TIME_STEP = 1/30f;
    private float accumulator = 0f;
    private World world;
    private Array<Entity> bodiesQueue;

    public PhysicsSystem(World world) {
        super(Family.all(BodyComponent.class, TransformComponent.class).get());
        this.world = world;
        bodiesQueue = new Array<Entity>();
    }

    @Override
    public void update(float deltaTime) {
        super.update(deltaTime);

        float frameTime = Math.min(deltaTime,0.25f);
        accumulator += frameTime;
        if(accumulator >= MAX_TIME_STEP) {
            world.step(MAX_TIME_STEP, 6, 2);
            accumulator -= MAX_TIME_STEP;

            for(Entity entity: bodiesQueue) {
                TransformComponent transformComponent = Mappers.transformComponent.get(entity);
                BodyComponent bodyComponent = Mappers.bodyComponent.get(entity);




            }




        }




    }

    @Override
    protected void processEntity(Entity entity, float deltaTime) {
        bodiesQueue.add(entity);
    }
}
