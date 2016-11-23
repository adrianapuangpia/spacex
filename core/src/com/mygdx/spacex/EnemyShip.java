package com.mygdx.spacex;

import java.util.ArrayList;
import java.util.Random;
import java.util.*;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.actions.MoveToAction;
import com.badlogic.gdx.utils.Timer;
import com.badlogic.gdx.utils.Timer.Task;

public class EnemyShip extends Entity{
	Player player;
	SpriteBatch batch;
	boolean alive, shotReady;
	Timer shotDelay;
	int delay = 1;
	ArrayList<Asteroid> asteroids;
	private ArrayList<Shot> shots = new ArrayList<Shot>();
	Random rand = new Random();

	public EnemyShip(SpriteBatch batch, Player player, ArrayList<Asteroid> asteroids) {
		super(new Texture("enemy_spaceship.png"));
		this.batch = batch;
		this.player = player;
		shotReady = true;
		shotDelay = new Timer();
		setX(rand.nextInt(Gdx.graphics.getWidth()) + 1);
		setY(Gdx.graphics.getHeight());
		setRotation(0f);
		speed = 10;
		bounds = new Rectangle(getX(), getY(), getWidth(), getHeight());
		alive = true;
		this.asteroids = asteroids;
	}
	
	@Override
	protected void movement()
	{
		super.movement();
		translateY(-speed);
		
		Vector2 dir = new Vector2(player.getX()-this.getX(),player.getY()-this.getY());
		double hyp = Math.sqrt(dir.x*dir.x +dir.y*dir.y);
		dir.x /= hyp;
		dir.y /= hyp;
		
		setX(dir.x*speed);
		setY(dir.y*speed);
		
		bounds.setX(getX());
		bounds.setY(getY());
		if (bounds.overlaps(player.bounds)) {
			dispose();
			player.dispose();
		}
		
	}
	protected void generateShot()
	{
		shotDelay = new Timer();
		shotDelay.scheduleTask(new Task() {
			@Override
			public void run() {
				shots.add(new Shot(new Vector2(getX() + getWidth() / 2, getY() + getHeight()),asteroids));
			}
		}, delay, delay);
		shotDelay.start();
	}
	
	public void update()
	{
		movement();
	}
	@Override
	public void dispose() {
		super.dispose();
		setX(-100); //fix this
		setY(-100); //fix this
		alive = false;
	}

	public void draw () {
		draw(batch); 
		for(int i = 0; i < shots.size(); i++){
			shots.get(i).draw(batch);
		}
	}


}
