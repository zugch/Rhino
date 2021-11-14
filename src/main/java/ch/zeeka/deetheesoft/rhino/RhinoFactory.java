package ch.zeeka.deetheesoft.rhino;

import com.almasb.fxgl.dsl.FXGL;
import com.almasb.fxgl.dsl.components.ProjectileComponent;
import com.almasb.fxgl.entity.Entity;
import com.almasb.fxgl.entity.EntityFactory;
import com.almasb.fxgl.entity.SpawnData;
import com.almasb.fxgl.entity.Spawns;
import javafx.geometry.Point2D;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

import static com.almasb.fxgl.dsl.FXGL.entityBuilder;

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
}