package com.sameperson.spacescroller;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.*;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.utils.Array;

public class GameScreen implements Screen {

    private App game;
    private Texture shipImage;
    private Texture projectileImage;
    private OrthographicCamera camera;
    private Rectangle ship;
    private Array<Rectangle> projectiles;
    private Vector3 pointer;

    public GameScreen(App game) {

        this.game = game;
        Gdx.graphics.setContinuousRendering(true);
        shipImage = new Texture(Gdx.files.internal("ship.png"));
        projectileImage = new Texture(Gdx.files.internal("projectile.png"));

        camera = new OrthographicCamera();
        camera.setToOrtho(false, 600, 800);

        ship = new Rectangle();
        ship.height = 40;
        ship.width = 22; // 30 (width of image) - 8 (total width of wings)
        ship.x = Gdx.graphics.getWidth() / 2 - ship.width / 2;
        ship.y = 20;

        pointer = new Vector3();
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
        game.batch.draw(shipImage, ship.x, ship.y, ship.width, ship.height);
        game.batch.end();

        pointer.set(Gdx.input.getX(), Gdx.input.getY(), 0);
        camera.unproject(pointer);
        ship.x = pointer.x - ship.width / 2;
        ship.y = pointer.y - ship.height/ 2;




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
