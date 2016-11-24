package com.mygdx.spacex;

import java.util.ArrayList;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

public class Shot extends Sprite {
	
	private float speed;
	private Rectangle bounds;
	private ArrayList<Asteroid> asteroids;
	;
	
	public Shot(Vector2 start, ArrayList<Asteroid> asteroids)
	{
		super(new Texture("laser.jpg"));
		setX(start.x);
		setY(start.y);
		setScale(10f, 20f);
		speed = 20;
		bounds = new Rectangle(getX(), getY(), getWidth(), getHeight());
		this.asteroids = asteroids;
		
	}
	public void update()
	{
		movement();
	}
	private void movement()
	{
		translateY(speed);
		bounds.setX(getX());
		bounds.setY(getY());
		for(int i =0; i<asteroids.size(); i++)
		if (bounds.overlaps(asteroids.get(i).bounds)) {
			dispose();
			asteroids.get(i).dispose();
			asteroids.remove(i);
		}
	}
	public void dispose () {
		getTexture().dispose();
	}
}
