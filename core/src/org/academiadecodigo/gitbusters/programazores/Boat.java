package org.academiadecodigo.gitbusters.programazores;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Rectangle;


public class Boat {

    private Texture boatImage;
    private Rectangle boat;

    public Boat() {
        boat = new Rectangle();
        boat.x = 500;
        boat.y = 50;
        boat.width = 50;
        boat.height = 50;

    }

    public void boatMovement() {
        if (Gdx.input.isKeyPressed(Input.Keys.LEFT)) {
            boat.x -= 500 * Gdx.graphics.getDeltaTime();
        }

        if (Gdx.input.isKeyPressed(Input.Keys.RIGHT)) {
            boat.x += 500 * Gdx.graphics.getDeltaTime();
        }

        if (Gdx.input.isKeyPressed(Input.Keys.UP)) {
            boat.y += 500 * Gdx.graphics.getDeltaTime();
        }
        if (Gdx.input.isKeyPressed(Input.Keys.DOWN)) {
            boat.y -= 500 * Gdx.graphics.getDeltaTime();
        }

        if (boat.x < 0) {
            boat.x = 0;
        }
        if (boat.x > 1200 - 2 * boat.getWidth()) {
            boat.x = 1200 - 2 * boat.getWidth();
        }

    }

    public Rectangle getBoat() {
        return boat;
    }

    public Texture getBoatImage() {
        return boatImage;
    }

    public void setBoatImage(Texture boatImage) {
        this.boatImage = boatImage;
    }
}
