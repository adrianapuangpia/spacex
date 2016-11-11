package com.mygdx.spacex;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class spacex extends ApplicationAdapter {
	SpriteBatch batch;
	
	// Declare player.
	Player player;
	
	@Override
	public void create () {
		batch = new SpriteBatch();
		// Set mem for player, call constructor.
		player = new Player();
	}

	@Override
	public void render () {
		
		player.update();
		
		Gdx.gl.glClearColor(1, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		batch.begin();
		player.draw(batch);
		batch.end();
	}
	
	@Override
	public void dispose () {
		batch.dispose();
		player.dispose();
	}
}
