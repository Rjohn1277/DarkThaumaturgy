package Systems;

import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.systems.IteratingSystem;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.Array;

import Components.RenderableComponent;
import Components.TextureComponent;
import Components.TransformComponent;
import Helpers.Figures;
import Helpers.Mappers;

public class RenderSystem extends IteratingSystem{

    private SpriteBatch batch;
    private Array<Entity> bodiesQueue;
    private OrthographicCamera camera;

    public RenderSystem(SpriteBatch batch, OrthographicCamera camera) {
        super(Family.all(RenderableComponent.class).get());
        this.batch = batch;
        this.camera = camera;
        bodiesQueue = new Array<>();
    }

    @Override
    public void update(float deltaTime) {
        super.update(deltaTime);

        batch.setProjectionMatrix(camera.combined);
        batch.begin();

        for(Entity entity: bodiesQueue) {
            TextureComponent texture = Mappers.textureComponent.get(entity);
            TransformComponent transform = Mappers.transformComponent.get(entity);


            batch.draw(texture.getRegion(),
                    transform.getPosition().x-(texture.getRegion().getRegionWidth()/2)/Figures.PPM,
                    transform.getPosition().y-(texture.getRegion().getRegionHeight()/2)/Figures.PPM,
                    texture.getRegion().getRegionWidth()/ Figures.PPM,
                    texture.getRegion().getRegionHeight()/Figures.PPM);
        }
        batch.end();
        bodiesQueue.clear();
    }

    @Override
    protected void processEntity(Entity entity, float deltaTime) {
        bodiesQueue.add(entity);
    }
}
