/**
 * Can create shots. Has time attributes.
 * Shots must also be updated.
 */
package com.mygdx.spacex;

import java.util.ArrayList;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Timer;
import com.badlogic.gdx.utils.Timer.Task;

public abstract class Ship extends DynamicEntity {

	// The list of shots.
	protected ArrayList<Shot> shots;
	// Shot type
	protected int shipType;
	// The timer for shots.
	protected Timer shotTimer;
	// The delay between shots.
	protected float shotDelay;
	// If the shot is ready.
	protected boolean shotReady;
	
	protected Sound explosionSound;

	public Ship(Texture texture, Vector2 start, SpriteBatch batch, ArrayList<Entity> world) {
		super(texture, start, batch, world);
		
		shots = new ArrayList<Shot>();
		
		// Allocate shot timer.
		shotTimer = new Timer();
		
		// Ready to fire.
		shotReady = true;
		
		// Default shot delay to 1/2 second.
		shotDelay = .5f;
		
		// Default to shipType 0.
		shipType = 0;
		
		explosionSound = Gdx.audio.newSound(Gdx.files.local("Explosion_03.mp3"));

	}
	
	protected void fire () {
		
		// If shot is ready.
		if(shotReady) {
			
			// Get location of ship, and create a spawn point for shot.
			Vector2 start = new Vector2(getX() + getWidth() / 2, getY() + getHeight() / 2);
			
			float rotation = getRotation() + 90f;
			
			shots.add(new Shot(start, rotation, shipType, batch, world));
		
			// Set shot ready to false to prevent spam.
			shotReady = false;
			
			// Wait 1 second before allowing next shot.
			shotTimer.scheduleTask(new Task() {
				@Override
				public void run() 
				{
					shotReady = true;
				}
			}, shotDelay);
			
		}

	} 
	
	// Process update for itself and its shots.
	@Override
	protected void update() {
		// TODO Auto-generated method stub
		super.update();
		shots.forEach(shot->{
			shot.update();
		});
	}
	
	// Draw itself and its shots.
	@Override
	protected void draw() {
		// TODO Auto-generated method stub
		super.draw();
		shots.forEach(shot->{
			shot.draw();
		});
	}

	// Dispose of itself. Does NOT clear or dipose shots.
	@Override
	protected void dispose() {
		// Dispose self.
		super.dispose();
	}

	@Override
	protected void kill() {
		explosionSound.play();
		super.kill();
	}
	
	
	
}
