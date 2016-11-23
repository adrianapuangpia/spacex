package com.mygdx.spacex;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Timer.Task;

public class Entity extends Sprite {
	
	protected float speed;
	protected Rectangle bounds;

	public Entity(Texture texture)
	{
		super(texture);
	}
	
	protected void movement () {
		
	}
	
	public void dispose () {
		getTexture().dispose();
	}
}
