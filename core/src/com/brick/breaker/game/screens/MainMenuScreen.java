package com.brick.breaker.game.screens;

import static com.brick.breaker.game.helper.Constants.PPM;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.utils.ScreenUtils;
import com.brick.breaker.game.BrickBreakerGame;

public class MainMenuScreen extends ScreenAdapter {

  private final BrickBreakerGame game;

  public MainMenuScreen(BrickBreakerGame game) {
    this.game = game;
    game.getCamera()
        .setToOrtho(false, Gdx.graphics.getWidth() / PPM, Gdx.graphics.getHeight() / PPM);
  }

  @Override
  public void show() {
  }

  @Override
  public void render(float delta) {
    ScreenUtils.clear(0.1f, 0.37f, 0.4f, 1);
    game.getBatch().begin();
    game.getBatch().setProjectionMatrix(game.getCamera().combined);
    game.getFont().draw(
        game.getBatch(),
        "Welcome to Brick Breaker Game",
        Gdx.graphics.getWidth() * 0.35f / PPM,
        Gdx.graphics.getHeight() * 0.55f / PPM);
    game.getFont().draw(
        game.getBatch(),
        "Press spacebar to start",
        Gdx.graphics.getWidth() * 0.35f / PPM,
        Gdx.graphics.getHeight() * 0.45f / PPM);
    game.getBatch().end();
    if (Gdx.input.isKeyJustPressed(Keys.SPACE)) {
      game.setScreen(new GameScreen(game));
      dispose();
    }
  }

  @Override
  public void resize(int width, int height) {
  }

  @Override
  public void pause() {
  }

  @Override
  public void resume() {
  }

  @Override
  public void hide() {
  }

  @Override
  public void dispose() {
  }
}

