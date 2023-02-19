package com.bltolstukha.labfx;

import com.almasb.fxgl.app.GameApplication;
import com.almasb.fxgl.app.GameSettings;
import com.almasb.fxgl.dsl.FXGL;
import com.almasb.fxgl.entity.Entity;
import com.almasb.fxgl.entity.components.CollidableComponent;
import com.bltolstukha.labfx.collision.PlayerCoinCollisionHandler;
import com.bltolstukha.labfx.entity.EntityType;
import javafx.scene.input.KeyCode;
import javafx.scene.text.Text;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;


public class BasicGameApp extends GameApplication {

    public static final int SCREEN_WIDTH = 800;
    public static final int SCREEN_HEIGHT = 600;

    private Entity player;

    private List<Entity> monsters = new ArrayList<>();


    @Override
    protected void initSettings(GameSettings settings) {
        settings.setWidth(SCREEN_WIDTH);
        settings.setHeight(SCREEN_HEIGHT);
        settings.setTitle("Porcelain Labyrinth");
        settings.setVersion("1.0-SNAPSHOT");
    }

    @Override
    protected void initGame() {
        player = FXGL.entityBuilder()
                .type(EntityType.PLAYER)
                .at(300, 300)
                .scale(0.4, 0.4)
                .viewWithBBox("player.png")
                .with(new CollidableComponent(true))
                .buildAndAttach();

        monsters.add(FXGL.entityBuilder()
                .type(EntityType.MONSTER)
                .at(-200, -200)
                .scale(0.3, 0.3)
                .viewWithBBox("monster3.png")
                .with(new CollidableComponent(true))
                .buildAndAttach());

        for (int i = 0; i < 10; i++) {
            double x = Math.random() * SCREEN_WIDTH;
            double y = Math.random() * SCREEN_HEIGHT;

            FXGL.entityBuilder()
                    .type(EntityType.COIN)
                    .at(x, y)
                    .scale(0.2, 0.2)
                    .viewWithBBox("coin.png")
                    .with(new CollidableComponent(true))
                    .buildAndAttach();
        }
    }

    @Override
    protected void initInput() {
        FXGL.onKey(KeyCode.RIGHT, () -> {
            player.translateX(5); // move right 5 pixels
        });

        FXGL.onKey(KeyCode.LEFT, () -> {
            player.translateX(-5); // move left 5 pixels
        });

        FXGL.onKey(KeyCode.UP, () -> {
            player.translateY(-5); // move up 5 pixels
        });

        FXGL.onKey(KeyCode.DOWN, () -> {
            player.translateY(5); // move down 5 pixels
        });
    }

    @Override
    protected void initUI() {
        Text textPixels = new Text();
        textPixels.textProperty().bind(FXGL.getWorldProperties().intProperty(Variables.GAME_VAR_SCORE).asString());
        textPixels.setTranslateX(10); // x = 50
        textPixels.setTranslateY(30); // y = 100

        FXGL.getGameScene().addUINode(textPixels); // add to the scene graph

//        Texture brickTexture = FXGL.getAssetLoader().loadTexture("brick.png");
//        brickTexture.setTranslateX(50);
//        brickTexture.setTranslateY(450);
//
//        FXGL.getGameScene().addUINode(brickTexture);
    }

    @Override
    protected void initGameVars(Map<String, Object> vars) {
        vars.put(Variables.GAME_VAR_SCORE, 0);
    }

    @Override
    protected void initPhysics() {
        FXGL.getPhysicsWorld().addCollisionHandler(new PlayerCoinCollisionHandler());
    }

    public static void main(String[] args) {
        launch(args);
    }

}