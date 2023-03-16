package com.brick.breaker.game;

import static com.brick.breaker.game.helper.Constants.DESKTOP_SCREEN_HEIGHT;
import static com.brick.breaker.game.helper.Constants.DESKTOP_SCREEN_WIDTH;

import com.badlogic.gdx.backends.lwjgl3.Lwjgl3Application;
import com.badlogic.gdx.backends.lwjgl3.Lwjgl3ApplicationConfiguration;
import com.brick.breaker.game.BrickBreakerGame;

// Please note that on macOS your application needs to be started with the -XstartOnFirstThread JVM argument
public class DesktopLauncher {
	public static void main (String[] arg) {
		Lwjgl3ApplicationConfiguration config = new Lwjgl3ApplicationConfiguration();
		config.setIdleFPS(60);
		config.useVsync(true);
		config.setTitle("BrickBreakerGame by Y4NK35");
		config.setWindowedMode(DESKTOP_SCREEN_WIDTH, DESKTOP_SCREEN_HEIGHT);
		new Lwjgl3Application(new BrickBreakerGame(), config);
	}
}
