package org.academiadecodigo.gitbusters.programazores;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class WestSideAdventure extends ApplicationAdapter {

	private SpriteBatch batch;

	private Boat boat;
	private Island island;
	private Octopussy octopussy;


	@Override
	public void create () {
		batch = new SpriteBatch();

		boat = new Boat();
		boat.setBoatImage(new Texture("boat.png"));

		island = new Island();
		island.setDepartureIslandImage(new Texture("island.png"));

		octopussy = new Octopussy();
		octopussy.setOctopussyImage(new Texture("octopussy.png"));

    }

	@Override
	public void render () {
		Gdx.gl.glClearColor(0, 0, 255, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		batch.begin();

        batch.draw(boat.getBoatImage(), boat.getBoat().getX(), boat.getBoat().y);
		batch.draw(island.getDepartureIslandImage(), island.getDepartureIsland().x, island.getDepartureIsland().y);
		batch.draw(octopussy.getOctopussyImage(), octopussy.getOctopussy().x, octopussy.getOctopussy().y);

		octopussy.octopussyMovement();

		boat.boatMovement();


		batch.end();
	}
	
	@Override
	public void dispose () {
		batch.dispose();
	}
}
