package com.mygdx.spacex;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class Background {
	
	private Texture background;
	TextureRegion imgRegion;
	
	
	public Background()
	{
		background = new Texture("newBackground.jpg");
		imgRegion = new TextureRegion(background);
	}
	
	public void draw (SpriteBatch batch) {
		batch.draw(imgRegion,0,0,1920,1080);

	}
	public void dispose () {
		background.dispose();
	}

}
