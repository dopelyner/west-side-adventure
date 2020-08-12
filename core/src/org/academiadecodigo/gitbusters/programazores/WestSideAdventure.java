package org.academiadecodigo.gitbusters.programazores;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class WestSideAdventure extends ApplicationAdapter {

    private SpriteBatch batch;

    private Boat boat;
    private Island island;

    private Octopussy octopussy;

    private int health;
    private String healthText;
    private BitmapFont font;

    @Override
    public void create() {

        batch = new SpriteBatch();

        boat = new Boat();
        boat.setBoatImage(new Texture("sailboat.png"));

        island = new Island();
        island.setDepartureIslandImage(new Texture("island.png"));

        octopussy = new Octopussy();
        octopussy.setOctopussyImage(new Texture("octopussy.png"));

        health = 3;
        healthText = "Health: 3";
        font = new BitmapFont();
    }

    @Override
    public void render() {
        Gdx.gl.glClearColor(0, 0, 255, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        batch.begin();

        batch.draw(boat.getBoatImage(), boat.getBoat().getX(), boat.getBoat().y);
        batch.draw(island.getDepartureIslandImage(), island.getDepartureIsland().x, island.getDepartureIsland().y);
        batch.draw(octopussy.getOctopussyImage(), octopussy.getOctopussy().x, octopussy.getOctopussy().y);

        font.setColor(1.0f, 1.0f, 1.0f, 1.0f);
        font.draw(batch, healthText, 20, 700);
        font.getData().setScale(1.20f, 1.20f);

        batch.end();

        boat.boatMovement();
        octopussy.octopussyMovement();

        if (health == 0) {
            System.out.println("GAME OVER BROTHER....");
        }

        if (boat.getBoat().overlaps(octopussy.getOctopussy())) {
            health--;
            healthText = "Health: " + health;
            System.out.println("Crashedddddd");
        }
        
    }

    @Override
    public void dispose() {
        batch.dispose();
    }


}
