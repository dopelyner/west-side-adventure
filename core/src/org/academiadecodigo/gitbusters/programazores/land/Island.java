package org.academiadecodigo.gitbusters.programazores.land;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Rectangle;

public class Island {

    private Texture islandImage;
    private Rectangle island;

    public Island(int x, int y){
        island = new Rectangle();
        island.x = x;
        island.y = y;
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
