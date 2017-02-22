package com.sameperson.spacescroller;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Vector3;

public class Ship {

    Vector3 position;
    int width = 30;
    int height = 40;
    int x, y;

    public Ship() {
        position = new Vector3();
    }

    public void update(float deltaTime) {

    }

    private void processInput() {
        position.set(Gdx.input.getX(), Gdx.input.getY(), 0);
        if(position.x > width / 2 && position.x < Gdx.graphics.getWidth() - width / 2) {
            x = (int)position.x - width / 2;
        }
        y = (int)position.y - height / 2;
    }

}
