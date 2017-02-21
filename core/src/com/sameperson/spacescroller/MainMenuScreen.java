package com.sameperson.spacescroller;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Cursor;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Pixmap;

public class MainMenuScreen implements Screen {

    public final App game;
    public OrthographicCamera camera;

    public MainMenuScreen(final App game) {
        this.game = game;
        this.camera = new OrthographicCamera();
        camera.setToOrtho(false, 600, 800);

        Gdx.graphics.setContinuousRendering(false);
        Gdx.graphics.requestRendering();
    }

    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        camera.update();
        game.batch.setProjectionMatrix(camera.combined);
        game.batch.begin();
        game.font.draw(game.batch, "Welcome to the Space Scroller!", 300, 270);
        game.font.draw(game.batch, "Tap anywhere to begin", 300, 250);
        game.batch.end();
        if(Gdx.input.isTouched()) {
            Cursor customCursor = Gdx.graphics.newCursor(new Pixmap(Gdx.files.internal("blank.png")), 0, 0);
            Gdx.graphics.setCursor(customCursor);

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
