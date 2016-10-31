package komorebi.clark.entities;

import komorebi.clark.engine.Draw;

public abstract class Entity {
	protected float x;
	protected float y;
	protected float sx;
	protected float sy;
	protected int type = 99;
	protected Draw draw = new Draw();

	public abstract void update();
	public abstract void render();
	public abstract void reset();
	
	
	public float getX(){
		return x;
	}
	public float getY(){
		return y;
	}
	public float getSX(){
		return sx;
	}
	public float getSY(){
		return sy;
	}
	public int getType(){
		return type;
	}
}
