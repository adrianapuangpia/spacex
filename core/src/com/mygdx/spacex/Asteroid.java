package com.mygdx.spacex;

import java.util.ArrayList;
import java.util.Random;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;

public class Asteroid extends DynamicEntity {
	
	// Random rotation speed of this asteroid.
	private float rotationSpeed;
	
	// Gonna put random in here for now, dont like it though.
	Random random;
	
	public Asteroid (Vector2 start, SpriteBatch batch, ArrayList<Entity> world) {
		super(new Texture("asteroid1.png"), start, batch, world);
		random = new Random();
		setRandomSpin();
		velocity.x = 0;
		velocity.y = -1;
		horizontalSpeed = 0f;
		verticalSpeed = 5f;
	}
	
	public void update () {
		super.movement();
		rotate(rotationSpeed);
		
		if (alive) collision();
	}
	
	private void collision () {
		world.forEach(entity->{
			boolean overlap = bounds.overlaps(entity.bounds);
			boolean isPlayer = entity.getClass() == Player.class;
			if(overlap && isPlayer) {
				dispose();
				world.get(0).dispose();
			}
		});
	}
	
	private void setRandomSpin () {
		if (random.nextBoolean()) {
			rotationSpeed = random.nextInt(15);
		}
		else {
			rotationSpeed = random.nextInt(15) - 15;
		}
	}
	
}
