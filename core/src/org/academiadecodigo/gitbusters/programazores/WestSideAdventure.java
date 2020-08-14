package org.academiadecodigo.gitbusters.programazores;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.*;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.TimeUtils;
import org.academiadecodigo.gitbusters.programazores.boats.Boat;
import org.academiadecodigo.gitbusters.programazores.boats.PirateBoat;
import org.academiadecodigo.gitbusters.programazores.enimies.Octopussy;
import org.academiadecodigo.gitbusters.programazores.land.Island;

import java.util.Iterator;

public class WestSideAdventure extends ApplicationAdapter {

    enum Screen {
        MAIN_MENU, GAME, GAME_OVER, LORE, CREDITS, MISSION_COMPLETE, MISSION_2, INSTRUCTIONS
    }

    private Stage mission2;
    private Stage stage;
    private Stage stageMenu;
    private Skin skinMenu;
    private Stage stageOver;
    private Skin skinOver;
    private Stage stageLore;
    private Stage stageIns;

    Screen currentScreen = Screen.MAIN_MENU;

    private SpriteBatch batch;
    private Boat boat;
    private Island puertoRico;
    private Island america;
    private int health;
    private OrthographicCamera camera;
    private Array<Octopussy> octopussyArray;
    private long lastOctopussy;
    private Array<PirateBoat> pirateBoatArray;
    private long lastPirateBoat;
    private Array<Wave> waveArray;
    private long lastWave;
    private Music backgroundMusic;
    private int progress;
    private ShapeRenderer shapeRenderer;
    private SandboxGame sandboxGame;

    @Override
    public void create() {
        batch = new SpriteBatch();

        puertoRico = new Island(Constants.PUERTO_RICO_SPAWN_X, Constants.PUERTO_RICO_SPAWN_Y);
        puertoRico.setIslandImage(new Texture("puerto-rico.png"));

        boat = new Boat();

        america = new Island(Constants.AMERICA_SPAWN_X, Constants.AMERICA_SPAWN_Y);
        america.setIslandImage(new Texture("america.png"));
        america.getIsland().setWidth(1664);
        america.getIsland().setHeight(1664);

        health = 3;

        camera = new OrthographicCamera(Constants.WORLD_WIDTH, Constants.WORLD_HEIGHT);
        //Viewport viewport = new ExtendViewport(Constants.WORLD_WIDTH, Constants.WORLD_HEIGHT, camera);

        octopussyArray = new Array<>();
        pirateBoatArray = new Array<>();
        waveArray = new Array<>();

        progress = 1320;

        backgroundMusic = Gdx.audio.newMusic(Gdx.files.internal("BrosdasCaraibas_8bit.mp3"));
        //backgroundMusic = Gdx.audio.newMusic(Gdx.files.internal("Bros_Das_Caraibas.mp3"));


        if (currentScreen == Screen.MAIN_MENU) {
            boat.setBoatImage(new Texture("yatch-green.png"));
            createMenu();
        }

        if (currentScreen == Screen.GAME_OVER) {
            createGameOver();
        }

        if (currentScreen == Screen.MISSION_COMPLETE) {
            completeMission();
        }

        if (currentScreen == Screen.LORE) {
            createLore();
        }

        if (currentScreen == Screen.CREDITS) {
            createCredits();
        }
        if (currentScreen == Screen.MISSION_2) {
            mission2();
        }
        sandboxGame = new SandboxGame();

        if(currentScreen == Screen.INSTRUCTIONS) {
            createInstructions();
        }

    }


