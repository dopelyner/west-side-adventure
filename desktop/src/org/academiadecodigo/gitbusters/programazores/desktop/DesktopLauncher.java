package org.academiadecodigo.gitbusters.programazores.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import org.academiadecodigo.gitbusters.programazores.WestSideAdventure;

public class DesktopLauncher {

	public static final int WIDTH = 1200;
	public static final int HEIGHT = 720;

	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		config.width = WIDTH;
		config.height = HEIGHT;
		config.title = "West Side Adventure";
		new LwjglApplication(new WestSideAdventure(), config);
	}
}
