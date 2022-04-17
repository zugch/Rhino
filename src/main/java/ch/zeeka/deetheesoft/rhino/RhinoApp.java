package ch.zeeka.deetheesoft.rhino;

import com.almasb.fxgl.app.GameApplication;
import com.almasb.fxgl.app.GameSettings;
import com.almasb.fxgl.core.math.FXGLMath;
import com.almasb.fxgl.dsl.FXGL;
import com.almasb.fxgl.entity.Entity;
import com.almasb.fxgl.input.UserAction;
import javafx.geometry.Rectangle2D;
import javafx.scene.input.KeyCode;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.util.Duration;

import java.util.Map;

import static com.almasb.fxgl.dsl.FXGL.*;

/**
 * @author Christian Züger (https://github.com/zugch)
 */
public class RhinoApp extends GameApplication {

    public static final String FOOD_SCORE = "foodScore";

    private Entity rhino;

    public static void main(String[] args) {
        launch(args);
    }
    @Override
    protected void initSettings(GameSettings gameSettings) {
        gameSettings.setTitle("Rhino App");
    }

    @Override
    protected void initGame() {
        getGameWorld().addEntityFactory(new RhinoFactory());

        rhino = spawn("rhino", 200,200);

        run(() -> {
            spawn("food", FXGLMath.randomPoint(new Rectangle2D(0,0, getAppWidth(), getAppHeight())));
        }, Duration.seconds(4));
    }

    @Override
    protected void initUI() {
        Text textFoodScore = getUIFactory().newText("", Color.BLACK, 22);

        textFoodScore.setTranslateX(getAppWidth()-30);
        textFoodScore.setTranslateY(50);

        textFoodScore.textProperty().bind(getGameState().intProperty(FOOD_SCORE).asString());

        getGameScene().addUINodes(textFoodScore);
    }

    @Override
    protected void initInput() {
        getInput().addAction(new UserAction("Move") {
            @Override
            protected void onAction() {
                rhino.getComponent(RhinoComponent.class).move();
            }
        }, KeyCode.UP);

        getInput().addAction(new UserAction("Move backwards") {
            @Override
            protected void onAction() {
                rhino.getComponent(RhinoComponent.class).moveBackwards();
            }
        }, KeyCode.DOWN);

        getInput().addAction(new UserAction("Rotate right") {
            @Override
            protected void onAction() {
                rhino.getComponent(RhinoComponent.class).rotateRight();
            }
        }, KeyCode.RIGHT);

        getInput().addAction(new UserAction("Rotate left") {
            @Override
            protected void onAction() {
                rhino.getComponent(RhinoComponent.class).rotateLeft();
            }
        }, KeyCode.LEFT);
    }

    @Override
    protected void initPhysics() {
        onCollisionBegin(EntityType.RHINO, EntityType.FOOD, (rhino, food) -> {
            FXGL.play("nom.wav");
            food.removeFromWorld();
            getGameState().increment(FOOD_SCORE, +1);
        });
    }

    @Override
    protected void initGameVars(Map<String, Object> vars) {
        vars.put(FOOD_SCORE, 0);
    }
}