package com.mygdx.spacex;

import java.util.ArrayList;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.Timer;
import com.badlogic.gdx.utils.Timer.Task;

public class spacex extends ApplicationAdapter {
	SpriteBatch batch;
	boolean isSpace = false;
	// Declare player.
	Player player;
	Background background;
	ArrayList<Asteroid> asteroids = new ArrayList<Asteroid>();
	Timer timer;
	private float delay = 2f;
	
	@Override
	public void create () {
		batch = new SpriteBatch();
		//Set mem for player and background, call constructor.
		player = new Player(batch, asteroids);
		background = new Background();
		timer = new Timer();
		timer.scheduleTask(new Task() {
			@Override
			public void run() {
				// TODO Auto-generated method stub
				asteroids.add(new Asteroid(batch, player));
			}
		}, delay, delay);
		timer.start();
	}

	@Override
	public void render () {
		
		player.update();
		for(int i = 0; i < asteroids.size(); i++) {
			asteroids.get(i).update();
		}
		Gdx.gl.glClearColor(0,0,0,1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		batch.begin();
		background.draw(batch);
		player.draw(); 
		for(int i = 0; i < asteroids.size(); i++) {
			asteroids.get(i).draw();
		}
		batch.end();
	}	
	
	
	@Override
	public void dispose () {
		batch.dispose();
		player.dispose();
		background.dispose();
		for(int i = 0; i < asteroids.size(); i++) {
			asteroids.get(i).dispose();
		}
	}
}
