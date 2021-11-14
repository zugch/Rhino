package ch.zeeka.deetheesoft.rhino;

import com.almasb.fxgl.core.math.FXGLMath;
import com.almasb.fxgl.dsl.FXGL;
import com.almasb.fxgl.entity.component.Component;
import com.almasb.fxgl.texture.AnimatedTexture;
import com.almasb.fxgl.texture.AnimationChannel;
import javafx.geometry.Point2D;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.util.Duration;

public class RhinoComponent extends Component {

    public static final int X = 0;
    public static final int Y = 1;

    public static final int FRAMES_PER_ROW = 4;
    public static final int FRAME_WIDTH = 50;
    public static final int  FRAME_HEIGHT = 50;

    private int[] speed = {0,0};

    @Override
    public void onAdded() {
        entity.getTransformComponent().setScaleOrigin(new Point2D(FRAME_WIDTH/2,FRAME_HEIGHT/2));
        entity.getViewComponent().addChild(new Rectangle(FRAME_WIDTH, FRAME_HEIGHT, Color.GRAY));
    }

    @Override
    public void onUpdate(double tpf) {
        entity.translateX(speed[X]*tpf);
        entity.translateY(speed[Y]*tpf);

        if(speed[X] != 0)
        {
            speed[X] = (int)(speed[X]*0.9);

            if(FXGLMath.abs(speed[X]) < 1){
                speed[X] = 0;
            }
        }

        if(speed[Y] != 0)
        {
            speed[Y] = (int)(speed[Y]*0.9);

            if(FXGLMath.abs(speed[Y]) < 1){
                speed[Y] = 0;
            }
        }
    }

    public void moveRight()
    {
        speed[X] = 150;
        getEntity().setScaleX(1);
    }

    public void moveLeft()
    {
        speed[X] = -150;
        getEntity().setScaleX(-1);
    }

    public void moveDown()
    {
        speed[Y] = 150;
        getEntity().setScaleY(1);
    }

    public void moveUp()
    {
        speed[Y] = -150;
        getEntity().setScaleY(-1);
    }
}