package com.tutorial.mario;

import java.awt.Graphics;
import java.util.LinkedList;
import com.tutorial.mario.entity.Entity;
import com.tutorial.mario.tile.Tile;
import com.tutorial.mario.tile.Wall;

//controle das entidades
public class Handler {
	public LinkedList<Entity> entity = new LinkedList<Entity>(); 
	public LinkedList<Tile> tile = new LinkedList<Tile>();
	
	public Handler() {
		createLevel();
	}
	
	public void render(Graphics g) {
		//para cada entity que tem na LinkedList<Entity> será criada uma entity chamada 'en'
		for  (Entity en:entity) {
			en.render(g);
		}
		
		//para cada tile que tem na LinkedList<Tile> será criada uma tile chamada 'ti'
		for (Tile ti:tile) {
			ti.render(g);
		}
	}
	
	public void tick() {
		//pra cada entity contida na lista LinkedList<Entity> será atualizada via método tick()
		for  (Entity en:entity) {
			en.tick();
		}
		
		for  (Tile ti:tile) {
			ti.tick();
		}
		
	}
	
	public void addEntity(Entity en) {
		entity.add(en);
	}
	
	public void removeEntity (Entity en) {
		entity.remove(en);
	}
	
	public void addTile (Tile ti) {
		tile.add(ti);
	}
	
	public void removeTile (Tile ti) {
		tile.remove(ti);
	}
	
	public void createLevel() {
		for(int i=0;i<Game.WIDTH*Game.SCALE/64+1;i++){
			addTile(new Wall(i*64,Game.HEIGHT*Game.SCALE-64,64,64,true,Id.wall,this));
			if(i!=0&&i!=1&&i!=15&&i!=16&&i!=17) {
				addTile(new Wall(i*57,300,64,64,true,Id.wall,this));
			}
		}
	}
}
