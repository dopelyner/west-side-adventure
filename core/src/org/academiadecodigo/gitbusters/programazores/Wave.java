package org.academiadecodigo.gitbusters.programazores;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Rectangle;

public class Wave {

    private Texture waveImage;
    private Rectangle wave;

    public Wave(){
        wave = new Rectangle();
        wave.x = 1000;
        wave.y = 1000;
        wave.width = 60;
        wave.height = 60;
    }

    public Texture getWaveImage() {
        return waveImage;
    }

    public Rectangle getWave() {
        return wave;
    }

    public void setWaveImage(Texture waveImage) {
        this.waveImage = waveImage;
    }

    public void waveMovement(){
        wave.x -= 200 * Gdx.graphics.getDeltaTime();;
    }
}
