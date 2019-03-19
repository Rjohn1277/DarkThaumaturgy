package Systems;

import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.systems.IteratingSystem;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.Array;

public class RenderSystem extends IteratingSystem{

    private SpriteBatch batch;
    private Array<Entity> bodiesQueue;
    private OrthographicCamera camera;










    @Override
    protected void processEntity(Entity entity, float deltaTime) {

    }
}
