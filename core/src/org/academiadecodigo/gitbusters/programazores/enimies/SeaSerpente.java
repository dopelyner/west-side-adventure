package org.academiadecodigo.gitbusters.programazores.enimies;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Rectangle;
import org.academiadecodigo.gitbusters.programazores.Constants;

public class SeaSerpente {
    private Texture seaSerpenteImage;
    private Rectangle seaSerpente;

    public SeaSerpente() {
        seaSerpente = new Rectangle();
        seaSerpente.x = Constants.BOAT_SPAWN_X;
        seaSerpente.y = Constants.BOAT_SPAWN_Y;
        seaSerpente.width = 221;
        seaSerpente.height = 175;
    }

    public Texture getSeaSerpenteImage() {
        return seaSerpenteImage;
    }

    public Rectangle getSeaSerpente() {
        return seaSerpente;
    }

    public void setSeaSerpenteImage(Texture seaSerpenteImage) {
        this.seaSerpenteImage = seaSerpenteImage;
    }

    public void setSeaSerpenteMovement(){
        seaSerpente.x += Constants.SEA_SERPENTE_SPEED * Gdx.graphics.getDeltaTime();
    }
}
