package org.academiadecodigo.gitbusters.programazores;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.TimeUtils;
import org.academiadecodigo.gitbusters.programazores.boats.Boat;
import org.academiadecodigo.gitbusters.programazores.boats.PirateBoat;
import org.academiadecodigo.gitbusters.programazores.enimies.Octopussy;
import org.academiadecodigo.gitbusters.programazores.land.Island;

import java.util.Iterator;

public class WestSideAdventure extends ApplicationAdapter {

    enum Screen{
        TITLE, MAIN_GAME, GAME_OVER;
    }

    Screen currentScreen = Screen.MAIN_GAME;

    private SpriteBatch batch;

    private Boat boat;
    private Island puertoRico;

    private int health;

    private OrthographicCamera camera;

    private Array<Octopussy> octopussyArray;
    private long lastOctopussy;

    private Array<PirateBoat> pirateBoatArray;
    private long lastPirateBoat;

    private Music backgroundMusic;
    private int progress;
    private ShapeRenderer shapeRenderer;


    @Override
    public void create() {

        batch = new SpriteBatch();
        puertoRico = new Island();
        puertoRico.setIslandImage(new Texture("puerto-rico.png"));
        boat = new Boat();
        boat.setBoatImage(new Texture("boat_green.png"));

        health = 3;

        camera = new OrthographicCamera(Constants.WORLD_WIDTH, Constants.WORLD_HEIGHT);
        //Viewport viewport = new ExtendViewport(Constants.WORLD_WIDTH, Constants.WORLD_HEIGHT, camera);

        octopussyArray = new Array<>();
        pirateBoatArray = new Array<>();

        progress = 1320;

        //backgroundMusic = Gdx.audio.newMusic(Gdx.files.internal("BrosdasCaraibas_8bit.mp3"));
        //backgroundMusic = Gdx.audio.newMusic(Gdx.files.internal("Bros_Das_Caraibas.mp3"));

    }


    @Override
    public void render() {

        if(currentScreen == Screen.MAIN_GAME) {
            Gdx.gl.glClearColor(0, 109/255.0f, 190/255.0f, 1);
            Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

            batch.setProjectionMatrix(camera.combined);
            camera.update(); //update our camera every frame
            batch.setProjectionMatrix(camera.combined);
            boat.boatMovement();

            batch.begin();


            camera.position.set(boat.getBoat().getX() + progress, boat.getBoat().getY(), 0);
            camera.zoom = 1;
            camera.update();
            batch.setProjectionMatrix(camera.combined);

            drawImages();
            //backgroundMusic.isLooping();
            //backgroundMusic.play();

            batch.end();

            if (TimeUtils.nanoTime() - lastOctopussy > 2000000000L) {
                spawnOctopussies();
            }

            if (TimeUtils.nanoTime() - lastPirateBoat > 4000000000L) {
                spawnPirateBoats();
            }

            octopussyCollisionsHandler();
            piratesCollisionsHandler();

            switch (health) {
                case 0:
                    System.out.println("GAME OVER BROTHER....");
                    currentScreen = Screen.TITLE;
                    dispose();
                    create();   // future menu here
                    boat.setBoatImage(new Texture("boat_green.png"));
                    break;
                case 1:
                    boat.setBoatImage(new Texture("boat_red.png"));
                    break;
                case 2:
                    boat.setBoatImage(new Texture("boat_yellow.png"));
                    break;
            }

//        System.out.println(boat.getBoat().x);
//        System.out.println(boat.getBoat().y);

            if(progress > 0 && boat.getBoat().getX() > Constants.WORLD_WIDTH - 500) {
                progress -= 25;
            } // push final

        }

        if(currentScreen == Screen.TITLE) {
            System.out.println("Main Menu");
        }

    }

    @Override
    public void dispose() {
        batch.dispose();
        //backgroundMusic.dispose();
    }

    private void drawImages() {
        batch.draw(puertoRico.getIslandImage(), puertoRico.getIsland().x, puertoRico.getIsland().y);
        batch.draw(boat.getBoatImage(), boat.getBoat().getX(), boat.getBoat().y);

        for (Octopussy o : octopussyArray) {
            batch.draw(o.getOctopussyImage(), o.getOctopussy().x, o.getOctopussy().y);
        }

        for (PirateBoat pirateBoat : pirateBoatArray) {
            batch.draw(pirateBoat.getPirateImage(), pirateBoat.getPirateBoat().x, pirateBoat.getPirateBoat().y);
        }
    }

    private void spawnOctopussies() {
        Octopussy octo = new Octopussy();
        octo.setOctopussyImage(new Texture("octopussy.png"));

        octo.getOctopussy().x = MathUtils.random(boat.getBoat().getX() -150);
        octo.getOctopussy().y = MathUtils.random(0,boat.getBoat().getY() + 500);

        octopussyArray.add(octo);
        lastOctopussy = TimeUtils.nanoTime();
    }

    private void spawnPirateBoats() {

        PirateBoat pirateBoat = new PirateBoat();
        pirateBoat.setPirateImage(new Texture("pirateboat.png"));

        pirateBoat.getPirateBoat().x = MathUtils.random(Constants.WORLD_WIDTH);
        pirateBoat.getPirateBoat().y = MathUtils.random(2160-1000,2160);

        pirateBoatArray.add(pirateBoat);
        lastPirateBoat = TimeUtils.nanoTime();
    }

    private void octopussyCollisionsHandler() {

        for (Iterator<Octopussy> iter = octopussyArray.iterator(); iter.hasNext(); ) {
            Octopussy octopussy = iter.next();
            octopussy.octopussyMovement();

            if (octopussy.getOctopussy().y > Constants.WORLD_HEIGHT) {
                iter.remove();
            }

            if (boat.getBoat().overlaps(octopussy.getOctopussy())) {
                health--;
                iter.remove();
                System.out.println("Crashed into an octopussy");
            }
        }
    }

    private void piratesCollisionsHandler() {

        for (Iterator<PirateBoat> iter = pirateBoatArray.iterator(); iter.hasNext(); ) {
            PirateBoat pirateBoat = iter.next();
            pirateBoat.pirateMovement();

            if (pirateBoat.getPirateBoat().y > Constants.WORLD_HEIGHT) {
                iter.remove();
            }

            if (boat.getBoat().overlaps(pirateBoat.getPirateBoat())) {
                health--;
                iter.remove();
                System.out.println("Crashed into a pirate boat");
            }
        }
    }
}
