/**
 * Sets graphical data/positional data.
 * Sets collision bound.
 * Can be alive, not alive.
 * Has information about other entities in game world.
 * Has no update. ABSTRACT.
 * Has transform (position, rotation, etc.) data inherited from Sprite class.
 * The transform data is not set by the constructor, and must be set by the setter methods.
 * Otherwise it will just use default values. (x = 0, y = 0, rotation = 0, etc.)
 */

package com.mygdx.spacex;

import java.util.ArrayList;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

public abstract class Entity extends Sprite {

	// A representation of this entity's boundaries.
	protected Rectangle bounds;
	// The world it belongs to.
	protected ArrayList<Entity> world;
	// The spriteBatch it will be drawn to.
	SpriteBatch batch;
	// State of Entity.
	Boolean alive;

	public Entity(Texture texture, Vector2 start, SpriteBatch batch, ArrayList<Entity> world)
	{
		super(texture);
		this.world = world;
		this.batch = batch;
		
		setPosition(start.x, start.y);
		bounds = new Rectangle(start.x, start.y, texture.getWidth(), texture.getHeight());
		alive = true;
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
}
