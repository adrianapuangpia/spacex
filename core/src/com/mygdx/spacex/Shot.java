package com.mygdx.spacex;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;

public class Shot extends Sprite {
	
	private float speed;
	
	public Shot(Vector2 start)
	{
		super(new Texture("laser.jpg"));
		setX(start.x);
		setY(start.y);
		setScale(10f, 20f);
		speed = 20;
	}
	public void update()
	{
		movement();
	}
	private void movement()
	{
		this.setY((this.getY() + speed));
		//if (this.location.y >= 900)
		//{
			//dispose();
		//}
	}
	public void dispose () {
		getTexture().dispose();
	}
}
