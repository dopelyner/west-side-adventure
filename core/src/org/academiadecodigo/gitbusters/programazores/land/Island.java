package org.academiadecodigo.gitbusters.programazores.land;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Rectangle;

public class Island {

    private Texture departureIslandImage;
    private Rectangle departureIsland;

    public Island(){
        departureIsland = new Rectangle();
        departureIsland.x = -1500;
        departureIsland.y = -1000;
    }

    public Rectangle getDepartureIsland() {
        return departureIsland;
    }

    public Texture getDepartureIslandImage() {
        return departureIslandImage;
    }

    public void setDepartureIslandImage(Texture departureIslandImage) {
        this.departureIslandImage = departureIslandImage;
    }
}
