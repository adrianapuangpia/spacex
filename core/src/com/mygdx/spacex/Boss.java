package com.mygdx.spacex;

import java.util.ArrayList;
import java.util.Random;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;

public class Boss extends EnemyShip {
	private Random rand = new Random();
	private int randomTexture = rand.nextInt(2) +1;
	private Sound sound2 = Gdx.audio.newSound(Gdx.files.local("Hit_02.mp3"));
	
	public Boss(Vector2 start, SpriteBatch batch, ArrayList<Entity> world) {
		super(start, batch, world);
		if (randomTexture == 1)
		{
			setTexture(new Texture("boss.png"));
			setScale(2f,2f);
		}
		else if (randomTexture == 2)
		{
			setTexture(new Texture("boss2.png"));
			setScale(2f, 2f);
		}
		shotDelay = 1f;
		horizontalSpeed = 1f;
		verticalSpeed = 1f;
		lives = 1;
		respawnVector = start;
		health = 5;
	}

	@Override
	protected void collision () {
		super.collision();
		if (bounds.overlaps(world.get(0).bounds)) {
			sound2.play(1.0f);
		}
	}
}
