package komorebi.clark.entities;


public class Ground extends Entity{
	
	private float speed = 3;
	private int lock = -1;
	private static int currGround = 0;

	/**
	 * A section of ground
	 * 
	 * @param x - The starting x value of the ground, should be a multiple of 16
	 */
	public Ground(int x){
		sx=16;
		sy=48;
		this.x=x;
		type = currGround;
		if(currGround==0)currGround=1;
		else currGround=0;
	}
	
	@Override
	public void update() {
		x-=speed;
		if(speed<=7)speed+=0.00125f;
	}
	
	public void render(){
		if(type != 4){
			draw.rect(x, y, 16, 16, "sheet", 0, 48, 16, 64);
			draw.rect(x, y+16, 16, 16, "sheet", 0, 48, 16, 64);
			
			switch(type){
				case 0:
					draw.rect(x, y+32, 16, 16, "sheet", 16, 48, 32, 64);
					break;
				case 1:
					draw.rect(x, y+32, 16, 16, "sheet", 32, 48, 48, 64);
					break;
				case 2:
					draw.rect(x, y+32, 16, 16, "sheet", 48, 48, 64, 64);
					break;
				case 3:
					draw.rect(x, y+32, 16, 16, "sheet", 64, 48, 78, 64);
					break;
				case 5:
					draw.rect(x, y+32, 16, 16, "sheet", 16, 48, 32, 64);
					draw.rect(x, y+48, 16, 16, "sheet", 48, 32, 64, 48);
					break;
				case 6:
					draw.rect(x, y+32, 16, 16, "sheet", 32, 48, 48, 64);
					draw.rect(x, y+48, 16, 16, "sheet", 48, 32, 64, 48);
					break;
			}
		}
	}
	
	public void setX(float x){
		this.x=x;
	}
	
	public void setBig(){
		sy = 64;
	}
	
	public void setSmall(){
		sy = 48;
	}
		
	public void setType(int type){
		this.type = type;
	}
	
	public void setType(){
		type = lock;
	}
	
	public void resetX(){
		x+=272;
	}

	public int getLock() {
		return lock;
	}

	public void setLock(int i) {
		lock = i;
	}

	public void reset(int x) {
		sx=16;
		sy=48;
		this.x=x;
		
		type = currGround;
		if(currGround==0)currGround=1;
		else currGround=0;
		
	}

	@Override
	public void reset() {
		sx=16;
		sy=48;
		type = currGround;
		if(currGround==0)currGround=1;
		else currGround=0;
		lock = -1;
		speed = 3;
	}
}
