package com.tutorial.mario.tile;

import java.awt.Graphics;
import java.awt.Rectangle;

import com.tutorial.mario.Handler;
import com.tutorial.mario.Id;

public abstract class Tile {

	//Declação das variáveis
	public int x, y;
	public int width, height;
	public boolean solid;
	
	public int velX, velY;
	
	public Id id;
	
	public Handler handler;

	//Método Construtor 
	public Tile(int x, int y, int width, int height, boolean solid, Id id, Handler handler) {
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height; 
		this.solid = solid;
		this.id = id;
		this.handler = handler;
	}
	
	//Getters and Setters
	public int getX() {
		return x;
	}
	
	public int getY() {
		return y;
	}
	
	public boolean isSolid() {
		return solid;
	}
	
	public Id getId() {
		return id;
	}

	public void setX(int x) {
		this.x = x;
	}

	public void setY(int y) {
		this.y = y;
	}

	public void setVelX(int velX) {
		this.velX = velX;
	}

	public void setVelY(int velY) {
		this.velY = velY;
	}

	//Métodos
	public abstract void render(Graphics g);
	public abstract void tick();
	
	public void die() {
		handler.removeTile(this); //this, pois refere-se a propria classe Tile
	}
	
	public Rectangle getBounds() {
		return new Rectangle(getX(),getY(),width,height);
	}
	

}
