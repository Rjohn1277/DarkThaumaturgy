package Helpers;

import com.badlogic.gdx.math.Vector2;

public class Figures {
    public static final int VIRTUALWIDTH = 16;
    public static final int VIRTUALHEIGHT = 11;
    public static final float PPM = 16;


    //Collision types
    public static final short OTHER = 2;
    public static final short LEVEL = 4;
    public static final short PLAYER = 8;
    public static final short ENEMY = 16;
    public static final short COIN = 32;

    //World gravity
    public static final Vector2 GRAVITATIONAL_FORCES = new Vector2(0,0);
}
