package ch.zeeka.deetheesoft.rhino;

import com.almasb.fxgl.entity.Entity;
import com.almasb.fxgl.entity.EntityFactory;
import com.almasb.fxgl.entity.SpawnData;
import com.almasb.fxgl.entity.Spawns;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;

import static com.almasb.fxgl.dsl.FXGL.entityBuilder;

/**
 * @author Christian Züger (https://github.com/zugch)
 */
public class RhinoFactory implements EntityFactory {

    @Spawns("rhino")
    public Entity NewRhino(SpawnData data) {
        return entityBuilder(data)
                .type(EntityType.RHINO)
                .viewWithBBox("rhino.png")
                .with(new RhinoComponent())
                .collidable()
                .build();
    }

    @Spawns("food")
    public Entity NewFood(SpawnData data) {
        return entityBuilder(data)
                .type(EntityType.FOOD)
                .viewWithBBox("food.png")
                .collidable()
                .build();
    }
}