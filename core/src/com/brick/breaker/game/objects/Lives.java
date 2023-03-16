package com.brick.breaker.game.objects;

import static com.brick.breaker.game.helper.Constants.PPM;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class Lives {

  private int liveCounter;
  private Texture liveImage;
  private final float INITIAL_X = Gdx.graphics.getWidth() - 150;
  private final float INITIAL_Y = Gdx.graphics.getHeight() - 30;
  private final float INITIAL_WIDTH = 20;
  private final float INITIAL_HEIGHT = 20;

  public Lives() {
    this.liveCounter = 3;
    liveImage = new Texture(Gdx.files.internal("Live.png"));
  }

  public void render(SpriteBatch batch) {
    for (int i = 0; i < liveCounter; i++) {
      batch.draw(liveImage,
          (INITIAL_X + i * 32) / PPM,
          (INITIAL_Y) / PPM,
          INITIAL_WIDTH / PPM,
          INITIAL_HEIGHT / PPM);
    }
  }

  public void lostLive() {
    liveCounter--;
  }

  public int getLiveCounter() {
    return liveCounter;
  }
}

