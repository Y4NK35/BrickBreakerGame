package com.brick.breaker.game.objects;

import static com.brick.breaker.game.helper.Constants.BAR_HEIGHT;
import static com.brick.breaker.game.helper.Constants.BAR_WIDTH;
import static com.brick.breaker.game.helper.Constants.PPM;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.physics.box2d.World;
import com.brick.breaker.game.helper.BodyHelperService;

public class Bar extends GameEntity {

  private final float BAR_INITIAL_X = Gdx.graphics.getWidth() / (2);
  private final float BAR_INITIAL_Y = 15f;
  private Texture barImage;
  int counter = 0;
  float previousVel = 0;
  float accelerationFactor = 0;

  public Bar(World world) {
    super(world);
    this.speed = 100f;
    this.body = BodyHelperService.createBody(
        BAR_INITIAL_X,
        BAR_INITIAL_Y,
        BAR_WIDTH,
        BAR_HEIGHT,
        false,
        world);
    this.barImage = new Texture(Gdx.files.internal("Bar.png"));
    body.setUserData(this);
  }

  @Override
  public void update() {
    x = body.getPosition().x;
    y = body.getPosition().y;
    checkUserInput();
    counter = 0;
    previousVel = body.getLinearVelocity().x;
  }

  private void checkUserInput() {
    velX = 0;
    if (Gdx.input.isKeyPressed(Keys.D)) {
      velX = 1f;
      if (body.getPosition().x > (Gdx.graphics.getWidth() - (BAR_WIDTH / 2) - 8) / (PPM)) {
        velX = 0;
      }
    }
    if (Gdx.input.isKeyPressed(Keys.A)) {
      velX = -1f;
      if (body.getPosition().x < (BAR_WIDTH + 16) / (2 * PPM)) {
        velX = 0;
      }
    }
    body.setLinearVelocity(velX * speed * (1 + Math.abs(accelerationFactor) / 200), 0);
    calculateAccelerationFactor(velX);
  }

  @Override
  public void render(SpriteBatch spriteBatch) {
    spriteBatch.draw(barImage,
        x - BAR_WIDTH / (PPM * 2),
        y - BAR_HEIGHT / (PPM * 2),
        BAR_WIDTH / PPM,
        BAR_HEIGHT / PPM);
  }

  public void calculateAccelerationFactor(float velX) {
    if (velX == 0) {
      accelerationFactor = 0;
    } else if (previousVel < 0 && velX < 0) {
      accelerationFactor--;
    } else if (previousVel > 0 && velX > 0) {
      accelerationFactor++;
    }
  }

  public float getVelX() {
    return velX;
  }
}
