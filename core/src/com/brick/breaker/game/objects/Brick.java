package com.brick.breaker.game.objects;

import static com.brick.breaker.game.helper.Constants.BRICK_HEIGHT;
import static com.brick.breaker.game.helper.Constants.BRICK_WIDTH;
import static com.brick.breaker.game.helper.Constants.PPM;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.physics.box2d.Body;

public class Brick {

  private final int brickType;
  private Body body;
  private Texture brick00Image;
  private Texture brick01Image;
  private boolean isDestroyed;

  public Brick(Body body, int brickType) {
    isDestroyed = false;
    this.brickType = brickType;
    this.body = body;
    this.brick00Image = new Texture(Gdx.files.internal("BrickWhite.png"));
    this.brick01Image = new Texture(Gdx.files.internal("BrickGreen.png"));
  }

  public void render(SpriteBatch spriteBatch) {
    if (brickType == 0) {
      spriteBatch.draw(brick00Image,
          body.getPosition().x - BRICK_WIDTH / (PPM * 2),
          body.getPosition().y - BRICK_HEIGHT / (PPM * 2),
          BRICK_WIDTH / PPM,
          BRICK_HEIGHT / PPM);
    } else if (brickType == 1) {
      spriteBatch.draw(brick01Image,
          body.getPosition().x - BRICK_WIDTH / (PPM * 2),
          body.getPosition().y - BRICK_HEIGHT / (PPM * 2),
          BRICK_WIDTH / PPM,
          BRICK_HEIGHT / PPM);
    }
    body.setUserData(this);
  }

  public Body getBody() {
    return body;
  }

  public boolean isDestroyed() {
    return isDestroyed;
  }

  public void setDestroyed(boolean destroyed) {
    isDestroyed = destroyed;
  }
}
