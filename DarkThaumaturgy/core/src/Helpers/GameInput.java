package Helpers;

import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.input.GestureDetector;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.utils.viewport.Viewport;

public class GameInput extends InputAdapter implements GestureDetector.GestureListener{
    private OrthographicCamera camera;
    private Viewport gameViewport;
    private Vector3 touch;
    private boolean left, right, up, down;

    public GameInput(Viewport gameViewport) {
        super();
        this.gameViewport = gameViewport;
        camera = (OrthographicCamera) gameViewport.getCamera();
        touch = new Vector3(Vector3.Zero);
    }



    @Override
    public boolean keyDown(int keycode) {
        boolean keyProcessed = false;
        switch(keycode) {
            case Input.Keys.A:
                left = true;
                keyProcessed = true;
                break;
            case Input.Keys.D:
                right = true;
                keyProcessed = true;
                break;
            case Input.Keys.W:
                up = true;
                keyProcessed = true;
                break;
            case Input.Keys.S:
                down = true;
                keyProcessed = true;
                break;
        }




        return keyProcessed;
    }

    @Override
    public boolean keyUp(int keycode) {
        boolean keyProcessed = false;
        switch(keycode) {
            case Input.Keys.A:
                left = false;
                keyProcessed = true;
                break;
            case Input.Keys.D:
                right = false;
                keyProcessed = true;
                break;
            case Input.Keys.W:
                up = false;
                keyProcessed = true;
                break;
            case Input.Keys.S:
                down = false;
                keyProcessed = true;
                break;
        }
        return super.keyUp(keycode);
    }

    @Override
    public boolean touchDown(float x, float y, int pointer, int button) {
        return false;
    }

    @Override
    public boolean tap(float x, float y, int count, int button) {
        if(count==1) {
            touch.x = x;
            touch.y = y;

            gameViewport.unproject(touch);
            touch.set(MathUtils.floor(touch.x), MathUtils.floor(touch.y), 0);
            return true;
        }
        return false;
    }

    @Override
    public boolean longPress(float x, float y) {
        return false;
    }

    @Override
    public boolean fling(float velocityX, float velocityY, int button) {
        return false;
    }

    @Override
    public boolean pan(float x, float y, float deltaX, float deltaY) {
        return false;
    }

    @Override
    public boolean panStop(float x, float y, int pointer, int button) {
        return false;
    }

    @Override
    public boolean zoom(float initialDistance, float distance) {
        return false;
    }

    @Override
    public boolean pinch(Vector2 initialPointer1, Vector2 initialPointer2, Vector2 pointer1, Vector2 pointer2) {
        return false;
    }

    @Override
    public void pinchStop() {

    }


    public boolean isLeft() {
        return left;
    }


    public boolean isRight() {
        return right;
    }


    public boolean isUp() {
        return up;
    }


    public boolean isDown() {
        return down;
    }


}
