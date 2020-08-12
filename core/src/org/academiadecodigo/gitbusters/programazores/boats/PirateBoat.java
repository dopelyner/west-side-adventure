package org.academiadecodigo.gitbusters.programazores.boats;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Rectangle;
import org.academiadecodigo.gitbusters.programazores.Constants;

public class PirateBoat {

    private Texture pirateImage;
    private Rectangle pirateBoat;

    public PirateBoat() {
        pirateBoat = new Rectangle();
        pirateBoat.x = -1200;
        pirateBoat.y = -850;
        pirateBoat.width = 221;
        pirateBoat.height = 175;
    }

    public Texture getPirateImage() {
        return pirateImage;
    }

    public Rectangle getPirateBoat() {
        return pirateBoat;
    }

    public void setPirateImage(Texture pirateImage) {
        this.pirateImage = pirateImage;
    }

    public void pirateMovement() {
        pirateBoat.x -= Constants.PIRATEBOAT_SPEED * Gdx.graphics.getDeltaTime();
    }
}
