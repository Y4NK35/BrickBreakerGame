package com.brick.breaker.game.screens;

import static com.brick.breaker.game.helper.Constants.PPM;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.utils.ScreenUtils;
import com.brick.breaker.game.BrickBreakerGame;
import com.brick.breaker.game.helper.GameLevelService;
import com.brick.breaker.game.helper.MyContactListener;
import com.brick.breaker.game.objects.Ball;
import com.brick.breaker.game.objects.Bar;
import com.brick.breaker.game.objects.Lives;

public class GameScreen extends ScreenAdapter {

  private final BrickBreakerGame game;
  private SpriteBatch batch;
  private OrthographicCamera camera;
  private Box2DDebugRenderer box2DDebugRenderer;
  private World world;
  private Bar bar;
  private GameLevelService gameLevelService;
  private Ball ball;
  private Lives lives;

  public GameScreen(BrickBreakerGame game) {
    this.game = game;
    batch = game.getBatch();
    camera = game.getCamera();
    this.world = new World(new Vector2(0, 0), false);
    this.box2DDebugRenderer = new Box2DDebugRenderer(false, false, false, false, false, false);
    this.bar = new Bar(world);
    this.gameLevelService = new GameLevelService(world);
    this.ball = new Ball(world);
    this.lives = new Lives();
    world.setContactListener(new MyContactListener());
    camera.setToOrtho(false, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
  }

  @Override
  public void show() {
  }

  @Override
  public void render(float delta) {
    this.update();
    ScreenUtils.clear(0, 0, 0.2f, 1);
    camera.update();
    batch.begin();
    batch.setProjectionMatrix(camera.combined.scl(4f));
    box2DDebugRenderer.render(world, camera.combined);
    gameLevelService.render(batch);
    bar.render(batch);
    ball.render(batch);
    lives.render(batch);
    printScore();
    batch.end();
    endGame();
    if (gameLevelService.isGameFinished()) {
      winGame();
    }
  }

  private void update() {
    world.step(1 / 60f, 6, 2);
    bar.update();
    ball.update();
    gameLevelService.update(getBallY());
    if (ball.getY() <= 3) {
      lives.lostLive();
    }
    if (Gdx.input.isKeyPressed(Keys.ESCAPE)) {
      Gdx.app.exit();
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

  public float getBallY() {
    return ball.getY();
  }

  public void endGame() {
    if (lives.getLiveCounter() < 0) {
      game.setScreen(new GameOverScreen(game, gameLevelService.score));
      dispose();
    }
  }

  public void winGame() {
    game.setScreen(new WinGameScreen(game, gameLevelService.score));
    dispose();
  }

  public void printScore() {
    game.getFont().draw(
        game.getBatch(),
        "SCORE: " + gameLevelService.getScore(),
        ((Gdx.graphics.getWidth() * 0.05f) / PPM),
        ((Gdx.graphics.getHeight() - 15) / PPM));
  }
}
