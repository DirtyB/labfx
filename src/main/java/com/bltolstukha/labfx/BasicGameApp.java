package com.bltolstukha.labfx;

import com.almasb.fxgl.app.GameApplication;
import com.almasb.fxgl.app.GameSettings;
import com.almasb.fxgl.dsl.FXGL;
import com.almasb.fxgl.entity.Entity;
import javafx.scene.input.KeyCode;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;

import java.util.Map;


public class BasicGameApp extends GameApplication {

    public static final String GAME_VAR_PIXELS_MOVED = "pixelsMoved";
    private Entity player;

    @Override
    protected void initSettings(GameSettings settings) {
        settings.setWidth(600);
        settings.setHeight(600);
        settings.setTitle("Porcelain Labyrinth");
        settings.setVersion("1.0-SNAPSHOT");
    }

    @Override
    protected void initGame() {
        player = FXGL.entityBuilder()
                .at(300, 300)
                .view(new Rectangle(25, 25, Color.GREEN))
                .buildAndAttach();
    }

    @Override
    protected void initInput() {
        FXGL.onKey(KeyCode.RIGHT, () -> {
            player.translateX(5); // move right 5 pixels
            FXGL.inc(GAME_VAR_PIXELS_MOVED, 5);
        });

        FXGL.onKey(KeyCode.LEFT, () -> {
            player.translateX(-5); // move left 5 pixels
            FXGL.inc(GAME_VAR_PIXELS_MOVED, 5);
        });

        FXGL.onKey(KeyCode.UP, () -> {
            player.translateY(-5); // move up 5 pixels
            FXGL.inc(GAME_VAR_PIXELS_MOVED, 5);
        });

        FXGL.onKey(KeyCode.DOWN, () -> {
            player.translateY(5); // move down 5 pixels
            FXGL.inc(GAME_VAR_PIXELS_MOVED, 5);
        });
    }

    @Override
    protected void initUI() {
        Text textPixels = new Text();
        textPixels.textProperty().bind(FXGL.getWorldProperties().intProperty(GAME_VAR_PIXELS_MOVED).asString());
        textPixels.setTranslateX(50); // x = 50
        textPixels.setTranslateY(100); // y = 100

        FXGL.getGameScene().addUINode(textPixels); // add to the scene graph
    }

    @Override
    protected void initGameVars(Map<String, Object> vars) {
        vars.put(GAME_VAR_PIXELS_MOVED, 0);
    }

    public static void main(String[] args) {
        launch(args);
    }

}