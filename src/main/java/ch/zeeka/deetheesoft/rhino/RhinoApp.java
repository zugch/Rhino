package ch.zeeka.deetheesoft.rhino;

import com.almasb.fxgl.app.GameApplication;
import com.almasb.fxgl.app.GameSettings;
import com.almasb.fxgl.dsl.FXGL;
import com.almasb.fxgl.entity.Entity;
import com.almasb.fxgl.input.UserAction;
import javafx.scene.input.KeyCode;

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
        rhino = FXGL.entityBuilder()
                .at(200, 200)
                .with(new RhinoComponent())
                .buildAndAttach();
    }

    @Override
    protected void initInput() {
        FXGL.getInput().addAction(new UserAction("Right") {
            @Override
            protected void onAction() {
                rhino.getComponent(RhinoComponent.class).moveRight();
            }
        }, KeyCode.D);

        FXGL.getInput().addAction(new UserAction("Left") {
            @Override
            protected void onAction() {
                rhino.getComponent(RhinoComponent.class).moveLeft();
            }
        }, KeyCode.A);

        FXGL.getInput().addAction(new UserAction("Up") {
            @Override
            protected void onAction() {
                rhino.getComponent(RhinoComponent.class).moveUp();
            }
        }, KeyCode.W);

        FXGL.getInput().addAction(new UserAction("Down") {
            @Override
            protected void onAction() {
                rhino.getComponent(RhinoComponent.class).moveDown();
            }
        }, KeyCode.S);
    }
}
