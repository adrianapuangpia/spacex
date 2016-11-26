package com.mygdx.spacex;

import java.util.ArrayList;
import java.util.ConcurrentModificationException;
import java.util.Random;
import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Timer;
import com.badlogic.gdx.utils.Timer.Task;

public class spacex extends ApplicationAdapter {
	SpriteBatch batch;
	
	Player player;
	Background background;
	
	ArrayList<Asteroid> asteroids = new ArrayList<Asteroid>();
	ArrayList<EnemyShip> ships = new ArrayList<EnemyShip>();
	
	Random random;
	
	private boolean asteroidsOn, enemiesOn;
	private Timer asteroidSpawnTimer, enemySpawnTimer;
	private float asteroidSpawnDelay, enemySpawnDelay;
	
	// Basically anything that inherited from Entity, can be put in here.
	ArrayList<Entity> world;
	
	@Override
	public void create () {
		world = new ArrayList<Entity>();
		batch = new SpriteBatch();
		
		// Spawn player.
		player = new Player(new Vector2(0f, 0f), batch, world);
		world.add(player);
		
		asteroidsOn = false;
		enemiesOn = true;
		
		setTimers();
		
		background = new Background();
		
	}
	
	private void setTimers () {
		// Set randomizer;
		random = new Random();
		
		// Timers
		asteroidSpawnTimer = new Timer();
		enemySpawnTimer = new Timer();
		asteroidSpawnDelay = 5f;
		enemySpawnDelay = 10f;
		
		if (asteroidsOn) { 
			asteroidSpawnTimer.scheduleTask(new Task() {
				@Override
				public void run() {
					// TODO Auto-generated method stub
					Asteroid asteroid = new Asteroid(randomTop(), batch, world);
					asteroids.add(asteroid);
					world.add(asteroid);
				}
			}, 0f, asteroidSpawnDelay);
		}
		
		if (enemiesOn) {
			enemySpawnTimer.scheduleTask(new Task() {
				@Override
				public void run()
				{
					EnemyShip ship = new EnemyShip(randomTop(), batch, world);
					ships.add(ship);
					world.add(ship);
				}
			}, 0f, enemySpawnDelay);
		}
		
		asteroidSpawnTimer.start();
		enemySpawnTimer.start();
	}

	@Override
	public void render () {

		// Logic, movement, ai, etc. for EVERYTHING.
		world.forEach(e->{
			e.update();
		});
		
		// Clear screen.
		Gdx.gl.glClearColor(0,0,0,1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		
		// Begin draw.
		batch.begin();
		background.draw(batch);
		
		// Loop through ALL world entities and draw them.
		world.forEach(e->{
			e.draw();
		});
		
		batch.end();
	}
	
	@Override
	public void dispose () {
		batch.dispose();
		world.forEach(e->{
			e.dispose();
		});
	}
	
	/* Utility methods. */
	private float randomX () {
		return (float) random.nextInt(Gdx.graphics.getWidth()) + 1;
	}
	
	private Vector2 randomTop () {
		Vector2 start = new Vector2();
		start.y = Gdx.graphics.getHeight();
		start.x = randomX();
		return start;
	}
	/* Utility methods. */
}
