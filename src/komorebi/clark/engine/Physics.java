package komorebi.clark.engine;

import java.awt.Rectangle;

import komorebi.clark.entities.Entity;
import komorebi.clark.entities.Penguin;

public class Physics {

	private Rectangle r1;
	private Rectangle r2;
	private Penguin penguin;
	
	
	public Physics(Penguin p){
		penguin = p;
	}

	public boolean checkCollisions(Entity go1, Entity go2){
		
		r1 = new Rectangle((int)go1.getX(),(int)go1.getY(),(int)go1.getSX(),(int)go1.getSY());
		r2 = new Rectangle((int)go2.getX(),(int)go2.getY(),(int)go2.getSX(),(int)go2.getSY());
		
		return r1.intersects(r2);
	}
	
	public void update(Entity e){
		if(checkCollisions(e,penguin)){
			penguin.hasCollided(e.getType());
		}
		
	}
	
	
}
