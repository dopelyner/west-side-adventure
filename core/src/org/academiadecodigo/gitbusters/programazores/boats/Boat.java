package org.academiadecodigo.gitbusters.programazores.boats;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Rectangle;
import org.academiadecodigo.gitbusters.programazores.Constants;


public class Boat {

    private Texture boatImage;
    private Rectangle boat;

    public Boat() {
        boat = new Rectangle();
        boat.x = -1200;
        boat.y = -850;
        boat.width = 221;
        boat.height = 175;
    }

    public void boatMovement() {
        if (Gdx.input.isKeyPressed(Input.Keys.LEFT)) {
            boat.x -= Constants.BOAT_SPEED * Gdx.graphics.getDeltaTime();
        }

        if (Gdx.input.isKeyPressed(Input.Keys.RIGHT)) {
            boat.x += Constants.BOAT_SPEED * Gdx.graphics.getDeltaTime();
        }

        if (Gdx.input.isKeyPressed(Input.Keys.UP)) {
            boat.y += Constants.BOAT_SPEED * Gdx.graphics.getDeltaTime();
        }

        if (Gdx.input.isKeyPressed(Input.Keys.DOWN)) {
            boat.y -= Constants.BOAT_SPEED * Gdx.graphics.getDeltaTime();
        }

        if (boat.x < -Constants.WORLD_WIDTH) {
            boat.x = -Constants.WORLD_WIDTH;
        }

        if (boat.y > Constants.WORLD_HEIGHT - boat.height) {
            boat.y = Constants.WORLD_HEIGHT - boat.height;
        }

        if (boat.y < -Constants.WORLD_WIDTH) {
            boat.y = -Constants.WORLD_WIDTH;
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
