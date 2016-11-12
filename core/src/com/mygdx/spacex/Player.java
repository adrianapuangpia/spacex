package com.mygdx.spacex;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;

public class Player {
	
	// Declare what kind of data this player class will use.
	private Vector2 location;
	private Texture image;
	TextureRegion imgRegion;
	private float speed;
	private float scale;
	private float angle;
	boolean isLeft, isRight, isUp, isDown;
	
	public Player () {
		// Set the mem for the data.
		location = new Vector2(0,0);
		image = new Texture("player.png");
		imgRegion = new TextureRegion(image);
		speed = 5;
		scale = 0.25f;
		angle = 0f;
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
		
		// Now, what kind of directional movement are you doing?
		// Mouse angle + forward backward
		// left right = change angle, forward backward
		// ? let just leave it simple up down left rigght for fnow
		// ok, you will need to do the math later to calculate the texture angle
		// this is the basis of a player class. theres more to write, but thats your part :]
		// did this help? 
		// This was absolutely great, thanlk you so much :) push to repo. how and where has the repo been created on github
	}
	
	private void movement () {
		System.out.println(location);
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
	
	public void draw (SpriteBatch batch) {
		batch.draw(imgRegion, 
				location.x, 
				location.y, 
				location.x, 
				location.y, 
				image.getWidth(),
				image.getHeight(),
				scale,
				scale, 
				angle);

	}
	
	public void dispose () {
		image.dispose();
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

	public float getScale() {
		return scale;
	}

	public void setScale(float scale) {
		this.scale = scale;
	}
	
}
