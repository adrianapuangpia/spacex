/*
 * Can take input.
 */

package com.mygdx.spacex;

import java.util.ArrayList;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;

public class Player extends Ship {

	public Player (Vector2 start, SpriteBatch batch, ArrayList<Entity> world) {
		super(new Texture("PlayerShip.png"), start, batch, world);
		
		// Override default speeds.
		horizontalSpeed = 20f;
		verticalSpeed = 20f;
	}
	
	// Process input.
	private void input () {
		velocity.x = 0;
		velocity.y = 0;
		if (Gdx.input.isKeyPressed(Keys.LEFT))
			velocity.x = -1;
		if (Gdx.input.isKeyPressed(Keys.RIGHT))
			velocity.x = 1;
		if (Gdx.input.isKeyPressed(Keys.UP))
			velocity.y = 1;
		if (Gdx.input.isKeyPressed(Keys.DOWN))
			velocity.y = -1;
		
		// Negate.
		if (Gdx.input.isKeyPressed(Keys.LEFT) && Gdx.input.isKeyPressed(Keys.RIGHT))
			velocity.x = 0;
		if (Gdx.input.isKeyPressed(Keys.UP) && Gdx.input.isKeyPressed(Keys.DOWN))
			velocity.y = 0;
		
		
		if (Gdx.input.isKeyPressed(Keys.SPACE)) {
			fire();
		}
	}
	
	private void collision () {
		// Collision with game boundaries.
		if (getX() < 0) {
			setX(Gdx.graphics.getWidth() - getWidth());
		}
		if (getX() + getWidth() > Gdx.graphics.getWidth()) {
			setX(0);
		}
		if (getY() < 0) {
			setY(Gdx.graphics.getHeight() - getHeight());
		}
		if (getY() + getHeight() > Gdx.graphics.getHeight()) {
			setY(0);
		}
	}
	
	@Override
	protected void update () {
		// Process own input if alive.
		if (alive) input();
		
		// Ship.update
		super.update();

		// Run own collision update if alive.		
		if (alive) collision();
	}
}