    @Override
    public void render() {

        if (currentScreen == Screen.GAME) {
            Gdx.gl.glClearColor(0, 109 / 255.0f, 190 / 255.0f, 1);
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

            if (TimeUtils.nanoTime() - lastOctopussy > 300000000L) {
                spawnOctopussies();
            }

            if (TimeUtils.nanoTime() - lastPirateBoat > 4000000000L) {
                spawnPirateBoats();
            }
            if (TimeUtils.nanoTime() - lastWave > 1000000) {
                spawnWaves();
            }

            octopussyCollisionsHandler();
            piratesCollisionsHandler();
            deleteWaves();

            System.out.println(boat.getBoatImage());
            /*
            switch (health) {
                case 0:
                    System.out.println("GAME OVER BROTHER....");
                    currentScreen = Screen.GAME_OVER;
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
            }*/

            switch (boat.getBoatImage() + "") {
                case "raft-green.png":
                    if (health == 2) {
                        boat.setBoatImage(new Texture("raft-yellow.png"));
                    }
                    break;
                case "raft-yellow.png":
                    if (health == 1) {
                        boat.setBoatImage(new Texture("raft-red.png"));
                    }
                    break;
                case "raft-red.png":
                    System.out.println("GAME OVER BROTHER....");
                    currentScreen = Screen.GAME_OVER;
                    dispose();
                    create();   // future menu here
                    boat.setBoatImage(new Texture("raft-green.png"));
                    break;

                case "boat_green.png":
                    if (health == 2) {
                        boat.setBoatImage(new Texture("boat_yellow.png"));
                    }
                    break;
                case "boat_yellow.png":
                    if (health == 1) {
                        boat.setBoatImage(new Texture("boat_red.png"));
                    }
                    break;
                case "boat_red.png":
                    System.out.println("GAME OVER BROTHER....");
                    currentScreen = Screen.GAME_OVER;
                    dispose();
                    create();   // future menu here
                    boat.setBoatImage(new Texture("raft-green.png"));
                    break;

                case "motorboat-green.png":
                    if (health == 2) {
                        boat.setBoatImage(new Texture("motorboat-yellow.png"));
                    }
                    break;
                case "motorboat-yellow.png":
                    if (health == 1) {
                        boat.setBoatImage(new Texture("motorboat-red.png"));
                    }
                    break;
                case "motorboat-red.png":
                    System.out.println("GAME OVER BROTHER....");
                    currentScreen = Screen.GAME_OVER;
                    dispose();
                    create();   // future menu here
                    boat.setBoatImage(new Texture("raft-green.png"));
                    break;

                case "yatch-green.png":
                    if (health == 2) {
                        boat.setBoatImage(new Texture("yatch-yellow.png"));
                    }
                    break;
                case "yatch-yellow.png":
                    if (health == 1) {
                        boat.setBoatImage(new Texture("yatch-red.png"));
                    }
                    break;
                case "yatch-red.png":
                    System.out.println("GAME OVER BROTHER....");
                    currentScreen = Screen.GAME_OVER;
                    dispose();
                    create();   // future menu here
                    boat.setBoatImage(new Texture("raft-green.png"));
                    break;
            }

            if (boat.getBoat().overlaps(america.getIsland())) {
                System.out.println("Mission complete");
                currentScreen = Screen.MISSION_COMPLETE;
                dispose();
                create();
            }

            if (progress > 0 && boat.getBoat().getX() > Constants.WORLD_WIDTH - 500) {
                progress -= 25;
            } // push final

        }

        if (currentScreen == Screen.MAIN_MENU) {
            Gdx.gl.glClearColor(1, 1, 1, 1);
            Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

            stageMenu.act();
            stageMenu.draw();
        }

        if (currentScreen == Screen.GAME_OVER) {
            Gdx.gl.glClearColor(1, 1, 1, 1);
            Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

            stageOver.act();
            stageOver.draw();
        }
        if (currentScreen == Screen.MISSION_COMPLETE) {
            Gdx.gl.glClearColor(1, 1, 1, 1);
            Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

            stage.act();
            stage.draw();
        }
        System.out.println(america.getIsland().getHeight());

        if (currentScreen == Screen.LORE) {
            Gdx.gl.glClearColor(1, 1, 1, 1);
            Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

            stageLore.act();
            stageLore.draw();
        }

        if (currentScreen == Screen.CREDITS) {
            Gdx.gl.glClearColor(1, 1, 1, 1);
            Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

            stageIns.act();
            stageIns.draw();
        }

        if(currentScreen == Screen.INSTRUCTIONS) {
            Gdx.gl.glClearColor(1, 1, 1, 1);
            Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

            stageIns.act();
            stageIns.draw();
        }

        if (currentScreen == Screen.MISSION_2) {
            Gdx.gl.glClearColor(1, 1, 1, 1);
            Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

            mission2.act();
            mission2.draw();
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
        batch.draw(america.getIslandImage(), america.getIsland().x, america.getIsland().y);
        //sandboxGame.create();
        //sandboxGame.render();

        for (Octopussy o : octopussyArray) {
            batch.draw(o.getOctopussyImage(), o.getOctopussy().x, o.getOctopussy().y);
        }

        for (PirateBoat pirateBoat : pirateBoatArray) {
            batch.draw(pirateBoat.getPirateImage(), pirateBoat.getPirateBoat().x, pirateBoat.getPirateBoat().y);
        }

        for (Wave wave : waveArray) {
            batch.draw(wave.getWaveImage(), wave.getWave().x, wave.getWave().y);
        }
    }

    private void spawnOctopussies() {
        Octopussy octo = new Octopussy();
        octo.setOctopussyImage(new Texture("octopussy.png"));

        octo.getOctopussy().x = MathUtils.random(boat.getBoat().getX() - 150);
        octo.getOctopussy().y = MathUtils.random(0, boat.getBoat().getY() + 500);

        octopussyArray.add(octo);
        lastOctopussy = TimeUtils.nanoTime();
    }

    private void spawnPirateBoats() {

        PirateBoat pirateBoat = new PirateBoat();
        pirateBoat.setPirateImage(new Texture("pirateboat.png"));

        pirateBoat.getPirateBoat().x = MathUtils.random(Constants.WORLD_WIDTH);
        pirateBoat.getPirateBoat().y = MathUtils.random(2160 - 1000, 2160);

        pirateBoatArray.add(pirateBoat);
        lastPirateBoat = TimeUtils.nanoTime();
    }

    private void spawnWaves() {
        Wave wave = new Wave();
        wave.setWaveImage(new Texture("waves.png"));

        wave.getWave().x = MathUtils.random(-Constants.WORLD_WIDTH - 300, Constants.WORLD_WIDTH - 70);
        wave.getWave().y = MathUtils.random(-Constants.WORLD_HEIGHT, Constants.WORLD_HEIGHT);

        waveArray.add(wave);
        lastPirateBoat = TimeUtils.nanoTime();
    }

    private void deleteWaves() {
        for (Iterator<Wave> iter = waveArray.iterator(); iter.hasNext(); ) {
            Wave wave = iter.next();
            wave.waveMovement();

            if (wave.getWave().x < -Constants.WORLD_HEIGHT + 200) {
                wave.getWaveImage().dispose();
                iter.remove();
            }

            if (boat.getBoat().overlaps(wave.getWave())) {
                iter.remove();
            }
        }
    }

    private void octopussyCollisionsHandler() {

        for (Iterator<Octopussy> iter = octopussyArray.iterator(); iter.hasNext(); ) {
            Octopussy octopussy = iter.next();

            octopussy.octopussyMovementDown();

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

    private void createMenu() {
        int buttonOffset = 20;

        stageMenu = new Stage();
        Gdx.input.setInputProcessor(stageMenu); // Make the stage consume events

        Table table = new Table();
        table.setBackground(new TextureRegionDrawable(new TextureRegion(new Texture("background-menu.png"))));
        table.setFillParent(true);
        table.setDebug(true);
        stageMenu.addActor(table);


        Drawable play = new TextureRegionDrawable(new TextureRegion(new Texture("play-button.png")));
        ImageButton playGameBtn = new ImageButton(play);
        playGameBtn.setPosition(Gdx.graphics.getWidth() / 2 - 250, Gdx.graphics.getWidth() / 8 + 20 + 100);
        playGameBtn.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                System.out.println("Play Game button clicked");
                currentScreen = Screen.GAME;
            }
        });
        stageMenu.addActor(playGameBtn);


        Drawable settings = new TextureRegionDrawable(new TextureRegion(new Texture("instructions-button.png")));
        ImageButton settingsBtn = new ImageButton(settings);
        settingsBtn.setPosition(Gdx.graphics.getWidth() / 2, Gdx.graphics.getWidth() / 8 - 3);
        settingsBtn.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                System.out.println("Instructions button clicked");
                currentScreen = Screen.INSTRUCTIONS;
                dispose();
                create();
            }
        });
        stageMenu.addActor(settingsBtn);

        Drawable lore = new TextureRegionDrawable(new TextureRegion(new Texture("lore-button.png")));
        ImageButton loreBtn = new ImageButton(lore);
        loreBtn.setPosition(Gdx.graphics.getWidth() / 2 - 500, Gdx.graphics.getWidth() / 8);
        loreBtn.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                System.out.println("Lore button clicked");
                currentScreen = Screen.LORE;
                dispose();
                create();
            }
        });
        stageMenu.addActor(loreBtn);

        Drawable shop = new TextureRegionDrawable(new TextureRegion(new Texture("creditsbtn.png")));
        ImageButton shopBtn = new ImageButton(shop);
        shopBtn.setPosition(Gdx.graphics.getWidth() / 2 - 500, Gdx.graphics.getWidth() / 8 - 120);
        shopBtn.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                currentScreen = Screen.CREDITS;
                dispose();
                create();
            }
        });
        stageMenu.addActor(shopBtn);


        Drawable exit = new TextureRegionDrawable(new TextureRegion(new Texture("exit-button.png")));
        ImageButton exitBtn = new ImageButton(exit);
        exitBtn.setPosition(Gdx.graphics.getWidth() / 2, Gdx.graphics.getWidth() / 8 - 120);
        exitBtn.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                System.out.println("Exit button clicked");
                System.exit(0);
            }
        });
        stageMenu.addActor(exitBtn);

    }

    private void createGameOver() {
        int buttonOffset = 20;

        stageOver = new Stage();
        Gdx.input.setInputProcessor(stageOver); // Make the stage consume events


        Table table = new Table();
        table.setBackground(new TextureRegionDrawable(new TextureRegion(new Texture("gameover.png"))));
        table.setFillParent(true);
        table.setDebug(true);
        stageOver.addActor(table);

        Drawable tryAgain = new TextureRegionDrawable(new TextureRegion(new Texture("tryagain-r.png")));
        ImageButton tryAgainBtn = new ImageButton(tryAgain);
        tryAgainBtn.setPosition(Gdx.graphics.getWidth() / 2 - 580, Gdx.graphics.getWidth() / 8 - 120);
        tryAgainBtn.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                System.out.println("Play Game button clicked");
                currentScreen = Screen.GAME;
            }
        });
        stageOver.addActor(tryAgainBtn);

        Drawable quit = new TextureRegionDrawable(new TextureRegion(new Texture("quitover.png")));
        ImageButton quitBtn = new ImageButton(quit);
        quitBtn.setPosition(Gdx.graphics.getWidth() / 2 + 200, Gdx.graphics.getWidth() / 8 - 120);
        quitBtn.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                System.out.println("Exit button clicked");
                System.exit(0);
            }
        });
        stageOver.addActor(quitBtn);

        Drawable menu = new TextureRegionDrawable(new TextureRegion(new Texture("menu-btn.png")));
        ImageButton menuBtn = new ImageButton(menu);
        menuBtn.setPosition(Gdx.graphics.getWidth() / 2 - 190, Gdx.graphics.getWidth() / 8 - 120);
        menuBtn.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                System.out.println("back to menu button clicked");
                currentScreen = Screen.MAIN_MENU;
                dispose();
                create();
            }
        });
        stageOver.addActor(menuBtn);

    }

    private void completeMission() {
        int buttonOffset = 20;

        stage = new Stage();
        Gdx.input.setInputProcessor(stage); // Make the stage consume events

        Table table = new Table();
        table.setBackground(new TextureRegionDrawable(new TextureRegion(new Texture("mission-complete.png"))));
        table.setFillParent(true);
        table.setDebug(true);
        stage.addActor(table);

        Drawable tryAgain = new TextureRegionDrawable(new TextureRegion(new Texture("tryagain-r.png")));
        ImageButton tryAgainBtn = new ImageButton(tryAgain);
        tryAgainBtn.setPosition(Gdx.graphics.getWidth() / 2 - 580, Gdx.graphics.getWidth() / 8 - 120);
        tryAgainBtn.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                System.out.println("Play Game button clicked");
                currentScreen = Screen.GAME;
            }
        });
        stage.addActor(tryAgainBtn);

        Drawable quit = new TextureRegionDrawable(new TextureRegion(new Texture("quitover.png")));
        ImageButton quitBtn = new ImageButton(quit);
        quitBtn.setPosition(Gdx.graphics.getWidth() / 2 + 200, Gdx.graphics.getWidth() / 8 - 120);
        quitBtn.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                System.out.println("Exit button clicked");
                System.exit(0);
            }
        });
        stage.addActor(quitBtn);

        Drawable menu = new TextureRegionDrawable(new TextureRegion(new Texture("menu-btn.png")));
        ImageButton menuBtn = new ImageButton(menu);
        menuBtn.setPosition(Gdx.graphics.getWidth() / 2 - 190, Gdx.graphics.getWidth() / 8 - 120);
        menuBtn.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                System.out.println("back to menu button clicked");
                currentScreen = Screen.MAIN_MENU;
                dispose();
                create();
            }
        });
        stage.addActor(menuBtn);


    }

    private void createLore() {
        int buttonOffset = 20;

        stageLore = new Stage();
        Gdx.input.setInputProcessor(stageLore); // Make the stage consume events

        Table table = new Table();
        table.setBackground(new TextureRegionDrawable(new TextureRegion(new Texture("lore-c.png"))));
        table.setFillParent(true);
        table.setDebug(true);
        stageLore.addActor(table);


        Drawable tryAgain = new TextureRegionDrawable(new TextureRegion(new Texture("back.png")));
        ImageButton tryAgainBtn = new ImageButton(tryAgain);
        tryAgainBtn.setPosition(Gdx.graphics.getWidth() / 2 - 620, Gdx.graphics.getWidth() / 8 - 120);
        tryAgainBtn.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                System.out.println("Lore button clicked");
                currentScreen = Screen.MAIN_MENU;
                dispose();
                create();
            }
        });
        stageLore.addActor(tryAgainBtn);
    }

    private void createCredits() {
        int buttonOffset = 20;

        stageIns = new Stage();
        Gdx.input.setInputProcessor(stageIns); // Make the stage consume events


        Table table = new Table();
        table.setBackground(new TextureRegionDrawable(new TextureRegion(new Texture("credits-c.png"))));
        table.setFillParent(true);
        table.setDebug(true);
        stageIns.addActor(table);


        Drawable tryAgain = new TextureRegionDrawable(new TextureRegion(new Texture("back-r.png")));
        ImageButton tryAgainBtn = new ImageButton(tryAgain);
        tryAgainBtn.setPosition(Gdx.graphics.getWidth() / 2 - 605, Gdx.graphics.getWidth() / 8 - 120);
        tryAgainBtn.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                System.out.println("Go button clicked");
                currentScreen = Screen.MAIN_MENU;
                dispose();
                create();
            }
        });
        stageIns.addActor(tryAgainBtn);
    }

    private void createInstructions(){
        int buttonOffset = 20;

        stageIns = new Stage();
        Gdx.input.setInputProcessor(stageIns); // Make the stage consume events


        Table table = new Table();
        table.setBackground(new TextureRegionDrawable(new TextureRegion(new Texture("keys.png"))));
        table.setFillParent(true);
        table.setDebug(true);
        stageIns.addActor(table);


        Drawable tryAgain = new TextureRegionDrawable(new TextureRegion(new Texture("back-r.png")));
        ImageButton tryAgainBtn = new ImageButton(tryAgain);
        tryAgainBtn.setPosition(Gdx.graphics.getWidth() / 2 - 605, Gdx.graphics.getWidth() / 8 - 120);
        tryAgainBtn.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                System.out.println("Go button clicked");
                currentScreen = Screen.MAIN_MENU;
                dispose();
                create();
            }
        });
        stageIns.addActor(tryAgainBtn);
    }

    private void mission2() {

    }
}
