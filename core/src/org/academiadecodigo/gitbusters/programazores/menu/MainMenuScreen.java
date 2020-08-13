package org.academiadecodigo.gitbusters.programazores.menu;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import org.academiadecodigo.gitbusters.programazores.WestSideAdventure;

public class MainMenuScreen extends ApplicationAdapter {

    private Stage stage;
    private Skin skin;
    private Label outputLabel;

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


        Drawable play = new TextureRegionDrawable(new TextureRegion(new Texture("play-button.png")));
        ImageButton playGameBtn = new ImageButton(play);
        playGameBtn.setPosition(Gdx.graphics.getWidth() / 2 - 500, Gdx.graphics.getWidth() / 8 + 20 + 100);
        playGameBtn.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                System.out.println("Play Game button clicked");
                new WestSideAdventure();
            }
        });
        stage.addActor(playGameBtn);

        Drawable instructions = new TextureRegionDrawable(new TextureRegion(new Texture("instructions-button.png")));
        ImageButton instructionsBtn = new ImageButton(instructions);
        instructionsBtn.setPosition(Gdx.graphics.getWidth() / 2 - 500, Gdx.graphics.getWidth() / 8 - 120);
        instructionsBtn.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                System.out.println("Instructions button clicked");
            }
        });
        stage.addActor(instructionsBtn);

        Drawable settings = new TextureRegionDrawable(new TextureRegion(new Texture("settings-button.png")));
        ImageButton settingsBtn = new ImageButton(settings);
        settingsBtn.setPosition(Gdx.graphics.getWidth() / 2, Gdx.graphics.getWidth() / 8 - 3);
        settingsBtn.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                System.out.println("Settings button clicked");
            }
        });
        stage.addActor(settingsBtn);

        Drawable lore = new TextureRegionDrawable(new TextureRegion(new Texture("lore-button.png")));
        ImageButton loreBtn = new ImageButton(lore);
        loreBtn.setPosition(Gdx.graphics.getWidth() / 2 - 500, Gdx.graphics.getWidth() / 8);
        loreBtn.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                System.out.println("Lore button clicked");
            }
        });
        stage.addActor(loreBtn);

        Drawable shop = new TextureRegionDrawable(new TextureRegion(new Texture("shop-button.png")));
        ImageButton shopBtn = new ImageButton(shop);
        shopBtn.setPosition(Gdx.graphics.getWidth() / 2, Gdx.graphics.getWidth() / 8 + 20 + 100);
        shopBtn.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                System.out.println("Shop button clicked");
            }
        });
        stage.addActor(shopBtn);


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
        stage.addActor(exitBtn);

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
