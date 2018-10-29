package Components;

import com.badlogic.ashley.core.Component;
import com.badlogic.gdx.utils.Pool;

import Helpers.Figures;

public class TypeComponent implements Component, Pool.Poolable{



    //type components type
    private short type = Figures.OTHER;

    public short getType() {
        return type;
    }

    public void setType(short type) {
        this.type = type;
    }

    @Override
    public void reset() {
        type = Figures.OTHER;
    }
}
