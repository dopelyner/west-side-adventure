package org.academiadecodigo.gitbusters.programazores;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class SandboxGame extends ApplicationAdapter {
    SpriteBatch batch;
    Animation<TextureRegion> animation;
    float elapsed;

    @Override
    public void create () {
        batch = new SpriteBatch();
        animation = GifDecoder.loadGIFAnimation(Animation.PlayMode.LOOP, Gdx.files.internal("anita.gif").read());
    }

    @Override
    public void render () {
        elapsed += Gdx.graphics.getDeltaTime();
        batch.begin();
        batch.draw(animation.getKeyFrame(elapsed), 0.0f, 0.0f);
        batch.end();
    }

    @Override
    public void dispose () {
        batch.dispose();
    }
}