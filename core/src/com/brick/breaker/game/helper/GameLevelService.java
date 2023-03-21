package com.brick.breaker.game.helper;

import static com.brick.breaker.game.helper.Constants.BRICK_COLUMNS;
import static com.brick.breaker.game.helper.Constants.BRICK_HEIGHT;
import static com.brick.breaker.game.helper.Constants.BRICK_ROWS;
import static com.brick.breaker.game.helper.Constants.BRICK_WIDTH;
import static com.brick.breaker.game.helper.Constants.PPM;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.utils.Array;
import com.brick.breaker.game.objects.Brick;
import java.util.List;
import java.util.Optional;

public class GameLevelService {

  private Levels levelsMaps;
  private List<int[][]> levels;
  private boolean gameFinished;
  private int[][] currentLevel;
  private World world;
  private Array<Brick> brickList;
  public int score;


  public GameLevelService(World world) {
    levelsMaps = new Levels();
    levels = levelsMaps.getLevels();
    gameFinished = false;
    brickList = new Array<>();
    this.world = world;
    createLevel();
    score = 0;
  }

  public void createLevel() {
    setCurrentLevel();
    for (int i = 0; i < BRICK_ROWS; i++) {
      for (int j = 0; j < BRICK_COLUMNS; j++) {
        Body tempBody = BodyHelperService.createBody(
            (j * BRICK_WIDTH + 0.5f * BRICK_WIDTH),
            (float) Gdx.graphics.getHeight() - 30f - (BRICK_HEIGHT * (1f + i)),
            BRICK_WIDTH-4,
            BRICK_HEIGHT-4,
            true,
            world);
        Brick tempBrick = new Brick(tempBody, currentLevel[i][j]);
        brickList.add(tempBrick);
      }
    }
  }

  public void update(float ballY) {
    removeDestroyedBricks();
    if (brickList.isEmpty() && ballY < Gdx.graphics.getHeight() / (2 * PPM)) {
      levels.remove(currentLevel);
      setCurrentLevel();
      createLevel();
    }
  }

  public void setCurrentLevel() {
    if (!setGameFinished()) {
      Optional<int[][]> levelOptional = levels.stream().findFirst();
      currentLevel = levelOptional.get();
    }
  }

  private void removeDestroyedBricks() {
    Array<Brick> tempList = brickList;
    Brick brickToRemove = null;
    for (Brick brick : tempList) {
      if (brick.isDestroyed()) {
        brickToRemove = brick;
      }
      if (brickToRemove != null) {
        brickList.removeValue(brickToRemove, true);
        world.destroyBody(brickToRemove.getBody());
        incraseScore();
      }
      brickToRemove = null;
    }
  }

  private boolean setGameFinished() {
    if (levels.isEmpty() && brickList.isEmpty()) {
      gameFinished = true;
    }
    return gameFinished;
  }

  public void render(SpriteBatch batch) {
    for (Brick brick : brickList) {
      brick.render(batch);
    }
  }

  public void incraseScore() {
    this.score += 10;
  }

  public int getScore() {
    return score;
  }

  public boolean isGameFinished() {
    return gameFinished;
  }
}
