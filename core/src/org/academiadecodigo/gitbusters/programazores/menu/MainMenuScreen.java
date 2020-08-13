package org.academiadecodigo.gitbusters.programazores.menu;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import org.academiadecodigo.gitbusters.programazores.WestSideAdventure;

public class MainMenuScreen extends ApplicationAdapter {

    private Stage stage;
    private Skin skin;

    @Override
    public void create() {

        int buttonOffset = 20;

        stage = new Stage();
        Gdx.input.setInputProcessor(stage); // Make the stage consume events

        createBasicSkin();

        Table table = new Table();
        table.setBackground(new TextureRegionDrawable(new TextureRegion(new Texture("background-menu.png"))));
        table.setFillParent(true);
        table.setDebug(true);
        stage.addActor(table);

        TextButton playGame = new TextButton("Play Game", skin);
        playGame.setPosition(Gdx.graphics.getWidth() / 2 - Gdx.graphics.getWidth() / 8, Gdx.graphics.getHeight() / 2 + (playGame.getHeight() + buttonOffset));
        playGame.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                System.out.println("Play game button clicked");
                new WestSideAdventure();
            }
        });
        stage.addActor(playGame);

        TextButton lore = new TextButton("Lore", skin);
        lore.setPosition(Gdx.graphics.getWidth() / 2 - Gdx.graphics.getWidth() / 8, Gdx.graphics.getHeight() / 2);
        lore.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                System.out.println("Lore button clicked");
            }
        });
        stage.addActor(lore);

        TextButton settings = new TextButton("Settings", skin);
        settings.setPosition(Gdx.graphics.getWidth() / 2 - Gdx.graphics.getWidth() / 8, Gdx.graphics.getHeight() / 2 - (playGame.getHeight() + buttonOffset));
        settings.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                System.out.println("Settings button clicked");
                System.exit(0);
            }
        });
        stage.addActor(settings);

        TextButton exit = new TextButton("Exit Game", skin);
        exit.setPosition(Gdx.graphics.getWidth() / 2 - Gdx.graphics.getWidth() / 8, Gdx.graphics.getHeight() / 2 - 2 * (playGame.getHeight() + buttonOffset));
        exit.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                System.out.println("Exit game button clicked");
                System.exit(0);
            }
        });
        stage.addActor(exit);

    }

    private void createBasicSkin() {
        // Create a font
        BitmapFont font = new BitmapFont();
        skin = new Skin();
        skin.add("default", font);

        // Create a texture
        Pixmap pixmap = new Pixmap(Gdx.graphics.getWidth() / 4, Gdx.graphics.getHeight() / 10, Pixmap.Format.RGB888);
        pixmap.setColor(Color.WHITE);
        pixmap.fill();
        skin.add("background-button", new Texture(pixmap));

        TextButton.TextButtonStyle buttonStyle = new TextButton.TextButtonStyle();
        buttonStyle.up = skin.newDrawable("background-button", Color.GRAY);
        buttonStyle.down = skin.newDrawable("background-button", Color.DARK_GRAY);
        buttonStyle.checked = skin.newDrawable("background-button", Color.DARK_GRAY);
        buttonStyle.over = skin.newDrawable("background-button", Color.LIGHT_GRAY);
        buttonStyle.font = skin.getFont("default");
        skin.add("default", buttonStyle);
    }

    @Override
    public void render() {
        Gdx.gl.glClearColor(1, 1, 1, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        stage.act();
        stage.draw();
    }


}
