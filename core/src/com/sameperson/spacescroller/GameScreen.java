package com.sameperson.spacescroller;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.*;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.TimeUtils;

import java.util.Iterator;

public class GameScreen implements Screen {

    private App game;
    private Texture shipImage;
    private Texture projectileImage;
    private OrthographicCamera camera;
    private Rectangle ship;
    private Array<Rectangle> projectiles;
    private Vector3 pointer;
    private long lastShotTime;

    public GameScreen(App game) {

        this.game = game;
        Gdx.graphics.setContinuousRendering(true);
        shipImage = new Texture(Gdx.files.internal("ship.png"));
        projectileImage = new Texture(Gdx.files.internal("projectile.png"));
        projectiles = new Array<Rectangle>();

        camera = new OrthographicCamera();
        camera.setToOrtho(false, 800, 480);

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
        for(Rectangle projectile : projectiles) {
            game.batch.draw(projectileImage, projectile.x, projectile.y);
        }
        game.batch.end();

        pointer.set(Gdx.input.getX(), Gdx.input.getY(), 0);
        camera.unproject(pointer);
        if(pointer.x > ship.width / 2 && pointer.x < Gdx.graphics.getWidth() - ship.width / 2) {
            ship.x = pointer.x - ship.width / 2;
        }
        ship.y = pointer.y - ship.height / 2;
//        if(pointer.y > ship.height / 2 && pointer.y < 400 - ship.height / 2) {
//            ship.y = pointer.y - ship.height / 2;
//        }

        if (TimeUtils.nanoTime() - lastShotTime > 300000000)
            spawnProjectile();

        Iterator<Rectangle> iterator = projectiles.iterator();
        while (iterator.hasNext()) {
            Rectangle projectile = iterator.next();
            projectile.y += 500 * Gdx.graphics.getDeltaTime();
            if (projectile.y > 800)
                iterator.remove();
        }

    }

    private void spawnProjectile() {
        Rectangle projectile = new Rectangle();
        projectile.x = ship.x + 15;
        projectile.y = ship.y + 40;
        projectile.width = 6;
        projectile.height = 6;
        projectiles.add(projectile);
        lastShotTime = TimeUtils.nanoTime();
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
