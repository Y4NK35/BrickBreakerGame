package com.brick.breaker.game.objects;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.World;

public abstract class GameEntity {

  protected float x, y, velX, velY, speed;
  protected float width, height;
  protected Body body;

  public GameEntity(World world) {
    this.width = 0;
    this.height = 0;
    this.body = null;
    this.velX = 0;
    this.velY = 0;
    this.speed = 0;
  }

  public GameEntity(World world, Body body) {
    this.width = 0;
    this.height = 0;
    this.body = body;
    this.velX = 0;
    this.velY = 0;
    this.speed = 0;
  }

  public abstract void update();

  public abstract void render(SpriteBatch spriteBatch);

  public Body getBody() {
    return body;
  }
}

