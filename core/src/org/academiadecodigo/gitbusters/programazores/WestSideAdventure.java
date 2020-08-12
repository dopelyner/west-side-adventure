package org.academiadecodigo.gitbusters.programazores;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import org.academiadecodigo.gitbusters.programazores.boats.Boat;
import org.academiadecodigo.gitbusters.programazores.enimies.Octopussy;
import org.academiadecodigo.gitbusters.programazores.land.Island;

public class WestSideAdventure extends ApplicationAdapter {

    private SpriteBatch batch;

    private Boat boat;
    private Island island;

    private Octopussy octopussy;

    private int health;
    private String healthText;
    private BitmapFont font;

    private OrthographicCamera camera;

    @Override
    public void create() {

        batch = new SpriteBatch();

        boat = new Boat();
        boat.setBoatImage(new Texture("boat_green.png"));

        octopussy = new Octopussy();
        octopussy.setOctopussyImage(new Texture("octopussy.png"));

        island = new Island();
        island.setDepartureIslandImage(new Texture("island.png"));

        health = 200;
        healthText = "Health: 3";
        font = new BitmapFont();

        // WORLD_WIDTH = 3600
        // WORLD_HEIGTH = 2160
        camera = new OrthographicCamera(Constants.WORLD_WIDTH, Constants.WORLD_HEIGHT);
        //Viewport viewport = new ExtendViewport(Constants.WORLD_WIDTH, Constants.WORLD_HEIGHT, camera);

    }

    @Override
    public void render() {
        Gdx.gl.glClearColor(0, 0, 255, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        camera.update(); //update our camera every frame
        batch.setProjectionMatrix(camera.combined);
        boat.boatMovement();

        batch.begin();

        camera.position.set(boat.getBoat().getX(), boat.getBoat().getY(), 0);
        drawImages();
        viewHealth();

        batch.end();

        octopussy.octopussyMovement();

        if (boat.getBoat().overlaps(octopussy.getOctopussy())) {
            health--;
            healthText = "Health: " + health;
            dispose();
            create();   // test
            System.out.println("Crashedddddd");
        }

        switch (health) {
            case 0:
                System.out.println("GAME OVER BROTHER....");
               dispose();
                create();
            case 50:
                boat.setBoatImage(new Texture("boat_yellow.png"));
            case 25:
                boat.setBoatImage(new Texture("boat_red.png"));
        }

    }

    @Override
    public void dispose() {
        batch.dispose();
    }

    public void drawImages() {
        batch.draw(boat.getBoatImage(), boat.getBoat().getX(), boat.getBoat().y);
        batch.draw(island.getDepartureIslandImage(), island.getDepartureIsland().x, island.getDepartureIsland().y);
        batch.draw(octopussy.getOctopussyImage(), octopussy.getOctopussy().x, octopussy.getOctopussy().y);
    }

    public void viewHealth() {
        font.setColor(1.0f, 1.0f, 1.0f, 1.0f);
        font.draw(batch, healthText, 20, 700);
        font.getData().setScale(5f, 5f);

    }

}
