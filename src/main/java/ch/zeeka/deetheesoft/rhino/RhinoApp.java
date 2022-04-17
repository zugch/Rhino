package ch.zeeka.deetheesoft.rhino;

import com.almasb.fxgl.app.GameApplication;
import com.almasb.fxgl.app.GameSettings;
import com.almasb.fxgl.core.math.FXGLMath;
import com.almasb.fxgl.dsl.FXGL;
import com.almasb.fxgl.entity.Entity;
import com.almasb.fxgl.input.UserAction;
import com.almasb.fxgl.ui.ProgressBar;
import javafx.geometry.Rectangle2D;
import javafx.scene.input.KeyCode;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import javafx.util.Duration;

import java.util.Map;

import static com.almasb.fxgl.dsl.FXGL.*;

/**
 * @author Christian Züger (https://github.com/zugch)
 */
public class RhinoApp extends GameApplication {

    public static final String FOOD_SCORE = "foodScore";
    public static final String ENERGY_LEVEL = "energyLevel";
    public static final int MAX_ENERGY_LEVEL = 100;

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
            spawn("food", FXGLMath.randomPoint(new Rectangle2D(0,0, getAppWidth(), getAppHeight() - 100)));
        }, Duration.seconds(1));
    }

    @Override
    protected void initUI() {
        // food score
        Text textFoodScore = getUIFactory().newText("", Color.BLACK, 22);
        textFoodScore.setTextAlignment(TextAlignment.RIGHT);
        textFoodScore.setTranslateX(getAppWidth()-30);
        textFoodScore.setTranslateY(50);
        textFoodScore.textProperty().bind(getGameState().intProperty(FOOD_SCORE).asString());
        getGameScene().addUINodes(textFoodScore);

        // energy level
        var energyLevelIcon = FXGL.getAssetLoader().loadTexture("food.png");
        energyLevelIcon.setTranslateX(5);
        energyLevelIcon.setTranslateY(getAppHeight()-50);
        getGameScene().addUINodes(energyLevelIcon);

        ProgressBar pbEnergyLevel = new ProgressBar(false);
        pbEnergyLevel.setWidth(200);
        pbEnergyLevel.setHeight(25);
        pbEnergyLevel.setFill(Color.LIGHTGREEN);
        pbEnergyLevel.setBackgroundFill(Color.BLACK);
        pbEnergyLevel.setMinValue(0);
        pbEnergyLevel.setMaxValue(100);
        pbEnergyLevel.setTranslateX(5+25+5);
        pbEnergyLevel.setTranslateY(getAppHeight()-50);
        pbEnergyLevel.currentValueProperty().bind(getGameState().intProperty(ENERGY_LEVEL));
        getGameScene().addUINodes(pbEnergyLevel);
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
            if(getGameState().getInt(ENERGY_LEVEL) < MAX_ENERGY_LEVEL)
            {
                getGameState().increment(ENERGY_LEVEL, +1);
            }
        });
    }

    @Override
    protected void initGameVars(Map<String, Object> vars) {
        vars.put(FOOD_SCORE, 0);
        vars.put(ENERGY_LEVEL, 0);
    }
}