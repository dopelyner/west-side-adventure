package org.academiadecodigo.gitbusters.programazores.land;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Rectangle;
import org.academiadecodigo.gitbusters.programazores.Constants;

public class Island {

    private Texture islandImage;
    private Rectangle island;

    public Island(){
        island = new Rectangle();
        island.x = -Constants.WORLD_WIDTH;
        island.y = 1175;
    }

    public Rectangle getIsland() {
        return island;
    }

    public Texture getIslandImage() {
        return islandImage;
    }

    public void setIslandImage(Texture islandImage) {
        this.islandImage = islandImage;
    }

}
