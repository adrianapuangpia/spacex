/**
 * Sets graphical movement.
 * Has movement attributes.
 * Has a velocity (direction) it can follow if set.
 * Has no update. ABSTRACT.
 */
package com.mygdx.spacex;

import java.util.ArrayList;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;

public abstract class DynamicEntity extends Entity {
	
	// The rates at which this entity will move per frame.
	protected float horizontalSpeed;
	protected float verticalSpeed;
	
	// A direction that can be set/followed.
	// A velocity is just the example of how something could move. Doesn't have to be used.
	protected Vector2 velocity;
	
	public DynamicEntity (Texture texture, Vector2 start, SpriteBatch batch, ArrayList<Entity> world) {
		super(texture, start, batch, world);
		velocity = new Vector2();
		velocity.x = 0;
		velocity.y = 0;
	}
	
	protected void setVelocity (Vector2 velocity) {
		this.velocity = velocity;
	}
	
	// A movement method that should be called in a sub class's update method.
	protected void movement () {
		// Set new x towards new delta x.
		float x = (getX()) + (velocity.x * horizontalSpeed);
		// Set new y towards new delata y.
		float y = (getY()) + (velocity.y * verticalSpeed);
		// Set new coordinates.
		setPosition(x, y);
		// Update entity boundaries. Not needed 
		bounds.setX(getX());
		bounds.setY(getY());
	}
	
	@Override
	protected void update () {
		movement();
		super.update();
	}

	@Override
	protected void draw() {
		// TODO Auto-generated method stub
		super.draw();
	}
	
	
}
