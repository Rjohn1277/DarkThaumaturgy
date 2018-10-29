package Components;

import com.badlogic.ashley.core.Component;
import com.badlogic.gdx.utils.Pool;

public class TypeComponent implements Component, Pool.Poolable{

    private static final short OTHER = 2;
    private static final short LEVEL = 4;
    private static final short PLAYER = 8;
    private static final short ENEMY = 16;

    //type components type
    private short type = OTHER;

    public short getType() {
        return type;
    }

    public void setType(short type) {
        this.type = type;
    }

    @Override
    public void reset() {
        type = OTHER;
    }
}
