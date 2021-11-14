package ch.zeeka.deetheesoft.rhino;

import com.almasb.fxgl.app.GameApplication;
import com.almasb.fxgl.app.GameSettings;
import com.almasb.fxgl.dsl.FXGL;
import com.almasb.fxgl.entity.Entity;
import com.almasb.fxgl.input.UserAction;
import javafx.scene.input.KeyCode;

import static com.almasb.fxgl.dsl.FXGL.*;

public class RhinoApp extends GameApplication {

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
    }

    @Override
    protected void initInput() {
        getInput().addAction(new UserAction("Move") {
            @Override
            protected void onAction() {
                rhino.getComponent(RhinoComponent.class).move();
            }
        }, KeyCode.UP);

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
}