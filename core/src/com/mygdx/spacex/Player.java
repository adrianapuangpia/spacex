package com.mygdx.spacex;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;

public class Player {
	
	private Vector2 location;
	private Texture image;
	private float speed;
	
	boolean isLeft, isRight, isUp, isDown;
	
	public Player () {
		 location = new Vector2(0,0);
		 image = new Texture("player.png");
	}
	
	private void input () {
		// Start everything in this frame is false.
		isLeft = false;
		isRight = false;
		isUp = false;
		isDown = false;
		// Only change if true this frame.
		if (Gdx.input.isKeyPressed(Keys.LEFT)) isLeft = true;
		if (Gdx.input.isKeyPressed(Keys.RIGHT)) isRight = true;
		if (Gdx.input.isKeyPressed(Keys.UP)) isUp = true;
		if (Gdx.input.isKeyPressed(Keys.DOWN)) isDown = true;
	}
	
	private void movement () {
		// if right and left are pressed, they cancel out.
		if (isLeft) location.x -= speed;
		if (isRight) location.x += speed;
		if (isUp) location.y += speed;
		if (isDown) location.y -= speed;
	}
	
	public void update () {
		input();
		movement();
	}

	public Vector2 getLocation() {
		return location;
	}

	public void setLocation(Vector2 location) {
		this.location = location;
	}

	public Texture getImage() {
		return image;
	}

	public void setImage(Texture image) {
		this.image = image;
	}
	
	
}
