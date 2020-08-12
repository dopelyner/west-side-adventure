package org.academiadecodigo.gitbusters.programazores;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Rectangle;

public class Octopussy {

    private Texture octopussyImage;
    private Rectangle octopussy;
    private final int speed = 300;

    public Octopussy() {
        octopussy = new Rectangle();
        octopussy.x = 500;
        octopussy.y = 50;
        octopussy.width = 50;
        octopussy.height = 50;
    }

    public void octopussyMovement() {

            octopussy.y += speed * Gdx.graphics.getDeltaTime();

            if (octopussy.y < 0) {
                octopussy.y += speed * Gdx.graphics.getDeltaTime();
            }

            if (octopussy.y > 720 - 2 * octopussy.getWidth()) {
                octopussy.y -= speed * Gdx.graphics.getDeltaTime();
            }


    }

    public Rectangle getOctopussy() {
        return octopussy;
    }

    public Texture getOctopussyImage() {
        return octopussyImage;
    }

    public void setOctopussyImage(Texture octopussyImage) {
        this.octopussyImage = octopussyImage;
    }
}
