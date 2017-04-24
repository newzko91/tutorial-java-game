package com.tutorial.mario.entity;

import java.awt.Color;
import java.awt.Graphics;
import com.tutorial.mario.Handler;
import com.tutorial.mario.Id;
import com.tutorial.mario.tile.Tile;

public class Player extends Entity {

	public Player(int x, int y, int width, int height, boolean solid, Id id, Handler handler) {
		super(x, y, width, height, solid, id, handler);
	}

	public void render(Graphics g) {
		g.setColor(Color.GREEN);
		g.fillRect(x, y, width, height);
	}

	public void tick() {
		x+=velX;
		y+=velY;
		
		if(x<-0) x = 0;
		//if(y<-0) y = 0;
		if(x+width>=1000) x = 1000-width; //Width*Scale
		if(y+height>=680) y = 680-height; //Width/(14*10*Scale) = 714,2857142857
		//Pra cada tile no tile de handler...
		for(Tile t:handler.tile){
			if(!t.solid) break;
			if(t.getId()==Id.wall) {
				if(getBoundsTop().intersects(t.getBounds())) {
					setVelY(0);
					if(jumping) {
						jumping = false;
						gravity = 0.0;
						falling = true;
					}
				}
				
				if(getBoundsBottom().intersects(t.getBounds())) {
					setVelY(0);
					if(falling) falling = false;
				} else {
					if(!falling&&!jumping) {
						gravity = 0.0;
						falling = true;
					}
				}
				
				if(getBoundsLeft().intersects(t.getBounds())) {
					setVelX(0);
					x = t.getX()+t.width;
				}
				
				if(getBoundsRight().intersects(t.getBounds())) {
					setVelX(0);
					x = t.getX()-t.width;
				}
			}
		}
		
		if(jumping==true) {
			gravity-=0.1;
			setVelY((int)-gravity);
			if(gravity<=0.0) {
				jumping = false;
				falling = true;
			}
		}
		
		if(falling) {
			gravity+=0.1;
			setVelY((int)gravity);
		}
	}

}
