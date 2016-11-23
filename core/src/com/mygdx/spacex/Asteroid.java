package com.mygdx.spacex;

import java.util.Random;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;

public class Asteroid extends Entity {
	Random rand = new Random();
	SpriteBatch batch = new SpriteBatch();
	private float rotation;
	private Player player;
	
	public Asteroid (SpriteBatch batch, Player player) {
		super(new Texture("asteroid1.png"));
		this.player = player;
		setX(rand.nextInt(Gdx.graphics.getWidth()) + 1);
		setY(Gdx.graphics.getHeight());
		
		if (rand.nextBoolean()) {
			rotation = rand.nextInt(15);
		}
		else {
			rotation = rand.nextInt(15) - 15;
		}
		speed = 2;
		bounds = new Rectangle(getX(), getY(), getWidth(), getHeight());
		this.batch = batch;
	}
	
	public void update () {
		movement();
	}

	@Override
	public void movement() {
		// TODO Auto-generated method stub
		super.movement();
		translateY(-speed);
		rotate(rotation);
		bounds.setX(getX());
		bounds.setY(getY());
		if (bounds.overlaps(player.bounds)) {
			dispose();
			player.dispose();
		}
	}
	
	public void draw () {
		draw(batch);
	}
	
	
}
