package com.mygdx.spacex;

import java.util.ArrayList;
import java.util.Random;
import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
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
	
	// yeah, you would instance it in spacex
	// ill brb in a bit yeah let me know through twitter? i yeah ok thx!!!!!!i brb tooo
	
	private boolean asteroidsOn, enemiesOn, BossOn;
	private Timer asteroidSpawnTimer, enemySpawnTimer, bossSpawnTimer;
	private float asteroidSpawnDelay, enemySpawnDelay, bossSpawnDelay;
	Sound backgroundSong;
	Sound bossFightSong;
	// Basically anything that inherited from Entity, can be put in here.
	ArrayList<Entity> world;
	
	@Override
	public void create () {
		backgroundSong = Gdx.audio.newSound(Gdx.files.local("VGM_01.mp3"));
		bossFightSong = Gdx.audio.newSound(Gdx.files.local("Boss_VGM.mp3"));
		world = new ArrayList<Entity>();
		batch = new SpriteBatch();
		backgroundSong.play(1.0f);
		// Spawn player.
		player = new Player(new Vector2(0f, 0f), batch, world);
		world.add(player);
		
		asteroidsOn = true;
		enemiesOn = true;
		BossOn = true;
		
		setTimers();
		
		background = new Background();
		
	}
	
	private void setTimers () {
		// Set randomizer;
		random = new Random();
		
		// Timers
		asteroidSpawnTimer = new Timer();
		enemySpawnTimer = new Timer();
		bossSpawnTimer = new Timer();
		asteroidSpawnDelay = 5f;
		enemySpawnDelay = 10f;
		bossSpawnDelay = 100f;
		
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
		
		if (BossOn) {
			bossSpawnTimer.scheduleTask(new Task() {
				@Override
				public void run()
				{
					Boss boss = new Boss(randomTop(), batch, world);
					world.add(boss);
					enemiesOn = false;
					backgroundSong.stop();
					bossFightSong.play(1.0f);
				}
			}, 10f, bossSpawnDelay);
		}
		
		asteroidSpawnTimer.start();
		enemySpawnTimer.start();
		bossSpawnTimer.start();
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
