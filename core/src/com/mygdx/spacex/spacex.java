package com.mygdx.spacex;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class spacex extends ApplicationAdapter {
	SpriteBatch batch;
	boolean isSpace = false;
	// Declare player.
	Player player;
	Background background;
	Enemy asteroid;
	
	@Override
	public void create () {
		batch = new SpriteBatch();
		//Set mem for player and background, call constructor.
		player = new Player(batch);
		background = new Background();
		asteroid = new Enemy(batch);
		
	}

	@Override
	public void render () {
		
		player.update();
		asteroid.update();
		Gdx.gl.glClearColor(0,0,0,1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		batch.begin();
		background.draw(batch);
		player.draw(); 
		asteroid.draw();
		batch.end();
	}	
	
	
	@Override
	public void dispose () {
		batch.dispose();
		player.dispose();
		background.dispose();
		asteroid.dispose();
	}
}
