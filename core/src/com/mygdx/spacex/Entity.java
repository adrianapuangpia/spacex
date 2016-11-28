/**
 * Entity - Base Class
 * World
 * Position
 * Boundary
 * Alive/Not alive
 * Lives
 * Kill/Respawn
 */

package com.mygdx.spacex;

import java.util.ArrayList;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Timer;
import com.badlogic.gdx.utils.Timer.Task;

public abstract class Entity extends Sprite {

	// A representation of this entity's boundaries.
	protected Rectangle bounds;
	// The world it belongs to.
	protected ArrayList<Entity> world;
	// The spriteBatch it will be drawn to.
	protected SpriteBatch batch;
	
	// State of Entity and lives.
	protected Boolean alive;
	protected int lives;
	protected int health;
	
	// How much damage this entity would apply if it collides.
	protected int collisionDamage;
	
	// Preferred respawn point * might change to array later
	protected Vector2 respawnVector;
	protected Timer respawnTimer;
	protected float respawnDelay;

	public Entity(Texture texture, Vector2 start, SpriteBatch batch, ArrayList<Entity> world)
	{
		super(texture);
		this.world = world;
		this.batch = batch;
		
		// Default lives to 1 and hp to 1. Override in sub classes..
		alive = true;
		lives = 1;
		health = 1;
		
		// Default collision damage to 1.
		collisionDamage = 1;
				
		setPosition(start.x, start.y);
		bounds = new Rectangle(start.x, start.y, texture.getWidth(), texture.getHeight());
				
		// Default respawn to 0,0
		respawnVector = new Vector2(0f, 0f);

		// Setup respawn timer.
		respawnTimer = new Timer();
		
		// Default respawn delay to 1 second.
		respawnDelay = 1f;
	}
	
	// Can be used outside of class.
	protected void dispose () {
		getTexture().dispose();
	}
	
	protected void update () {}
	
	protected void draw () {
		if (alive)
			draw(batch);
	}
	
	protected void damage (int d) {
		System.out.println(toString() + "| health: " + health + ", damagedBy: " + d );
		// Decrement health by damage.
		health -= d;
		// If health reaches 0 or less, kill it.
		if (health < 1) kill();
	}
	
	protected void kill () {
		// If entity is alive and has lives.
		if (alive && lives > 0) {
			System.out.println(toString() + ": I died.");
			lives -= 1;
			alive = false;
			
			// If player still has lives after deducting * might move this logic to respawn.
			if (lives > 0) {
				respawn();
			}
			// If not
			else {
				// Place bounds outside of game and set dimensions to 0.
				bounds.set(-100, -100, 0, 0);
				dispose();
			}
		}
	}
	
	protected void respawn () {
		// If entity is not alive and has lives.
		if (!alive && lives > 0) {
			// Schedule a respawn.
			respawnTimer.scheduleTask(new Task() {
				@Override
				public void run() {
					alive = true;
					setPosition(respawnVector.x, respawnVector.y);
				}
			}, respawnDelay);
		}
	}
}
