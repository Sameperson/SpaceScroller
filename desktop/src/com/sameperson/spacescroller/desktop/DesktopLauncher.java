package com.sameperson.spacescroller.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.sameperson.spacescroller.App;

public class DesktopLauncher {
    public static void main(String[] arg) {
        LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
        config.title = "Scroller";
        config.width = 800;
        config.height = 480;
        new LwjglApplication(new App(), config);
    }
}
