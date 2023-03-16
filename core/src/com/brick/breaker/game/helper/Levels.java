package com.brick.breaker.game.helper;

import static com.brick.breaker.game.helper.Constants.BRICK_COLUMNS;
import static com.brick.breaker.game.helper.Constants.BRICK_ROWS;

import java.util.ArrayList;
import java.util.List;

public class Levels {

  private final int[][] level01Map = new int[BRICK_ROWS][BRICK_COLUMNS];
  private final int[][] level02Map = new int[BRICK_ROWS][BRICK_COLUMNS];
  private List<int[][]> levels = new ArrayList<>();

  public Levels() {
    createLevel01Map();
    createLevel02Map();
    levels.add(level01Map);
    levels.add(level02Map);
  }

  private void createLevel01Map() {
    for (int i = 0; i < BRICK_ROWS; i++) {
      for (int j = 0; j < BRICK_COLUMNS; j++) {
        if (i % 2 == 0) {
          level01Map[i][j] = 0;
        } else {
          level01Map[i][j] = 1;
        }
      }
    }
  }

  private void createLevel02Map() {
    for (int i = 0; i < BRICK_ROWS; i++) {
      for (int j = 0; j < BRICK_COLUMNS; j++) {
        if ((i + j) % 2 == 0) {
          level02Map[i][j] = 0;
        } else {
          level02Map[i][j] = 1;
        }
      }
    }
  }

  public List<int[][]> getLevels() {
    return levels;
  }
}
