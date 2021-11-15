package ch.zeeka.deetheesoft.rhino;

import com.almasb.fxgl.core.math.FXGLMath;
import com.almasb.fxgl.core.math.Vec2;
import com.almasb.fxgl.dsl.FXGL;
import com.almasb.fxgl.entity.component.Component;
import com.almasb.fxgl.texture.AnimatedTexture;
import com.almasb.fxgl.texture.AnimationChannel;
import javafx.util.Duration;

/**
 * @author Christian Züger (https://github.com/zugch)
 */
public class RhinoComponent extends Component {

    public static final int FULL_SPEED = 2;
    public static final int ROTATE_ANGLE = 2;
    public static final int RHINO_WIDTH = 60;
    public static final int RHINO_HEIGHT = 120;

    private int speed = 0;
    private AnimatedTexture texture;
    private AnimationChannel animIdle, animWalk;

    public RhinoComponent(){
        animIdle = new AnimationChannel(FXGL.image("rhino_sprite_sheet.png"), 2, RHINO_WIDTH, RHINO_HEIGHT, Duration.seconds(1),0,1);
        animWalk = new AnimationChannel(FXGL.image("rhino_sprite_sheet.png"), 2, RHINO_WIDTH, RHINO_HEIGHT, Duration.seconds(1),0,1);
        texture = new AnimatedTexture(animIdle);
    }

    @Override
    public void onAdded() {
        entity.getTransformComponent().setScaleOrigin(entity.getCenter());
        entity.getViewComponent().addChild(texture);
        texture.loopAnimationChannel(animIdle);
    }

    @Override
    public void onUpdate(double tpf) {

        Vec2 dir = Vec2.fromAngle(entity.getRotation() - 90)
                .mulLocal(speed);
        entity.translate(dir);

        if(speed != 0)
        {
            if(texture.getAnimationChannel() == animIdle){
                texture.loopAnimationChannel(animWalk);
            }

            speed = (int)(speed*0.9);

            if(FXGLMath.abs(speed) < 1){
                speed = 0;
                texture.loopAnimationChannel(animIdle);
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