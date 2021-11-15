package ch.zeeka.deetheesoft.rhino;

import com.almasb.fxgl.core.math.FXGLMath;
import com.almasb.fxgl.core.math.Vec2;
import com.almasb.fxgl.entity.component.Component;

/**
 * @author Christian Züger (https://github.com/zugch)
 */
public class RhinoComponent extends Component {

    public static final int FULL_SPEED = 2;
    public static final int ROTATE_ANGLE = 2;

    private int speed = 0;

    @Override
    public void onAdded() {
        entity.getTransformComponent().setScaleOrigin(entity.getCenter());
    }

    @Override
    public void onUpdate(double tpf) {

        Vec2 dir = Vec2.fromAngle(entity.getRotation() - 90)
                .mulLocal(speed);
        entity.translate(dir);

        if(speed != 0)
        {
            speed = (int)(speed*0.9);

            if(FXGLMath.abs(speed) < 1){
                speed = 0;
            }
        }
    }

    public void move()
    {
        speed = FULL_SPEED;
    }

    public void rotateLeft()
    {
        entity.rotateBy(-ROTATE_ANGLE);
    }

    public void rotateRight()
    {
        entity.rotateBy(ROTATE_ANGLE);
    }
}