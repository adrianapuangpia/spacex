/**
 * Can create shots. Has time attributes.
 * Shots must also be updated.
 */
package com.mygdx.spacex;

import java.util.ArrayList;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Timer;
import com.badlogic.gdx.utils.Timer.Task;

public abstract class Ship extends DynamicEntity {
	
	// Shot type
	protected int shipType;
	
	// ArrayList of shots made by this ship.
	protected ArrayList<Shot> shots;
	
	// The timer for shots.
	protected Timer shotTimer;
	
	// The delay between shots.
	protected float shotDelay;
	
	// If the shot is ready.
	protected boolean shotReady;

	public Ship(Texture texture, Vector2 start, SpriteBatch batch, ArrayList<Entity> world) {
		super(texture, start, batch, world);
		
		shots = new ArrayList<Shot>();
		
		// Allocate shot timer.
		shotTimer = new Timer();
		
		// Ready to fire.
		shotReady = true;
		
		// Default shot delay to 1 second.
		shotDelay = 1f;
		
		// Set ship type depending on which class was instanced.
		if(getClass() == Player.class)
			shipType = 0;
		if(getClass() == EnemyShip.class)
			shipType = 1;

	}
	
	@Override
	protected void update () {
		// DynamicEntity.update() processes movement.
		super.update();
		
		// Process movement for each shot made by this ship.
		shots.forEach(shot -> {
			shot.update();
		});
	}
	
	@Override
	protected void draw () {
		// Draw self.
		super.draw();
		// Draw each the shots created by this ship.
		shots.forEach(shot->{
			if (shot.alive) {
				shot.draw();
			}
		});
	}
	
	protected void fire () {
		
		// If shot is ready.
		if(shotReady) {
			
			// Get location of ship, and create a spawn point for shot.
			Vector2 start = new Vector2(getX(), getY());
			
			// dirty fix for now, while i figure this out? ok?yea of course
			// Pass in start point, velocity of the shot, and world it belongs to.
			if (getClass() == Player.class) {
				shots.add(new Shot(start, new Vector2(0, 1), shipType, batch, world));
			}
			if (getClass() == EnemyShip.class) {
				Vector2 target = new Vector2();
				target.x = getX() - world.get(0).getX();
				target.y = getY() - world.get(0).getY();
				shots.add(new Shot(start, target, shipType, batch, world));
			}
			// ok, figure this out tomorrow, ill research it lool
			//math +poop there's some fun to it lmaao ok im gonna call it talk to u tomorrow
			
		
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
	
	@Override
	protected void dispose() {
		
		// Dispose of shots created.
		shots.forEach(shot -> {
			// Run dispose on each shot -> DynamicEntity -> Entity.dispose -> texture dispose.
			shot.dispose();
		});
		// Clear list.
		shots.clear();
		// TODO Auto-generated method stub
		// -> DynamicEntity -> Entity.dipose -> texture Dispose
		super.dispose();
	}
}
