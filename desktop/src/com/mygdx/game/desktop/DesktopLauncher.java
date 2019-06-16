package com.mygdx.game.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;

import planet.Strive.Actor.MainGame;

public class DesktopLauncher {
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		
		config.title = "Planet Strive";
		config.width = 15360 /*3840*/;
		config.height = 1200;
		config.fullscreen = true;
		
		new LwjglApplication(new MainGame(), config);
	}
}
