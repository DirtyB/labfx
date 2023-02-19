package com.bltolstukha.labfx.collision;

import com.almasb.fxgl.dsl.FXGL;
import com.almasb.fxgl.entity.Entity;
import com.almasb.fxgl.physics.CollisionHandler;
import com.bltolstukha.labfx.Variables;
import com.bltolstukha.labfx.entity.EntityType;

public class PlayerCoinCollisionHandler extends CollisionHandler {

    public PlayerCoinCollisionHandler() {
        super(EntityType.PLAYER, EntityType.COIN);
    }

    // order of types is the same as passed into the constructor
    @Override
    protected void onCollisionBegin(Entity player, Entity coin) {
        FXGL.play("drop.wav");
        FXGL.inc(Variables.GAME_VAR_SCORE, 10);
        coin.removeFromWorld();
    }
}
