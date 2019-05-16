package Systems;

import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.systems.IteratingSystem;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

import Components.AnimationComponent;
import Components.RenderableComponent;
import Components.StateComponent;
import Components.TextureComponent;
import Helpers.Mappers;

public class AnimationSystem extends IteratingSystem{
    public static final String TAG = AnimationSystem.class.getSimpleName();

    public AnimationSystem() {
        super(Family.all(RenderableComponent.class, TextureComponent.class, AnimationComponent.class).get());
    }

    @Override
    protected void processEntity(Entity entity, float deltaTime) {
        AnimationComponent animationComponent = Mappers.animationComponent.get(entity);
        TextureComponent textureComponent = Mappers.textureComponent.get(entity);
        StateComponent stateComponent = Mappers.stateComponent.get(entity);

        animationComponent.setTime(deltaTime+animationComponent.getTime());
        float stateTimer = animationComponent.getTime();

        Animation currentAnimation;

        switch(stateComponent.getState()) {
            case MOVING:
                if(stateComponent.getDirection() == StateComponent.DIRECTION.DOWN) {
                    currentAnimation = animationComponent.getAnimation(AnimationComponent.ANIMATIONSTATE.DOWN);
                    animationComponent.setLooping(true);

                    if(currentAnimation == null) {
                        Gdx.app.log(TAG, "Animation is not loaded properly: DOWN");
                        return;
                    }

                    textureComponent.setRegion((TextureRegion) currentAnimation.getKeyFrame(stateTimer, animationComponent.isLooping()));
                }

                else if (stateComponent.getDirection() == StateComponent.DIRECTION.LEFT) {
                    currentAnimation = animationComponent.getAnimation(AnimationComponent.ANIMATIONSTATE.LEFT);
                    animationComponent.setLooping(true);

                    if(currentAnimation == null) {
                        Gdx.app.log(TAG, "Animation is not loaded properly: LEFT");
                        return;
                    }

                    textureComponent.setRegion((TextureRegion) currentAnimation.getKeyFrame(stateTimer, animationComponent.isLooping()));
                }

                else if (stateComponent.getDirection() == StateComponent.DIRECTION.UP) {
                    currentAnimation = animationComponent.getAnimation(AnimationComponent.ANIMATIONSTATE.UP);
                    animationComponent.setLooping(true);

                    if(currentAnimation == null) {
                        Gdx.app.log(TAG, "Animation is not loaded properly: UP");
                        return;
                    }

                    textureComponent.setRegion((TextureRegion) currentAnimation.getKeyFrame(stateTimer, animationComponent.isLooping()));
                }

                else if (stateComponent.getDirection() == StateComponent.DIRECTION.RIGHT) {
                    currentAnimation = animationComponent.getAnimation(AnimationComponent.ANIMATIONSTATE.RIGHT);
                    animationComponent.setLooping(true);

                    if(currentAnimation == null) {
                        Gdx.app.log(TAG, "Animation is not loaded properly: RIGHT");
                        return;
                    }

                    textureComponent.setRegion((TextureRegion) currentAnimation.getKeyFrame(stateTimer, animationComponent.isLooping()));
                }
                break;

            case IDLE:
                if(stateComponent.getDirection() == StateComponent.DIRECTION.DOWN) {
                    currentAnimation = animationComponent.getAnimation(AnimationComponent.ANIMATIONSTATE.DOWN);
                    animationComponent.setLooping(false);

                    if(currentAnimation == null) {
                        Gdx.app.log(TAG, "Animation is not loaded properly: DOWN");
                        return;
                    }

                    textureComponent.setRegion((TextureRegion) currentAnimation.getKeyFrames()[0]);
                }

                else if (stateComponent.getDirection() == StateComponent.DIRECTION.LEFT) {
                    currentAnimation = animationComponent.getAnimation(AnimationComponent.ANIMATIONSTATE.LEFT);
                    animationComponent.setLooping(false);

                    if(currentAnimation == null) {
                        Gdx.app.log(TAG, "Animation is not loaded properly: LEFT");
                        return;
                    }

                    textureComponent.setRegion((TextureRegion) currentAnimation.getKeyFrames()[0]);
                }

                else if (stateComponent.getDirection() == StateComponent.DIRECTION.UP) {
                    currentAnimation = animationComponent.getAnimation(AnimationComponent.ANIMATIONSTATE.UP);
                    animationComponent.setLooping(false);

                    if(currentAnimation == null) {
                        Gdx.app.log(TAG, "Animation is not loaded properly: UP");
                        return;
                    }

                    textureComponent.setRegion((TextureRegion) currentAnimation.getKeyFrames()[0]);
                }

                else if (stateComponent.getDirection() == StateComponent.DIRECTION.RIGHT) {
                    currentAnimation = animationComponent.getAnimation(AnimationComponent.ANIMATIONSTATE.RIGHT);
                    animationComponent.setLooping(false);

                    if(currentAnimation == null) {
                        Gdx.app.log(TAG, "Animation is not loaded properly: RIGHT");
                        return;
                    }

                    textureComponent.setRegion((TextureRegion) currentAnimation.getKeyFrames()[0]);
                }
                break;






        }
    }
}
