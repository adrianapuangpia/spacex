package com.mygdx.spacex;
/**
 * Has a spawn point, and velocity (direction).
 * Usually not made on its own.
 * Update from the parent class that makes it.
 */
import java.util.ArrayList;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import com.badlogic.gdx.math.Vector2;

public class Shot extends DynamicEntity {
	
	// 0: player shot, 1: enemy shot, .. etc.
	protected int type;
	Sound sound = Gdx.audio.newSound(Gdx.files.local("Shoot_00.mp3"));
	
	public Shot(Vector2 start, float rotation, int type, SpriteBatch batch, ArrayList<Entity> world)
	{
		super(new Texture("laser.jpg"), start, batch, world);
		sound.play(1.0f);
		horizontalSpeed = 10f;
		verticalSpeed = 10f;
		
		// Set the type of shot.
		this.type = type;
		
		setVelocityToAngle(rotation);
		
		// might change this later.
		setScale(10f, 20f);
		
		// this kinda bothers me, we'll refactor it later. its already being set in Entity.
		// but i know you need it because you scale it. we'll figure this out later.
		bounds.set(getX(), getY(), getWidth(), getHeight());
	}
	
	
	// A shot will only update if it's alive.
	@Override
	protected void update() {
		// TODO Auto-generated method stub
		// DynamicEntity.update()
		super.update();
		
		// Run collision update if alive.
		if (alive) collision();
	}

	// Switch collision method.
	private void collision () {
		switch(type) {
		case 0:
			playerShotCollision();
			break;
		case 1:
			enemyShotCollision();
			break;
		}
	}
	
	private void playerShotCollision () {
		// Check each world object.
		world.forEach(e-> {
			
			// Check if bound.
			boolean overlaps = bounds.overlaps(e.bounds);
			
			// Check if any of the targets.
			boolean isEnemy = e.getClass() == EnemyShip.class;
			boolean isAsteroid = e.getClass() == Asteroid.class;
			boolean isBoss = e.getClass() == Boss.class;
			
			// If it collides with this shot. (This first if, will filter out a lot of objects)
			if (overlaps && (isEnemy || isAsteroid || isBoss )) {
				kill();
				e.damage(collisionDamage);
			}
			
		});
	}
	
	private void enemyShotCollision () {
		if (bounds.overlaps(world.get(0).bounds)) {
			kill();
			world.get(0).damage(collisionDamage);
		}
	}

}
