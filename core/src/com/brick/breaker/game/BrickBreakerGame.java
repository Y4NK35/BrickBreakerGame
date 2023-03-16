package com.brick.breaker.game;

import static com.brick.breaker.game.helper.Constants.PPM;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.ScreenUtils;
import com.brick.breaker.game.screens.MainMenuScreen;
import java.awt.Font;

public class BrickBreakerGame extends Game {

  private static BrickBreakerGame INSTANCE;
  private SpriteBatch batch;
  private BitmapFont font;
  private OrthographicCamera camera;

  public BrickBreakerGame() {
    INSTANCE = this;
  }

  @Override
  public void create() {
    batch = new SpriteBatch();
    font = new BitmapFont();
    font.getData().setScale(2 / PPM);
    camera = new OrthographicCamera();
    this.setScreen(new MainMenuScreen(this));
  }

  @Override
  public void dispose() {
    batch.dispose();
    font.dispose();
  }

  public SpriteBatch getBatch() {
    return batch;
  }

  public BitmapFont getFont() {
    return font;
  }

  public OrthographicCamera getCamera() {
    return camera;
  }
}
