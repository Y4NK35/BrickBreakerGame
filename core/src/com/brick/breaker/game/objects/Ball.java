package com.brick.breaker.game.objects;

import static com.brick.breaker.game.helper.Constants.BRICK_HEIGHT;
import static com.brick.breaker.game.helper.Constants.PPM;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.World;
import com.brick.breaker.game.helper.BodyHelperService;

public class Ball {

  private final float BALL_INITIAL_X = Gdx.graphics.getWidth() / (2);
  private final float BALL_INITIAL_Y = 200f;
  private final float RADIUS = 12;
  protected float x, y, velX, velY, speed;
  private Texture ballImage;
  protected Body body;
  private float velXFactor;
  private float velYFactor;

  public Ball(World world) {
    this.speed = 100f;
    this.velX = 1;
    this.velY = 1;
    this.body = BodyHelperService.createBody(
        BALL_INITIAL_X,
        BALL_INITIAL_Y,
        RADIUS,
        false,
        world);
    body.setUserData(this);
    ballImage = new Texture(Gdx.files.internal("Ball.png"));
  }

  public void update() {
    x = body.getPosition().x;
    y = body.getPosition().y;
    ballMovement();
  }

  public void ballMovement() {
    if (body.getPosition().x > (Gdx.graphics.getWidth() - RADIUS - 1) / PPM) {
      velX *= -1;
    }
    if (body.getPosition().x < (RADIUS + 1) / PPM) {
      velX *= -1;
    }
    if (body.getPosition().y < ((RADIUS + 1) / PPM)) {
      velY *= -1;
    }
    if (body.getPosition().y
        > ((29 / 30f) * Gdx.graphics.getHeight() - RADIUS - BRICK_HEIGHT / 2) / PPM) {
      velY *= -1;
    }
    if (velXFactor != 0) {
      velX *= velXFactor;
      setVelYFactor();
    }
    velXFactor = 0;
    velYFactor = 0;
    body.setLinearVelocity(velX * speed, velY * speed);
  }

  public void setVelX(float velX) {
    this.velX *= velX;
  }

  public void setVelY(float velY) {
    this.velY *= velY;
  }

  public void render(SpriteBatch spriteBatch) {
    spriteBatch.draw(ballImage,
        x - RADIUS / (PPM),
        y - RADIUS / (PPM),
        2 * RADIUS / PPM,
        2 * RADIUS / PPM);
  }

  public float getY() {
    return body.getPosition().y;
  }

  public float getVelX() {
    return velX;
  }

  public void setVelXFactor(float velXFactor) {
    this.velXFactor = velXFactor;
  }

  public void setVelYFactor() {
    if (velX > 0) {
      velY = 2 - velX;
    } else if (velX < 0) {
      velY = Math.abs(-velX - 2);
    }
  }
}
