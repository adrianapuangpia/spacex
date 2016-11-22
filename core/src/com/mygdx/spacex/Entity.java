package com.mygdx.spacex;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Timer.Task;

public class Entity extends Sprite {
	
	private float speed;
	private float newY, newX;

	public Entity()
	{
		setX(0);
		setY(0);
		speed = 5;
	}
	public Entity(Texture texture)
	{
		super(texture);
	}
	
	public void movement () {
		newX +=speed;
		newY+=speed;
		setX(newX);
		setY(newY);
	}
	public void dispose () {
		getTexture().dispose();
	}
}
