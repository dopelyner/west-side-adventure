package org.academiadecodigo.gitbusters.programazores.enimies;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Rectangle;
import org.academiadecodigo.gitbusters.programazores.Constants;

public class Octopussy {

    private Texture octopussyImage;
    private Rectangle octopussy;


    public Octopussy() {
        octopussy = new Rectangle();
        octopussy.x = 700;
        octopussy.y = 120;
        octopussy.width = 50;
        octopussy.height = 50;
    }

    public void octopussyMovement() {
        octopussy.y -= Constants.OCTOPUSSY_SPEED * Gdx.graphics.getDeltaTime();
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
