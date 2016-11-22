package com.mygdx.spacex;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import java.util.*;


public class Enemy extends Entity {

	Random rand = new Random();
	int  newX = rand.nextInt(Gdx.graphics.getWidth()) +1;
	int  newY = Gdx.graphics.getHeight();
	SpriteBatch batch = new SpriteBatch();
	
	
	public Enemy(SpriteBatch batch)
	{
		super(new Texture("Explosion.png"));
		this.setX(newX);
		this.setY(newY);
		this.batch = batch;
	}
	public void update()
	{
		movement();
	}
	
	
	public void draw () {
		draw(batch);		
		}
}
