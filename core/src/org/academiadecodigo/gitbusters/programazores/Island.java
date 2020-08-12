package org.academiadecodigo.gitbusters.programazores;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Rectangle;

public class Island {

    private Texture departureIslandImage;
    private Rectangle departureIsland;

    public Island(){
        departureIsland = new Rectangle();
        departureIsland.x = 0;
        departureIsland.y = 0;
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
